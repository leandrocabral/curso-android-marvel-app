package com.example.marvelapp.presentation.characters

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.example.core.domain.model.Character
import com.example.core.usecase.GetCharacterUseCase
import com.example.testing.MainCoroutineRule
import com.example.testing.model.CharacterFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutinesRule = MainCoroutineRule()

    @Mock
    lateinit var getCharactersUseCase: GetCharacterUseCase

    private lateinit var charactersViewModel: CharactersViewModel

    private val charactersFactory = CharacterFactory()

    val pagingDataCharacters = PagingData.from(
        listOf(
            charactersFactory.create(CharacterFactory.Hero.TreedDMan),
            charactersFactory.create(CharacterFactory.Hero.ABomb),
        )
    )

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        charactersViewModel = CharactersViewModel(getCharactersUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should validade the paging data object values when calling charactersPaginData`() =
        runBlockingTest {
            whenever(
                getCharactersUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    pagingDataCharacters
                )
            )

            val result = charactersViewModel.charactersPagingData("")

            assertEquals(1, result.count())
        }


    @ExperimentalCoroutinesApi
    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling to the use case returns an exception`() =
        runBlockingTest {
            whenever(getCharactersUseCase.invoke(any()))
                .thenThrow(RuntimeException())

            charactersViewModel.charactersPagingData("")
        }
}
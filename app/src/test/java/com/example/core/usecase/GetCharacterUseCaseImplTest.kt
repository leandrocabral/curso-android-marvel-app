package com.example.core.usecase

import androidx.paging.PagingConfig
import com.example.core.data.repository.CharactersRepository
import com.example.testing.MainCoroutineRule
import com.example.testing.model.CharacterFactory
import com.example.testing.pagingsource.PagingSourceFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times

import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetCharacterUseCaseImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutinesRule = MainCoroutineRule()

    @Mock
    lateinit var repository: CharactersRepository

    private lateinit var getCharacterUseCase: GetCharacterUseCase

    private val hero = CharacterFactory().create(CharacterFactory.Hero.TreedDMan)

    private val fakePagingSource = PagingSourceFactory().create(listOf(hero))

    @Before
    fun setUp() {
        getCharacterUseCase = GetCharacterUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data createion when invoke from use is called`() =
        runBlockingTest {

            whenever(repository.getCharacters(""))
                .thenReturn(fakePagingSource)

            val result = getCharacterUseCase.invoke(
                GetCharacterUseCase.GetCharactersParams(
                    "",
                    PagingConfig(20)
                )
            )

            verify(repository, times(1)).getCharacters("")

            assertNotNull(result.first())
        }
}
package com.xavidev.testessential.ui.profile

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.google.common.truth.Truth.assertThat
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.entity.User
import com.xavidev.testessential.data.source.repository.ProfileRepositoryFake
import com.xavidev.testessential.getOrAwaitValue
import com.xavidev.testessential.rules.MainCoroutineRule
import com.xavidev.testessential.utils.Event
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.junit4.MockKRule
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
 class ProfileViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProfileViewModel

    private val userRepository = spyk<ProfileRepositoryFake>()

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = getApplicationContext<SneakersApplication>()
        viewModel = ProfileViewModel(userRepository)
    }

    @Test
    fun signOutShouldRemoveUser() = runTest {
        val user = User(
            id = "1",
            name = "Javier Licea",
            currencyId = "",
            size = 27.5
        )
        userRepository.insertUser(user)
        userRepository.getUser(user.id).collect{
            if (it is Result.Success){
                assertThat(it.data).isEqualTo(user)
            }
        }

        viewModel.signOut(context)
        coVerify { userRepository.signOut(context) }
        userRepository.getUser(user.id).collect{
            if (it is Result.Success){
                assertThat(it.data).isNull()
            }
        }
    }
}
package com.xavidev.testessential.data.entity

import androidx.room.Room
import com.google.common.truth.Truth.assertThat
import com.xavidev.testessential.data.DatabaseTestUtil
import com.xavidev.testessential.data.dao.ImagesDao
import com.xavidev.testessential.data.db.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class ImagesTest {

    companion object {
        val IMAGES_LIST = DatabaseTestUtil.createSneakerImagesList()
    }

    private lateinit var imagesDao: ImagesDao
    private lateinit var db: AppDatabase

    @Before
    fun setup() {
        val context = RuntimeEnvironment.getApplication()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        imagesDao = db.imagesDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun writeImagesListReadImagesList() = runTest {
        imagesDao.insertImages(IMAGES_LIST)
        val insertedImages = imagesDao.getAllImages()
        assertThat(insertedImages).isEqualTo(IMAGES_LIST)
    }

    @Test
    fun deleteImagesReadEmptyList() = runTest {
        imagesDao.insertImages(listOf(IMAGES_LIST[0]))
        imagesDao.deleteImages()
        val images = imagesDao.getAllImages()
        assertThat(images).isEmpty()
    }

    @Test
    fun populateImagesReadImagesList() = runTest {
        imagesDao.insertImages(listOf(IMAGES_LIST[0], IMAGES_LIST[1]))
        imagesDao.populateImages(IMAGES_LIST)
        val images = imagesDao.getAllImages()
        assertThat(images).isEqualTo(IMAGES_LIST)
    }
}
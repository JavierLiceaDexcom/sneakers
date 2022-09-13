package com.xavidev.testessential.data.entity

import androidx.room.Room
import com.google.common.truth.Truth.assertThat
import com.xavidev.testessential.data.DatabaseTestUtil
import com.xavidev.testessential.data.dao.BrandsDao
import com.xavidev.testessential.data.db.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class BrandTest {
    //Constants for expected results
    companion object {
        const val EXPECTED_SIZE_TWO = 2
        val SINGLE_BRAND = DatabaseTestUtil.createBrand()
    }

    private lateinit var brandsDao: BrandsDao
    private lateinit var db: AppDatabase

    @Before
    fun setup() {
        val context = RuntimeEnvironment.getApplication()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        brandsDao = db.brandsDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    fun writeBrandAndReadInList() = runTest {
        brandsDao.insertBrands(listOf(SINGLE_BRAND))
        val insertedBrand = brandsDao.getAllBrands()[0]
        assertThat(insertedBrand).isEqualTo(SINGLE_BRAND)
    }

    @Test
    fun clearBrandsTableReadEmptyList() = runTest {
        brandsDao.insertBrands(listOf(SINGLE_BRAND))
        brandsDao.clearBrandsTable()
        val brands = brandsDao.getAllBrands()
        assertThat(brands).isEmpty()
    }

    @Test
    fun writeBrandListReadBrandList() = runTest {
        val brands = DatabaseTestUtil.createBrandList()
        brandsDao.insertBrands(brands)
        val insertedBrands = brandsDao.getAllBrands()
        assertThat(brands).isEqualTo(insertedBrands)
        assertThat(brands).hasSize(EXPECTED_SIZE_TWO)
    }

    @Test
    fun clearAndPopulateBrandsTableReadBrandsList() = runTest {
        brandsDao.insertBrands(listOf(SINGLE_BRAND))
        val brands = DatabaseTestUtil.createBrandList()
        brandsDao.populateBrandsTable(brands)
        val insertedBrands = brandsDao.getAllBrands()
        assertThat(brands).isEqualTo(insertedBrands)
        assertThat(brands).hasSize(EXPECTED_SIZE_TWO)
    }
}
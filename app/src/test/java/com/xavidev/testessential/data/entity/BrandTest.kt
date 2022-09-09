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
    @Throws(Exception::class)
    fun writeBrandAndReadInList() = runTest {
        val brand = DatabaseTestUtil.createBrand(name = "Nike", logo = "logo_example_url")
        brandsDao.insertBrands(listOf(brand))
        val insertedBrand = brandsDao.getAllBrands()[0]
        assertThat(insertedBrand).isEqualTo(brand)
    }

    @Test
    fun clearBrandsTableReadEmptyList() = runTest {
        val brand = DatabaseTestUtil.createBrand(name = "Nike", logo = "logo_example_url")
        brandsDao.insertBrands(listOf(brand))
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
        assertThat(brands).hasSize(2)
    }

    @Test
    fun clearAndPopulateBrandsTableReadBrandsList() = runTest {
        val brand = DatabaseTestUtil.createBrand(
            name = "Adidas",
            logo = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Adidas_Logo.svg/2560px-Adidas_Logo.svg.png"
        )
        brandsDao.insertBrands(listOf(brand))
        val brands = DatabaseTestUtil.createBrandList()
        brandsDao.populateBrandsTable(brands)
        val insertedBrands = brandsDao.getAllBrands()
        assertThat(brands).isEqualTo(insertedBrands)
        assertThat(brands).hasSize(2)
    }
}
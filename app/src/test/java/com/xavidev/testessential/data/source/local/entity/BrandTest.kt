package com.xavidev.testessential.data.source.local.entity

import androidx.room.Room
import com.google.common.truth.Truth.assertThat
import com.xavidev.testessential.data.DatabaseTestUtil
import com.xavidev.testessential.data.source.local.dao.BrandsDao
import com.xavidev.testessential.data.source.local.db.AppDatabase
import com.xavidev.testessential.rules.LogTimes
import com.xavidev.testessential.rules.TimeRules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class BrandTest {

    @get: Rule
    val timeRules = TimeRules()

    //Constants for expected results
    companion object {
        const val EXPECTED_BRANDS_SIZE = 8
        val SINGLE_BRAND = DatabaseTestUtil.createBrand()

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            println("Executing BeforeClass...")
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            println("Executing AfterClass...")
        }
    }

    private lateinit var brandsDao: BrandsDao
    private lateinit var db: AppDatabase

    @Before
    fun setup() {
        println("Executing before...")
        val context = RuntimeEnvironment.getApplication()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        brandsDao = db.brandsDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
        println("Executing After...")
    }

    @LogTimes
    @Test
    fun writeBrandAndReadInList() = runTest {
        brandsDao.insertBrands(listOf(SINGLE_BRAND))
        val insertedBrand = brandsDao.getAllBrands()[0]
        assertThat(insertedBrand).isEqualTo(SINGLE_BRAND)
    }

    @LogTimes
    @Test
    fun clearBrandsTableReadEmptyList() = runTest {
        brandsDao.insertBrands(listOf(SINGLE_BRAND))
        brandsDao.clearBrandsTable()
        val brands = brandsDao.getAllBrands()
        assertThat(brands).isEmpty()
    }

    @LogTimes
    @Test
    fun writeBrandListReadBrandList() = runTest {
        val brands = DatabaseTestUtil.createBrandList()
        brandsDao.insertBrands(brands)
        val insertedBrands = brandsDao.getAllBrands()
        assertThat(brands).isEqualTo(insertedBrands)
        assertThat(brands).hasSize(EXPECTED_BRANDS_SIZE)
    }

    @LogTimes
    @Test
    fun clearAndPopulateBrandsTableReadBrandsList() = runTest {
        brandsDao.insertBrands(listOf(SINGLE_BRAND))
        val brands = DatabaseTestUtil.createBrandList()
        brandsDao.populateBrandsTable(brands)
        val insertedBrands = brandsDao.getAllBrands()
        assertThat(brands).isEqualTo(insertedBrands)
        assertThat(brands).hasSize(EXPECTED_BRANDS_SIZE)
    }
}
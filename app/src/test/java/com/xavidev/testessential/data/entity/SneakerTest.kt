package com.xavidev.testessential.data.entity

import androidx.room.Room
import com.google.common.truth.Truth.assertThat
import com.xavidev.testessential.data.DatabaseTestUtil
import com.xavidev.testessential.data.dao.BrandsDao
import com.xavidev.testessential.data.dao.ImagesDao
import com.xavidev.testessential.data.dao.SneakersDao
import com.xavidev.testessential.data.db.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class SneakerTest {

    companion object {
        private val SINGLE_SNEAKER_COMPLETE = DatabaseTestUtil.createSneakerComplete()
        private val SINGLE_SNEAKER = DatabaseTestUtil.createSneaker()
        private val SNEAKERS_LIST = DatabaseTestUtil.createSneakersList()
        private val SNEAKERS_COMPLETE_LIST = DatabaseTestUtil.createSneakerCompletesList()

        const val COUNT_ONE = 1
        const val BRAND_NIKE_COUNT = 1
        const val TYPE_CASUAL_COUNT = 3
        const val SNEAKER_IMAGES_LIST_COUNT = 7
        const val BRAND_ID_NIKE = "fe8b8e2c-2272-11ed-861d-0242ac120002"
        const val TYPE_ID_CASUAL = "0d4f9ac4-2a3f-11ed-a261-0242ac120002"
        const val TRUE_VALUE = true
        const val FALSE_VALUE = false
    }

    private lateinit var sneakersDao: SneakersDao
    private lateinit var brandsDao: BrandsDao
    private lateinit var imagesDao: ImagesDao
    private lateinit var db: AppDatabase

    @Before
    fun setup() {
        val context = RuntimeEnvironment.getApplication()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        sneakersDao = db.sneakerDao()
        brandsDao = db.brandsDao()
        imagesDao = db.imagesDao()
    }

    private fun insertAssets() = runBlocking {
        sneakersDao.insertCurrencies(DatabaseTestUtil.createCurrenciesList())
        sneakersDao.insertTypes(DatabaseTestUtil.createSneakerTypesList())
        brandsDao.insertBrands(DatabaseTestUtil.createBrandList())
        imagesDao.insertImages(DatabaseTestUtil.createSneakerImagesList())
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun populateSneakersReadSneakersListCount() = runTest {
        sneakersDao.insertSneakers(SNEAKERS_LIST)
        val sneakers = sneakersDao.getSneakersCount()
        assertThat(sneakers).isEqualTo(SNEAKERS_LIST.size)
    }

    @Test
    fun insertSneakersListReadSneakersList() = runTest {
        insertAssets()
        sneakersDao.insertAll(SNEAKERS_LIST)
        val sneakers = sneakersDao.getAllCompleteSneakers()
        assertThat(sneakers).isEqualTo(SNEAKERS_COMPLETE_LIST)
    }

    @Test
    fun clearSneakersReadEmptyList() = runTest {
        sneakersDao.insertSneakers(listOf(SINGLE_SNEAKER))
        sneakersDao.clearSneakerTable()
        val sneakers = sneakersDao.getAllCompleteSneakers()
        assertThat(sneakers).isEmpty()
    }

    @Test
    fun readAllSneakersReadSneakersList() = runTest {
        insertAssets()
        sneakersDao.insertAll(SNEAKERS_LIST)
        val sneakers = sneakersDao.getAllCompleteSneakers()
        assertThat(sneakers).isEqualTo(SNEAKERS_COMPLETE_LIST)
    }

    @Test
    fun readSneakersCountGetCountNumber() = runTest {
        sneakersDao.insertAll(listOf(SINGLE_SNEAKER))
        val sneakerCount = sneakersDao.getSneakersCount()
        assertThat(sneakerCount).isEqualTo(COUNT_ONE)
    }

    @Test
    fun readSneakersByBrandGetSneakersList() = runTest {
        insertAssets()
        sneakersDao.insertAll(SNEAKERS_LIST)
        val sneakers = sneakersDao.getSneakersByBrand(SINGLE_SNEAKER.brandId)
        assertThat(sneakers).containsExactly(SINGLE_SNEAKER_COMPLETE)
        assertThat(sneakers).hasSize(BRAND_NIKE_COUNT)
    }

    @Test
    fun readSneakersByTypeGetSneakersList() = runTest {
        insertAssets()
        sneakersDao.insertAll(SNEAKERS_LIST)
        val sneakers = sneakersDao.getSneakersByType(TYPE_ID_CASUAL)
        assertThat(sneakers).hasSize(TYPE_CASUAL_COUNT)
    }

    @Test
    fun readSneakerByIdGetSingleSneaker() = runTest {
        insertAssets()
        sneakersDao.insertAll(SNEAKERS_LIST)
        val sneaker = sneakersDao.getSneaker(SINGLE_SNEAKER_COMPLETE.id)
        assertThat(sneaker).isEqualTo(SINGLE_SNEAKER_COMPLETE)
    }

    @Test
    fun updateSneakerFavoriteTrueReadSneakerWithUpdatedFavoriteValueTrue() = runTest {
        insertAssets()
        sneakersDao.insertSneakers(listOf(SINGLE_SNEAKER))
        assertThat(SINGLE_SNEAKER.favorite).isFalse()
        sneakersDao.updateFavoriteValue(SINGLE_SNEAKER.id, TRUE_VALUE)
        val sneakerUpdated = sneakersDao.getSneaker(SINGLE_SNEAKER.id)
        assertThat(sneakerUpdated.favorite).isTrue()
    }

    @Test
    fun updateSneakerInCartTrueReadSneakerWithUpdatedInCartValueTrue() = runTest {
        insertAssets()
        sneakersDao.insertSneakers(listOf(SINGLE_SNEAKER))
        assertThat(SINGLE_SNEAKER.inCart).isFalse()
        sneakersDao.updateSneakerInCart(SINGLE_SNEAKER.id, TRUE_VALUE)
        val sneakerUpdated = sneakersDao.getSneaker(SINGLE_SNEAKER.id)
        println(sneakerUpdated)
        assertThat(sneakerUpdated.inCart).isTrue()
    }

    @Test
    fun readSneakerImagesGetListOfImagesCount() = runTest {
        insertAssets()
        sneakersDao.insertSneakers(listOf(SINGLE_SNEAKER))
        val sneakerImages = sneakersDao.getSneakerImages(SINGLE_SNEAKER.photosId)
        assertThat(sneakerImages.images).hasSize(SNEAKER_IMAGES_LIST_COUNT)
    }

    @Test
    fun populateCurrencyReadCurrencyList() = runTest {
        val currenciesList = DatabaseTestUtil.createCurrenciesList()
        sneakersDao.populateCurrencyTable(currenciesList)
        val currencies = sneakersDao.getCurrencies()
        assertThat(currencies).isEqualTo(currenciesList)
    }

    @Test
    fun deleteCurrencyReadEmptyList() = runTest {
        val currenciesList = DatabaseTestUtil.createCurrenciesList()
        sneakersDao.insertCurrencies(currenciesList)
        sneakersDao.clearCurrencyTable()
        val currencies = sneakersDao.getCurrencies()
        assertThat(currencies).isEmpty()
    }

    @Test
    fun insertCurrencyListReadCurrencyList() = runTest {
        val currenciesList = DatabaseTestUtil.createCurrenciesList()
        sneakersDao.insertCurrencies(currenciesList)
        val currencies = sneakersDao.getCurrencies()
        assertThat(currencies).isEqualTo(currenciesList)
    }

    @Test
    fun populateSneakerTypeReadSneakerTypesList() = runTest {
        val sneakerTypes = DatabaseTestUtil.createSneakerTypesList()
        sneakersDao.populateTypesTable(sneakerTypes)
        val types = sneakersDao.getTypes()
        assertThat(types).isEqualTo(sneakerTypes)
    }

    @Test
    fun insertSneakerTypesReadSneakerTypesList() = runTest {
        val sneakerTypes = DatabaseTestUtil.createSneakerTypesList()
        sneakersDao.insertTypes(sneakerTypes)
        val types = sneakersDao.getTypes()
        assertThat(types).isEqualTo(sneakerTypes)
    }

    @Test
    fun deleteSneakerTypesReadEmptySneakerTypesList() = runTest {
        val sneakerTypes = DatabaseTestUtil.createSneakerTypesList()
        sneakersDao.insertTypes(sneakerTypes)
        sneakersDao.clearTypesTable()
        val types = sneakersDao.getTypes()
        assertThat(types).isEmpty()
    }
}
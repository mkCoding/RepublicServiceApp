package com.example.republicserviceapp.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kemakolam.republicserviceapp.data.db.dao.DriversDao
import com.kemakolam.republicserviceapp.data.db.tables.DriverEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify

@RunWith(AndroidJUnit4::class)
class DriverDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockDriversDao: DriversDao

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }


//    @Test
//    fun testInsertAndGetDriver() = runBlocking{
//        val driver = DriverEntity(id = "1", name="Harvey Dent")
//
//        //Mock the behavior of insertDrivers and get all drivers
//        doNothing().`when`(mockDriversDao).insertDrivers(listOf(driver))
//        `when`(mockDriversDao.getAllDrivers()).thenReturn(listOf(driver))
//
//        //Call the mocked methods
//        mockDriversDao.insertDrivers(listOf(driver))
//        val drivers = mockDriversDao.getAllDrivers()
//
//        //verify the interactions
//        verify(mockDriversDao).insertDrivers(listOf(driver))
//        verify(mockDriversDao).getAllDrivers()
//
//        //Assert
//        Assert.assertEquals(1, drivers.size) //test driver size
//        Assert.assertEquals("Harvey Dent",drivers[0].name)
//
//    }
}
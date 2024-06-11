package com.example.republicserviceapp.driver

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kemakolam.republicserviceapp.data.db.dao.DriversDao
import com.kemakolam.republicserviceapp.data.db.dao.RoutesDao
import com.kemakolam.republicserviceapp.data.db.tables.DriverEntity
import com.kemakolam.republicserviceapp.data.repository.ApiRepository
import com.kemakolam.republicserviceapp.ui.driver.DriverViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)

class DriverViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    // Set up main dispatcher for testing
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: ApiRepository

    @Mock
    private lateinit var driversDao: DriversDao

    @Mock
    private lateinit var routesDao: RoutesDao




    private lateinit var driverViewModel: DriverViewModel

    @Mock
    private lateinit var driversObserver: Observer<List<DriverEntity>?>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        driverViewModel = DriverViewModel(repository, driversDao, routesDao)
        driverViewModel.driversList.observeForever(driversObserver)
    }


    //Test the ViewModel's behavior when getStoredDrivers returns a non-null list

    @Test
    fun testGetStoredDriversIsCalled() = runBlocking {

        //Create the mock drivers list
        val mockDrivers = listOf(
            DriverEntity(1,"2","John Doe"),
            DriverEntity(2,"3","Jane Doe"),
            DriverEntity(3,"9","Spike Doe"),
            DriverEntity(4,"40","Jason Doe"))

        //when mock repo will get stored drivers and return the mock drivers
        `when`(repository.getStoredDrivers()).thenReturn(mockDrivers)

        //get all drivers from the view model
        driverViewModel.getAllDrivers()

        //The onChanged() method of the mocked driversObserver object
        //should be called with mockDrivers as an argument.
        // This is a common pattern in unit testing to verify that certain
        // methods are being called with the expected parameters.
        verify(driversObserver).onChanged(mockDrivers)
    }


    //Test the ViewModel's behavior when getStoredDrivers returns null

    @Test
    fun testGetStoredDriverReturnsNull()= runBlocking {

        //when you go to DB and get stored drivers is null
        `when`(repository.getStoredDrivers()).thenReturn(null)

        //call get all drivers from view model
        driverViewModel.getAllDrivers()

        //verify that this method call should never happen
        //- verify -> allows you to verify that a certain method call on a mock object is called
        //- driversObserver -> mock object on which you're verifying the method call
        //- never -> the method being verified should never be called during test execution
        //- onChanged() - verify the onChanged method on driversObserver mock object is never called with any argument
        verify(driversObserver, never()).onChanged(any())

    }




    class MainCoroutineRule(private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) : TestWatcher() {

        override fun starting(description: Description?) {
            super.starting(description)
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: Description?) {
            super.finished(description)
            Dispatchers.resetMain()
            testDispatcher.cleanupTestCoroutines()
        }
    }


}








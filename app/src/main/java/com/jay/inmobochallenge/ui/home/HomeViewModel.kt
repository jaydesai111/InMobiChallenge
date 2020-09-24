package com.jay.inmobochallenge.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.inmobochallenge.data.Posts
import com.jay.inmobochallenge.data.Users
import com.jay.inmobochallenge.data.database.PostDatabaseDao
import com.jay.inmobochallenge.repository.RepositoryImp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(val repositoryImp: RepositoryImp, val postDatabaseDao: PostDatabaseDao): ViewModel() {

    private val _postList = MutableLiveData<List<Posts>>()

    val postList: LiveData<List<Posts>>
    get() = postDatabaseDao.getPost()

    private val _userList = MutableLiveData<List<Users>>()

    private val userList: LiveData<List<Users>>
        get() = _userList

    var time: Long = 0L

    val _apiTime = MutableLiveData<Long>()

    val apiTime : LiveData<Long>
    get()= _apiTime

    val _userCount = MutableLiveData<Int>()

    val userCount: LiveData<Int>
        get()= _userCount



    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }

    fun getData()
    {
    viewModelScope.launch(exceptionHandler)
        {
            time = System.currentTimeMillis()
            val postData = async {
                repositoryImp.fetchPost()
            }
            val userData = async {
                repositoryImp.fetchUser()
            }
            _userCount.postValue(userData.await().size)
            repositoryImp.updatePost(postData.await())
            time = (System.currentTimeMillis() - time)
            _apiTime.postValue(time)

            Log.i("HomeVieModel","this is value of time "+time)
        }
    }

    fun deleteData()
    {
        viewModelScope.launch {
            postDatabaseDao.deletePost()
        }
    }



}
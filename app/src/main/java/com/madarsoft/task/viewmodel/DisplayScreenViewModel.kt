package com.madarsoft.task.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madarsoft.core.model.Result
import com.madarsoft.core.model.User
import com.madarsoft.core.repository.UserInformationRepository
import com.madarsoft.core.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisplayScreenViewModel@Inject constructor(private val getUserUseCase: GetUserUseCase) : ViewModel() {

    var fetchingUserState: MutableSharedFlow<Result<User>> = MutableSharedFlow()
        private set(value) {}

    fun getUserFromDatabase(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = getUserUseCase.invoke()
            fetchingUserState.emit(result)
        }
    }
}
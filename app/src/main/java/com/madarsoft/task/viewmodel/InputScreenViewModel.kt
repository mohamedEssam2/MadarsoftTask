package com.madarsoft.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madarsoft.core.model.Result
import com.madarsoft.core.model.User
import com.madarsoft.core.usecase.AddNewUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputScreenViewModel @Inject constructor(private val addNewUserUseCase: AddNewUserUseCase): ViewModel() {

    var insertingState:MutableSharedFlow<Result<User>> = MutableSharedFlow()
        private set(value) {}

    fun addNewUserIntoDatabase(user: User){
        viewModelScope.launch(Dispatchers.IO) {
           val result = addNewUserUseCase.invoke(user)
            insertingState.emit(result)
        }
    }
}
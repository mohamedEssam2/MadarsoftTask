package com.madarsoft.task

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import com.madarsoft.core.exception.DatabaseError
import com.madarsoft.core.model.Result
import com.madarsoft.task.viewmodel.DisplayScreenViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.madarsoft.core.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayActivity : ComponentActivity() {
    val displayScreenViewModel: DisplayScreenViewModel by viewModels()

    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Task App", color = Color.White) },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
                    )
                }
            ) { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    val user = remember { mutableStateOf(User("", 0f, "", "")) }
                    UserCard(user)
                    lifecycleScope.launch{
                        displayScreenViewModel.fetchingUserState.collect{
                            when(it){
                                is Result.Error -> {
                                    val errorMessage = getErrorMessage(it.error)
                                    Toast.makeText(
                                        baseContext,
                                        errorMessage,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                is Result.Success -> {
                                    user.value = it.data
                                }
                            }
                        }
                    }
                }
            }

            displayScreenViewModel.getUserFromDatabase()
        }

    }

    private fun getErrorMessage(error: DatabaseError): String {
        when (error) {
            is DatabaseError.CancellationException -> {
                return error.message
            }

            is DatabaseError.IOException -> {
                return error.message
            }

            is DatabaseError.IllegalStateException -> {
                return error.message
            }

            is DatabaseError.RuntimeException -> {
                return error.message
            }

            is DatabaseError.SQLiteAbortException -> {
                return error.message
            }

            is DatabaseError.SQLiteDatabaseCorruptException -> {
                return error.message
            }

            is DatabaseError.SQLiteFullException -> {
                return error.message
            }

            is DatabaseError.TransactionException -> {
                return error.message
            }

            is DatabaseError.InvalidDataException -> {
                return error.message
            }
        }
    }

    @Composable
    fun UserCard(user: MutableState<User>) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Name: ${user.value.name}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Age: ${user.value.age}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Job: ${user.value.jobTitle}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Gender: ${user.value.gender}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }

}


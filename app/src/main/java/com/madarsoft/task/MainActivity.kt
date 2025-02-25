package com.madarsoft.task

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.madarsoft.core.exception.DatabaseError
import com.madarsoft.core.model.User

import com.madarsoft.task.viewmodel.InputScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val inputScreenViewModel: InputScreenViewModel by viewModels()
    private var nameError: MutableState<Boolean> = mutableStateOf(false)
    private var jobTitleError: MutableState<Boolean> = mutableStateOf(false)


    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("CoroutineCreationDuringComposition")
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
                    UserInputForm()
                }
            }

            lifecycleScope.launch {
                inputScreenViewModel.insertingState.collect {
                    when (it) {
                        is com.madarsoft.core.model.Result.Error -> {
                            var errorMessage = getErrorMessage(it.error)

                            if (errorMessage.contains("name")) {
                                nameError.value = true
                            }
                            if (errorMessage.contains("job")) {
                                jobTitleError.value = true
                            }

                            Toast.makeText(
                                baseContext,
                                errorMessage,
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        is com.madarsoft.core.model.Result.Success -> {
                            if (nameError.value == true) {
                                nameError.value = false
                            }

                            if (jobTitleError.value == true) {
                                jobTitleError.value = false
                            }
                            startActivity(Intent(baseContext, DisplayActivity::class.java))
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun PreviewUserInputForm() {
        UserInputForm()
    }

    @Composable
    private fun UserInputForm() {
        var name by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var jobTitle by remember { mutableStateOf("") }
        var selectedGender by remember { mutableStateOf("Male") }

        val genders = listOf("Male", "Female")

        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                isError = nameError.value
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = age,
                onValueChange = { age = it.filter { char -> char.isDigit() } },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = jobTitle,
                onValueChange = { jobTitle = it },
                label = { Text("Job Title") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                isError = jobTitleError.value
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Gender")
            Column {
                genders.forEach { genderOption ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedGender = genderOption }
                    ) {
                        RadioButton(
                            selected = (selectedGender == genderOption),
                            onClick = { selectedGender = genderOption }
                        )
                        Text(text = genderOption, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                var user = User(name, age.toFloat(), jobTitle, selectedGender)
                inputScreenViewModel.addNewUserIntoDatabase(user)
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Submit")
            }
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
}



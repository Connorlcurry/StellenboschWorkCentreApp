package com.swerksentrum.stellenboschworkcentre.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.swerksentrum.stellenboschworkcentre.AuthState
import com.swerksentrum.stellenboschworkcentre.AuthViewModel
import com.swerksentrum.stellenboschworkcentre.R
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(

    onNavigateToHome: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToLogin: () -> Unit,
    authViewModel: AuthViewModel

) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneNum by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    val authState by authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {

        when (authState) {

            is AuthState.Authenticated -> onNavigateToHome()
            is AuthState.Error -> Toast.makeText(
                context,
                (authState as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
            else -> Unit

        }

    }

    Scaffold { paddingValues ->

        Box(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center

        ) {

            Image(

                painter = painterResource(id = R.drawable.bg_img_2),
                contentDescription = null,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.5f

            )

            Card(

                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(

                        text = "Register a Beneficiary",
                        color = Color(0xff2f8137),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(

                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)

                    ) {

                        OutlinedTextField(

                            value = firstName,
                            onValueChange = { firstName = it },
                            label = { Text("First Name") },
                            modifier = Modifier.weight(1f)

                        )

                        OutlinedTextField(

                            value = lastName,
                            onValueChange = { lastName = it },
                            label = { Text("Last Name") },
                            modifier = Modifier.weight(1f)

                        )

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(

                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(

                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(

                        value = phoneNum,
                        onValueChange = { phoneNum = it },
                        label = { Text("Phone Number") },
                        modifier = Modifier.fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(

                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Address") },
                        modifier = Modifier.fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(

                        onClick = { authViewModel.register(firstName, lastName, email, password, phoneNum, address) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff2f8137))

                    ) {

                        Text("Next Step")

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(onClick = onNavigateToLogin) {

                        Text(

                            text = "Already have an account? Login",
                            color = Color(0xffd2a622)

                        )

                    }

                }

            }

        }

    }

}

@Serializable
private data object RegisterDestination


fun NavGraphBuilder.registerScreen(

    onNavigateToHome: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToLogin: () -> Unit

) {

    composable<RegisterDestination> {

        val authViewModel: AuthViewModel = viewModel()

        RegisterScreen(

            onNavigateToHome = onNavigateToHome,
            onNavigateUp = onNavigateUp,
            onNavigateToLogin = onNavigateToLogin,
            authViewModel = authViewModel

        )

    }

}

fun NavController.navigateToRegister() {

    navigate(RegisterDestination)

}
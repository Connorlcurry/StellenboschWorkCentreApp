package com.swerksentrum.stellenboschworkcentre.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import com.swerksentrum.stellenboschworkcentre.AuthState
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
import com.swerksentrum.stellenboschworkcentre.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.swerksentrum.stellenboschworkcentre.AuthViewModel
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(

    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit,
    authViewModel: AuthViewModel

) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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

    Scaffold() { paddingValues ->

        Box(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center

        ) {

            Image(

                painter = painterResource(id = R.drawable.bg_img_1),
                contentDescription = null,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.5f

            )

            // Card that displays the login form
            Card(

                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(

                        text = "Login",
                        color = Color(0xff2f8137),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(

                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") }

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(

                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") }

                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(

                        onClick = { authViewModel.login(email, password) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff2f8137))

                    ) {

                        Text("Login")

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(onClick = onNavigateToRegister) {

                        Text(text = "Don't have an account? Register", color = Color(0xffd2a622))

                    }

                }

            }

        }

    }

}

@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(

    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit

) {

    composable<LoginDestination> {

        val authViewModel: AuthViewModel = viewModel()

        LoginScreen(

            onNavigateToHome = onNavigateToHome,
            onNavigateToRegister = onNavigateToRegister,
            authViewModel = authViewModel

        )

    }

}

fun NavController.navigateToLogin() {

    navigate(LoginDestination)

}
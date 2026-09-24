package com.swerksentrum.stellenboschworkcentre.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.swerksentrum.stellenboschworkcentre.AuthState
import com.swerksentrum.stellenboschworkcentre.AuthViewModel
import com.swerksentrum.stellenboschworkcentre.R
import com.swerksentrum.stellenboschworkcentre.components.NavigationDrawerContent
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToContact: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToDonate: () -> Unit,
    authViewModel: AuthViewModel
) {
    val authState by authViewModel.authState.observeAsState()
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(authState) {

        when (authState) {

            is AuthState.Unauthenticated -> onNavigateToLogin()
            is AuthState.Error -> Toast.makeText(
                context,
                (authState as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit

        }

    }

    ModalNavigationDrawer(

        drawerState = drawerState,
        drawerContent = {

            NavigationDrawerContent(

                onNavigateToHome = onNavigateToHome,
                onNavigateToAbout = onNavigateToAbout,
                onNavigateToServices = onNavigateToServices,
                onNavigateToShop = onNavigateToShop,
                onNavigateToContact = onNavigateToContact,
                onNavigateToChatbot = onNavigateToChatbot,
                onNavigateToDonate = onNavigateToDonate,
                onLogout = { authViewModel.logout() },
                onCloseDrawer = { scope.launch { drawerState.close() } }

            )
        }

    ) {
        Scaffold(

            topBar = {

                CenterAlignedTopAppBar(

                    title = { Text(text = "Home") },

                    navigationIcon = {

                        IconButton(onClick = { scope.launch { drawerState.open() } }) {

                            Icon(Icons.Default.Menu, contentDescription = "Menu")

                        }

                    }

                )

            }
        ) { paddingValues ->

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Text(text = "Welcome Home!")

                Spacer(modifier = Modifier.height(16.dp))

                Button(

                    onClick = onNavigateToAbout,
                    modifier = Modifier.fillMaxWidth()

                ) {

                    Text(text = "About")

                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(

                    onClick = onNavigateToServices,
                    modifier = Modifier.fillMaxWidth()

                ) {

                    Text(text = "Services")

                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(

                    onClick = onNavigateToShop,
                    modifier = Modifier.fillMaxWidth()

                ) {

                    Text(text = "Shop")

                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(

                    onClick = onNavigateToContact,
                    modifier = Modifier.fillMaxWidth()

                ) {

                    Text(text = "Contact")

                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(

                    onClick = onNavigateToChatbot,
                    modifier = Modifier.fillMaxWidth()

                ) {

                    Text(text = "AI Chatbot")

                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(

                    onClick = onNavigateToDonate,
                    modifier = Modifier.fillMaxWidth()

                ) {

                    Text(text = "Donate")

                }

                Spacer(modifier = Modifier.weight(1f))

                TextButton(onClick = { authViewModel.logout() }) {

                    Text(text = "Log Out")

                }

            }

        }

    }

}

@Serializable
data object HomeDestination

fun NavGraphBuilder.homeScreen(

    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToContact: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToDonate: () -> Unit

) {

    composable<HomeDestination> {

        val authViewModel: AuthViewModel = viewModel()

        HomeScreen(

            onNavigateToLogin = onNavigateToLogin,
            onNavigateToHome = onNavigateToHome,
            onNavigateToAbout = onNavigateToAbout,
            onNavigateToServices = onNavigateToServices,
            onNavigateToShop = onNavigateToShop,
            onNavigateToContact = onNavigateToContact,
            onNavigateToChatbot = onNavigateToChatbot,
            onNavigateToDonate = onNavigateToDonate,
            authViewModel = authViewModel

        )

    }

}

fun NavController.navigateToHome() {

    navigate(HomeDestination)

}
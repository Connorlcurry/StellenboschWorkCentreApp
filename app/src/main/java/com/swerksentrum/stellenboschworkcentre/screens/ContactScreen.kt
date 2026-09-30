package com.swerksentrum.stellenboschworkcentre.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.swerksentrum.stellenboschworkcentre.AuthViewModel
import com.swerksentrum.stellenboschworkcentre.R
import com.swerksentrum.stellenboschworkcentre.components.NavigationDrawerContent
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(

    onNavigateUp: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToContact: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToDonate: () -> Unit,
    authViewModel: AuthViewModel

) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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

                    navigationIcon = {

                        Row {

                            IconButton(onClick = { scope.launch { drawerState.open() } }) {

                                Icon(Icons.Default.Menu, contentDescription = "Menu")

                            }

                        }

                    },

                    title = {

                        Text(

                            text = "Get In Touch",
                            color = Color(0xff2f8137),
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold

                        )

                    }

                )

            },
            bottomBar = {

                NavigationBar {

                    NavigationBarItem(
                        selected = false,
                        onClick = onNavigateToHome,
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = onNavigateToAbout,
                        icon = { Icon(Icons.Default.Info, contentDescription = "About") },
                        label = { Text("About") }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = onNavigateToServices,
                        icon = { Icon(Icons.Default.DesignServices, contentDescription = "Services") },
                        label = { Text("Services") }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = onNavigateToShop,
                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Shop") },
                        label = { Text("Shop") }
                    )
                    NavigationBarItem(
                        selected = true,
                        onClick = onNavigateToContact,
                        icon = { Icon(Icons.Default.Email, contentDescription = "Contact", tint = Color(0xff2f8137)) },
                        label = { Text("Contact") }
                    )

                }

            }

        ) { paddingValues ->

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {

                Card(

                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)

                ) {

                    Column(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Text(

                            text = "Contact Information",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xff2f8137)

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Address", fontWeight = FontWeight.Bold)
                        Text(text = "Stellenbosch, Western Cape, South Africa")

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Phone", fontWeight = FontWeight.Bold)
                        Text(text = "+27 17 123 4567")
                        Text(text = "+21 555 0100")

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Email", fontWeight = FontWeight.Bold)
                        Text(text = "info@stellenboschwork.org")

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Office Hours", fontWeight = FontWeight.Bold)
                        Text(text = "Monday - Friday: 8:00 AM - 5:00 PM")
                        Text(text = "Saturday: 9:00 AM - 1:00 PM")

                    }

                }

            }

        }

    }

}

@Serializable
data object ContactDestination

fun NavGraphBuilder.contactScreen(

    onNavigateUp: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToContact: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToDonate: () -> Unit

) {

    composable<ContactDestination> {

        val authViewModel: AuthViewModel = viewModel()

        ContactScreen(

            onNavigateUp = onNavigateUp,
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

fun NavController.navigateToContact() {

    navigate(ContactDestination)

}

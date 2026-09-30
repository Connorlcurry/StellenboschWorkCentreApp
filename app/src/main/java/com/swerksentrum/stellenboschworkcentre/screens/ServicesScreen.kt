package com.swerksentrum.stellenboschworkcentre.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
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
fun ServicesScreen(

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

                            text = "Our Services",
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
                        selected = true,
                        onClick = onNavigateToServices,
                        icon = { Icon(Icons.Default.DesignServices, contentDescription = "Services", tint = Color(0xff2f8137)) },
                        label = { Text("Services") }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = onNavigateToShop,
                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Shop") },
                        label = { Text("Shop") }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = onNavigateToContact,
                        icon = { Icon(Icons.Default.Email, contentDescription = "Contact") },
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

                        Text(text = "High-quality services delivered by our dedicated team, supporting meaningful employment.")

                    }

                }

                Spacer(modifier = Modifier.height(18.dp))

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

                            text = "What We Offer",
                            color = Color(0xff2f8137),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold

                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Column(

                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)

                        ) {

                            Card(

                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FBF9)),
                                border = BorderStroke(1.dp, Color(0xFFE2E8E2)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

                            ) {

                                Column(

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)

                                ) {

                                    Text(

                                        text = "Folding Services",
                                        color = Color(0xff2f8137),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(

                                        text = "Professional document folding and preparation for meetings, brochures, and promotional materials.",
                                        fontSize = 14.sp

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    OutlinedButton(

                                        onClick = onNavigateToContact,
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                        border = BorderStroke(1.dp, Color(0xffd2a622))

                                    ) {

                                        Text(text = "Request Service", color = Color(0xffd2a622))

                                    }
                                }

                            }

                            Card(

                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FBF9)),
                                border = BorderStroke(1.dp, Color(0xFFE2E8E2)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

                            ) {

                                Column(

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)

                                ) {

                                    Text(

                                        text = "Sorting Services",
                                        color = Color(0xff2f8137),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(

                                        text = "Efficient sorting solutions for documents, products, and materials with attention to accuracy.",
                                        fontSize = 14.sp

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    OutlinedButton(

                                        onClick = onNavigateToContact,
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                        border = BorderStroke(1.dp, Color(0xffd2a622))

                                    ) {

                                        Text(text = "Request Service", color = Color(0xffd2a622))

                                    }
                                }

                            }

                            Card(

                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FBF9)),
                                border = BorderStroke(1.dp, Color(0xFFE2E8E2)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

                            ) {

                                Column(

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)

                                ) {

                                    Text(

                                        text = "Packaging Services",
                                        color = Color(0xff2f8137),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(

                                        text = "Quality packaging and presentation services for products, gifts, boxes and promotional materials.",
                                        fontSize = 14.sp

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    OutlinedButton(

                                        onClick = onNavigateToContact,
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                        border = BorderStroke(1.dp, Color(0xffd2a622))

                                    ) {

                                        Text(text = "Request Service", color = Color(0xffd2a622))

                                    }
                                }

                            }

                            Card(

                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FBF9)),
                                border = BorderStroke(1.dp, Color(0xFFE2E8E2)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

                            ) {

                                Column(

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)

                                ) {

                                    Text(

                                        text = "Photocopying",
                                        color = Color(0xff2f8137),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(

                                        text = "Reliable copying and printing services with fast turnaround times and competitive rates.",
                                        fontSize = 14.sp

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    OutlinedButton(

                                        onClick = onNavigateToContact,
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                        border = BorderStroke(1.dp, Color(0xffd2a622))

                                    ) {

                                        Text(text = "Request Service", color = Color(0xffd2a622))

                                    }

                                }

                            }

                        }

                    }

                }

            }

        }

    }

}

@Serializable
data object ServicesDestination

fun NavGraphBuilder.servicesScreen(

    onNavigateUp: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToContact: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToDonate: () -> Unit

) {

    composable<ServicesDestination> {

        val authViewModel: AuthViewModel = viewModel()

        ServicesScreen(

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

fun NavController.navigateToServices() {

    navigate(ServicesDestination)

}

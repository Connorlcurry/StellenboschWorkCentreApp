package com.swerksentrum.stellenboschworkcentre.screens

import android.widget.Toast
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
import androidx.compose.material3.Button
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.swerksentrum.stellenboschworkcentre.AuthState
import com.swerksentrum.stellenboschworkcentre.AuthViewModel
import com.swerksentrum.stellenboschworkcentre.components.NavigationDrawerContent
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(

    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToContact: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToDonate: () -> Unit,
    authViewModel: AuthViewModel

) {

    val auth = FirebaseAuth.getInstance()
    val authState by authViewModel.authState.observeAsState()
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current
    val currentUser = auth.currentUser

    var firstName by remember { mutableStateOf("") }

    // Collects the user's first name from the Firestore database
    LaunchedEffect(currentUser) {

        if (currentUser != null) {

            db.collection("users").document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        firstName = document.getString("firstName") ?: ""
                    }
                }

        }

    }

    LaunchedEffect(authState) {

        when (authState) {

            is AuthState.Unauthenticated -> onNavigateToLogin()
            is AuthState.Error -> Toast.makeText(
                context,
                (authState as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit

        }

    }

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

                            text = if (firstName.isNotBlank())
                                "Welcome back, $firstName"
                            else "Welcome back",
                            color = Color(0xff2f8137),
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold

                        )

                    },

                    )

            },
            bottomBar = {

                NavigationBar {

                    NavigationBarItem(
                        selected = true,
                        onClick = onNavigateToHome,
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = Color(0xff2f8137)) },
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
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Text(

                            text = "Empowering Ability. Creating Opportunity.",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xff2f8137)

                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(

                            fontSize = 15.sp,
                            text = "Supporting adults with disabilities through meaningful work, skills development, and social inclusion."

                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(

                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)

                        ) {

                            Button(

                                onClick = onNavigateToDonate,
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffd2a622))

                            ) {

                                Text(text = "Donate Now")

                            }

                            OutlinedButton(

                                onClick = onNavigateToAbout,
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                border = BorderStroke(1.dp, Color(0xff2f8137))

                            ) {

                                Text(text = "Learn More", color = Color(0xff2f8137))

                            }

                        }

                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

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

                            text = "Success Stories",
                            color = Color(0xff2f8137),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold

                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(

                            text = "Real stories from real people whose lives have been transformed.",
                            fontSize = 14.sp,
                            color = Color.Gray

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

                                        text = "“",
                                        color = Color(0xffd2a622),
                                        fontSize = 36.sp,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 24.sp

                                    )

                                    Text(

                                        text = "The work training program gave me confidence. I now have more independence and support.",
                                        fontSize = 14.sp,
                                        fontStyle = FontStyle.Italic,
                                        color = Color.DarkGray

                                    )

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Text(

                                        text = "Sarah M.",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = Color(0xff2f8137)

                                    )

                                    Text(

                                        text = "Sorting Services",
                                        fontSize = 13.sp,
                                        color = Color.Gray

                                    )

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

                                        text = "“",
                                        color = Color(0xffd2a622),
                                        fontSize = 36.sp,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 24.sp

                                    )

                                    Text(

                                        text = "I found more hope in my future. I found a community that believes in me every day.",
                                        fontSize = 14.sp,
                                        fontStyle = FontStyle.Italic,
                                        color = Color.DarkGray

                                    )

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Text(

                                        text = "David L.",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = Color(0xff2f8137)

                                    )

                                    Text(

                                        text = "Handcrafts",
                                        fontSize = 13.sp,
                                        color = Color.Gray

                                    )

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

                                        text = "“",
                                        color = Color(0xffd2a622),
                                        fontSize = 36.sp,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 24.sp

                                    )

                                    Text(

                                        text = "The skills I learned opened doors. I'm proud of the work I do every day.",
                                        fontSize = 14.sp,
                                        fontStyle = FontStyle.Italic,
                                        color = Color.DarkGray

                                    )

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Text(

                                        text = "Thandi K.",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = Color(0xff2f8137)

                                    )

                                    Text(

                                        text = "Office Work",
                                        fontSize = 13.sp,
                                        color = Color.Gray

                                    )

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
data object HomeDestination

fun NavGraphBuilder.homeScreen(

    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit,
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
            onNavigateToRegister = onNavigateToRegister,
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
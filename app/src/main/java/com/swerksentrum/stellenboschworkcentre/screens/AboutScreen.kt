package com.swerksentrum.stellenboschworkcentre.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
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
fun AboutScreen(

    onNavigateUp: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToContact: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToDonate: () -> Unit,
    onNavigateToAccount: () -> Unit,
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
                onNavigateToAccount = onNavigateToAccount,
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

                            text = "About Us",
                            color = Color(0xffd8a13a),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold

                        )

                    },

                    actions = {

                        Row {

                            IconButton(onClick = onNavigateToAccount) {

                                Icon(Icons.Default.Person, contentDescription = "Account")

                            }

                        }

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
                        selected = true,
                        onClick = onNavigateToAbout,
                        icon = { Icon(Icons.Default.Info, contentDescription = "About", tint = Color(0xff1f6f4a)) },
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

            },
            floatingActionButton = {

                FloatingActionButton(

                    onClick = onNavigateToChatbot,
                    containerColor = Color(0xffd8a13a),
                    contentColor = Color(0xFF8C4800)

                ) {

                    Text(

                        text = "✦",
                        fontSize = 25.sp

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

                Image(

                    painter = painterResource(id = R.drawable.volunteers),
                    contentDescription = "Volunteers",
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .fillMaxWidth(0.85f)

                )

                Spacer(modifier = Modifier.height(18.dp))

                Column(

                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.Start

                ) {

                    Text(

                        text = "About Our Mission",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff1f6f4a)

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Stellenbosch Work Centre is dedicated to empowering adults with disabilities through comprehensive work training, skills development programs, and meaningful social inclusion initiatives")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "We believe every individual deserves the opportunity to contribute, grow, and thrive.")

                }

                Spacer(modifier = Modifier.height(18.dp))

                Card(

                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)

                ) {

                    Row(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Box(

                            modifier = Modifier
                                .width(4.dp)
                                .fillMaxHeight()
                                .background(Color(0xffd8a13a))

                        )

                        Column(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.Start

                        ) {

                            Text(

                                text = "Our Vision",
                                color = Color(0xff1f6f4a),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold

                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(text = "A community where every person, regardless of ability, has access to meaningful work opportunities and support.")

                        }

                    }

                }

                Spacer(modifier = Modifier.height(18.dp))

                Button(

                    onClick = onNavigateToContact,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xff1f6f4a))

                ) {

                    Text(text = "Contact Us")

                }

                // Footer
                Spacer(modifier = Modifier.height(8.dp))

                Row(

                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp)

                ) {

                    Text(

                        text = "© 2026 Stellenbosch Work Centre. Empowering ability. Creating opportunity.",
                        color = Color(0xE23A3A3A),
                        fontSize = 10.sp

                    )

                }

            }

        }

    }

}

@Serializable
data object AboutDestination

fun NavGraphBuilder.aboutScreen(

    onNavigateUp: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToContact: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToDonate: () -> Unit,
    onNavigateToAccount: () -> Unit

) {
    composable<AboutDestination> {

        val authViewModel: AuthViewModel = viewModel()

        AboutScreen(

            onNavigateUp = onNavigateUp,
            onNavigateToHome = onNavigateToHome,
            onNavigateToAbout = onNavigateToAbout,
            onNavigateToServices = onNavigateToServices,
            onNavigateToShop = onNavigateToShop,
            onNavigateToContact = onNavigateToContact,
            onNavigateToChatbot = onNavigateToChatbot,
            onNavigateToDonate = onNavigateToDonate,
            onNavigateToAccount = onNavigateToAccount,
            authViewModel = authViewModel

        )

    }

}

fun NavController.navigateToAbout() {

    navigate(AboutDestination)

}

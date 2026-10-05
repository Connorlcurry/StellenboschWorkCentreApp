package com.swerksentrum.stellenboschworkcentre.screens

import android.R.attr.shape
import android.content.Intent
import android.net.Uri
import android.view.ViewDebug
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
fun DonateScreen(

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

    var amountIndex by remember { mutableStateOf(0) }
    var purposeIndex by remember { mutableStateOf(0) }
    val paymentOptions = listOf("R50", "R100", "R500")
    val purposeOptions = listOf("Training Programs", "Transport Support", "General Support")
    var checked by remember { mutableStateOf(true) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

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

                            IconButton(onClick = onNavigateUp) {

                                Icon(

                                    painter = painterResource(id = R.drawable.arrow_back),
                                    contentDescription = "Back",
                                    tint = Color.Black

                                )

                            }

                        }

                    },

                    title = {

                        Text(

                            text = "Donate",
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

                            IconButton(onClick = { scope.launch { drawerState.open() } }) {

                                Icon(Icons.Default.Menu, contentDescription = "Menu")

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

                Column(

                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.Start

                ) {

                    Text(

                        text = "Make a Difference Today",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff1f6f4a)

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Your donation directly supports employment, training, and independence for adults with disabilities.")

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
                        horizontalAlignment = Alignment.Start

                    ) {

                        Text(text = "Select Amount:", fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.height(8.dp))

                        SingleChoiceSegmentedButtonRow() {

                            paymentOptions.forEachIndexed { index, label ->

                                SegmentedButton(

                                    shape = SegmentedButtonDefaults.itemShape(

                                        index = index,
                                        count = paymentOptions.size

                                    ),
                                    colors = SegmentedButtonDefaults.colors(

                                        activeContainerColor = Color(0xFF83CCAA),
                                        activeContentColor = Color(0xff1f6f4a),
                                        activeBorderColor = Color(0xff1f6f4a)

                                    ),
                                    onClick = { amountIndex = index },
                                    selected = index == amountIndex,
                                    label = { Text(label) }

                                )

                            }

                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = "Choose purpose:", fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.height(8.dp))

                        SingleChoiceSegmentedButtonRow() {

                            purposeOptions.forEachIndexed { index, label ->

                                SegmentedButton(

                                    shape = SegmentedButtonDefaults.itemShape(

                                        index = index,
                                        count = purposeOptions.size

                                    ),
                                    colors = SegmentedButtonDefaults.colors(

                                        activeContainerColor = Color(0xFF83CCAA),
                                        activeContentColor = Color(0xff1f6f4a),
                                        activeBorderColor = Color(0xff1f6f4a)

                                    ),
                                    onClick = { purposeIndex = index },
                                    selected = index == purposeIndex,
                                    label = { Text(label) }

                                )

                            }

                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(

                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            Checkbox(

                                checked = checked,
                                onCheckedChange = { checked = it },
                                colors = CheckboxDefaults.colors(

                                    uncheckedColor = Color(0xffd8a13a),
                                    checkedColor = Color(0xffd8a13a)

                                )

                            )

                            Text(

                                text = "Make this a monthly payment",
                                fontSize = 14.sp

                            )

                        }

                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(

                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xE2ECECEC)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)

                ) {

                    Column(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.Start

                    ) {

                        Row(

                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {

                            Text(

                                text = "Your contribution:"

                            )

                            Text(

                                text = "${paymentOptions[amountIndex]}"

                            )

                        }

                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(

                    onClick = {

                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://stellenboscheworkcenter.pages.dev/donate")
                        )
                        context.startActivity(intent)

                    },
                    modifier = Modifier.fillMaxWidth(0.85f),
                    colors = ButtonDefaults.buttonColors(Color(0xffd8a13a))

                ) {

                    Text(

                        text = "Continue Donation",
                        color = Color.Black

                    )

                }

                // Footer
                Spacer(modifier = Modifier.height(8.dp))

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
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
data object DonateDestination

fun NavGraphBuilder.donateScreen(

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
    composable<DonateDestination> {

        val authViewModel: AuthViewModel = viewModel()

        DonateScreen(

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

fun NavController.navigateToDonate() {

    navigate(DonateDestination)

}

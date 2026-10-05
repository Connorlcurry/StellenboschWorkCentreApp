package com.swerksentrum.stellenboschworkcentre.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
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
fun ShopScreen(

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

                            text = "Shop",
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
                        selected = true,
                        onClick = onNavigateToShop,
                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Shop", tint = Color(0xff1f6f4a)) },
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
                        .fillMaxWidth(0.90f)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Text(

                        text = "Handmade with Love",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff1f6f4a)

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(

                        text = "Every purchase supports our beneficiaries and their journey to independence. Each piece is made to order, so allow 2 days or 1 week depending on the item.",
                        fontSize = 15.sp

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = onNavigateToContact, colors = ButtonDefaults.buttonColors(containerColor = Color(0xff1f6f4a))) {

                        Text(text = "Enquire About a Product")

                    }

                }

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

                                Image(

                                    painter = painterResource(id = R.drawable.basket_weaving),
                                    contentDescription = "Basket Weaving",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(16f / 9f),
                                    contentScale = ContentScale.Crop

                                )

                                Column(

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)

                                ) {

                                    Text(

                                        text = "Handwoven Nguni Rug",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(

                                        text = "Beautiful handmade item supporting our artisans.",
                                        fontSize = 14.sp

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(

                                        text = "⏱ Made to order · Ready in 2 days",
                                        color = Color(0xffd8a13a),
                                        fontSize = 14.sp

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Row(

                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically

                                    ) {

                                        Text(

                                            text = "R450",
                                            fontSize = 20.sp,
                                            color = Color((0xff1f6f4a)),
                                            fontWeight = FontWeight.Bold

                                        )

                                        IconButton(

                                            onClick = { TODO() },
                                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xff1f6f4a))

                                        ) {

                                            Icon(

                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Add to Cart",
                                                tint = Color.White

                                            )

                                        }

                                    }

                                }

                            }

                            Card(

                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FBF9)),
                                border = BorderStroke(1.dp, Color(0xFFE2E8E2)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

                            ) {

                                Image(

                                    painter = painterResource(id = R.drawable.basket_weaving),
                                    contentDescription = "Basket Weaving",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(16f / 9f),
                                    contentScale = ContentScale.Crop

                                )

                                Column(

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)

                                ) {

                                    Text(

                                        text = "Woven Basket Set",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(

                                        text = "Beautiful handmade item supporting our artisans.",
                                        fontSize = 14.sp

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(

                                        text = "⏱ Made to order · Ready in 2 days",
                                        color = Color(0xffd8a13a),
                                        fontSize = 14.sp

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Row(

                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically

                                    ) {

                                        Text(

                                            text = "R280",
                                            fontSize = 20.sp,
                                            color = Color((0xff1f6f4a)),
                                            fontWeight = FontWeight.Bold

                                        )

                                        IconButton(

                                            onClick = { TODO() },
                                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xff1f6f4a))

                                        ) {

                                            Icon(

                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Add to Cart",
                                                tint = Color.White

                                            )

                                        }

                                    }

                                }

                            }

                            Card(

                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FBF9)),
                                border = BorderStroke(1.dp, Color(0xFFE2E8E2)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

                            ) {

                                Image(

                                    painter = painterResource(id = R.drawable.basket_weaving),
                                    contentDescription = "Basket Weaving",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(16f / 9f),
                                    contentScale = ContentScale.Crop

                                )

                                Column(

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)

                                ) {

                                    Text(

                                        text = "Decorative Wall Art",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(

                                        text = "Beautiful handmade item supporting our artisans.",
                                        fontSize = 14.sp

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(

                                        text = "⏱ Made to order · Ready in 2 days",
                                        color = Color(0xffd8a13a),
                                        fontSize = 14.sp

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Row(

                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically

                                    ) {

                                        Text(

                                            text = "R350",
                                            fontSize = 20.sp,
                                            color = Color((0xff1f6f4a)),
                                            fontWeight = FontWeight.Bold

                                        )

                                        IconButton(

                                            onClick = { TODO() },
                                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xff1f6f4a))

                                        ) {

                                            Icon(

                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Add to Cart",
                                                tint = Color.White

                                            )

                                        }

                                    }

                                }

                            }

                            Card(

                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FBF9)),
                                border = BorderStroke(1.dp, Color(0xFFE2E8E2)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

                            ) {

                                Image(

                                    painter = painterResource(id = R.drawable.basket_weaving),
                                    contentDescription = "Basket Weaving",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(16f / 9f),
                                    contentScale = ContentScale.Crop

                                )

                                Column(

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)

                                ) {

                                    Text(

                                        text = "Ceramic Bowl Collection",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(

                                        text = "Beautiful handmade item supporting our artisans.",
                                        fontSize = 14.sp

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(

                                        text = "⏱ Made to order · Ready in 2 days",
                                        color = Color(0xffd8a13a),
                                        fontSize = 14.sp

                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Row(

                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically

                                    ) {

                                        Text(

                                            text = "R320",
                                            fontSize = 20.sp,
                                            color = Color((0xff1f6f4a)),
                                            fontWeight = FontWeight.Bold

                                        )

                                        IconButton(

                                            onClick = { TODO() },
                                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xff1f6f4a))

                                        ) {

                                            Icon(

                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Add to Cart",
                                                tint = Color.White

                                            )

                                        }

                                    }

                                }

                            }

                        }

                    }

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
data object ShopDestination

fun NavGraphBuilder.shopScreen(

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

    composable<ShopDestination> {

        val authViewModel: AuthViewModel = viewModel()

        ShopScreen(

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

fun NavController.navigateToShop() {

    navigate(ShopDestination)

}

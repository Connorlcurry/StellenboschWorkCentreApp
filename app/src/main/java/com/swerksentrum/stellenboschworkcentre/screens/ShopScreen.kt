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
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
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

                        text = "Our Shop",
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
                        selected = true,
                        onClick = onNavigateToShop,
                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Shop", tint = Color(0xff2f8137)) },
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

                        Text(

                            text = "Handmade with Love",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xff2f8137)

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Every purchase supports our artisans and their journey to independence.")

                    }

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

                                    Row(

                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically

                                    ) {

                                        Text(

                                            text = "R450",
                                            fontSize = 20.sp,
                                            color = Color((0xff2f8137))

                                        )

                                        IconButton(

                                            onClick = { TODO() },
                                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xff2f8137))

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

                                    Row(

                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically

                                    ) {

                                        Text(

                                            text = "R280",
                                            fontSize = 20.sp,
                                            color = Color((0xff2f8137))

                                        )

                                        IconButton(

                                            onClick = { TODO() },
                                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xff2f8137))

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

                                    Row(

                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically

                                    ) {

                                        Text(

                                            text = "R350",
                                            fontSize = 20.sp,
                                            color = Color((0xff2f8137))

                                        )

                                        IconButton(

                                            onClick = { TODO() },
                                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xff2f8137))

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

                                    Row(

                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically

                                    ) {

                                        Text(

                                            text = "R320",
                                            fontSize = 20.sp,
                                            color = Color((0xff2f8137))

                                        )

                                        IconButton(

                                            onClick = { TODO() },
                                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xff2f8137))

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
    onNavigateToDonate: () -> Unit

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
            authViewModel = authViewModel

        )

    }

}

fun NavController.navigateToShop() {

    navigate(ShopDestination)

}

package com.swerksentrum.stellenboschworkcentre.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavigationDrawerContent(

    onNavigateToHome: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToContact: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToDonate: () -> Unit,
    onLogout: () -> Unit,
    onCloseDrawer: () -> Unit

) {

    ModalDrawerSheet {

        Column(

            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)

        ) {

            Text(

                text = "Stellenbosch Work Centre",
                modifier = Modifier.padding(16.dp)

            )

            HorizontalDivider()
            Spacer(modifier = Modifier.padding(8.dp))

            NavigationDrawerItem(

                label = { Text(text = "Home") },
                selected = false,
                onClick = {
                    onNavigateToHome()
                    onCloseDrawer()
                },

                icon = {

                    Icon(Icons.Default.Home,contentDescription = "Home")

                }

            )
            NavigationDrawerItem(

                label = { Text(text = "About") },
                selected = false,
                onClick = {
                    onNavigateToAbout()
                    onCloseDrawer()
                },

                icon = {

                    Icon(Icons.Default.Info, contentDescription = "About")

                }

            )
            NavigationDrawerItem(

                label = { Text(text = "Services") },
                selected = false,
                onClick = {
                    onNavigateToServices()
                    onCloseDrawer()
                },

                icon = {

                    Icon(Icons.Default.List, contentDescription = "Services")

                }

            )

            NavigationDrawerItem(

                label = { Text(text = "Shop") },
                selected = false,
                onClick = {
                    onNavigateToShop()
                    onCloseDrawer()
                },

                icon = {

                    Icon(Icons.Default.ShoppingCart, contentDescription = "Shop")

                }

            )

            NavigationDrawerItem(

                label = { Text(text = "Contact") },
                selected = false,
                onClick = {
                    onNavigateToContact()
                    onCloseDrawer()
                },

                icon = {

                    Icon(Icons.Default.Email, contentDescription = "Contact")

                }

            )

            NavigationDrawerItem(

                label = { Text(text = "AI Support") },
                selected = false,
                onClick = {
                    onNavigateToChatbot()
                    onCloseDrawer()
                },

                icon = {

                    Icon(Icons.Default.Face, contentDescription = "AI Support")

                }

            )

            NavigationDrawerItem(

                label = { Text(text = "Donate") },
                selected = false,
                onClick = {
                    onNavigateToDonate()
                    onCloseDrawer()
                },

                icon = {

                    Icon(Icons.Default.Favorite, contentDescription = "Donate")

                }

            )

            Spacer(modifier = Modifier.weight(1f))

            HorizontalDivider()

            NavigationDrawerItem(

                label = { Text(text = "Log Out") },
                selected = false,
                onClick = {
                    onLogout()
                    onCloseDrawer()
                },

                icon = {

                    Icon(Icons.Default.ExitToApp, contentDescription = "Logout")

                }

            )

        }

    }

}
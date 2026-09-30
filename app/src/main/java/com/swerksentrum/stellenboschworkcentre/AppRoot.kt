package com.swerksentrum.stellenboschworkcentre

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation3.runtime.NavKey
import com.swerksentrum.stellenboschworkcentre.screens.AboutDestination
import com.swerksentrum.stellenboschworkcentre.screens.AboutScreen
import com.swerksentrum.stellenboschworkcentre.screens.ChatbotDestination
import com.swerksentrum.stellenboschworkcentre.screens.ChatbotScreen
import com.swerksentrum.stellenboschworkcentre.screens.ContactDestination
import com.swerksentrum.stellenboschworkcentre.screens.ContactScreen
import com.swerksentrum.stellenboschworkcentre.screens.DonateDestination
import com.swerksentrum.stellenboschworkcentre.screens.DonateScreen
import com.swerksentrum.stellenboschworkcentre.screens.LoginDestination
import com.swerksentrum.stellenboschworkcentre.screens.ServicesDestination
import com.swerksentrum.stellenboschworkcentre.screens.ServicesScreen
import com.swerksentrum.stellenboschworkcentre.screens.ShopDestination
import com.swerksentrum.stellenboschworkcentre.screens.ShopScreen
import com.swerksentrum.stellenboschworkcentre.screens.aboutScreen
import com.swerksentrum.stellenboschworkcentre.screens.chatbotScreen
import com.swerksentrum.stellenboschworkcentre.screens.contactScreen
import com.swerksentrum.stellenboschworkcentre.screens.donateScreen
import com.swerksentrum.stellenboschworkcentre.screens.homeScreen
import com.swerksentrum.stellenboschworkcentre.screens.loginScreen
import com.swerksentrum.stellenboschworkcentre.screens.navigateToAbout
import com.swerksentrum.stellenboschworkcentre.screens.navigateToChatbot
import com.swerksentrum.stellenboschworkcentre.screens.navigateToContact
import com.swerksentrum.stellenboschworkcentre.screens.navigateToDonate
import com.swerksentrum.stellenboschworkcentre.screens.navigateToHome
import com.swerksentrum.stellenboschworkcentre.screens.navigateToLogin
import com.swerksentrum.stellenboschworkcentre.screens.navigateToRegister
import com.swerksentrum.stellenboschworkcentre.screens.navigateToServices
import com.swerksentrum.stellenboschworkcentre.screens.navigateToShop
import com.swerksentrum.stellenboschworkcentre.screens.registerScreen
import com.swerksentrum.stellenboschworkcentre.screens.servicesScreen
import com.swerksentrum.stellenboschworkcentre.screens.shopScreen
import kotlinx.serialization.Serializable

@Composable
fun AppRoot() {

    val navController = rememberNavController()

    val onNavigateToLogin = { navController.navigateToLogin() }
    val onNavigateToRegister = { navController.navigateToRegister() }
    val onNavigateToHome = { navController.navigateToHome() }
    val onNavigateToAbout = { navController.navigateToAbout() }
    val onNavigateToServices = { navController.navigateToServices() }
    val onNavigateToShop = { navController.navigateToShop() }
    val onNavigateToContact = { navController.navigateToContact() }
    val onNavigateToChatbot = { navController.navigateToChatbot() }
    val onNavigateToDonate = { navController.navigateToDonate() }

    NavHost(

        navController = navController,
        startDestination = LoginDestination

    ) {

        loginScreen(

            onNavigateToHome = onNavigateToHome,
            onNavigateToRegister = onNavigateToRegister

        )

        registerScreen(

            onNavigateToHome = onNavigateToHome,
            onNavigateUp = { navController.navigateUp() },
            onNavigateToLogin = onNavigateToLogin

        )

        homeScreen(

            onNavigateToLogin = onNavigateToLogin,
            onNavigateToRegister = onNavigateToRegister,
            onNavigateToHome = onNavigateToHome,
            onNavigateToAbout = onNavigateToAbout,
            onNavigateToServices = onNavigateToServices,
            onNavigateToShop = onNavigateToShop,
            onNavigateToContact = onNavigateToContact,
            onNavigateToChatbot = onNavigateToChatbot,
            onNavigateToDonate = onNavigateToDonate

        )

        aboutScreen(

            onNavigateUp = { navController.navigateUp() },
            onNavigateToHome = onNavigateToHome,
            onNavigateToAbout = onNavigateToAbout,
            onNavigateToServices = onNavigateToServices,
            onNavigateToShop = onNavigateToShop,
            onNavigateToContact = onNavigateToContact,
            onNavigateToChatbot = onNavigateToChatbot,
            onNavigateToDonate = onNavigateToDonate

        )

        servicesScreen(

            onNavigateUp = { navController.navigateUp() },
            onNavigateToHome = onNavigateToHome,
            onNavigateToAbout = onNavigateToAbout,
            onNavigateToServices = onNavigateToServices,
            onNavigateToShop = onNavigateToShop,
            onNavigateToContact = onNavigateToContact,
            onNavigateToChatbot = onNavigateToChatbot,
            onNavigateToDonate = onNavigateToDonate

        )

        shopScreen(

            onNavigateUp = { navController.navigateUp() },
            onNavigateToHome = onNavigateToHome,
            onNavigateToAbout = onNavigateToAbout,
            onNavigateToServices = onNavigateToServices,
            onNavigateToShop = onNavigateToShop,
            onNavigateToContact = onNavigateToContact,
            onNavigateToChatbot = onNavigateToChatbot,
            onNavigateToDonate = onNavigateToDonate

        )

        contactScreen(

            onNavigateUp = { navController.navigateUp() },
            onNavigateToHome = onNavigateToHome,
            onNavigateToAbout = onNavigateToAbout,
            onNavigateToServices = onNavigateToServices,
            onNavigateToShop = onNavigateToShop,
            onNavigateToContact = onNavigateToContact,
            onNavigateToChatbot = onNavigateToChatbot,
            onNavigateToDonate = onNavigateToDonate

        )

        chatbotScreen(

            onNavigateUp = { navController.navigateUp() },
            onNavigateToHome = onNavigateToHome,
            onNavigateToAbout = onNavigateToAbout,
            onNavigateToServices = onNavigateToServices,
            onNavigateToShop = onNavigateToShop,
            onNavigateToContact = onNavigateToContact,
            onNavigateToChatbot = onNavigateToChatbot,
            onNavigateToDonate = onNavigateToDonate,

        )

        donateScreen(

            onNavigateUp = { navController.navigateUp() },
            onNavigateToHome = onNavigateToHome,
            onNavigateToAbout = onNavigateToAbout,
            onNavigateToServices = onNavigateToServices,
            onNavigateToShop = onNavigateToShop,
            onNavigateToContact = onNavigateToContact,
            onNavigateToChatbot = onNavigateToChatbot,
            onNavigateToDonate = onNavigateToDonate,

        )

    }

}
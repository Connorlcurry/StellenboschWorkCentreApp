package com.swerksentrum.stellenboschworkcentre.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.painterResource
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
import com.swerksentrum.stellenboschworkcentre.R
import com.swerksentrum.stellenboschworkcentre.components.NavigationDrawerContent
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAccountScreen(

    onNavigateUp: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToContact: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToDonate: () -> Unit,
    onNavigateToAccount: () -> Unit,
    onLogout: () -> Unit,
    authViewModel: AuthViewModel

) {

    val auth = FirebaseAuth.getInstance()
    val authState by authViewModel.authState.observeAsState()
    val db = FirebaseFirestore.getInstance()
    val currentUser = auth.currentUser

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneNum by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Unauthenticated -> onNavigateToLogin()
            else -> Unit
        }
    }

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

    // Collects the user's last name from the Firestore database
    LaunchedEffect(currentUser) {

        if (currentUser != null) {

            db.collection("users").document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        lastName = document.getString("lastName") ?: ""
                    }
                }

        }

    }

    // Collects the user's email from the Firestore database
    LaunchedEffect(currentUser) {

        if (currentUser != null) {

            db.collection("users").document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        email = document.getString("email") ?: ""
                    }
                }

        }

    }

    // Collects the user's password from the Firestore database
    LaunchedEffect(currentUser) {

        if (currentUser != null) {

            db.collection("users").document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        password = document.getString("password") ?: ""
                    }
                }

        }

    }

    // Collects the user's phone number from the Firestore database
    LaunchedEffect(currentUser) {

        if (currentUser != null) {

            db.collection("users").document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        phoneNum = document.getString("phoneNum") ?: ""
                    }
                }

        }

    }

    // Collects the user's address from the Firestore database
    LaunchedEffect(currentUser) {

        if (currentUser != null) {

            db.collection("users").document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        address = document.getString("address") ?: ""
                    }
                }

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

                            text = "My Account",
                            color = Color(0xffd8a13a),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold

                        )

                    },

                    actions = {

                        Row {

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
                        icon = {
                            Icon(Icons.Default.Home,contentDescription = "Home")},
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
                        icon = {Icon(Icons.Default.DesignServices, contentDescription = "Services") },
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
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.Start

                ) {

                    Text(

                        text = if (firstName.isNotBlank())
                            "Welcome, $firstName"
                        else "Welcome",
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 20.sp

                    )

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
                        horizontalAlignment = Alignment.Start

                    ) {

                        Text(

                            text = "Account Details",
                            color = Color(0xff1f6f4a),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold

                        )

                        Spacer(modifier = Modifier.height(4.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(4.dp))

                        Column(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp),
                            horizontalAlignment = Alignment.Start

                        ) {

                            Text(

                                text = "Full Name:",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold

                            )

                            Row(

                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween

                            ) {

                                Text(

                                    text = "$firstName $lastName"

                                )

                                Icon(

                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit",
                                    modifier = Modifier.size(20.dp)

                                )

                            }

                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(4.dp))

                        Column(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp),
                            horizontalAlignment = Alignment.Start

                        ) {

                            Text(

                                text = "Email:",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold

                            )

                            Row(

                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween

                            ) {

                                Text(

                                    text = email

                                )

                                Icon(

                                    imageVector = Icons.Default.Email,
                                    contentDescription = "Email",
                                    modifier = Modifier.size(20.dp)

                                )

                            }

                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(4.dp))

                        Column(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp),
                            horizontalAlignment = Alignment.Start

                        ) {

                            Text(

                                text = "Password:",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold

                            )

                            Row(

                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween

                            ) {

                                Text(

                                    text = password

                                )

                                Icon(

                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit",
                                    modifier = Modifier.size(20.dp)

                                )

                            }

                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(4.dp))

                        Column(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp),
                            horizontalAlignment = Alignment.Start

                        ) {

                            Text(

                                text = "Phone:",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold

                            )

                            Row(

                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween

                            ) {

                                Text(

                                    text = phoneNum

                                )

                                Icon(

                                    imageVector = Icons.Default.Phone,
                                    contentDescription = "Phone",
                                    modifier = Modifier.size(20.dp)

                                )

                            }

                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(4.dp))

                        Column(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp),
                            horizontalAlignment = Alignment.Start

                        ) {

                            Text(

                                text = "Address:",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold

                            )

                            Row(

                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween

                            ) {

                                Text(

                                    text = address

                                )

                                Icon(

                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = "Location",
                                    modifier = Modifier.size(20.dp)

                                )

                            }

                        }

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
                        horizontalAlignment = Alignment.Start

                    ) {

                        Text(

                            text = "My Orders",
                            color = Color(0xff1f6f4a),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(

                            text = "No orders yet.",
                            fontSize = 14.sp,
                            color = Color.Gray

                        )

                    }

                }

                Column(

                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    TextButton (

                        onClick = {

                            onLogout()
                            onNavigateToLogin()

                        }

                    ) {

                        Row(

                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            Icon(

                                Icons.Default.ExitToApp, contentDescription = "Logout", tint = Color.Red

                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(

                                text = "Logout",
                                fontSize = 14.sp,
                                color = Color.Red

                            )

                        }

                    }

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
data object UserAccountDestination

fun NavGraphBuilder.userAccountScreen(

    onNavigateUp: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateToContact: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToDonate: () -> Unit,
    onNavigateToAccount: () -> Unit

) {

    composable<UserAccountDestination> {

        val authViewModel: AuthViewModel = viewModel()

        UserAccountScreen(

            onNavigateUp = onNavigateUp,
            onNavigateToLogin = onNavigateToLogin,
            onNavigateToHome = onNavigateToHome,
            onNavigateToAbout = onNavigateToAbout,
            onNavigateToServices = onNavigateToServices,
            onNavigateToShop = onNavigateToShop,
            onNavigateToContact = onNavigateToContact,
            onNavigateToChatbot = onNavigateToChatbot,
            onNavigateToDonate = onNavigateToDonate,
            onNavigateToAccount = onNavigateToAccount,
            onLogout = { authViewModel.logout() },
            authViewModel = authViewModel

        )

    }

}

fun NavController.navigateToAccount() {

    navigate(UserAccountDestination)

}
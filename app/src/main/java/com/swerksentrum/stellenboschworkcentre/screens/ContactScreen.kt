package com.swerksentrum.stellenboschworkcentre.screens

import android.content.Context
import android.content.Intent
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
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.swerksentrum.stellenboschworkcentre.AuthViewModel
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
    onNavigateToAccount: () -> Unit,
    authViewModel: AuthViewModel

) {

    val senderName = remember { mutableStateOf(TextFieldValue()) }
    val senderEmail = remember { mutableStateOf(TextFieldValue()) }
    val emailSubject = remember { mutableStateOf(TextFieldValue()) }
    val emailMessage = remember { mutableStateOf(TextFieldValue()) }

    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var hasLaunchedEmailApp by remember { mutableStateOf(false) }

    val lifecycleOwner = LocalLifecycleOwner.current

    // This monitors when the user returns to the contact screen from an external app
    // This will therefore prompt the success message to display when the user sends an email
    DisposableEffect(lifecycleOwner) {

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (hasLaunchedEmailApp) {
                    hasLaunchedEmailApp = false
                    showSuccessDialog = true
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }

    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    if (showErrorDialog) {

        AlertDialog(

            onDismissRequest = { showErrorDialog = false },
            title = { Text("Unable to Send Email") },
            text = { Text(errorMessage) },
            confirmButton = {

                Button(onClick = { showErrorDialog = false }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xff1f6f4a))) {

                    Text("OK")

                }

            }

        )

    }

    if (showSuccessDialog) {

        AlertDialog(

            onDismissRequest = { showSuccessDialog = false },
            title = { Text("Message Status") },
            text = { Text("Welcome back! If you successfully sent your message, thank you for reaching out. We will get back to you as soon as we can.") },
            confirmButton = {

                Button(onClick = {

                    showSuccessDialog = false
                    senderName.value = TextFieldValue()
                    senderEmail.value = TextFieldValue()
                    emailSubject.value = TextFieldValue()
                    emailMessage.value = TextFieldValue()

                }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xff1f6f4a))) {

                    Text("OK")

                }

            }

        )

    }

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

                            text = "Contact Us",
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
                        selected = false,
                        onClick = onNavigateToShop,
                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Shop") },
                        label = { Text("Shop") }
                    )
                    NavigationBarItem(
                        selected = true,
                        onClick = onNavigateToContact,
                        icon = { Icon(Icons.Default.Email, contentDescription = "Contact", tint = Color(0xff1f6f4a)) },
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

                        text = "Get In Touch",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff1f6f4a)

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(

                        text = "Have questions? We'd love to hear from you. Send us a message and we'll respond as soon as possible.",
                        fontSize = 15.sp

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
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Text(

                            text = "Send Us a Message",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xff1f6f4a)

                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        OutlinedTextField(

                            value = senderName.value,
                            onValueChange = { senderName.value = it },
                            label = { Text("Full Name") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xff1f6f4a),
                                focusedLabelColor = Color(0xff1f6f4a)
                            )

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(

                            value = senderEmail.value,
                            onValueChange = { senderEmail.value = it },
                            label = { Text("Email") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xff1f6f4a),
                                focusedLabelColor = Color(0xff1f6f4a)
                            )

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(

                            value = emailSubject.value,
                            onValueChange = { emailSubject.value = it },
                            label = { Text("Subject") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xff1f6f4a),
                                focusedLabelColor = Color(0xff1f6f4a)
                            )

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(

                            value = emailMessage.value,
                            onValueChange = { emailMessage.value = it },
                            label = { Text("Message") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xff1f6f4a),
                                focusedLabelColor = Color(0xff1f6f4a)
                            )

                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Button(onClick = {

                                sendEmailIntent(

                                    context = context,
                                    recipient = "conleecurry@gmail.com",
                                    subject = emailSubject.value.text,
                                    message = emailMessage.value.text,
                                    onError = { msg ->
                                        errorMessage = msg
                                        showErrorDialog = true
                                    },
                                    onSuccess = {
                                        hasLaunchedEmailApp = true
                                    }

                                )

                            },

                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff1f6f4a))

                        ) {

                            Text(

                                text = "Send Email",
                                modifier = Modifier.padding(10.dp),
                                color = Color.White,
                                fontSize = 15.sp

                            )

                        }

                    }

                }

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Text(

                        text = "Contact Information",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff1f6f4a)

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(

                        horizontalAlignment = Alignment.Start

                    ) {

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

fun sendEmailIntent(

    context: Context,
    recipient: String,
    subject: String,
    message: String,
    onError: (String) -> Unit,
    onSuccess: () -> Unit

) {

    if (subject.isBlank() || message.isBlank()) {
        onError("Please fill in both the subject and message fields.")
        return
    }

    val intent = Intent(Intent.ACTION_SEND).apply {

        type = "message/rfc822"
        putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, message)

    }

    try {

        context.startActivity(Intent.createChooser(intent, "Select Email Client: "))
        onSuccess()

    } catch(_: android.content.ActivityNotFoundException) {

        onError("No email apps installed on this device.")

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
    onNavigateToDonate: () -> Unit,
    onNavigateToAccount: () -> Unit

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
            onNavigateToAccount = onNavigateToAccount,
            authViewModel = authViewModel

        )

    }

}

fun NavController.navigateToContact() {

    navigate(ContactDestination)

}

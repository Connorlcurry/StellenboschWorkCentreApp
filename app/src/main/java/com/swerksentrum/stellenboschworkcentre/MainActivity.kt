package com.swerksentrum.stellenboschworkcentre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.swerksentrum.stellenboschworkcentre.data.database.AppDatabase
import com.swerksentrum.stellenboschworkcentre.ui.theme.StellenboschWorkCentreTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialise database
        val db = AppDatabase.getDatabase(applicationContext)
        val userDao = db.userDao()

    }
}

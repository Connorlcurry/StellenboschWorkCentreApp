package com.swerksentrum.stellenboschworkcentre.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.swerksentrum.stellenboschworkcentre.data.database.dao.*
import com.swerksentrum.stellenboschworkcentre.data.database.entities.*

@Database(entities = [

    // Lists all the data classes/tables
    User::class,
    Registration::class,
    Donation::class,
    Comment::class,
    AIChatHistory::class,
    Service::class,
    ServiceRequest::class,
    Order::class,
    OrderItem::class,
    Product::class

], version = 1, exportSchema = false)

// Abstract class that provides the database connection
abstract class AppDatabase : RoomDatabase() {

    // Return all the DAO interface containing SQL queries for their respective entity/data class
    abstract fun userDao(): UserDao
    abstract fun registrationDao(): RegistrationDao
    abstract fun donationDao(): DonationDao
    abstract fun commentDao(): CommentDao
    abstract fun aiChatHistory(): AIChatHistoryDao
    abstract fun serviceDao(): ServiceDao
    abstract fun serviceRequestDao(): ServiceRequestDao
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao(): OrderItemDao
    abstract fun productDao(): ProductDao

    companion object {

        @Volatile // Changes to INSTANCE are immediately visible
        private var INSTANCE: AppDatabase? = null // Stores single database instance

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                // If the database doesn't exist, the database is created.
                val instance = Room.databaseBuilder(
                    context.applicationContext, // Use app context to prevent memory leaks
                    AppDatabase::class.java, // Database class
                    "swc_database" // Database filename
                ).build()

                INSTANCE = instance
                instance

            }

        }

    }
}

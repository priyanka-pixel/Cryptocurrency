package com.example.cryptocurrency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrency.core.Screen
import com.example.cryptocurrency.presentation.coin_detail.components.CoinDetailScreen
import com.example.cryptocurrency.presentation.coin_list.components.CoinListScreen
import com.example.cryptocurrency.ui.theme.CryptocurrencyTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(
                            route = Screen.CoinListScreen.route
                        ) {
                            CoinListScreen(navController = navController, modifier = Modifier)
                        }
                        composable(
                            route = Screen.CoinDetailScreen.route + "/{id}"
                        ) {
                            CoinDetailScreen(navController = navController)

                        }
                    }
                }
            }
        }
    }
}

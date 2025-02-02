package org.d3if0006.converterbajurevisi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if0006.converterbajurevisi.ui.screen.AboutScreen
import org.d3if0006.converterbajurevisi.ui.screen.MainScreen
//commit
@Composable
fun SetupNavGraph(navController: NavHostController= rememberNavController()) {
    NavHost(navController = navController,
        startDestination = Screen.Home.route)
    {
        composable(route=Screen.Home.route){
            MainScreen(navController)
        }
        composable(route=Screen.About.route){
            AboutScreen(navController)
        }

    }

}
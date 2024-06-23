package org.d3if0006.converterbajurevisi.navigation

sealed class Screen (val route:String){
    data object Home :Screen("mainscreen")
    data object About: Screen("AboutScreen")
}
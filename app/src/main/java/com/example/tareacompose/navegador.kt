package com.example.tareacompose

import android.provider.ContactsContract.Profile
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
object Login
@Serializable
//object Calculadora
data class Calculadora(val correo:String)

@Composable
fun navegador(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login>{
            loginScreen{correo ->
                navController.navigate(route = Calculadora(correo=correo))
            }
        }
        composable<Calculadora> {backStackEntry ->
            val parametro: Calculadora = backStackEntry.toRoute()
            calculadoraScreen(parametro.correo) {
                navController.navigate(Login){
                    popUpTo<Login>{
                        inclusive = true
                    }
                }
            }
        }
    }
}
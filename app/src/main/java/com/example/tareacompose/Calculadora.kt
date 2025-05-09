package com.example.tareacompose

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tareacompose.ui.theme.azulFuerte
import androidx.compose.ui.platform.LocalContext
import java.lang.NumberFormatException

@Composable
fun calculadoraScreen(correo: String, navLogin: () -> Unit) {
    encabezadoLogin1(correo, navLogin)
    cuerpoLogin1(correo, navLogin)
}

@Composable
fun encabezadoLogin1(correo: String, navLogin: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = "Doodle",
            color = azulFuerte,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f)
        )

        Text(
            text = correo,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 15.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "icono",
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )
            TextButton(
                onClick = { navLogin() },
                modifier = Modifier
                    .width(70.dp)
            ) {
                Text(
                    text = "Salir",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun cuerpoLogin1(correo: String, navLogin: () -> Unit) {
    var numeroIngresado by remember { mutableStateOf("") }
    var numeroIngresado2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    val context = LocalContext.current

    fun performOperation(operation: (Double, Double) -> Double) {
        try {
            val num1 = numeroIngresado.toDouble()
            val num2 = numeroIngresado2.toDouble()
            resultado = operation(num1, num2).toString()
        } catch (e: NumberFormatException) {
            Toast.makeText(context, "Ingrese números válidos", Toast.LENGTH_SHORT).show()
            resultado = ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
            .padding(top = 90.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 5.dp)
        )
        Text(
            text = "Usuario:$correo",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 20.dp)
        )
        TextField(
            value = numeroIngresado,
            onValueChange = { numeroIngresado = it },
            placeholder = { Text(text = "Ingrese un número") },
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, shape = RoundedCornerShape(25.dp), color = Color.Gray),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = numeroIngresado2,
            onValueChange = { numeroIngresado2 = it },
            placeholder = { Text(text = "Ingrese un número") },
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, shape = RoundedCornerShape(25.dp), color = Color.Gray),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { performOperation { a, b -> a + b } },
                modifier = Modifier
                    .width(160.dp)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text(
                    text = "Sumar",
                    fontSize = 20.sp
                )
            }
            Button(
                onClick = { performOperation { a, b -> a - b } },
                modifier = Modifier
                    .width(160.dp)
                    .padding(start = 8.dp),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text(
                    text = "Restar",
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { performOperation { a, b -> a / b } },
                modifier = Modifier
                    .width(160.dp)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text(
                    text = "Dividir",
                    fontSize = 20.sp
                )
            }
            Button(
                onClick = { performOperation { a, b -> a * b } },
                modifier = Modifier
                    .width(160.dp)
                    .padding(start = 8.dp),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text(
                    text = "Multiplicar",
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Resultado",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 5.dp)
            )
            TextField(
                value = resultado,
                onValueChange = {}, // Indicate that no changes are allowed
                enabled = false,
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .width(200.dp)
                    .border(width = 0.5.dp, shape = RoundedCornerShape(25.dp), color = Color.Gray),
                colors = TextFieldDefaults.colors(
                    disabledIndicatorColor = Color.Transparent,
                    disabledLeadingIconColor = Color.Transparent,
                    disabledTrailingIconColor = Color.Transparent,
                    disabledTextColor = Color.Black, // Ensure text is readable when disabled
                    disabledContainerColor = Color.White // Keep the background white
                ),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navLogin() },
                modifier = Modifier
                    .width(160.dp)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text(
                    text = "Salir",
                    fontSize = 20.sp
                )
            }
        }
    }
}

/*@Preview(showSystemUi = true)
@Composable
fun prevewLogin1() {
    calculadoraScreen(correo = "preview@example.com", navLogin = {})
}*/
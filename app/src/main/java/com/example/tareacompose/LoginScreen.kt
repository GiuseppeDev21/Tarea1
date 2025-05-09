package com.example.tareacompose

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tareacompose.ui.theme.azulFuerte

fun isValidPassword(password: String): Boolean {
    if (password.length <= 8) return false
    if (!password.any { it.isUpperCase() }) return false
    if (!password.any { it.isLowerCase() }) return false
    if (!password.any { it.isDigit() }) return false
    if (!password.contains('_')) return false
    return true
}

@Composable
fun loginScreen(navCalculadora: (String) -> Unit) {
    encabezadoLogin()
    cuerpoLogin(navCalculadora)
}

@Composable
fun encabezadoLogin() {
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
            text = "Usuario",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 15.dp)
        )
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "icono",
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
        )
    }
}

@Composable
fun cuerpoLogin(navCalculadora: (String) -> Unit) {
    var correo by remember { mutableStateOf("") }
    var contra by remember { mutableStateOf("") }
    val contexto = LocalContext.current
    var passShow by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_manage_accounts_24),
            contentDescription = "icono",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            tint = Color.Gray
        )
        Text(
            text = "ACCESO | LOGIN",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(40.dp))

        TextField(
            value = correo,
            onValueChange = { correo = it },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "email") },
            placeholder = { Text(text = "Correo Electronico") },
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
            value = contra,
            onValueChange = { contra = it },
            leadingIcon = { Icon(painter = painterResource(R.drawable.baseline_key_24), contentDescription = "icono contraseña") },
            placeholder = { Text(text = "Contraseña") },
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
            maxLines = 1,

            visualTransformation = if (passShow) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val iconoShow: Painter =
                    if (passShow) painterResource(R.drawable.baseline_visibility_off_24)
                    else painterResource(R.drawable.baseline_visibility_24)
                IconButton(
                    onClick = {
                        passShow = !passShow
                    }
                ) {
                    Icon(
                        painter = iconoShow,
                        contentDescription = "Icono visibilidad"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (correo.isNotBlank() && isValidPassword(contra)) {
                    navCalculadora(correo)
                } else {
                    val errorMessage = if (correo.isBlank()) {
                        "Ingrese un correo electrónico"
                    } else if (!isValidPassword(contra)) {
                        "La contraseña no cumple con los requisitos"
                    } else {
                        "Correo y contraseña inválidos" // Should not happen if individual checks are correct
                    }
                    Toast.makeText(contexto, errorMessage, Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier
                .width(400.dp)
        ) {
            Text(
                text = "Entrar",
                fontSize = 20.sp
            )
        }
    }
}

/*@Preview(showSystemUi = true)
@Composable
fun previewLogin() {
    loginScreen {}
}*/
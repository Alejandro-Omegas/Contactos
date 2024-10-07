package com.alejandroarriola.contactos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandroarriola.contactos.ui.theme.ContactosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactosTheme {
                AplicacionDeContactos()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaPreviewAplicacionDeContactos() {
    ContactosTheme {
        AplicacionDeContactos()
    }
}

@Composable
fun AplicacionDeContactos() {
    var contactos by remember { mutableStateOf(listOf<Contacto>())}
    var nombre by remember { mutableStateOf("")}
    var direccion by remember { mutableStateOf("")}
    var telefono by remember { mutableStateOf("")}
    var email by remember { mutableStateOf("")}
    var metodoPreferido by remember { mutableStateOf(MetodoContactoPreferido.TELEFONO)}

    Column(modifier = Modifier.padding(16.dp)) {
        FormularioDeContacto(
            nombre = nombre,
            direccion = direccion,
            telefono = telefono,
            email = email,
            metodoPreferido = metodoPreferido,
            onNombreChange = { nombre = it },
            onDireccionChange = { direccion = it },
            onTelefonoChange = { telefono = it },
            onEmailChange = { email = it },
            onMetodoPreferidoChange = { metodoPreferido = it },
            onAgregarContacto = {
                val nuevoContacto = Contacto(nombre, direccion, telefono, email, metodoPreferido)
                contactos = contactos + nuevoContacto
                nombre = ""
                direccion = ""
                telefono = ""
                email = ""
                metodoPreferido = MetodoContactoPreferido.TELEFONO
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ListaDeContactos(contactos = contactos)
    }
}

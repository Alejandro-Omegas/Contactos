package com.alejandroarriola.contactos

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FormularioDeContacto(
    nombre: String,
    direccion: String,
    telefono: String,
    email: String,
    metodoPreferido: MetodoContactoPreferido,
    onNombreChange: (String) -> Unit,
    onDireccionChange: (String) -> Unit,
    onTelefonoChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onMetodoPreferidoChange: (MetodoContactoPreferido) -> Unit,
    onAgregarContacto: () -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            "Ingrese un contacto",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = direccion,
            onValueChange = onDireccionChange,
            label = { Text("Direccion") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = telefono,
            onValueChange = onTelefonoChange,
            label = { Text("Telefono") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column() {
            Text("Metodo de contacto preferido:")

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = metodoPreferido == MetodoContactoPreferido.TELEFONO,
                    onClick = { onMetodoPreferidoChange(MetodoContactoPreferido.TELEFONO) }
                )

                Text("Telefono")

                Spacer(modifier = Modifier.height(8.dp))

                RadioButton(
                    selected = metodoPreferido == MetodoContactoPreferido.EMAIL,
                    onClick = { onMetodoPreferidoChange(MetodoContactoPreferido.EMAIL) }
                )

                Text("Email")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onAgregarContacto,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Agregar contacto")
        }
    }
}

@Composable
fun ListaDeContactos(contactos: List<Contacto>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(contactos) { contacto ->
            TarjetaDeContacto(contacto = contacto)
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun TarjetaDeContacto(contacto: Contacto) {
    Card(
       modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = contacto.nombre,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            BoxWithConstraints(
                modifier = Modifier.fillMaxWidth().height(120.dp)
            ) {
                val anchoPantalla = maxWidth

                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item{ InfoContacto(titulo = "Direccion",valor = contacto.direccion,ancho = anchoPantalla)}
                    item{ InfoContacto(titulo = "Telefono",valor = contacto.telefono,ancho = anchoPantalla)}
                    item{ InfoContacto(titulo = "Email",valor = contacto.email,ancho = anchoPantalla)}
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Metodo preferido: ${if(contacto.metodoPreferido == MetodoContactoPreferido.TELEFONO) "Telefono" else "Email"}")
        }
    }
}

@Composable
fun InfoContacto(titulo: String, valor: String, ancho: Dp) {
    Column(
        modifier = Modifier
            .width(ancho)
            .fillMaxWidth()
            .padding(end = 32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = titulo, style = MaterialTheme.typography.titleSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = valor, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))
    }
}

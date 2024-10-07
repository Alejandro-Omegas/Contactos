package com.alejandroarriola.contactos

enum class MetodoContactoPreferido{
    TELEFONO, EMAIL
}

data class Contacto (
    val nombre: String,
    val direccion: String,
    val telefono: String,
    val email: String,
    val metodoPreferido: MetodoContactoPreferido
)
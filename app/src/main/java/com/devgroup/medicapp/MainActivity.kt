package com.devgroup.medicapp

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ¡Esta es la línea clave! Le dice a la actividad que cargue tu diseño XML.
        // Tienes que elegir el layout XML que deseas mostrar.
        // Si quieres mostrar el registro, y tu layout es fragment_registro.xml:
        setContentView(R.layout.fragment_registro)

        // Si quieres mostrar el login:
        // setContentView(R.layout.activity_login)

        // Si quieres mostrar el home:
        // setContentView(R.layout.activity_home)
    }
}

// Ya no necesitas las funciones @Composable (Greeting y GreetingPreview) en esta clase.

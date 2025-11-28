package com.devgroup.medicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.devgroup.medicapp.PastilleroActivity;
import com.devgroup.medicapp.TurnosActivity;

public class HomeActivity extends AppCompatActivity {

    // Declaración de los ImageView que funcionan como botones
    private ImageView imageViewMedicina;
    private ImageView imageViewMisTurnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 1. Inicializar las vistas (botones)
        // Usamos los IDs del XML: imageView_medicina e imageView_mis_turnos
        imageViewMedicina = findViewById(R.id.imageView_medicina);
        imageViewMisTurnos = findViewById(R.id.imageView_mis_turnos);

        // 2. Establecer los Listeners de Clic
        // Lógica para el botón MEDICINA (abre Pastillero)
        imageViewMedicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navega a la Activity del Pastillero
                abrirActivity(PastilleroActivity.class);
            }
        });

        // Lógica para el botón MIS TURNOS (abre Turnos)
        imageViewMisTurnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navega a la Activity de Turnos
                abrirActivity(TurnosActivity.class);
            }
        });

        // Nota: Los botones restantes (Home, Perfil, Laboratorio, etc.)
        // pueden ser configurados de manera similar aquí.
    }

    /**
     * Método auxiliar para manejar la navegación con Intent explícito.
     * @param activityClass La clase de la Activity a iniciar.
     */
    private void abrirActivity(Class<?> activityClass) {
        // El Intent se crea desde la Activity actual (HomeActivity.this) hacia la Activity de destino.
        Intent intent = new Intent(HomeActivity.this, activityClass);
        startActivity(intent);
    }
}
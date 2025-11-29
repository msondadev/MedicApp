package com.devgroup.medicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.devgroup.medicapp.PastilleroActivity;
import com.devgroup.medicapp.TurnosActivity;
import com.devgroup.medicapp.PerfilActivity;

public class HomeActivity extends AppCompatActivity {

    // Declaración de los ImageView que funcionan como botones
    private ImageView imageViewMedicina;
    private ImageView imageViewMisTurnos;
    private ImageView imageViewHome;
    private ImageView imageViewPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 1. Inicializar las vistas (botones)
        imageViewMedicina = findViewById(R.id.imageView_medicina);
        imageViewMisTurnos = findViewById(R.id.imageView_mis_turnos);
        imageViewHome = findViewById(R.id.imageView_home);
        imageViewPerfil = findViewById(R.id.imageView_perfil);

        // 2. Establecer los Listeners de Clic

        // Botón MEDICINA (abre Pastillero)
        imageViewMedicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(PastilleroActivity.class);
            }
        });

        // Botón MIS TURNOS (abre Turnos)
        imageViewMisTurnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(TurnosActivity.class);
            }
        });

        // Botón HOME (abre HomeActivity)
        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(HomeActivity.class);
            }
        });

        // Botón PERFIL (abre PerfilActivity)
        imageViewPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(PerfilActivity.class);
            }
        });

        // Nota: Los botones restantes (Laboratorio, etc.)
        // pueden ser configurados de manera similar aquí.
    }

    /**
     * Método auxiliar para manejar la navegación con Intent explícito.
     * @param activityClass La clase de la Activity a iniciar.
     */
    private void abrirActivity(Class<?> activityClass) {
        Intent intent = new Intent(HomeActivity.this, activityClass);
        startActivity(intent);
    }
}


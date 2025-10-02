package com.devgroup.medicapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Animación de parpadeo para el texto "Cargando..."
        TextView textCargando = findViewById(R.id.textCargando);

        AlphaAnimation animacionParpadeo = new AlphaAnimation(0.0f, 1.0f);
        animacionParpadeo.setDuration(500); // medio segundo
        animacionParpadeo.setRepeatMode(Animation.REVERSE);
        animacionParpadeo.setRepeatCount(Animation.INFINITE);

        textCargando.startAnimation(animacionParpadeo);

        // Espera 2 segundos y pasa a LoginActivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}
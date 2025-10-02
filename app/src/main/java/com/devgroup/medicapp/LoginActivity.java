package com.devgroup.medicapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.ads.mediationtestsuite.activities.HomeActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editUsuario = findViewById(R.id.editUsuario);
        EditText editContrasena = findViewById(R.id.editContrasena);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegistro = findViewById(R.id.btnRegistro);

        //Acá espero al Sensei//
        btnRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        })


        btnLogin.setOnClickListener(v -> {
            String usuario = editUsuario.getText().toString().trim();
            String contrasena = editContrasena.getText().toString().trim();

            if (TextUtils.isEmpty(usuario) || TextUtils.isEmpty(contrasena)) {
                Toast.makeText(this, "Por favor completá ambos campos", Toast.LENGTH_SHORT).show();
            } else {
                // Simulación de login exitoso
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
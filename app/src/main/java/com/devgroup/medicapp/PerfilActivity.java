package com.devgroup.medicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Vinculamos la Activity con su layout XML
        setContentView(R.layout.activity_mi_perfil);
    }
}


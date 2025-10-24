package com.devgroup.medicapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// Importaciones para Google Sign-In y Firebase Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.ApiException;

// ❌ IMPORTACIÓN CORREGIDA ❌: Elimina la línea 'com.google.android.ads.mediationtestsuite.activities.HomeActivity;'
// Y asegura que tu propia HomeActivity esté en el mismo paquete.

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    // Asumo que tu botón de Google en el XML tiene el ID 'btnGoogleSignIn'
    private com.google.android.gms.common.SignInButton btnGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Obtener referencias de la UI
        EditText editUsuario = findViewById(R.id.editUsuario);
        EditText editContrasena = findViewById(R.id.editContrasena);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegistro = findViewById(R.id.btnRegistro);
        btnGoogleSignIn = (com.google.android.gms.common.SignInButton) findViewById(R.id.btnGoogleSignIn); // ID para el botón de Google

        // --- Configuración de Google Sign-In ---
        // El ID del string debe ser el ID de Cliente (Aplicación web) que creaste manualmente
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Listener para el botón de Google
        btnGoogleSignIn.setOnClickListener(v -> signInWithGoogle());

        // --- Flujo de Registro (Mantienes tu código original) ---
        btnRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
            startActivity(intent);
        });

        // --- Flujo de Login (Mantienes tu código original) ---
        btnLogin.setOnClickListener(v -> {
            String usuario = editUsuario.getText().toString().trim();
            String contrasena = editContrasena.getText().toString().trim();

            if (TextUtils.isEmpty(usuario) || TextUtils.isEmpty(contrasena)) {
                Toast.makeText(this, "Por favor completá ambos campos", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Aquí iría la llamada a mAuth.signInWithEmailAndPassword
                // Simulación de login exitoso
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // =========================================================
    // MÉTODOS DE GOOGLE SIGN-IN
    // =========================================================

    /** Inicia el flujo de autenticación de Google */
    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Resultado devuelto al iniciar el Intent de Google Sign-In
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign-In exitoso: ahora autenticamos con Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // El inicio de sesión de Google falló
                Toast.makeText(this, "Error de Google Sign-In: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    /** Usa el ID Token para iniciar sesión en Firebase */
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Autenticación con Firebase exitosa
                        Toast.makeText(LoginActivity.this, "¡Bienvenido con Google!", Toast.LENGTH_SHORT).show();
                        // Navegar a la pantalla principal
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        // Autenticación con Firebase falló
                        Toast.makeText(LoginActivity.this, "Error de Firebase: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
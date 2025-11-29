package com.devgroup.medicapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.SignInButton;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    private SignInButton btnGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Referencias UI
        EditText editUsuario = findViewById(R.id.editUsuario);
        EditText editContrasena = findViewById(R.id.editContrasena);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegistro = findViewById(R.id.btnRegistro);
        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn);

        // --- Configuración de Google Sign-In ---
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // ID de cliente web de Firebase
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Listener para Google Sign-In
        btnGoogleSignIn.setOnClickListener(v -> signInWithGoogle());

        // --- Flujo de Registro ---
        btnRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
            startActivity(intent);
        });

        // --- Flujo de Login con usuario/contraseña ---
        btnLogin.setOnClickListener(v -> {
            String usuario = editUsuario.getText().toString().trim();
            String contrasena = editContrasena.getText().toString().trim();

            if (TextUtils.isEmpty(usuario) || TextUtils.isEmpty(contrasena)) {
                Toast.makeText(this, "Por favor completá ambos campos", Toast.LENGTH_SHORT).show();
            } else {
                // Aquí iría mAuth.signInWithEmailAndPassword(usuario, contrasena)
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
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

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    firebaseAuthWithGoogle(account.getIdToken());
                } else {
                    Toast.makeText(this, "Cuenta de Google nula", Toast.LENGTH_LONG).show();
                }
            } catch (ApiException e) {
                Toast.makeText(this, "Error de Google Sign-In: " + e.getStatusCode(), Toast.LENGTH_LONG).show();
            }
        }
    }

    /** Usa el ID Token para iniciar sesión en Firebase */
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "¡Bienvenido con Google!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Error de Firebase: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}


package com.epicodus.meetuplist.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.meetuplist.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = LogInActivity.class.getSimpleName();

    @Bind(R.id.passwordLoginButton) Button mPasswordLoginButton;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.registerTextView) TextView mRegisterTextView;
    @Bind(R.id.unregisteredLoginButton) Button mUnregisteredLoginButton;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private ProgressDialog mAuthProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);
        mRegisterTextView.setOnClickListener(this);
        mPasswordLoginButton.setOnClickListener(this);
        mUnregisteredLoginButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        createAuthProgressDialog();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
       if (view == mRegisterTextView) {
           Intent intent = new Intent(LogInActivity.this, CreateAccountActivity.class);
           startActivity(intent);
           finish();
       }
       if (view == mUnregisteredLoginButton) {
           Intent intent = new Intent(LogInActivity.this, MainActivity.class);
           startActivity(intent);
           finish();
       }
       if (view == mPasswordLoginButton) {
           loginWithPassword();
       }
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating...");
        mAuthProgressDialog.setCancelable(false);
    }

    private void loginWithPassword() {
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        if (email.equals("")) {
            mEmailEditText.setError("Please enter your email");
            return;
        }
        if (password.equals("")) {
            mPasswordEditText.setError("Password cannot be blank");
            return;
        }

        mAuthProgressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mAuthProgressDialog.dismiss();
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}

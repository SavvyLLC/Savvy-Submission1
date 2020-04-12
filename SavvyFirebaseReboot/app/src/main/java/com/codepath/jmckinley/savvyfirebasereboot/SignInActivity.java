package com.codepath.jmckinley.savvyfirebasereboot;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class SignInActivity extends AppCompatActivity {

    EditText userEmail;
    EditText userPassword;

    Button signInButton;
    Button signUpButton;

    FirebaseAuth mAuth;

    final public String TAG = "SignInActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        this.userEmail = findViewById(R.id.signInEmail);
        this.userPassword = findViewById(R.id.signInPassword);
        this.signInButton = findViewById(R.id.signInButton);
        this.signUpButton = findViewById(R.id.newUserSignUpButton);

        mAuth = FirebaseAuth.getInstance();
        FirebaseAuth.getInstance();

        this.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkForRequredEntriesAreFilled())
                    signInUser();

            }
        });

        this.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startIntentToSignUp();

            }
        });
    }

    /*
   Makes sure everything that's required by the user is filled in
    */
    public boolean checkForRequredEntriesAreFilled(){
        //Checks String Entries
        //schoolName is talking about toString()
        if(this.userEmail.getText().toString() == "" || this.userPassword.getText().toString() == "" ){
            Toast.makeText(this, "An Entry was left blank", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    /*
    Starts the SignUpActivity Screen
     */
    public boolean startIntentToSignUp(){
        Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(i);


        return true;
    }

    public boolean signInUser(){
        mAuth.signInWithEmailAndPassword(this.userEmail.getText().toString(), this.userPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            //intent.putExtra(EXTRA_MESSAGE);
                            startActivity(intent);

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //pdateUI(null);
                            // ...

                        }

                        // ...
                    }
                });

        return true;

    }

}

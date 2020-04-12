package com.codepath.jmckinley.savvyfirebasereboot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText userEmail;
    EditText userPassword;

    Button selectSchoolbutton;
    Button signUpButton;

    TextView schoolName;

    RadioGroup radioGroup;
    RadioButton companyRadioButton;
    RadioButton studentRadioButton;

    private FirebaseFirestore db;


    final public static String TAG = "SignUpActivity";



    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.userEmail = findViewById(R.id.signUpEmail);
        this.userPassword = findViewById(R.id.signUpPassword);


        this.signUpButton = findViewById(R.id.signUpButton);



        this.radioGroup = findViewById(R.id.radioGroupSignup);
        this.companyRadioButton = findViewById(R.id.companyRadioButton);
        this.studentRadioButton = findViewById(R.id.studentRadioButton);

        EditText userBriefBio = findViewById(R.id.userBriefBio);

        //Hide the keyboard for input types
        userEmail.setInputType(0);
        userPassword.setInputType(0);
        userBriefBio.setInputType(0);

        Spinner spinner = findViewById(R.id.selectSchoolSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.schools, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

//        InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

        spinner.setOnItemSelectedListener(this);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        Map<String, Object> city = new HashMap<>();
        city.put("name", "Los Angeles");
        city.put("state", "CA");
        city.put("country", "USA");

        db = FirebaseFirestore.getInstance();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkForRequredEntriesAreFilled())
                    signUpNewUser();

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
        //Checks Radio Buttons
        /*
        Make sure one option selected for radio buttons
        Make sure both options aren't selected at same time
         */
        if(this.companyRadioButton.isChecked() && this.studentRadioButton.isChecked() || this.companyRadioButton.isChecked() == false && this.studentRadioButton.isChecked() == false){
            Toast.makeText(this, "You must select only to be either a company or student", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private boolean signUpNewUser(){

        mAuth.createUserWithEmailAndPassword(this.userEmail.getText().toString().trim(), this.userPassword.toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loadAdditionalInfoForUser();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Failed to Sign Up User");
            }
        });

        return true;
    }

    private boolean loadAdditionalInfoForUser(){

        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance();
        Map<String, Object> userAccountInfo = new HashMap<>();
        userAccountInfo.put("firstName", "Ada");
        userAccountInfo.put("lastName", "Lovelace");
        userAccountInfo.put("DOB", "01/01/98");

        if(this.companyRadioButton.isChecked())
            userAccountInfo.put("isCompany", false);
        else
            userAccountInfo.put("isCompany", true);

        userAccountInfo.put("schoolName", "Florida A&M University");


        Log.d(TAG, "Load AddtionalInfoForUser running");

        db.collection("test")
                .add(userAccountInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {


                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Added Document");
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                        //Activiate transition to MainActivity
                        startIntentToMainActivity();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Failed to add document");
                        Log.w(TAG, "Error adding document", e);
                    }
                }).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Log.d(TAG,"TASK WAS COMPLETE");
            }
        });

        Log.d(TAG, "Bottom of METHOD(LAST CALL)");

        return true;
    }

    public boolean startIntentToMainActivity(){
        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(i);

        return true;
    }

    @Override
    /*

    Get the text from spinner and display it for the user
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String itemText = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

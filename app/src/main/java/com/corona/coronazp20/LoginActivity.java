package com.corona.coronazp20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);

        Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View focusView) {
                // čia bus vykdomas kodas, kai paspaudžiamas mygtukas

                boolean cancel = false;
                focusView = null;

                if (isValid(username.getText().toString())) {
                    username.setError(getString(R.string.login_invalid_username));
                    cancel = true;
                }
                if (isValid(password.getText().toString())) {
                    password.setError(getString(R.string.login_invalid_password));
                    cancel = true;
                }
                if (cancel) {
                    focusView.requestFocus();
                }
                else {


                    Toast.makeText(LoginActivity.this,
                            username.getText().toString() + "\n" + password.getText().toString(),
                            Toast.LENGTH_SHORT).show();
                    // If logging is successful
                    Intent goToSearchActivity = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(goToSearchActivity);
                }




                username.setError(null);
                if(Validation.isValidUsername(username.getText().toString())) {
                    Intent goToSearchActivity = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(goToSearchActivity);
                } else { // jeigu username neteisingas
                    username.setError("Error! Wrong username");
                    username.requestFocus();
            }





    });

    }

    private boolean isValid(String credentials) {
        final String CREDENTIALS_PATTERN = "(a-zA-z){8,15}$" ;

        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);
        Matcher matcher = pattern.matcher(credentials);
        return matcher.matches();
    }

}

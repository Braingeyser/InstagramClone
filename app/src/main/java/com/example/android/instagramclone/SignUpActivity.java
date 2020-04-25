package com.example.android.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmailSignup;
    private EditText edtUsernameSignup;
    private EditText edtPasswordSignup;
    private Button btnSignup;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle(R.string.signup_activity_title);

        edtEmailSignup = findViewById(R.id.edtEmailLogin);
        edtUsernameSignup = findViewById(R.id.edtUsernameSignup);
        edtPasswordSignup = findViewById(R.id.edtPasswordLogin);
        btnSignup = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        //log out previous user on startup or we will get a session token exception
        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignup:

                if (edtEmailSignup.getText().toString().equals("") ||
                        edtUsernameSignup.getText().toString().equals("") ||
                        edtPasswordSignup.getText().toString().equals("")) {
                    FancyToast.makeText(SignUpActivity.this,
                            getString(R.string.message_incomplete_signup_fields),
                            Toast.LENGTH_SHORT,
                            FancyToast.INFO,
                            true).show();

                } else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmailSignup.getText().toString());
                    appUser.setUsername(edtUsernameSignup.getText().toString());
                    appUser.setPassword(edtPasswordSignup.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage(getString(R.string.message_prefix_sign_in_progress) + edtUsernameSignup.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUpActivity.this,
                                        appUser.getUsername() + R.string.message_suffix_signup_successful,
                                        Toast.LENGTH_SHORT,
                                        FancyToast.SUCCESS,
                                        true).show();
                            } else {
                                FancyToast.makeText(SignUpActivity.this,
                                        e.getMessage(),
                                        Toast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();

                        }
                    });
                }

                break;

            case R.id.btnLogin:
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}

package com.example.android.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

	private EditText edtUsernameSignUp;
	private EditText edtPasswordSignUp;
	private EditText edtUsernameLogin;
	private EditText edtPasswordLogin;

	private Button btnSignUp;
	private Button btnLogin;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up_login);

		edtUsernameSignUp = findViewById(R.id.edtUsernameSignUp);
		edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
		edtUsernameLogin = findViewById(R.id.edtUsernameLogin);
		edtPasswordLogin = findViewById(R.id.edtPasswordLogin);

		btnSignUp = findViewById(R.id.btnSignUp);
		btnLogin = findViewById(R.id.btnLogin);

		btnSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				final ParseUser appUser = new ParseUser();
				appUser.setUsername(edtUsernameSignUp.getText().toString());
				appUser.setPassword(edtPasswordSignUp.getText().toString());

				appUser.signUpInBackground(new SignUpCallback() {
					@Override
					public void done(ParseException e) {
						if(e == null){
							FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username") + " signed up successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show();
						}else{
							FancyToast.makeText(SignUpLoginActivity.this,e.getMessage() ,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
						}
					}
				});
			}
		});

		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ParseUser.logInInBackground(edtUsernameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
					@Override
					public void done(ParseUser user, ParseException e) {
						if(user != null && e == null){
							FancyToast.makeText(SignUpLoginActivity.this,user.getUsername() + " logged in successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show();
						}else{
							FancyToast.makeText(SignUpLoginActivity.this,e.getMessage() ,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
						}
					}
				});
			}
		});
	}
}

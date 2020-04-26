package com.example.android.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

	private EditText edtEmailLogin;
	private  EditText edtPasswordLogin;

	private Button btnSignup;
	private Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		edtEmailLogin = findViewById(R.id.edtEmailLogin);
		edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
		btnSignup = findViewById(R.id.btnSignup);
		btnLogin = findViewById(R.id.btnLogin);

		btnLogin.setOnClickListener(this);
		btnSignup.setOnClickListener(this);

		if(ParseUser.getCurrentUser() != null){
			ParseUser.getCurrentUser().logOut();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btnLogin:
				ParseUser.logInInBackground(edtEmailLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
					@Override
					public void done(ParseUser parseUser, ParseException e) {
						if(parseUser != null && e == null){
							FancyToast.makeText(LoginActivity.this,
									parseUser.getUsername() + getString(R.string.message_suffix_login_successful),
									Toast.LENGTH_SHORT,
									FancyToast.SUCCESS,
									true).show();
							transitionToSocialMediaActivity();
						}
					}
				});
				break;
			case R.id.btnSignup:
				break;
		}
	}
	private void transitionToSocialMediaActivity(){
		Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);

		startActivity(intent);
	}
}

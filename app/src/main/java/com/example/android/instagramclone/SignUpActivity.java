package com.example.android.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

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

		edtEmailSignup 		= findViewById(R.id.edtEmailSignup);
		edtUsernameSignup 	= findViewById(R.id.edtUsernameSignup);
		edtPasswordSignup 	= findViewById(R.id.edtPasswordSignup);
		btnSignup 			= findViewById(R.id.btnSignup);
		btnLogin 			= findViewById(R.id.btnLogin);

		btnSignup.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
	}


	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.btnSignup:
				final ParseUser appUser = new ParseUser();
				appUser.setEmail(edtEmailSignup.getText().toString());
				appUser.setUsername(edtUsernameSignup.getText().toString());
				appUser.setPassword(edtPasswordSignup.getText().toString());

				appUser.signUpInBackground(new SignUpCallback() {
					@Override
					public void done(ParseException e) {
						if(e == null){
							FancyToast.makeText(SignUpActivity.this,
									appUser.getUsername() + R.string.message_suffix_signup_successful,
									Toast.LENGTH_SHORT,
									FancyToast.SUCCESS,
									true).show();
						}else{
							FancyToast.makeText(SignUpActivity.this,
									e.getMessage(),
									Toast.LENGTH_LONG,	FancyToast.ERROR,true).show();

						}

					}
				});
				break;

			case R.id.btnLogin:
				break;
		}
	}
}

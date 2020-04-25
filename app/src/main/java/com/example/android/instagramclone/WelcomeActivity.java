package com.example.android.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		TextView txtWelcome = findViewById(R.id.txtWelcome);
		txtWelcome.setText("Welcome " + ParseUser.getCurrentUser().get("username"));

		Button btnLogout = findViewById(R.id.btnLogout);
		btnLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ParseUser.logOut();
				finish();
			}
		});
	}
}

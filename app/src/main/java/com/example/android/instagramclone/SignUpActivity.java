package com.example.android.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

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
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

	private Button btnSave;
	private EditText edtName;
	private EditText edtPunchSpeed;
	private EditText edtPunchPower;
	private EditText edtKickSpeed;
	private EditText edtKickPower;
	private TextView txtRetrieveData;
	private Button btnRetrieveAllData;
	private String allKickboxers;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		btnSave = findViewById(R.id.btnSave);
		btnSave.setOnClickListener(SignUpActivity.this);

		edtName = findViewById(R.id.edtName);
		edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
		edtPunchPower = findViewById(R.id.edtPunchPower);
		edtKickSpeed = findViewById(R.id.edtKickSpeed);
		edtKickPower = findViewById(R.id.edtKickPower);

		txtRetrieveData = findViewById(R.id.txtRetrieveData);
		txtRetrieveData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Kickboxer");
				parseQuery.getInBackground("rNJeMyiigP", new GetCallback<ParseObject>() {
					@Override
					public void done(ParseObject parseObject, ParseException e) {
						if(parseObject != null && e == null){
							txtRetrieveData.setText(parseObject.get("name") +" - Punch Power: " + parseObject.get("punchPower"));
						}
					}
				});
			}
		});

		btnRetrieveAllData = findViewById(R.id.btnRetrieveAllData);
		btnRetrieveAllData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				allKickboxers = "";
				ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Kickboxer");
				queryAll.findInBackground(new FindCallback<ParseObject>() {
					@Override
					public void done(List<ParseObject> list, ParseException e) {
						if(e == null){
							if(list.size() > 0){
								for(ParseObject kickBoxer: list){
									allKickboxers += kickBoxer.get("name") + "\n";
								}
								FancyToast.makeText(SignUpActivity.this,allKickboxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false).show();
							}
							else{
								FancyToast.makeText(SignUpActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
							}
						}
					}
				});
			}
		});
	}


	@Override
	public void onClick(View view) {

		final ParseObject kickBoxer = new ParseObject("Kickboxer");
		kickBoxer.put("name", edtName.getText().toString());
		kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
		kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
		kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
		kickBoxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));

		kickBoxer.saveInBackground(new SaveCallback() {
			@Override
			public void done(ParseException e) {
				if(e == null) {
//					Toast.makeText(SignUpActivity.this, kickBoxer.get("name") + " is saved to server.", Toast.LENGTH_LONG).show();
					FancyToast.makeText(SignUpActivity.this,kickBoxer.get("name") + " is saved to server.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show();
				}else{
					FancyToast.makeText(SignUpActivity.this,"Server error: " + kickBoxer.get("name") + " not saved.",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
				}
			}
		});
	}
}

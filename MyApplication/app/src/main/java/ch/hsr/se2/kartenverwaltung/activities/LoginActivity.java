package ch.hsr.se2.kartenverwaltung.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;


import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.data.Crypto;
import ch.hsr.se2.kartenverwaltung.services.JsonRequestHandler;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_login)
public class LoginActivity extends CommonActivity {

	static SecretKeySpec aesKey;
	static String userID;
	private JsonRequestHandler jsonHandler;



	@InjectView(R.id.email_editText)
	EditText emailFieldText;

	@InjectView(R.id.password_editText)
	EditText passwordField;

	@InjectView(R.id.login_button)
	private Button loginButton;

	@InjectView(R.id.register_button)
	private Button registerButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	public void jsonResponseFinished(){
	}

	public void startOverviewActivity(View view) {


		Crypto crypto = new Crypto();


		EditText mailForm = (EditText)findViewById(R.id.email_editText);
		EditText passForm = (EditText)findViewById(R.id.password_editText);
		String email = mailForm.getText().toString();
		String password = passForm.getText().toString();

		String passwordHash = crypto.getHash(password);

		Map<String, String> jsonParams = new HashMap<String, String>();
		jsonParams.put("email", email);
		jsonParams.put("password", passwordHash);

		String respons = jsonHandler.jsonLoginMethod(jsonParams);


		aesKey = crypto.getKey(password);
		Log.d("Login", "Successfull");



		if(respons != null){



			Intent intent = new Intent(this, OverviewActivity.class);
			Log.d("Login", "Successfull");
			startActivity(intent);

		}else{
			Log.d("Login", "Failed");;

		}




		Intent intent = new Intent(this, OverviewActivity.class);
		startActivity(intent);
	}

	// Todo: When loginButton pressed, validate user input. Add a listener to loginButton
	// and implement it in a private class where the validator is called.

}

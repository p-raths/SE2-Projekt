package ch.hsr.se2.kartenverwaltung.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;


import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.data.Crypto;
import ch.hsr.se2.kartenverwaltung.services.JsonEventInterface;
import ch.hsr.se2.kartenverwaltung.services.JsonRequestHandler;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_login)
public class LoginActivity extends CommonActivity {

	static String userID;

	Crypto crypto = new Crypto();

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

	public void  login(View view) {

		EditText mailForm = (EditText)findViewById(R.id.email_editText);
		EditText passForm = (EditText)findViewById(R.id.password_editText);
		String email = mailForm.getText().toString();
		String password = passForm.getText().toString();

		byte[] passwordHash = crypto.getHash(password);

		Map<String, String> jsonParams = new HashMap<String, String>();
		jsonParams.put("usrId", "0");
		jsonParams.put("usrName", email);
		String secret = Base64.encodeToString(passwordHash, Base64.DEFAULT);
		jsonParams.put("usrPassword", secret.trim()); //1234

		crypto.getKey(password, this);




		Intent intent = new Intent(this, OverviewActivity.class);
		startActivity(intent);

	}

/*	public void loginMethod(String response, String user){

		if (!response.equals("Login-Failed")){
			Log.d("Login", "Successfull");

			SharedPreferences prefs = this.getSharedPreferences("Kartenverwaltung", 0);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("userId", response);
			editor.commit();

			//Intent intent = new Intent(this, OverviewActivity.class);
			//startActivity(intent);

		}else{
			Log.d("Login", "Failed");
		}

	}
*/
}

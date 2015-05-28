package ch.hsr.se2.kartenverwaltung.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.data.Crypto;

import roboguice.inject.ContentView;

@ContentView(R.layout.activity_login)
public class LoginActivity extends CommonActivity {

	Crypto crypto = new Crypto();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void  login(View view) {

		EditText passForm = (EditText)findViewById(R.id.password_editText);
		String password = passForm.getText().toString();


		crypto.getKey(password, this);

		Intent intent = new Intent(this, OverviewActivity.class);
		startActivity(intent);
	}
}

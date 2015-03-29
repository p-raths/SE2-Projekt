package ch.hsr.se2.kartenverwaltung.activities;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import ch.hsr.se2.kartenverwaltung.R;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_login)
public class LoginActivity extends CommonActivity {

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

    public void startOverviewActivity(View view) {
        Intent intent = new Intent(this, OverviewActivity.class);
        startActivity(intent);
    }

    /*
    Todo: When loginButton pressed, validate user input. Add a listener to loginButton
    and implement it in a private class where the validator is called.
    */


}

package ch.hsr.se2.kartenverwaltung.activities;



import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import ch.hsr.se2.kartenverwaltung.R;
import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
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


}

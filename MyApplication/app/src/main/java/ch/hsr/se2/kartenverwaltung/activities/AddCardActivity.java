package ch.hsr.se2.kartenverwaltung.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.adapters.CardAdapter;
import ch.hsr.se2.kartenverwaltung.services.JsonEventInterface;
import ch.hsr.se2.kartenverwaltung.services.JsonRequestHandler;
import ch.hsr.se2.kartenverwaltung.services.JsonServiceHandler;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/*
 * This activity shows the card details
 */
@ContentView(R.layout.activity_add_card)

public class AddCardActivity extends CommonActivity implements JsonEventInterface{

	@InjectView(R.id.card_name)
	private EditText cardNameField;

	@InjectView(R.id.card_description_field)
	private EditText cardDescriptionField;

	@InjectView(R.id.card_date_textField)
	private EditText cardDateField;

	@InjectView(R.id.save_button)
	private Button saveButton;

	@InjectView(R.id.cancel_button)
	private Button cancelButton;

	private static String TAG = AddCardActivity.class.getSimpleName();

	// json array response url
	private String urlJsonObj = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/PostFeed";

	// Progress dialog
	private ProgressDialog pDialog;

    // Initalize request handler to get json data
    private JsonRequestHandler en;

	@Override
	public void onStop() {
		super.onStop();
		this.finish();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		saveButton.setOnClickListener(new CardSavelistener());

		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);

        en = new JsonRequestHandler(this);
	}

    public void jsonResponseFinished(){

    }

	private void createOverViewActivity() {
		Intent intent = new Intent(this, OverviewActivity.class);
		startActivity(intent);
	}

	private class CardSavelistener implements OnClickListener {

		@Override
		public void onClick(View view) {

            en.jsonPostMethod(inputFieldsToMap());
			createOverViewActivity();
			// finish();
		}
	}

	private Map<String, String> inputFieldsToMap() {

        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("id", "-1");
        jsonParams.put("name", cardNameField.getText().toString());
        jsonParams.put("description", cardDescriptionField.getText().toString());
        jsonParams.put("defaultattributes", "Imanattribute");
        Log.d("AddCardActivitygetP", jsonParams.toString());
        return jsonParams;
    }
}


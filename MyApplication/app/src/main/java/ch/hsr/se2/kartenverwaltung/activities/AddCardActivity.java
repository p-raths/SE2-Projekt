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
import ch.hsr.se2.kartenverwaltung.services.JsonServiceHandler;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/*
 * This activity shows the card details
 */
@ContentView(R.layout.activity_add_card)
public class AddCardActivity extends CommonActivity {

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
	}

	private void createOverViewActivity() {
		Intent intent = new Intent(this, OverviewActivity.class);
		startActivity(intent);
	}

	private class CardSavelistener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO we should send this object to the server
			// Card newCard = new Card(cardNameField.getText().toString(), cardDescriptionField.getText().toString());
			// createOverViewActivity();

			makeJsonObjectPost();
			createOverViewActivity();
			// finish();
		}
	}

	/**
	 * Method to make json post [
	 */
	private void makeJsonObjectPost() {


        //    JsonServiceHandler();
        StringRequest req = new StringRequest(Request.Method.POST, urlJsonObj, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("CardActivityResponse", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> jsonParams = new HashMap<String, String>();
                jsonParams.put("id", "-1");
                jsonParams.put("name", cardNameField.getText().toString());
                jsonParams.put("description", cardDescriptionField.getText().toString());
                jsonParams.put("defaultattributes", "Imanattribute");
                Log.d("AddCardActivitygetP", jsonParams.toString());
                return jsonParams;
            }

            ;
        };
        JsonServiceHandler.getInstance().addToRequestQueue(req);}
}


/*
 * {
 * 
 * @Override public Map<String, String> getHeaders() throws com.android.volley.AuthFailureError { HashMap<String, String> headers = new HashMap<String, String>();
 * headers.put("Content-Type", "application/x-www-form-urlencoded"); //headers.put("Content-Type", "application/json; charset=utf-8"); return headers; }
 */

/*
 * JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, urlJsonObj, new JSONArray(feedData), new Response.Listener<JSONArray>() {
 * 
 * @Override public void onResponse(JSONArray response) { Log.d(TAG, response.toString()); } }, new Response.ErrorListener() {
 * 
 * @Override public void onErrorResponse(VolleyError error) { VolleyLog.d(TAG, "Error: " + error.getMessage()); Toast.makeText(getApplicationContext(), error.getMessage(),
 * Toast.LENGTH_LONG).show(); }
 * 
 * 
 * }); JSONServiceHandler.getInstance().addToRequestQueue(req);} }
 */


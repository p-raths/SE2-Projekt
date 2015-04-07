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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.domain.Card;
import ch.hsr.se2.kartenverwaltung.services.JSONServiceHandler;
import ch.hsr.se2.kartenverwaltung.services.VolleyCustomRequest;
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		saveButton.setOnClickListener(new CardSavelistener());


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);




    }

	/*
	 * Necessary at the moment in order to show the recently created card
	 */
	private void createOverViewActivity(final Card newCard) {
		Toast.makeText(this, getString(R.string.card_saved), Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, OverviewActivity.class);
		intent.putExtra("card_name", newCard.getCardName());
		intent.putExtra("card_description", newCard.getDescription());
		startActivity(intent);
	}

	private class CardSavelistener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO we should send this object to the server
//			Card newCard = new Card(cardNameField.getText().toString(), cardDescriptionField.getText().toString());
//			createOverViewActivity(newCard);

/*
            Testzeugs, kann gelöscht werden sobla dPOST funktioniert

           JSONObject jsonObjectInput = new JSONObject();
            try {
                jsonObjectInput.put("id", "0");
                jsonObjectInput.put("name", cardNameField.getText().toString());
                jsonObjectInput.put("description", cardDescriptionField.getText().toString());
                jsonObjectInput.put("defaultattributes", "");
            }catch (JSONException e) {
                e.printStackTrace();
            }
*/
            makeJsonObjectPost();

        }
	}




    /**
     * Method to make json post [
     * */
    private void makeJsonObjectPost() {

/*
        Testzeugs, kann gelöscht werden sobla dPOST funktioniert

        JSONObject jsonParse = jsonObj;
        showpDialog();
        Log.d("jsonParse: ", jsonParse.toString());
*/
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("id", "0");
        jsonParams.put("name", cardNameField.getText().toString());
        jsonParams.put("description", cardDescriptionField.getText().toString());
        jsonParams.put("defaultattributes", "I'm an attribute");

        VolleyCustomRequest req = new VolleyCustomRequest(Request.Method.POST, urlJsonObj, jsonParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hidepDialog();
            }
            })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", "My useragent");
                return headers;
            }
        };
        JSONServiceHandler.getInstance().addToRequestQueue(req);}


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



}


 /*
            @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());





                        try {
                            // Parsing json array response
                            // loop through each json object

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject card = (JSONObject) response
                                        .get(i);

                                String name = card.getString("name");
                                String description = card.getString("description");

                                cardList.add(new Card(name, description));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        // Adding request to request queue
        ServiceHandler.getInstance().addToRequestQueue(req);
    }
*/



















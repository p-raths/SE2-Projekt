package ch.hsr.se2.kartenverwaltung.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewDebug;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.services.JSONServiceHandler;

public class CardViewActivity extends ActionBarActivity {

    private int cardId;
	private TextView cardName;
	private TextView cardDescription;

    // json array response url
    private String urlJsonObj = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/PostFeed";

    private static String TAG = CardViewActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_view);

		Bundle bundle = getIntent().getExtras();
		cardName = (TextView) findViewById(R.id.cardItem_name_textView);
		cardDescription = (TextView) findViewById(R.id.cardItem_description_textView);
		cardId = bundle.getInt("card_id");
        cardName.setText(bundle.getString("card_name"));
		cardDescription.setText(bundle.getString("card_description"));
        Log.d("CardViewParams", Integer.toString(cardId) + bundle.getString("card_name") + bundle.getString("card_description"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_card_list_item, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_shareCard:
			// TODO complete
			return true;
		case R.id.action_settings:
			// TODO complete
			return true;
		case R.id.action_editCard:
			// TODO complete
			return true;
		case R.id.action_deleteCard:
            deleteCard();
            createOverViewActivity();
			return true;
		case R.id.action_logout:
			doLogout();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void doLogout() {
		startActivity(new Intent(this, LoginActivity.class));
	}

    private void createOverViewActivity() {
        Intent intent = new Intent(this, OverviewActivity.class);
        startActivity(intent);
    }

    private void deleteCard(){

        StringRequest req = new StringRequest(Request.Method.POST, urlJsonObj, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("CardDeleteResponse", response.toString());
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
                jsonParams.put("id", Integer.toString(cardId));
                jsonParams.put("name", "");
                jsonParams.put("description", "");
                jsonParams.put("defaultattributes", "");
                Log.d("DeleteCardParams", jsonParams.toString());
                return jsonParams;
            }

            ;
        };
        JSONServiceHandler.getInstance().addToRequestQueue(req);

    }

}

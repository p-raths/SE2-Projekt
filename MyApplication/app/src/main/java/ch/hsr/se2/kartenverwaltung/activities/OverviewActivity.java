package ch.hsr.se2.kartenverwaltung.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.adapters.CardAdapter;
import ch.hsr.se2.kartenverwaltung.domain.Card;
import ch.hsr.se2.kartenverwaltung.services.JSONServiceHandler;

/*
 *This activity shows a card list.
 */
public class OverviewActivity extends ActionBarActivity {

	private CardAdapter cardAdapter;
	private ListView cardsListView;
	private ArrayList<Card> cardList;

	private static String TAG = OverviewActivity.class.getSimpleName();

	// json array response url
	private final String URL_JSON_ARRAY = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/GetFeeds";

	// Progress dialog
	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
		cardList = new ArrayList<Card>();
		cardsListView = (ListView) findViewById(R.id.listView_overview);

		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);
		makeJsonArrayRequest();

		cardAdapter = new CardAdapter(this, R.layout.activity_card_detail, cardList);
		cardsListView.setAdapter(cardAdapter);
		cardsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		cardsListView.setItemsCanFocus(false);
		cardsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
				showCardDetail();
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
        makeJsonArrayRequest();
        this.cardAdapter.notifyDataSetChanged();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_overview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_editCard:
			return true;
		case R.id.action_addCard:
			startAddActivity();
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

	private void startAddActivity() {

		startActivity(new Intent(this, AddCardActivity.class));
	}

	private void showCardDetail() {
		List<Card> list = getSelectedCards();
		Intent intent = new Intent(this, CardViewActivity.class);
		for (Card card : list) {
			intent.putExtra("card_name", card.getCardName());
			intent.putExtra("card_description", card.getDescription());
			System.out.println(card.getCardName());
			System.out.println(card.getDescription());
			startActivity(intent);
		}
	}

	private List<Card> getSelectedCards() {
		List<Card> list = new ArrayList<Card>();
		SparseBooleanArray checked = cardsListView.getCheckedItemPositions();
		for (int i = 0; i < cardsListView.getCount(); i++) {
			if (checked.get(i)) {
				list.add(cardAdapter.getItem(i));
			}
		}
		return list;
	}

	/**
	 * Method to make json array request where response starts with [
	 */
	private void makeJsonArrayRequest() {

		showpDialog();

		JsonArrayRequest req = new JsonArrayRequest(URL_JSON_ARRAY, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				Log.d(TAG, response.toString());

				try {
					// Parsing json array response
					// loop through each json object

					for (int i = 0; i < response.length(); i++) {

						JSONObject card = (JSONObject) response.get(i);

						String name = card.getString("name");
						String description = card.getString("description");

						cardList.add(new Card(name, description));

					}

				} catch (JSONException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
				}

				hidepDialog();
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
				hidepDialog();
			}
		});

		// Adding request to request queue
		JSONServiceHandler.getInstance().addToRequestQueue(req);
	}

	private void showpDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hidepDialog() {
		if (pDialog.isShowing())
			pDialog.dismiss();
	}

}
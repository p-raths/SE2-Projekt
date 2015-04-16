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
import ch.hsr.se2.kartenverwaltung.services.JsonEventInterface;
import ch.hsr.se2.kartenverwaltung.services.JsonRequestHandler;
import ch.hsr.se2.kartenverwaltung.services.JsonServiceHandler;

/*
 *This activity shows a card list.
 */
public class OverviewActivity extends ActionBarActivity implements JsonEventInterface {

	private CardAdapter cardAdapter;
	private ListView cardsListView;
	private ArrayList<Card> cardList;

    // tag for Log.d
	private static String TAG = OverviewActivity.class.getSimpleName();

	// json array response url
	private final String URL_JSON_ARRAY = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/GetFeeds";

	// Progress dialog
	private ProgressDialog pDialog;

    // Initalize request handler to get json data
    private JsonRequestHandler en;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);

		cardList = new ArrayList<Card>();
		cardsListView = (ListView) findViewById(R.id.listView_overview);

		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);

        en = new JsonRequestHandler(this);
        en.jsonGetMethod();

//		cardAdapter = new CardAdapter(this, R.layout.activity_card_detail, cardList);
//		cardsListView.setAdapter(cardAdapter);
		cardsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		cardsListView.setItemsCanFocus(false);
		cardsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
				showCardDetail();
			}
		});
	}

    public void jsonResponseFinished(){
        cardAdapter = new CardAdapter(this, R.layout.activity_card_detail, en.jsonGetList());
        cardsListView.setAdapter(cardAdapter);
    }

	@Override
	protected void onResume() {
		super.onResume();
        en.jsonGetMethod();
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
            intent.putExtra("card_id", card.getCardId());
            intent.putExtra("card_name", card.getCardName());
			intent.putExtra("card_description", card.getDescription());
            System.out.println(card.getCardId());
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
}
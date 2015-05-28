package ch.hsr.se2.kartenverwaltung.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.adapters.CardAdapter;
import ch.hsr.se2.kartenverwaltung.domain.Card;
import ch.hsr.se2.kartenverwaltung.services.JsonEventInterface;
import ch.hsr.se2.kartenverwaltung.services.JsonRequestHandler;

/*
 *This activity shows a card list.
 */
public class OverviewActivity extends ActionBarActivity implements JsonEventInterface {

	private CardAdapter cardAdapter;
	private ListView cardsListView;
    private ArrayList<Card> cardList;


    // tag for Log.d
	private static String TAG = OverviewActivity.class.getSimpleName();

    // Initalize request handler to get json data
    private JsonRequestHandler jsonHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);

		cardList = new ArrayList<Card>();
		cardsListView = (ListView) findViewById(R.id.listView_overview);

        jsonHandler = new JsonRequestHandler(this);
        jsonHandler.jsonGetMethod();


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
       cardAdapter = new CardAdapter(this, R.layout.activity_card_detail, jsonHandler.jsonGetList());
       cardsListView.setAdapter(cardAdapter);
    }

	@Override
	protected void onResume() {
		super.onResume();

        cardAdapter = new CardAdapter(this, R.layout.activity_card_detail, jsonHandler.jsonGetList());
        cardsListView.setAdapter(cardAdapter);
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

		Context context=this;
		SharedPreferences myPrefs = context.getSharedPreferences("Kartenverwaltung",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = myPrefs.edit();
		editor.clear();
		editor.commit();

		Intent i = new Intent(this,LoginActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.putExtra("EXIT", true);
		startActivity(i);

	}

	private void startAddActivity() {
		startActivity(new Intent(this, AddCardActivity.class));
	}

	private void showCardDetail() {
		List<Card> list = getSelectedCards();
		Intent intent = new Intent(this, CardViewActivity.class);
		for (Card card : list) {
            intent.putExtra("card_id", card.getId());
            intent.putExtra("card_name", card.getName());
			intent.putExtra("card_description", card.getDescription());

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
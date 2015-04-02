package ch.hsr.se2.kartenverwaltung.activities;

import android.content.Intent;
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

/*
 *This activity shows a card list.
 */
public class OverviewActivity extends ActionBarActivity {

	private CardAdapter cardAdapter;
	private ListView cardsListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);

		ArrayList<Card> dummyCardList = new ArrayList<>();
		dummyCardList.add(new Card("Karte1", "Creditcaard"));
		dummyCardList.add(new Card("Karte2", "Copop"));
		Bundle bundle = getIntent().getExtras();
		// TODO remove this crap. Necessary at the moment in order to be able to show the added.
		if (bundle != null) {
			dummyCardList.add(new Card(bundle.getString("card_name"), bundle.getString("card_description")));
		}

		cardsListView = (ListView) findViewById(R.id.listView_overview);
		cardAdapter = new CardAdapter(this, R.layout.activity_card_detail, dummyCardList);
		cardsListView.setAdapter(cardAdapter);
		cardsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		cardsListView.setItemsCanFocus(false);
		cardsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				showCardDetail();
			}
		});
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
}
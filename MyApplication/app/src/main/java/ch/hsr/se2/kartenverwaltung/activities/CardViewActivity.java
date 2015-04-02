package ch.hsr.se2.kartenverwaltung.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ch.hsr.se2.kartenverwaltung.R;

public class CardViewActivity extends ActionBarActivity {

	private TextView cardName;
	private TextView carDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_view);

		Bundle bundle = getIntent().getExtras();
		cardName = (TextView) findViewById(R.id.cardItem_name_textView);
		carDescription = (TextView) findViewById(R.id.cardItem_description_textView);
		cardName.setText(bundle.getString("card_name"));
		carDescription.setText(bundle.getString("card_description"));
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
			// TODO complete
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
}

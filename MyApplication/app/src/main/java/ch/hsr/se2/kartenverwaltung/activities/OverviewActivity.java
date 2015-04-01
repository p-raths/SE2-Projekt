package ch.hsr.se2.kartenverwaltung.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ch.hsr.se2.kartenverwaltung.R;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_overview)
public class OverviewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_overview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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
}
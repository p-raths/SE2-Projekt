package ch.hsr.se2.kartenverwaltung.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import ch.hsr.se2.kartenverwaltung.R;
import roboguice.inject.ContentView;

/*
 *This activity es related to an card item in the card-list
   * in the {@link OverviewActivity}
 */
@ContentView(R.layout.activity_card_detail)
public class CardDetailActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Do nothing at the moment
	}
}

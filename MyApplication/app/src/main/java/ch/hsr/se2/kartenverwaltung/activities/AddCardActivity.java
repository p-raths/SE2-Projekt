package ch.hsr.se2.kartenverwaltung.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.domain.Card;
import ch.hsr.se2.kartenverwaltung.data.CardDataSource;
import ch.hsr.se2.kartenverwaltung.services.JsonEventInterface;
import ch.hsr.se2.kartenverwaltung.services.JsonRequestHandler;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/*
 * This activity shows the card details
 */
@ContentView(R.layout.activity_add_card)

public class AddCardActivity extends CommonActivity implements JsonEventInterface{

	@InjectView(R.id.card_name)
	private EditText cardNameField;

	@InjectView(R.id.card_description_field)
	private EditText cardDescriptionField;

	@InjectView(R.id.save_button)
	private Button saveButton;

	@InjectView(R.id.cancel_button)
	private Button cancelButton;

    private JsonRequestHandler jsonHandler;

    private ListView attributesListView;

	@Override
	public void onStop() {
		super.onStop();
		this.finish();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        attributesListView = (ListView) findViewById(R.id.listView_card_add_attribute);
        saveButton.setOnClickListener(new CardSavelistener());

        saveButton.setOnClickListener(new CardSavelistener());
        jsonHandler = new JsonRequestHandler(this);

	}

    public void jsonResponseFinished(){
    }

	private void createOverViewActivity() {
		Intent intent = new Intent(this, OverviewActivity.class);
		startActivity(intent);
	}

	private class CardSavelistener implements OnClickListener {

		@Override
		public void onClick(View view) {
            jsonHandler.jsonAddCardMethod(inputFieldsToCard());

//            datasource.createCard(inputFieldsToCard());
//            datasource.close();
			createOverViewActivity();
		}
	}

	private Card inputFieldsToCard() {
        Card card = new Card(0, cardNameField.getText().toString(), cardDescriptionField.getText().toString());
        Log.d("AddCardActivitygetP", "Id: " + card.getCardId() + " Name: " + card.getCardName() + " Description: " + card.getDescription());
        return card;
    }

}


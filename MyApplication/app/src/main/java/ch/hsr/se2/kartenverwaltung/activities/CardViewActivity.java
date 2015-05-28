package ch.hsr.se2.kartenverwaltung.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.adapters.AttributeAdapter;
import ch.hsr.se2.kartenverwaltung.domain.Attribute;
import ch.hsr.se2.kartenverwaltung.domain.AttributeType;
import ch.hsr.se2.kartenverwaltung.domain.Card;
import ch.hsr.se2.kartenverwaltung.domain.CardType;
import ch.hsr.se2.kartenverwaltung.domain.User;
import ch.hsr.se2.kartenverwaltung.services.JsonEventInterface;
import ch.hsr.se2.kartenverwaltung.services.JsonRequestHandler;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;


public class CardViewActivity extends ActionBarActivity implements JsonEventInterface {

    private int cardId;
	private EditText cardName;
	private EditText cardDescription;
    private EditText cardAttributes;
    private Button saveButton;
    private Card parseCard;
    private AttributeAdapter attributeAdapter;
    private ArrayList<Attribute> attributeList;
    private ListView attributeListView;

    private static String TAG = CardViewActivity.class.getSimpleName();

    // Initalize request handler to get json data
    private JsonRequestHandler jsonHandler;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_view);

        saveButton = (Button) findViewById(R.id.button_save_edited_card);
        saveButton.setOnClickListener(new CardSavelistener());

		Bundle bundle = getIntent().getExtras();
		cardName = (EditText) findViewById(R.id.editText_card_name);
        cardName.setText(bundle.getString("card_name"));
        cardId = bundle.getInt("card_id");
        cardDescription = (EditText) findViewById(R.id.editText_card_description);
		cardDescription.setText(bundle.getString("card_description"));
        Log.d("CardViewParams", Integer.toString(cardId) + bundle.getString("card_name") + bundle.getString("card_description"));

        jsonHandler = new JsonRequestHandler(this);

        parseCard = new Card(cardId, bundle.getString("card_name"),
                bundle.getString("card_description"), 0, new CardType("CardTypeName",
                        "CardTypeDescription","Attribute"), new User());
	}

    public void jsonResponseFinished(){
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
			// not implemented
			return true;
		case R.id.action_settings:
            // not implemented
			return true;
		case R.id.action_editCard:
            findViewById(R.id.editText_card_name).setEnabled(true);
            findViewById(R.id.editText_card_description).setEnabled(true);
            findViewById(R.id.button_save_edited_card).setVisibility(View.VISIBLE);
            return true;
		case R.id.action_deleteCard:
            jsonHandler.jsonDeleteCardMethod(inputFieldsToCard("",""));
            createOverViewActivity();
			return true;
		case R.id.action_logout:
			doLogout();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

    private class CardSavelistener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            jsonHandler.jsonUpdateCardMethod(inputFieldsToCard(cardName.getText().toString(),
                    cardDescription.getText().toString()));
            createOverViewActivity();
        }
    }

	private void doLogout() {
		startActivity(new Intent(this, LoginActivity.class));
	}

    private void createOverViewActivity() {
        Intent intent = new Intent(this, OverviewActivity.class);
        startActivity(intent);
        finish();
    }

    private Card inputFieldsToCard(String name, String description) {
        Card card = new Card(cardId, name, description, 0,
             new CardType("CardTypeName","CardTypeDescription","Attribute"),
                new User());
        Log.d("CardView", "Id: " + cardId + " Name: " + "" + " Description: " + "");
        return card;
    }
}

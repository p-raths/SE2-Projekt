package ch.hsr.se2.kartenverwaltung.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.adapters.AttributeAdapter;
import ch.hsr.se2.kartenverwaltung.services.JsonEventInterface;
import ch.hsr.se2.kartenverwaltung.services.JsonRequestHandler;
import domain.Attribute;
import domain.AttributeType;
import domain.Card;
import domain.CardType;
import domain.User;

public class CardViewActivity extends ActionBarActivity implements JsonEventInterface {

    private int cardId;
	private EditText cardName;
	private EditText cardDescription;
    private EditText cardAttributes;
    private domain.Card parseCard;
    private AttributeAdapter attributeAdapter;
    private ArrayList<domain.Attribute> attributeList;
    private ListView attributeListView;

    private static String TAG = CardViewActivity.class.getSimpleName();

    // Initalize request handler to get json data
    private JsonRequestHandler jsonHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_view);

		Bundle bundle = getIntent().getExtras();
		cardName = (EditText) findViewById(R.id.editText_card_name);
        cardName.setText(bundle.getString("card_name"));
        cardId = bundle.getInt("card_id");
        cardDescription = (EditText) findViewById(R.id.editText_card_description);
		cardDescription.setText(bundle.getString("card_description"));
        Log.d("CardViewParams", Integer.toString(cardId) + bundle.getString("card_name") + bundle.getString("card_description"));

        jsonHandler = new JsonRequestHandler(this);

        parseCard = new Card(bundle.getInt("card_id"), bundle.getString("card_name"),
                bundle.getString("card_description"), 0, new CardType("CardTypeName",
                        "CardTypeDescription","Attribute"), new User());

        attributeList = new ArrayList<Attribute>();
        attributeList.add(0, new Attribute("attribute1", "value", new AttributeType("AttributeTypeName",5,1), parseCard));
        attributeList.add(1, new Attribute("attribute1", "value", new AttributeType("AttributeTypeName",5,1), parseCard));
        attributeAdapter = new AttributeAdapter(this, R.layout.activity_card_view, attributeList);

        attributeListView = (ListView) findViewById(R.id.listView_card_view_attribute);
        attributeListView.setAdapter(attributeAdapter);

        this.attributeAdapter.notifyDataSetChanged();

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
            //jsonHandler.jsonAddCardMethod(inputFieldsToCard());
            jsonHandler.jsonDeleteCardMethod(inputFieldsToCard());
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

    private Card inputFieldsToCard() {
        domain.Card card = new domain.Card(cardId, "", "", 0,
             new domain.CardType("CardTypeName","CardTypeDescription","Attribute"),
                new domain.User());
        Log.d("DeleteCard", "Id: " + cardId + " Name: " + "" + " Description: " + "");
        return card;
    }
}

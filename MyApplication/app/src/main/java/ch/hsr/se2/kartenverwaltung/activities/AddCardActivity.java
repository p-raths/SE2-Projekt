package ch.hsr.se2.kartenverwaltung.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.domain.Card;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_add_card)
public class AddCardActivity extends CommonActivity {

	@InjectView(R.id.card_name)
	private EditText cardNameField;

	@InjectView(R.id.card_description_field)
	private EditText cardDescriptionField;

	@InjectView(R.id.card_date_textField)
	private EditText cardDateField;

	@InjectView(R.id.save_button)
	private Button saveButton;

	@InjectView(R.id.cancel_button)
	private Button cancelButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		saveButton.setOnClickListener(new CardSavelistener());
	}

	private class CardSavelistener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO we should send this object to the server
			Card newCard = new Card(cardNameField.getText().toString(), cardDescriptionField.getText().toString());
        }
	}
}

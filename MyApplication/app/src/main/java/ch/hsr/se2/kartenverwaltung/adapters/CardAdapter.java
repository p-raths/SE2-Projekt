package ch.hsr.se2.kartenverwaltung.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.domain.Card;

/**
 * Created by roberto on 01.04.15. Necessary in order to show the cards items in the {@link ch.hsr.se2.kartenverwaltung.activities.OverviewActivity} class.
 */
public class CardAdapter extends ArrayAdapter<Card> {

	public CardAdapter(Context context, int textViewResourceId, ArrayList<Card> cardList) {
		super(context, textViewResourceId, cardList);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Card receivedCard = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_card_detail, parent, false);
		}
		TextView cardName = (TextView) convertView.findViewById(R.id.card_name_item_editText);
		TextView additionalCardInformation = (TextView) convertView.findViewById(R.id.additional_line_cardItem_editText);
		cardName.setText(receivedCard.getCardName());
		DateFormat dataFormat = DateFormat.getDateInstance();
		additionalCardInformation.setText(getContext().getString(R.string.expiration_date) + " " + dataFormat.format(receivedCard.getCreationDate()));
		return convertView;
	}

}

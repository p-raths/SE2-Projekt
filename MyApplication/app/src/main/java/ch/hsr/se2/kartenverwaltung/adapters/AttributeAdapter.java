package ch.hsr.se2.kartenverwaltung.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

import ch.hsr.se2.kartenverwaltung.R;
import ch.hsr.se2.kartenverwaltung.domain.Attribute;
import ch.hsr.se2.kartenverwaltung.domain.Card;

/**
 * Created by Fehr on 14.05.2015.
 */
public class AttributeAdapter extends ArrayAdapter<Attribute> {

    public AttributeAdapter(Context context, int textViewResourceId, ArrayList<Attribute> attributeList) {
        super(context, textViewResourceId, attributeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Attribute receivedAttribute = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_attribute_detail, parent, false);
        }
        EditText attributeName = (EditText) convertView.findViewById(R.id.editText_attribute_detail_name);
        EditText attributeValue = (EditText) convertView.findViewById(R.id.editText_attribute_detail_value);
        attributeName.setText(receivedAttribute.getAttributeName());
        attributeValue.setText(receivedAttribute.getAttributeValue());
        return convertView;
    }

}

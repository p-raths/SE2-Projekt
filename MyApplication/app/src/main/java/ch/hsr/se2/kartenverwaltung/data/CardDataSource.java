package ch.hsr.se2.kartenverwaltung.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.UUID;

import ch.hsr.se2.kartenverwaltung.domain.Card;
import ch.hsr.se2.kartenverwaltung.services.JsonEventInterface;
import ch.hsr.se2.kartenverwaltung.services.JsonRequestHandler;

/**
 * Created by Fehr on 16.04.2015.
 */
public class CardDataSource implements JsonEventInterface{

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    // Initalize request handler to get json data
    private JsonRequestHandler en;

    private String[] allColumns = { SQLiteHelper.ATTRIBUTE_COLUMN_CARDID,
            SQLiteHelper.CARD_COLUMN_CARDNAME,
            SQLiteHelper.CARD_COLUMN_CARDDESCRIPTION,
            SQLiteHelper.CARD_COLUMN_CARDCREATEDON,
            SQLiteHelper.CARD_COLUMN_CATID,
            SQLiteHelper.CARD_COLUMN_USRID};

    public CardDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
        en = new JsonRequestHandler(this);
        en.jsonGetMethod();
    }

    public void jsonResponseFinished(){}

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean createCard(Card card) {
        ContentValues values = new ContentValues();
        // füllt contentValue mit den Werten
        values.put(SQLiteHelper.CARD_COLUMN__ID, UUID.randomUUID().hashCode());
        values.put(SQLiteHelper.CARD_COLUMN_CARDID, UUID.randomUUID().hashCode());
        values.put(SQLiteHelper.CARD_COLUMN_CARDNAME, card.getCardName());
        values.put(SQLiteHelper.CARD_COLUMN_CARDDESCRIPTION, card.getDescription());
        values.put(SQLiteHelper.CARD_COLUMN_CARDCREATEDON, "");
        values.put(SQLiteHelper.CARD_COLUMN_CATID, "");
        values.put(SQLiteHelper.CARD_COLUMN_USRID, "");
        values.put(SQLiteHelper.CARD_COLUMN_CARDREVISION, 1);
        long insertId = database.insert(SQLiteHelper.CARD_TABLE, null, values);
        return true;
    }

    // noch nicht getestet!!!
    public boolean updateCard(Card card) {
        ContentValues values = new ContentValues();
        //values.put(SQLiteHelper.CARD_COLUMN__ID, UUID.randomUUID().hashCode());
        values.put(SQLiteHelper.CARD_COLUMN_CARDID, card.getCardId());
        values.put(SQLiteHelper.CARD_COLUMN_CARDNAME, card.getCardName());
        values.put(SQLiteHelper.CARD_COLUMN_CARDDESCRIPTION, card.getDescription());
        values.put(SQLiteHelper.CARD_COLUMN_CARDCREATEDON, "");
        values.put(SQLiteHelper.CARD_COLUMN_CATID, "");
        values.put(SQLiteHelper.CARD_COLUMN_USRID, "");
        values.put(SQLiteHelper.CARD_COLUMN_CARDREVISION, +1);
        long insertId = database.update(SQLiteHelper.CARD_TABLE, values, "CARDID "+"=" + card.getCardId(), null);
        return true;
    }

    public ArrayList<Card> getAllCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        Cursor cursor = database.query(SQLiteHelper.CARD_TABLE,
            new String[] { SQLiteHelper.CARD_COLUMN_CARDID ,SQLiteHelper.CARD_COLUMN_CARDNAME,SQLiteHelper.CARD_COLUMN_CARDDESCRIPTION},
            null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Card card = cursorToCard(cursor);
            cardList.add(card);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return cardList;
    }

    public ArrayList<Card> getSingleCard(int id) {
        ArrayList<Card> cardList = new ArrayList<Card>();
        Cursor cursor = database.query(SQLiteHelper.CARD_TABLE,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Card card = cursorToCard(cursor);
            cardList.add(card);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return cardList;
    }

    public boolean deleteCard(Card card) {
//        long id = comment.getId();
        System.out.println("Card deleted with id: " + card.getCardId());
        database.delete(SQLiteHelper.CARD_TABLE, SQLiteHelper.CARD_COLUMN_CARDID + " = "
                + card.getCardId(), null);
        return true;
    }

    private Card cursorToCard(Cursor cursor) {
        Card card = new Card(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        return card;
    }
}

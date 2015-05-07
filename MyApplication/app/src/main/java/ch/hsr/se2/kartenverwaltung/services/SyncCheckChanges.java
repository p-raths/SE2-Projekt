package ch.hsr.se2.kartenverwaltung.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;

import ch.hsr.se2.kartenverwaltung.data.CardDataSource;
import ch.hsr.se2.kartenverwaltung.data.SQLiteHelper;
import ch.hsr.se2.kartenverwaltung.domain.Card;

/**
 * Created by Fehr on 30.04.2015.
 */
public class SyncCheckChanges implements JsonEventInterface{

    // Database fields
    private SQLiteDatabase database;

    // Initalize request handler to get json data
    private JsonRequestHandler jsonHandler;

    private CardDataSource datasource;

    private String[] allColumns = { SQLiteHelper.ATTRIBUTE_COLUMN_CARDID,
            SQLiteHelper.CARD_COLUMN_CARDNAME,
            SQLiteHelper.CARD_COLUMN_CARDDESCRIPTION,
            SQLiteHelper.CARD_COLUMN_CARDCREATEDON,
            SQLiteHelper.CARD_COLUMN_CATID,
            SQLiteHelper.CARD_COLUMN_USRID,
            SQLiteHelper.CARD_COLUMN_CARDREVISION};

    public SyncCheckChanges(Context context) {
        datasource = new CardDataSource(context);
        datasource.open();
    }

    public void jsonResponseFinished (){}

    public void syncCards(ArrayList<Card> list) {

        // Vergleicht Server Cards mit lokalen Cards und macht entsprechend den Revisionsnummer ein update
        // Neue Revision in Client Datenbank können nicht behandelt werden.

        JsonRequestHandler jsonHandler = new JsonRequestHandler(this);
        jsonHandler.jsonGetMethod();

        ArrayList<Card> cardListServer = list;
        Cursor cursor = database.query(SQLiteHelper.CARD_TABLE,
                allColumns, null, null, null, null, null);
        for (int i = 0; cardListServer.size() >= i; i++) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Card card = cursorToCard(cursor);
                if ((card.getCardId() == cardListServer.get(i).getCardId())
                        && (card.getRevision()==cardListServer.get(i).getRevision())){
                    syncLog(card, cardListServer.get(i));
                }
                else if ((card.getCardId() == cardListServer.get(i).getCardId())
                        && (card.getRevision()>cardListServer.get(i).getRevision())){
                    syncLog(card, cardListServer.get(i));
                    try {
                        if(jsonHandler.jsonUpdateCardMethod(card)){
                            Log.d("syncCards Rev. Server", "success");}
                    } catch (Exception e) {Log.d("syncCards Rev. Client", "failed");}
                }
                else if ((card.getCardId() == cardListServer.get(i).getCardId())
                        && (card.getRevision()<cardListServer.get(i).getRevision())){
                    syncLog(card, cardListServer.get(i));
                    try {
                        if(datasource.updateCard(cardListServer.get(i))){
                            Log.d("syncCards Rev. Server", "success");}
                    } catch (Exception e) {Log.d("syncCards Rev. Server", "failed");}
                }
                else{
                    Log.d("syncCards !exist ", "Id: " + cardListServer.get(i).getCardId()
                            + " Revision Client: " + cardListServer.get(i).getCardName()
                            + " Revision Server: " + cardListServer.get(i).getRevision() );
                    try {
                        if(datasource.createCard(card)){
                            Log.d("syncCards !exist", "success");}
                    } catch (Exception e) {Log.d("syncCards !exist", "failed");}
                }
                cursor.moveToNext();
            }
        }
        datasource.close();
    }

    private void syncLog(Card card1, Card card2){
        Log.d("syncCards Rev. Server", "Id: " + card1.getCardId()
                + " Revision Client: " + card1.getRevision()
                + " Revision Server: " + card2.getRevision() );
    }

    private Card cursorToCard(Cursor cursor) {
        Card card = new Card(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        return card;
    }
}

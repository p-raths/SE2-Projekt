package ch.hsr.se2.kartenverwaltung.services;

import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ch.hsr.se2.kartenverwaltung.domain.Card;

/**
 * Created by Fehr on 09.04.2015.
 */
public class JsonRequestHandler implements JsonEventInterface{

    public static final String TAG = JsonRequestHandler.class.getSimpleName();

    // json array response url
    private final String URL_JSON_POST = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/PostFeed";
    private final String URL_JSON_GET = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/GetFeeds";

    private Card postCard;

    private ArrayList<Card> getCardList;

    private JsonEventInterface ie;

    public JsonRequestHandler(JsonEventInterface event){
       getCardList = new ArrayList<Card>();
       ie=event;
    }

    public ArrayList<Card> jsonGetList() {
        return getCardList;
    }

    public void jsonPostMethod(Card card) {

        postCard = card;

        StringRequest req = new StringRequest(Request.Method.POST, URL_JSON_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSONPostMethod", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> jsonParams = new HashMap<String, String>();
                jsonParams.put("id", Integer.toString(postCard.getCardId()));
                jsonParams.put("name", postCard.getCardName());
                jsonParams.put("description", postCard.getDescription());
                jsonParams.put("defaultattributes", "Imanattribute");
                Log.d("JsonPostParams", jsonParams.toString());
                return jsonParams;
            }
            ;
        };
        JsonServiceHandler.getInstance().addToRequestQueue(req);}

    public void jsonResponseFinished(){}

    public void jsonGetMethod() {

        JsonArrayRequest req = new JsonArrayRequest(URL_JSON_GET, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Log response from server
                Log.d(TAG, response.toString());

                try {
                    // Parsing json array response
                    // loop through each json object

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject card = (JSONObject) response.get(i);

                        int id = card.getInt("id");
                        String name = card.getString("name");
                        String description = card.getString("description");
                        getCardList.add(new Card(id, name, description));
                    }
                    ie.jsonResponseFinished();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        // Adding request to request queue
        JsonServiceHandler.getInstance().addToRequestQueue(req);
    }
}

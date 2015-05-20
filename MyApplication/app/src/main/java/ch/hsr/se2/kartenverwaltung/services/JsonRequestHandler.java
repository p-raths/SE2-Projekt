package ch.hsr.se2.kartenverwaltung.services;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

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

import ch.hsr.se2.kartenverwaltung.activities.LoginActivity;
import ch.hsr.se2.kartenverwaltung.activities.OverviewActivity;
import ch.hsr.se2.kartenverwaltung.domain.Attribute;
import ch.hsr.se2.kartenverwaltung.domain.Card;

/**
 * Created by Fehr on 09.04.2015.
 */
public class JsonRequestHandler implements JsonEventInterface{

    public static final String TAG = JsonRequestHandler.class.getSimpleName();

    // json array response url
    private final String URL_JSON_POST = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/PostFeed";
    private final String URL_JSON_GET = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/GetCards";
    private final String URL_JSON_LOGIN = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/Login";


    private Card postCard;

    private ArrayList<Card> getCardList;

    private JsonEventInterface jsonEvent;

    private Map<String, String> jsonPostParams;

    private Card cardToMap;

    private String loginRespond = null;


    public JsonRequestHandler(JsonEventInterface event){
       getCardList = new ArrayList<Card>();
       jsonEvent =event;
    }



    public void jsonResponseFinished(){}

    public ArrayList<Card> jsonGetList() {
        return getCardList;
    }

    // method for insert / update / delete
    public void jsonAddCardMethod(Card card) {
        cardToMap = card;
        StringRequest req = new StringRequest(Request.Method.POST, URL_JSON_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSONPostMethod", response);
                jsonEvent.jsonResponseFinished();
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
                jsonParams.put("id", Integer.toString(cardToMap.getCardId()));
                jsonParams.put("cardName", cardToMap.getCardName());
                jsonParams.put("cardDescription", cardToMap.getDescription());
                Log.d("AddCardActivitygetP", jsonParams.toString());
                return jsonParams;
            }
        };
        JsonServiceHandler.getInstance().addToRequestQueue(req);}

    public void jsonDeleteCardMethod(Card card) {
        cardToMap = card;
        StringRequest req = new StringRequest(Request.Method.POST, URL_JSON_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSONPostMethod", response);
                jsonEvent.jsonResponseFinished();
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
                jsonParams.put("id", Integer.toString(cardToMap.getCardId()));
                jsonParams.put("name", "");
                jsonParams.put("description", "");
                jsonParams.put("defaultattributes", "");
                Log.d("AddCardActivitygetP", jsonParams.toString());
                return jsonParams;
            }};
        JsonServiceHandler.getInstance().addToRequestQueue(req);}

    public boolean jsonUpdateCardMethod(Card card){
        cardToMap = card;

        StringRequest req = new StringRequest(Request.Method.POST, URL_JSON_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSONPostMethod", response.toString());
                jsonEvent.jsonResponseFinished();
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
                jsonParams.put("id", Integer.toString(cardToMap.getCardId()));
                jsonParams.put("name", cardToMap.getCardName());
                jsonParams.put("description", cardToMap.getDescription());
                jsonParams.put("defaultattributes", "Imanattribute");
                jsonParams.put("revision", Integer.toString(cardToMap.getRevision()));
                Log.d("JsonUpdateCard", jsonParams.toString());
                return jsonParams;
            };};
        JsonServiceHandler.getInstance().addToRequestQueue(req);
        return true;}

    public void jsonGetMethod() {
        JsonArrayRequest req = new JsonArrayRequest(URL_JSON_GET, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response Get", response.toString());
                try {
                    // Parsing json array response
                    // loop through each json object
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject card = (JSONObject) response.get(i);
                        int id = card.getInt("catId");
                        String name = card.getString("cardName");
                        String description = card.getString("cardDescription");
                        getCardList.add(new Card(id, name, description));
                    } jsonEvent.jsonResponseFinished();
                } catch (JSONException e) { e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error Get", "Error: " + error.getMessage());
            }
        });
        // Adding request to request queue
        JsonServiceHandler.getInstance().addToRequestQueue(req);}

    public void jsonLoginMethod(Map<String, String> jsonParams, final Activity fActivity) {

        jsonPostParams = jsonParams;


        StringRequest req = new StringRequest(Request.Method.POST, URL_JSON_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                loginRespond=response.toString();
                Log.d("JSONPostMethod", loginRespond);
                //jsonEvent.jsonResponseFinished();


                LoginActivity login = (LoginActivity)fActivity;

                login.loginMethod(loginRespond);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                return jsonPostParams;
            }
        };
        JsonServiceHandler.getInstance().addToRequestQueue(req);

    }

}







package ch.hsr.se2.kartenverwaltung.services;

import android.app.Activity;
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

import ch.hsr.se2.kartenverwaltung.activities.LoginActivity;

/**
 * Created by Fehr on 09.04.2015.
 */
public class JsonRequestHandler implements JsonEventInterface {

    public static final String TAG = JsonRequestHandler.class.getSimpleName();

    // json array response url
    private final String URL_JSON_INSERT = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/PostCardInsertForm";
    private final String URL_JSON_DELETE = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/PostCardDelete";
    private final String URL_JSON_UPDATE = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/PostCardUpdate";
    private final String URL_JSON_POST = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/PostFeed";
    private final String URL_JSON_GET = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/GetCards";
    private final String URL_JSON_LOGIN = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/Login";


    private domain.Card postCard;

    private ArrayList<domain.Card> getCardList;

    private JsonEventInterface jsonEvent;

    private Map<String, String> jsonAddParams;
    private Map<String, String> jsonLoginParams;

    private domain.Card cardToMap;

    private String loginRespond = "";


    public JsonRequestHandler(JsonEventInterface event) {
        getCardList = new ArrayList<domain.Card>();
        jsonEvent = event;
    }

    public void jsonResponseFinished() {
    }

    public ArrayList<domain.Card> jsonGetList() {
        return getCardList;
    }

    public void jsonAddCardMethod(domain.Card card) {
        cardToMap = card;

        StringRequest req = new StringRequest(Request.Method.POST, URL_JSON_INSERT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSONAddCard", response.toString());
                jsonEvent.jsonResponseFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {;

            @Override
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> jsonParams = new HashMap<String, String>();
                jsonParams.put("id", Integer.toString(cardToMap.getId()));
                jsonParams.put("cardName", cardToMap.getName());
                jsonParams.put("cardDescription", cardToMap.getDescription());
                jsonParams.put("cardRevision", Integer.toString(cardToMap.getRevision()));
                Log.d("JsonInsertParams", jsonParams.toString());
                return jsonParams;
            }
        };
        JsonServiceHandler.getInstance().addToRequestQueue(req);
    }

    public void jsonDeleteCardMethod(domain.Card card) {
        cardToMap = card;
        StringRequest req = new StringRequest(Request.Method.POST, URL_JSON_DELETE, new Response.Listener<String>() {
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
                jsonParams.put("id", Integer.toString(cardToMap.getId()));
                return jsonParams;
            }
        };
        JsonServiceHandler.getInstance().addToRequestQueue(req);
    }

    public boolean jsonUpdateCardMethod(domain.Card card) {
        cardToMap = card;

        StringRequest req = new StringRequest(Request.Method.POST, URL_JSON_UPDATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSONUpdateCard", response.toString());
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
                jsonParams.put("id", Integer.toString(cardToMap.getId()));
                jsonParams.put("cardName", cardToMap.getName());
                jsonParams.put("cardDescription", cardToMap.getDescription());
                jsonParams.put("cardRevision", Integer.toString(cardToMap.getRevision()));

                Log.d("JsonUpdateCard", jsonParams.toString());
                return jsonParams;
            }

            ;
        };
        JsonServiceHandler.getInstance().addToRequestQueue(req);
        return true;
    }

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
                        int id = card.getInt("id");
                        String name = card.getString("cardName");
                        String description = card.getString("cardDescription");
                        getCardList.add(new domain.Card(name, description, 0, new domain.CardType("CardTypeName", "CardTypeDescription", "Attribute"), new domain.User()));
                    }
                    Log.d("cardlist", getCardList.toString());
                    jsonEvent.jsonResponseFinished();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error Get", "Error: " + error.getMessage());
            }
        });
        // Adding request to request queue
        JsonServiceHandler.getInstance().addToRequestQueue(req);
    }

 /*   public void jsonLoginMethod(Map<String, String> jsonParams, final Activity fActivity, final String user) {

        jsonLoginParams = jsonParams;

        StringRequest req = new StringRequest(Request.Method.POST, URL_JSON_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("JSONLoginRespo", response);
                loginRespond = response.toString();


                LoginActivity login = (LoginActivity) fActivity;

                login.loginMethod(loginRespond, user);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Log.d("JSONLoginParams", jsonLoginParams.toString());
                return jsonLoginParams;
            }
        };
        JsonServiceHandler.getInstance().addToRequestQueue(req);
    }*/
}







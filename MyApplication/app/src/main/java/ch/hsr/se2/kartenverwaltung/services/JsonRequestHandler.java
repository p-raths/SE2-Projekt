package ch.hsr.se2.kartenverwaltung.services;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
    private final String URL_JSON_POST_CARD = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/InsertCard";
    private final String URL_JSON_GET = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/GetCards";
    private final String URL_JSON_LOGIN = "http://sinv-56072.edu.hsr.ch/restfulproject/WebService/Login";


    private Card postCard;

    private ArrayList<Card> getCardList;

    private JsonEventInterface jsonEvent;

    private Map<String, String> jsonAddParams;
    private Map<String, String> jsonLoginParams;

    private Card cardToMap;

    private String loginRespond = "";


    public JsonRequestHandler(JsonEventInterface event){
       getCardList = new ArrayList<Card>();
       jsonEvent =event;
    }



    public void jsonResponseFinished(){}

    public ArrayList<Card> jsonGetList() {
        return getCardList;
    }
    /*
        public void jsonAddCardMethod(Card card) {
            cardToMap = card;
            StringRequest req = new StringRequest(Request.Method.POST, URL_JSON_POST_CARD, new Response.Listener<String>() {
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
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id", new Integer(1).toString());
                    params.put("cardName", "1234");
                    params.put("cardRevision", new Integer(0).toString());
                    params.put("cardDescription", "4321");
                    Log.d("params", params.toString());
                    return params;
                };

                @Override
                protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                    Map<String, String> jsonParams = new HashMap<String, String>();
                    jsonParams.put("id", Integer.toString(cardToMap.getCardId()));
                    jsonParams.put("name", cardToMap.getCardName());
                    jsonParams.put("description", cardToMap.getDescription());
                    jsonParams.put("defaultAttributes", cardToMap.getAttribut());
                    Log.d("AddCardActivitygetP", jsonParams.toString());
                    return jsonParams;
                }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            return headers;
            }

        };
        JsonServiceHandler.getInstance().addToRequestQueue(req);}
                */

   public void jsonAddCardMethod(Card card) {

       JSONObject js = new JSONObject();
        try {
            JSONObject jsonobject_card = new JSONObject();
            jsonobject_card.put("id", 0);
            jsonobject_card.put("cardName", "1234");
            jsonobject_card.put("cardRevision", 0);
            jsonobject_card.put("cardDescription", "4321");

            JSONObject jsonobject_user = new JSONObject();
            jsonobject_user.put("usrId", 1);
            jsonobject_user.put("usrPassword", "qUqP5cyxm6YcTAhz05Hph5gvu9M=");
            jsonobject_user.put("usrName", "admin");

            JSONObject jsonobject_cardType = new JSONObject();
            jsonobject_cardType.put("catDefaultAttributes", "DefaultAttributes");
            jsonobject_cardType.put("catDescription", "CardTypeDescriptionTest");
            jsonobject_cardType.put("catId", 1);
            jsonobject_cardType.put("catName", "CardTypeTest");

            jsonobject_card.put("user", jsonobject_user);
            jsonobject_card.put("cardType", jsonobject_cardType);
            js.put("card", jsonobject_card.toString());
            Log.d("JsonObjectPost", jsonobject_card.toString());


        }catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,URL_JSON_POST_CARD, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

    };JsonServiceHandler.getInstance().addToRequestQueue(jsonObjReq);}


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
                        int id = card.getInt("id");
                        String name = card.getString("cardName");
                        String description = card.getString("cardDescription");
                        getCardList.add(new Card(id, name, description));
                    }
                    Log.d("cardlist", getCardList.toString());
                    jsonEvent.jsonResponseFinished();
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

    public void jsonLoginMethod(Map<String, String> jsonParams, final Activity fActivity, final String user) {

        jsonLoginParams = jsonParams;

        StringRequest req = new StringRequest(Request.Method.POST, URL_JSON_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("JSONLoginRespo", response);
                loginRespond=response.toString();


                LoginActivity login = (LoginActivity)fActivity;

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
    }
}







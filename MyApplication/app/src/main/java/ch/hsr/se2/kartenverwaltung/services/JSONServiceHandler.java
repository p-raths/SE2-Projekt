package ch.hsr.se2.kartenverwaltung.services;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Fehr on 07.04.2015.
 */
public class JSONServiceHandler extends Application{


    public static final String TAG = JSONServiceHandler.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static JSONServiceHandler mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Log.d("ServiceHandler: ","Created");
    }

    public static synchronized JSONServiceHandler getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        Log.d("ServiceHandler: ","addToRequest");
        req.setTag(TAG);
        getRequestQueue().add(req);

    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}

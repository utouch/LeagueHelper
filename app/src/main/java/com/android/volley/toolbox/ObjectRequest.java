package com.android.volley.toolbox;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;

/**
 * Created by zs on 15/8/5.
 * <p>
 * Description
 */
public class ObjectRequest extends Request<NetworkResponse> {

    /** The default socket timeout in milliseconds */
    public static final int DEFAULT_TIMEOUT_MS = 2500;

    /** The default number of retries */
    public static final int DEFAULT_MAX_RETRIES = 1;

    /** The default backoff multiplier */
    public static final float DEFAULT_BACKOFF_MULT = 1f;

    public ObjectRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(NetworkResponse response) {

    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);

    }

    @Override
    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        DefaultRetryPolicy drp = new DefaultRetryPolicy(DEFAULT_TIMEOUT_MS,DEFAULT_MAX_RETRIES,DEFAULT_BACKOFF_MULT);
        return super.setRetryPolicy(drp);
    }



}

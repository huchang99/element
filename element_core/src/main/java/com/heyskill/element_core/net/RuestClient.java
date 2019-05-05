package com.heyskill.element_core.net;

import com.heyskill.element_core.net.callback.HttpMethod;
import com.heyskill.element_core.net.callback.IError;
import com.heyskill.element_core.net.callback.IFailure;
import com.heyskill.element_core.net.callback.IRequest;
import com.heyskill.element_core.net.callback.ISuccess;
import com.heyskill.element_core.net.callback.RequestCallback;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RuestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RuestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody body;

    public RuestClient(String url,
                       Map<String, Object> params,
                       IRequest request,
                       ISuccess success,
                       IFailure failure,
                       IError error,
                       RequestBody body) {
        URL = url;
        PARAMS.putAll(params);
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        this.body = body;
    }

    public static RuestClientBuilder builder() {
        return new RuestClientBuilder();
    }

    private void request(HttpMethod method) {
        RuestInterface retrofit_client = RuestCreator.getRuestInterface();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = retrofit_client.get(URL, PARAMS);
                break;
            case POST:
                call = retrofit_client.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = retrofit_client.postRaw(URL, body);
                break;
            case PUT:
                call = retrofit_client.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = retrofit_client.putRaw(URL, body);
                break;
            case DELETE:
                call = retrofit_client.delete(URL, PARAMS);
                break;
            case UPLOAD:
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallback(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);

    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

}

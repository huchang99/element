package com.heyskill.element_core.net;

import com.heyskill.element_core.net.callback.IError;
import com.heyskill.element_core.net.callback.IFailure;
import com.heyskill.element_core.net.callback.IRequest;
import com.heyskill.element_core.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RuestClientBuilder {

    private String mUrl;
    private static final Map<String, Object> PARAMS = RuestCreator.getParams();
    private IRequest mRequest;
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IError mError;
    private RequestBody mbody;

    public RuestClientBuilder url(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    public RuestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public RuestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public RuestClientBuilder onRequest(IRequest iRequest) {
        this.mRequest = iRequest;
        return this;
    }

    public RuestClientBuilder success(ISuccess mSuccess) {
        this.mSuccess = mSuccess;
        return this;
    }

    public RuestClientBuilder failure(IFailure mFailure) {
        this.mFailure = mFailure;
        return this;
    }

    public RuestClientBuilder error(IError mError) {
        this.mError = mError;
        return this;
    }
    public RuestClientBuilder raw(String raw) {
        this.mbody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }


    public final RuestClient build(){
        return new RuestClient(mUrl,PARAMS,mRequest,mSuccess,mFailure,mError,mbody);
    }


}



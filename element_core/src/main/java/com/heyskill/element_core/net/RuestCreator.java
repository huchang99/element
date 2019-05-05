package com.heyskill.element_core.net;

import com.heyskill.element_core.appConfig.ConfigFlag;
import com.heyskill.element_core.appConfig.ConfigGet;

import java.util.WeakHashMap;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RuestCreator {

    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) ConfigGet.getConfiguration(ConfigFlag.HOST_API);
        private static final Retrofit RETROFIT_ClIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class RuestHolder {
        private static final RuestInterface RUEST_INTERFACE = RetrofitHolder.RETROFIT_ClIENT.create(RuestInterface.class);
    }

    public static RuestInterface getRuestInterface() {
        return RuestHolder.RUEST_INTERFACE;
    }

    private static final class ParamHolder{
        public static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamHolder.PARAMS;
    }




}

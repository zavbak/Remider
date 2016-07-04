package com.anit.remider;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 79900 on 04.07.2016.
 */
public class PreferenceHelper {
    private static PreferenceHelper instannce;
    private Context context;
    private SharedPreferences sharedPreferences;

    public static final String SPLASH_IS_INVISIBLE = "splash_is_invisible";


    private PreferenceHelper() {

    }


    public static PreferenceHelper getInstance(){

          if(instannce==null){
              instannce = new PreferenceHelper();
          }

        return instannce;
    }


    public void init(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("preference",Context.MODE_PRIVATE);
    }


    public void putBoolean(String key,Boolean value){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();

    }

    public Boolean getBoolean(String key){

        return sharedPreferences.getBoolean(key,false);

    }

}

package com.kelompok3.uas_pbp_kelompok_3.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String USER_ID = "id";
    public static final String TOKEN= "token";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public UserPreferences(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUser(Long id, String name, String token, String email, String password){

        editor.putBoolean(IS_LOGIN, true);
        editor.putLong(USER_ID,id);
        editor.putString(TOKEN,token);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PASSWORD,password);

        editor.commit();
    }

    public void updateUser(Long id, String name, String token, String email, String password){
        editor.clear();
        editor.commit();

        editor.putBoolean(IS_LOGIN, true);
        editor.putLong(USER_ID,id);
        editor.putString(TOKEN,token);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PASSWORD,password);

        editor.commit();
    }

    public Long getUserLogin_id(){
        return sharedPreferences.getLong(USER_ID,0);
    }

    public String getUserLogin_token(){
        return sharedPreferences.getString(TOKEN,null);
    }

    public String getUserLogin_name(){
        return sharedPreferences.getString(KEY_NAME,null);
    }

    public String getUserLogin_email(){
        return sharedPreferences.getString(KEY_EMAIL,null);
    }

    public String getUserLogin_password(){
        return sharedPreferences.getString(KEY_PASSWORD,null);
    }

    public boolean checkLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }
}

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
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public UserPreferences(Context context) {
        this.context = context;
        /* penamaan bebas namun disini digunakan "userPreferences" */
        sharedPreferences = context.getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUser(int id, String token, String email, String password){

        /* Menyimpan data login ke sharedPreferences dengan key dan value  */
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(USER_ID,id);
        editor.putString(TOKEN,token);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PASSWORD,password);

        /* Jangan lupa commit karena kalo hanya set editonya saja tidak commit akan sia-sia */
        editor.commit();
    }

    public int getUserLogin_id(){
        return sharedPreferences.getInt(USER_ID,0);
    }
    public String getUserLogin_token(){
        return sharedPreferences.getString(TOKEN,null);
    }

    public String getUserLogin_email(){
        return sharedPreferences.getString(KEY_EMAIL,null);
    }

    public String getUserLogin_password(){
        return sharedPreferences.getString(KEY_PASSWORD,null);
    }

    public boolean checkLogin(){
        /* Mengembalikan nilai is_login, jika sudah login otomatis nilai true jika tidak akan return false */
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void logout(){
        /* Melakukan clear data yang ada pada sharedPreferences  , jangan lupa di commit agar data terubah*/
        editor.clear();
        editor.commit();
    }
}

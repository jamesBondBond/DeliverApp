package com.example.anshul.deliverapp.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.anshul.deliverapp.utility.AppConstants;


public class PreferenceManager {

    private PreferenceManager() {
        super();
    }


    /**
     * Method to add String values in Preferences.
     *
     * 
     * @return void
     */
    public static void saveStringInPref(Context context, String key,
                                        String value) {
        SharedPreferences prefs = context.getSharedPreferences(
                AppConstants.M_SHARED_PREFERENCE_FILE, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }


    /**
     * Method to get String values in Preferences.
     * @return void
     */
    public static String getStringInPref(Context context, String key,
                                         String defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(
                AppConstants.M_SHARED_PREFERENCE_FILE, 0);
        return prefs.getString(key, defaultValue);
    }


    /**
     * Method to add String values in Preferences.
     *
     *
     * @return void
     */
    public static void saveLongInPref(Context context, String key,
                                        long value) {
        SharedPreferences prefs = context.getSharedPreferences(
                AppConstants.M_SHARED_PREFERENCE_FILE, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);
        editor.commit();
    }


    /**
     * Method to get String values in Preferences.
     * @return void
     */
    public static long getLongInPref(Context context, String key,
                                         long defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(
                AppConstants.M_SHARED_PREFERENCE_FILE, 0);
        return prefs.getLong(key, defaultValue);
    }
}

package it.jaschke.alexandria;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import it.jaschke.alexandria.data.BookProvider;

/**
 * Created by Olga on 12/6/2015.
 */
public class Utility {

    /**
     * Returns true if there is an available network or one is about to become available
     * @param c
     * @return
     */
   static public boolean isNetworkAvailable(Context c){
       ConnectivityManager cm = (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);

       NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
       return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
   }

    /**
     * @olga gets the isbn status from the SharedPreference
     * @param context - context to get SharedPreference for
     * @return int isbn status
     */
    @SuppressWarnings("ResourceType")
    static public @BookProvider.ISBN_Status int getIsbnStatus(Context context){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getInt(context.getString(R.string.isbn_status_key), BookProvider.ISBN_STATUS_UNKNOWN);
    }
}

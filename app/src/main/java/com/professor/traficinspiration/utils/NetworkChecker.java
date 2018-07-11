package com.professor.traficinspiration.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.professor.traficinspiration.ApplicationContext;

public class NetworkChecker {

    public static boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) ApplicationContext.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}

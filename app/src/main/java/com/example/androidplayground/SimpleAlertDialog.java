package com.example.androidplayground;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SimpleAlertDialog {
    public static void displayWithOK(Context c, String message) {
        displayWithOK(c, message, "CodePath Demo Intro Android");
    }

    public static void displayWithOK(Context c, String message, String title) {
        final AlertDialog alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.hide();
            }
        });
        alertDialog.show();
    }
}

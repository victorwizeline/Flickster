package com.codepath.flickster.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.codepath.flickster.R;
import com.codepath.flickster.interfaces.DialogsInterface;

public class Dialogs {

    private static final Dialogs ourInstance = new Dialogs();

    public static Dialogs getInstance() {
        return ourInstance;
    }

    public void showAlert(final Context context, DialogsInterface dialogsInterface) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.alert)
                .setCancelable(false)
                .setMessage(R.string.internet)
                .setNegativeButton(R.string.cancel, (dialog, which) -> ((Activity) context).finish())
                .setPositiveButton(R.string.retry, (dialog, which) -> dialogsInterface.onRetry())
                .show();
    }

    public AlertDialog showProgressDizlog(Context context) {
        return new ProgressDialog.Builder(context)
                .setMessage(R.string.loading)
                .setCancelable(false)
                .show();
    }
}

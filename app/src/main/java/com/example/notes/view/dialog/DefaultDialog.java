package com.example.notes.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;

import com.example.notes.R;
import com.example.notes.entity.Note;

public abstract class DefaultDialog extends AlertDialog {
    private Context context;

    protected DefaultDialog(Context context) {
        super(context);
        this.context = context;
    }

    /*
        *don't forget show dialog
     */
    public AlertDialog createDialog(String title, @LayoutRes int layout, OnClickListener positiveButton, OnClickListener negativeButton) {
        View view = LayoutInflater.from(context).inflate(layout, null);
        return new AlertDialog.Builder(context).
                setTitle(title)
                .setView(view)
                .setPositiveButton(R.string.positiveButton, positiveButton)
                .setNegativeButton(R.string.negativeButton, negativeButton)
                .create();
    }

    public abstract OnClickListener createClickListenerForPositiveButton(Note newNote);
    public abstract OnClickListener createClickListenerForNegativeButton();
}

package com.example.notes.util;

import android.content.Context;
import android.widget.Toast;

public class Util {

    public static void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

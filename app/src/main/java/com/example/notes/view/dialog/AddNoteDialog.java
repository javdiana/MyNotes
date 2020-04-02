package com.example.notes.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

import com.example.notes.R;
import com.example.notes.entity.Note;
import com.example.notes.util.DatabaseUtil;
import com.example.notes.util.Util;
import com.example.notes.view.activity.NotesViewModel;

public class AddNoteDialog extends DefaultDialog {
    private NotesViewModel notesViewModel;
    private Note note;
    private Context context;

    public AddNoteDialog(Context context) {
        super(context);
        notesViewModel = new NotesViewModel();
        this.context = context;
    }

    @Override
    public OnClickListener createClickListenerForPositiveButton(Note newNote) {
        return new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final EditText textNoteEditText = ((AlertDialog)dialog).findViewById(R.id.textNoteEditText);
                Note note = new Note();
                note.setId(DatabaseUtil.getLastIdFromDatabase() + 1L);
                note.setTextNote(textNoteEditText.getText().toString());
                notesViewModel.saveNote(note);
            }
        };
    }
}

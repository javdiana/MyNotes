package com.example.notes.view.dialog;

import android.content.Context;
import android.content.DialogInterface;

import com.example.notes.R;
import com.example.notes.entity.Note;
import com.example.notes.util.Util;
import com.example.notes.view.activity.NotesViewModel;

public class DeleteNoteDialog extends DefaultDialog {
    private NotesViewModel notesViewModel;
    private Context context;

    public DeleteNoteDialog(Context context) {
        super(context);
        notesViewModel = new NotesViewModel();
        this.context = context;
    }

    @Override
    public OnClickListener createClickListenerForPositiveButton(final Note note) {
        return new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notesViewModel.deleteNote(note.getId());
            }
        };
    }

    @Override
    public OnClickListener createClickListenerForNegativeButton() {
        return new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Util.showToast(context.getString(R.string.failDeletingNote), context);
            }
        };
    }
}

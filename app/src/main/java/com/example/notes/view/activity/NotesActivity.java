package com.example.notes.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.entity.Note;
import com.example.notes.view.activity.NoteAdapter.NotesDiffUtilCallBack;
import com.example.notes.view.dialog.AddNoteDialog;
import com.example.notes.view.dialog.DefaultDialog;
import com.example.notes.view.dialog.DeleteNoteDialog;
import com.example.notes.view.dialog.EditNoteDialog;

import static com.example.notes.view.activity.NoteAdapter.ActionNoteOnClickLister;

public class NotesActivity extends AppCompatActivity {
    private NotesViewModel notesViewModel;
    private NoteAdapter adapter;
    private ActionNoteOnClickLister actionNoteOnClickLister = new ActionNoteOnClickLister() {
        @Override
        public void update(Note newNote) {
            DefaultDialog editNoteDialog = new EditNoteDialog(NotesActivity.this);
            editNoteDialog.createDialog(getString(R.string.editingNote), R.layout.dialog_note_add_edit, editNoteDialog.createClickListenerForPositiveButton(newNote), editNoteDialog.createClickListenerForNegativeButton()).show();
            updateNotes();
        }

        @Override
        public void delete(Long id) {
            Note note = new Note();
            note.setId(id);
            DefaultDialog deleteNoteDialog = new DeleteNoteDialog(NotesActivity.this);
            deleteNoteDialog.createDialog(getString(R.string.deletingNote), R.layout.dialog_delete_note, deleteNoteDialog.createClickListenerForPositiveButton(note), deleteNoteDialog.createClickListenerForNegativeButton()).show();
            updateNotes();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        notesViewModel = new NotesViewModel();

        configNotesAdapter();
        configViews();
    }

    private void configNotesAdapter() {
        RecyclerView recyclerView = findViewById(R.id.notesRecyclerView);
        adapter = new NoteAdapter(actionNoteOnClickLister);
        adapter.setNotes(notesViewModel.getNotes());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void configViews() {
        ImageView addNoteImageView = findViewById(R.id.addNoteImageView);
        addNoteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefaultDialog addNoteDialog = new AddNoteDialog(NotesActivity.this);
                addNoteDialog.createDialog(getString(R.string.addingNote), R.layout.dialog_note_add_edit, addNoteDialog.createClickListenerForPositiveButton(null), addNoteDialog.createClickListenerForNegativeButton()).show();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void updateNotes() {
        DiffUtil.Callback notesDiffUtilCallBack = new NotesDiffUtilCallBack(adapter.getNotes(), notesViewModel.getNotes());
        DiffUtil.DiffResult noDiffResult = DiffUtil.calculateDiff(notesDiffUtilCallBack);
        adapter.setNotes(notesViewModel.getNotes());
        noDiffResult.dispatchUpdatesTo(adapter);
    }

}

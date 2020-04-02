package com.example.notes.view.activity;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.entity.Note;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<? extends RealmObject> notes;
    private ActionNoteOnClickLister actionNoteOnClickLister;

    public NoteAdapter(/*List<? extends RealmObject> notes,*/ ActionNoteOnClickLister actionNoteOnClickLister) {
        //this.notes = notes;
        this.actionNoteOnClickLister = actionNoteOnClickLister;
    }

    public void setNotes(List<? extends RealmObject> notes) {
        this.notes = notes;
    }

    public List<? extends RealmObject> getNotes() {
        if (notes.size() > 0) return notes;
        else return new ArrayList<>();
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        holder.bind((Note) notes.get(position), actionNoteOnClickLister);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        private TextView noteTextView;
        private ImageView updateImageView;
        private ImageView deleteImageView;
        private CheckBox isDoneCheckBox;
        private Note note;
        private ActionNoteOnClickLister actionNoteOnClickLister;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            noteTextView = itemView.findViewById(R.id.noteTextView);
            updateImageView = itemView.findViewById(R.id.updateImageView);
            updateImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateNote(note);
                }
            });

            deleteImageView = itemView.findViewById(R.id.deleteImageView);
            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteNote(note.getId());
                }
            });

            isDoneCheckBox = itemView.findViewById(R.id.isDoneCheckBox);
            isDoneCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDone();
                }
            });
        }

        public void bind(Note note, ActionNoteOnClickLister actionNoteOnClickLister) {
            this.note = note;
            this.actionNoteOnClickLister = actionNoteOnClickLister;

            noteTextView.setText(note.getTextNote());
            if (note.getDone() != null) {
                isDoneCheckBox.setChecked(note.getDone());
            }
        }

        private void setDone() {
            if (note.getDone() != null) {
                note.setDone(isDoneCheckBox.isChecked());
                actionNoteOnClickLister.update(note);
                setCrossedUpToTextNote();
            }
        }

        private void setCrossedUpToTextNote() {
            if (note.getDone()) {
                noteTextView.setPaintFlags(noteTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                noteTextView.setPaintFlags(0);
            }
            noteTextView.setText(note.getTextNote());
        }

        private void deleteNote(Long id) {
            actionNoteOnClickLister.delete(id);
        }

        private void updateNote(Note newNote) {
            actionNoteOnClickLister.update(newNote);
        }

    }

    interface ActionNoteOnClickLister {
        void update(Note newNote);

        void delete(Long id);
    }

    public static class NotesDiffUtilCallBack extends DiffUtil.Callback {
        private List<? extends RealmObject> oldList;
        private List<? extends RealmObject> newList;

        public NotesDiffUtilCallBack(List<? extends RealmObject> oldList, List<? extends RealmObject> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition) == newList.get(newItemPosition);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }
    }
}

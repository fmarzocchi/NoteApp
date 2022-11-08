package com.example.roomripasso;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);
    @Update
    void update(Note note);
    @Delete
    void delete(Note note);

    @Query("Delete from note_table")
    void deleteAllNotes();
    @Query("Select * from note_table order by id ")
    LiveData<List<Note>> getAllNotes();



    //TODO devo inserire il metodo findNoteById che chiede l'id e ritorna un Note.
    // istanzio quel Note nel NoteContent e lo uso per chiamare un setContent.
    // dopo che ho settato il content, chiamo il benedetto metodo update e ci passo quell'oggetto Note

}

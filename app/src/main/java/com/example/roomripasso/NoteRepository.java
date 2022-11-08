package com.example.roomripasso;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    private Note note;

    public NoteRepository (Application application){

        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert ( Note note){
        NoteDatabase.executorService.execute(()->{
            noteDao.insert(note);
        });
    }
    public void update ( Note note){
        NoteDatabase.executorService.execute(()->{
            noteDao.update(note);
        });
    }
    public void delete (Note note){
        NoteDatabase.executorService.execute(()->{
            noteDao.delete(note);
        });
    }
    public void deleteAllNotes(){
        NoteDatabase.executorService.execute(()->{
            noteDao.deleteAllNotes();
        });
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }





}

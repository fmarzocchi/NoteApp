package com.example.roomripasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    private EditText title;
    private EditText content;
    private Note note;
    public static final String INTENT_ADDNOTE = "com.example.roomripasso.AddNoteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title = findViewById(R.id.edtxt_title);
        content = findViewById(R.id.edtxt_content);
        // verifico se siamo venuti in questa Activity per creare una nuova nota o se ne stiamo modificando
        // una già esistente. Se si tratta della seconda opzione, riprendo quella nota e setto il titolo
        // e il contenuto
        if (!getIntent().getBooleanExtra(MainActivity.INTENT_MAIN,false)){
            if (getIntent().getSerializableExtra("note")!=null){
                note = (Note) getIntent().getSerializableExtra("note");
                title.setText(note.getTitle());
                content.setText(note.getContent());
            }
        }

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");

    }

    private void saveNote(){
        // se stiamo salvano una nuova nota entro in questo metodo e la creo
        if (getIntent().getBooleanExtra(MainActivity.INTENT_MAIN,false)){
            note = new Note(title.getText().toString(),content.getText().toString());
            NoteDatabase.executorService.execute(()->{
                NoteViewModel noteViewModel = new NoteViewModel(getApplication());
                noteViewModel.insert(note);
            });
            Toast.makeText(this,"Nota salvata!",Toast.LENGTH_SHORT).show();
        } else {
            // altrimenti qui e la modifico
            note = (Note) getIntent().getSerializableExtra("note");
            note.setContent(content.getText().toString());
            note.setTitle(title.getText().toString());
            NoteDatabase.executorService.execute(()->{
                NoteViewModel noteViewModel = new NoteViewModel(getApplication());
                noteViewModel.update(note);
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    public void getBackToMain(Intent intent){
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getBackToMain(new Intent(AddNoteActivity.this,MainActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                getBackToMain(new Intent(AddNoteActivity.this,MainActivity.class));
                return true;
            case android.R.id.home:
                getBackToMain(new Intent(AddNoteActivity.this,MainActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
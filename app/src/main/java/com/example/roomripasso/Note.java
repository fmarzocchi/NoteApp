package com.example.roomripasso;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "note_table")
public class Note implements Serializable {

    @PrimaryKey ( autoGenerate = true)
    private int id;
    private String title;
    @ColumnInfo(name = "content")
    private String content;

    public Note(String title,String content) {
        this.title = title;
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

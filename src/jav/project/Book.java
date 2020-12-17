package jav.project;

import jav.project.Frame;

public class Book {
    private String title;
    private String author;
    private int release;
    private int column;
    private int row;
    private String pitch;

    public Book(String title, String author, int release, int column, int row, String pitch) {
        this.title = title;
        this.author = author;
        this.release = release;
        this.column = column;
        this.row = row;
        this.pitch = pitch;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public int getRelease(){
        return release;
    }

    public int getColumn(){
        return column;
    }

    public int getRow(){
        return row;
    }

    public String getPitch(){
        return pitch;
    }

}
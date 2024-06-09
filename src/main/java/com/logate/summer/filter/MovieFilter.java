package com.logate.summer.filter;

public class MovieFilter {
    String title;
    String genre;
    String year;

    @Override
    public String toString() {
        return "MovieFilter{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

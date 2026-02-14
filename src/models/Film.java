package models;

public class Film {
    private int id;
    private String title;
    private int duration;
    private int genreId;
    private String genreName;

    public Film(String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public Film(String title, int duration, int genreId) {
        this.title = title;
        this.duration = duration;
        this.genreId = genreId;
    }

    public Film(String title) {
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getDuration() { return duration; }
    public int getGenreId() { return genreId; }
    public String getGenreName() { return genreName; }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return id + ". " + title + " (" + duration + " min, " + genreName + ")";
    }
}
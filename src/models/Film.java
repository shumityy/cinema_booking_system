package models;

public class Film {
    private int id;
    private String title;
    private int duration;

    public Film(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public Film(int id, String title, int duration) {
        this(title, duration);
        this.id = id;
    }


    public Film(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getId() { return id; }

    public int getDuration() {
        return duration;
    }


    @Override
    public String toString() {
        return id + ". " + title;
    }
}
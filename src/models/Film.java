package models;

public class Film {
    private int id;
    private String title;
    private int duration;
    private String category;

    public Film(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Film(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }


    @Override
    public String toString() {
        return title;
    }
}
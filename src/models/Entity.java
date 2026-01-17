package models;

public abstract class Entity {
    private int id;
    private String name;

    public Entity() {
    }
    public Entity(int id, String name) {
        setId(id);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return id + " " + name;
    }
}

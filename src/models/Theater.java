package models;

import java.util.ArrayList;

public class Theater extends Entity{
    private String name;
    private String address;
    private ArrayList<Hall> halls;

    public Theater(int id){
        this.id = id;
    }
    public Theater(int id, String name, String address, ArrayList<Hall> halls){
        this(id);
        setName(name);
        setAddress(address);
        setHalls(halls);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Hall> getHalls() {
        return halls;
    }

    public void setHalls(ArrayList<Hall> halls) {
        this.halls = halls;
    }
}

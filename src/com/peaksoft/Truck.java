package com.peaksoft;

public class Truck {
    private int id;
    private String name;
    private String driver;
    private State state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver.getName();
        driver.setTruck(getName());
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public static Truck createTruck(int id, String name, State state) {
        Truck truck = new Truck();
        truck.id = id;
        truck.name = name;
        truck.state = state;
        return truck;
    }

    @Override
    public String toString() {
        return  "ID :" +"\t"+"\t"+ id +"\n"+
                "Truck  :" + "\t"+name + "\n" +
                "Driver :" + "\t"+driver +"\n"+
                "State  :" + "\t"+state ;
    }
}

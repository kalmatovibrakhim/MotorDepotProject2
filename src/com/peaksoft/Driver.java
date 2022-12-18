package com.peaksoft;

public class Driver {
    private int id;
    private String name;
    private String truck;

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

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }
    public void setTruck(Truck truck) {
        this.truck = truck.getName();
        truck.setDriver(getName());
    }

    public static Driver createDriver(int id, String name) {
        Driver driver = new Driver();
        driver.id = id;
        driver.name = name;
//        driver.truck = truck;
        return driver;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", truck=" + truck +
                '}';
    }
}



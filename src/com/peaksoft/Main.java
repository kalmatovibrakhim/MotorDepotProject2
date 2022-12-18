package com.peaksoft;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonToken;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static GsonBuilder GSON_BUILDER = new GsonBuilder();
    private static Gson GSON = GSON_BUILDER.create();

    private static Path truckURI = Paths.get("./truck.json");
    private static Path driverURI = Paths.get("./driver.json");

    public static void main(String[] args) throws Exception {
        /**
        * Welcome guis it is first project of second stage
         * good luck*/

        Scanner scanner = new Scanner(System.in);

        Truck[] trucks = {
                Truck.createTruck(1,"Renault Magnum",State.base),
                Truck.createTruck(2,"Volvo",State.base),
                Truck.createTruck(3,"DAF XT",State.base)
        };
        Driver[] drivers = {
                Driver.createDriver(1,"Petr"),
                Driver.createDriver(2,"Askar"),
                Driver.createDriver(3,"Uson")
        };

        String truckJson = GSON.toJson(trucks);
        String driverJson = GSON.toJson(drivers);
        writeTruck(truckJson);
        writeDriver(driverJson);


        System.out.println("Info About Trucks");
        Truck[] trucks1 = GSON.fromJson(readTruck(), Truck[].class);
        System.out.println(String.format(
                "%2s | %15s | %15s | %15s |",
                "#","Truck","Driver","State"));
        System.out.println("---+-----------------+-----------------+------------------");
        for (Truck truck : trucks1) {
            System.out.println(truck.getDriver()==null ? String.format(
                    "%2d | %15s | %15s | %15s |",
                    truck.getId(),truck.getName(),"",truck.getState()):String.format(
                    "%2d | %15s | %15s | %15s |",
                    truck.getId(),truck.getName(),truck.getDriver(),truck.getState()));
        }

        System.out.println();

        System.out.println("Info About Drivers");
        Driver[] drivers1 = GSON.fromJson(readDriver(),Driver[].class);
        System.out.println(String.format(
                "%2s | %15s | %15s |",
                "#","Driver","Truck"));
        System.out.println("---+-----------------+------------------");
        for (Driver driver : drivers1) {
            System.out.println(driver.getTruck()==null ? String.format(
                    "%2d | %15s | %15s |",
                    driver.getId(),driver.getName(),""):String.format(
                    "%2d | %15s | %15s |",
                    driver.getId(),driver.getName(),driver.getTruck()));
        }
//        System.out.println(trucks[1]);
//        trucks[1].setDriver(drivers[0]);
//        System.out.println(trucks[1]);
        TruckService service = new TruckService();
//        service.changeDriver(trucks[2],drivers);
//        System.out.println(trucks[2]);
        System.out.println("выберите грузовика или нажмите на 0 чтобы остановить программу");
        int identi = scanner.nextInt();
        int choice ;
        try{
            while (identi!=0){
                if(identi>trucks.length+1||identi<0){
                    System.out.println("выберите заново ");
                    continue;
                }
                if (identi<=trucks.length+1){
                    System.out.println(trucks[identi-1]);
                    System.out.println("если хотите отправить в путь нажмите 1");
                    System.out.println("если хотите отправить на ремонт нажмите 2");
                    System.out.println("если хотите выбрать водителя нажмите 3");
                    choice = scanner.nextInt();
                    if(choice==1){
                        service.startDriving(trucks[identi-1],drivers);
                    System.out.println(trucks[identi-1]);
                    }
                    if(choice==2){
                        service.startRepair(trucks[identi-1]);
                    System.out.println(trucks[identi-1]);
                    }
                    if(choice==3){
                        service.changeDriver(trucks[identi-1],drivers);
                    System.out.println(trucks[identi-1]);
                    }
                }
                System.out.println("выберите грузовика или нажмите на 0 чтобы остановить программу");
                identi = scanner.nextInt();

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }
    public static void writeTruck(String json){
        try{
            Files.writeString(truckURI,json, StandardOpenOption.CREATE,StandardOpenOption.WRITE);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String readTruck(){
        String json = "";
        int a ;
        try{
            FileReader reader = new FileReader(String.valueOf(truckURI));
            while ((a= reader.read())!=-1){
                json+=(char)a;
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
    public static void writeDriver(String json){
        try{
            Files.writeString(driverURI,json, StandardOpenOption.CREATE,StandardOpenOption.WRITE);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String readDriver(){
        StringBuilder json = new StringBuilder();
        int a ;
        try{
            FileReader reader = new FileReader(String.valueOf(driverURI));
            while ((a = reader.read()) != -1){
                json.append((char) a);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return json.toString();
    }
}
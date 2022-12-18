package com.peaksoft;

import java.util.Random;

public class TruckService{
    Random rd = new Random();
    public void changeDriver(Truck truck,Driver[] drivers) throws Exception{
        if(truck.getState()==State.route){
            throw new Exception("Грузовик в пути, невозможно сменить водителя");
        }
        if (truck.getState()==State.repair){
            throw new Exception("Нельзя сменить водителя");
        }
        if (truck.getState()==State.base){
            int x=0;
            for (Driver driver : drivers) {
                if(driver.getTruck()==null){
                    truck.setDriver(driver);
                    driver.setTruck(truck.getName());
                    System.out.println("Теперь грузовик "+truck.getName()+" ведёт водитель "+driver.getName());
                    break;
                }else {
                    x++;
                }
            }
            if (x==drivers.length){
                System.out.println("нет свободных водителей");
            }
//            System.out.println(truck);
        }
    }
    public void startDriving(Truck truck,Driver[] drivers) throws Exception{
        if(truck.getDriver()==null){
            for (Driver driver : drivers) {
                if(driver.getTruck()==null){
                    truck.setDriver(driver);
                    driver.setTruck(truck);
                    break;
                }
            }
//            System.out.println(truck);
        }
        if(truck.getState()==State.route){
            throw new Exception("Грузовик уже в пути");
        }
        if(truck.getState()==State.repair){
            int x = rd.nextInt(1);
            if(x==0){
                truck.setState(State.base);
                System.out.println("Грузовик на базе");
//                System.out.println(truck);
            }else {
                truck.setState(State.route);
                System.out.println("Грузовик вышел в путь");
//                System.out.println(truck);
            }
        }
        if (truck.getState()==State.base) {
            truck.setState(State.route);
            System.out.println("успешно вышли на маршрут");
//            System.out.println(truck);
        }

    }
    public void startRepair(Truck truck) throws Exception{
        if (truck.getState()==State.repair){
            throw new Exception("Уже в ремонте");
        }
        if(truck.getState()==State.base) {
            truck.setState(State.repair);
            System.out.println("Грузовик на ремонте ");
//            System.out.println(truck);
        }
        if (truck.getState()==State.route){
            truck.setState(State.repair);
            System.out.println("Грузовик на ремонте ");
//            System.out.println(truck);
        }

    }

}

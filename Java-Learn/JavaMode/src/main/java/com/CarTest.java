package com;

interface Car{
    void run();
}

class AoDiCar implements Car{
    public void run() {
        System.out.println("我是奥迪汽车");
    }
}
class BaoMaCar implements Car{
    public void run() {
        System.out.println("我是宝马汽车");
    }
}
class MaShaLaDiCar implements Car{
    public void run() {
        System.out.println("我是玛莎拉蒂汽车");
    }
}
class AoDiCarFactory{
    static public Car createCar(){
        return new AoDiCar();
    }
}
class BaoMaCarFactory{
    //相当于使用公共接口
    static public Car createCar(){
        return new BaoMaCar();
    }
}
class MaShaLaDiCarFactory{
    static public Car createCar(){
        return new MaShaLaDiCar();
    }
}
class CarFactory{
   static public Car createCar(String name){
        Car car=null;
        switch (name){
            case "奥迪":
                car=new AoDiCar();
                break;
            case "宝马":
                car=new BaoMaCar();
                break;
            case "玛莎拉蒂":
                car=new MaShaLaDiCar();
                break;
            default:
                break;
        }
        return car;
    }
}
//工厂模式
public class CarTest {
    public static void main(String[] args) {
        //简单工厂模式
        Car car=CarFactory.createCar("奥迪");
        car.run();
        Car car1=BaoMaCarFactory.createCar();
        car1.run();
    }
}

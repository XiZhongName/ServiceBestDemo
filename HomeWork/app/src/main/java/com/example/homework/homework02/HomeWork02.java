package com.example.homework.homework02;

public class HomeWork02 {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        Thread thead1 = new Thread(cat);
        Thread thead2 = new Thread(dog);
        thead1.start();
        thead2.start();
    }
}

class Cat implements Runnable{

    @Override
    public void run() {
        while (true) {
            System.out.println("小猫：喵喵叫...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Dog implements Runnable{

    @Override
    public void run() {
        while (true) {
            System.out.println("小狗：汪汪叫...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
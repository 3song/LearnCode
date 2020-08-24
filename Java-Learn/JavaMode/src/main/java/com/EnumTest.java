package com;

public class EnumTest {
    public static void main(String[] args) {
        NewOneSingleton newOneSingleton = Enum.INSTANCE.getNewOneSingleton();
        System.out.println(newOneSingleton.hashCode());
    }
}

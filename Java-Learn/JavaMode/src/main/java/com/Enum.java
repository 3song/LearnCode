package com;

public enum Enum {
    INSTANCE;
    private NewOneSingleton newOneSingleton;

    public NewOneSingleton getNewOneSingleton(){
        return newOneSingleton;
    }


}

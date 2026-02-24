package com.abdullah.model;

public enum Category {
    FOOD,
    TRANSPORT,
    GROCERIES,
    SNACKS,
    SUBSCRIPTIONS,
    OTHERS;
    @Override
    public String toString(){
        String name =name();
        return
                name.charAt(0)+name.substring(1).toLowerCase();
    }
}

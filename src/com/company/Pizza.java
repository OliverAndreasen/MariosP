package com.company;

import java.util.ArrayList;

public class Pizza {
    private String name;
    private ArrayList<String> toppings = new ArrayList();
    private int price;


    public Pizza(String name, ArrayList<String> toppings, int price) {
        this.name = name;
        this.toppings = toppings;
        this.price = price;
        defaultToppings();

    }

    public void defaultToppings() {
        toppings.add(0, "tomat");
        toppings.add(1, "ost");
        toppings.add("oregano");
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public String getToppingsToString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < toppings.size(); i++) {
            if (i + 1 == toppings.size()) {
                result.append(toppings.get(i));
            } else {
                result.append(toppings.get(i));
                result.append(",");
            }

        }
        return result.toString();
    }

    @Override
    public String toString() {
        String result = "";
        result += getName();
        result += " ";
        result += getToppingsToString();
        result += " ";
        result += getPrice();
        result += ",-";
        return result;
    }
}
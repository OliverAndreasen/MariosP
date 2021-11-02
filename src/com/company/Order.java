package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private final ArrayList<Pizza> pizzas = new ArrayList();
    private String src = "data/orders.txt";
    private final PrintWriter writer = new PrintWriter(new FileWriter(src, true));
    private int[] deliveryTime;

    public Order() throws IOException {
    }


    public void addPizzaToOrder(Pizza pizza) {
        pizzas.add(pizza);
    }

    public String orderList() {
        StringBuilder result = new StringBuilder("Your order list:\n");
        if (pizzas.size() != 0) {
            for (Pizza pizza : pizzas) {
                result.append(pizza.getName());
                result.append("\n");
            }
        } else {
            result.append("ingen ordre");
        }

        return result.toString();
    }

    public int[] deliveryTime(int hour, int minute) {
        LocalDateTime date = LocalDateTime.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        this.deliveryTime = new int[]{year, month, day, hour, minute};
        //System.out.printf("%d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
        return deliveryTime;
    }

    public void clearOrder() {
        pizzas.clear();
    }


    public void save() {
        String result = "";
        int totalCost = 0;
        for (int i = 0; i < pizzas.size(); i++) {
            totalCost = totalCost + pizzas.get(i).getPrice();
            int count = i + 1;
            if (count == pizzas.size()) {
                result += pizzas.get(i).getName();
                result += ";";
                result += totalCost;
                result += ";";
            } else {
                result += pizzas.get(i).getName();
                result += ",";
            }
        }
        for (int j = 0; j < deliveryTime.length; j++) {
            int count = j + 1;
            if (count == deliveryTime.length) {
                result += deliveryTime[j];
                result += ";";
            } else {
                result += deliveryTime[j];
                result += ",";
            }
        }
        System.out.println(result);
        writer.println(result);
        writer.flush();
    }

}
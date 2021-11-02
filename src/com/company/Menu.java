package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    String src = "data/menu.txt";
    private final Scanner sc = new Scanner(new File(src));
    private final PrintWriter writer = new PrintWriter(new FileWriter(src, true));
    private ArrayList<Pizza> pizzas = new ArrayList<>();

    public Menu() throws IOException {
        sc.useDelimiter(";");
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void save(Pizza pizza) {

        StringBuilder toppings = new StringBuilder();

        String pizzaName = pizza.getName();
        String pizzaPrice = String.valueOf(pizza.getPrice());
        for (int i = 0; i < pizza.getToppings().size(); i++) {
            if (i + 1 != pizza.getToppings().size()) {
                toppings.append(pizza.getToppings().get(i));
                toppings.append(",");
            } else {
                toppings.append(pizza.getToppings().get(i));
            }
        }

        String result = pizzaName + ";" + toppings + ";" + pizzaPrice + ";";

        writer.println(result);
        writer.flush();

    }

    public void loadMenu() {
        while (sc.hasNextLine()) {
            String pizzaName = sc.next();
            String toppings = sc.next();
            int pizzaPrice = Integer.parseInt(sc.next());
            ArrayList<String> pizzaToppings = new ArrayList<>(Arrays.asList(toppings.split(",")));
            Pizza pizza = new Pizza(pizzaName, pizzaToppings, pizzaPrice);
            addPizza(pizza);
            sc.nextLine();
        }

    }

    public Pizza selectPizzaFromMenu(int pizzaNum) {
        if (pizzaNum > 0 && pizzaNum <= pizzas.size()) {
            return pizzas.get(pizzaNum);
        }
        return null;
    }

    public String toString() {
        StringBuilder result;
        result = new StringBuilder();
        int count = 1;
        for (Pizza pizza : pizzas) {
            result.append(count).append(" ");
            result.append(pizza.toString());
            result.append("\n");
            count++;
        }
        return result.toString();
    }
}
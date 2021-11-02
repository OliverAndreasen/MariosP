package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        Order order = new Order();

        while (isRunning) {
            menu.loadMenu();
            System.out.println("Welcome to Marios Pizza");
            System.out.println("type 'menu' to show menu");
            System.out.println("type 'order' to begin your order");
            System.out.println("type 'orderlist' to see your current order list and to confirm order");
            System.out.println("type 'create' to create a new pizza");
            String input = sc.nextLine();

            switch (input) {
                case "create" -> {
                    System.out.println("Skriv pizza navn");
                    String pizzaName = sc.nextLine();
                    ArrayList<String> toppings = new ArrayList<>();
                    System.out.println("Skriv toppings eksklusiv ost og tomatsauce");
                    System.out.println("for at stoppe med at tilføje tryk enter uden at indtaste noget");
                    boolean bl = true;
                    while (bl) {
                        String pizzaTopping = sc.nextLine();
                        if (!Objects.equals(pizzaTopping, "")) {
                            toppings.add(pizzaTopping);
                        } else {
                            bl = false;
                        }
                    }
                    System.out.println("Skriv pizza pris");
                    int pizzaPrice = Integer.parseInt(sc.nextLine());
                    Pizza pizza = new Pizza(pizzaName, toppings, pizzaPrice);
                    menu.save(pizza);
                }
                case "order" -> {
                    boolean addPizzas = true;
                    while (addPizzas) {
                        System.out.println("select which pizza you want by typing the number");
                        System.out.println("(type 0 if you dont want to add more pizzas to your order)");
                        int pizzaNum = sc.nextInt();
                        pizzaNum = pizzaNum - 1;
                        if (pizzaNum != -1) {
                            Pizza selectedPizza = menu.selectPizzaFromMenu(pizzaNum);
                            order.addPizzaToOrder(selectedPizza);
                            System.out.println(selectedPizza.getName() + " pizza has been added to the order list");
                        } else {
                            addPizzas = false;
                        }
                    }
                }
                case "menu" -> System.out.println(menu);

                case "orderlist" -> {
                    System.out.println(order.orderList());
                    System.out.println("do you want to confirm your order y/n");
                    input = sc.nextLine();
                    if (input.equals("y")) {
                        System.out.println("do you want to specify the time of delivery? y/n");
                        input = sc.nextLine();
                        if (input.equals("y")) {
                            System.out.println("which time do you want it delivered? (ex: 20:50)");
                            input = sc.nextLine();
                            int split = input.indexOf(":");
                            int hour = Integer.parseInt(input.substring(0, split));
                            int minute = Integer.parseInt(input.substring(split + 1));
                            int[] deliveryTime = order.deliveryTime(hour, minute);

                            int year = deliveryTime[0];
                            int month = deliveryTime[1];
                            int day = deliveryTime[2];
                            hour = deliveryTime[3];
                            minute = deliveryTime[4];
                            System.out.println("your order will be delivered:");
                            System.out.printf("%d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
                            System.out.println();
                            System.out.println("thanks for your order");
                            order.save();
                            order.clearOrder();
                        }
                    } else {

                    }
                }
                default -> {
                    System.out.println("invalid command");
                }
                // code block
            }
        }
    }
}

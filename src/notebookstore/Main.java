package notebookstore;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Laptop> laptops = LaptopStore.generateLaptops(20);

        System.out.println("Список всех ноутбуков:");
        for (Laptop laptop : laptops) {
            System.out.println(laptop);
            System.out.println("Brand: " + laptop.brand()); // Использование метода brand()
        }

        LaptopStore.filterLaptops(laptops);
    }
}


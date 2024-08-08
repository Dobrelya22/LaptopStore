package notebookstore;

import java.util.*;

public class LaptopStore {

    public static Set<Laptop> generateLaptops(int count) {
        Set<Laptop> laptops = new HashSet<>();
        String[] brands = {"Dell", "HP", "Lenovo", "Apple", "Asus"};
        String[] osOptions = {"Windows", "macOS", "Linux"};
        String[] colors = {"Black", "Silver", "White", "Gray"};

        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String brand = brands[random.nextInt(brands.length)];
            int ram = (random.nextInt(4) + 1) * 4; // 4, 8, 12, 16
            int storage = (random.nextInt(5) + 1) * 256; // 256, 512, 768, 1024, 1280
            String os = osOptions[random.nextInt(osOptions.length)];
            String color = colors[random.nextInt(colors.length)];
            laptops.add(new Laptop(brand, ram, storage, os, color));
        }

        return laptops;
    }

    public static void filterLaptops(Set<Laptop> laptops) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filters = new HashMap<>();

        while (true) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            System.out.println("5 - Завершить выбор критериев");

            int criteria = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (criteria == 5) {
                break;
            }

            switch (criteria) {
                case 1:
                    System.out.print("Введите минимальное значение ОЗУ (ГБ): ");
                    filters.put("ram", scanner.nextInt());
                    scanner.nextLine(); // consume newline
                    break;
                case 2:
                    System.out.print("Введите минимальное значение объема ЖД (ГБ): ");
                    filters.put("storage", scanner.nextInt());
                    scanner.nextLine(); // consume newline
                    break;
                case 3:
                    System.out.print("Введите операционную систему: ");
                    filters.put("os", scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Введите цвет: ");
                    filters.put("color", scanner.nextLine());
                    break;
                default:
                    System.out.println("Некорректный критерий");
                    return;
            }
        }

        Set<Laptop> filteredLaptops = new HashSet<>(laptops);

        for (Laptop laptop : laptops) {
            if (filters.containsKey("ram") && laptop.ram() < (int) filters.get("ram")) {
                filteredLaptops.remove(laptop);
            }
            if (filters.containsKey("storage") && laptop.storage() < (int) filters.get("storage")) {
                filteredLaptops.remove(laptop);
            }
            if (filters.containsKey("os") && !laptop.os().equalsIgnoreCase((String) filters.get("os"))) {
                filteredLaptops.remove(laptop);
            }
            if (filters.containsKey("color") && !laptop.color().equalsIgnoreCase((String) filters.get("color"))) {
                filteredLaptops.remove(laptop);
            }
        }

        System.out.println("Ноутбуки, соответствующие критериям:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
}

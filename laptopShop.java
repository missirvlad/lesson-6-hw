import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    String brand;
    int ram;
    int storage ;
    String os;
    String color;
    public Laptop(String brand, int ram, int storage , String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage  = storage ;
        this.os = os;
        this.color = color;
    }
    @Override
    public String toString() {
        return "Laptop {" +
                "Бренд=<" + brand + '>' + ", ОЗУ=" + ram + ", Обём накопителя=" + storage  + ", Операционная система=<" + os + '>' + ", Цвет=" + color + '}';
    }
}

public class laptopShop {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 16, 512, "Windows", "чёрный"));
        laptops.add(new Laptop("Apple", 8, 256, "MacOS", "серебрянный"));
        laptops.add(new Laptop("Lenovo", 32, 1024, "Windows", "серый"));
        laptops.add(new Laptop("Ardor", 16, 512, "Windows", "белый"));
        laptops.add(new Laptop("Ardor", 64, 2048, "Windows", "золотой"));

        Map<String, Object> filters = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите критерии для фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем накопителя");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("0 - Завершить выбор");

        int choice;
        while (true) {
            choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    System.out.println("Какой минимальный объем ОЗУ?");
                    filters.put("ram", scanner.nextInt());
                    System.out.println("Следующее действие");
                    break;
                case 2:
                    System.out.println("Какой минимальный объем накопителя?");
                    filters.put("hdd", scanner.nextInt());
                    System.out.println("Следующее действие");
                    break;
                case 3:
                    System.out.println("Какая операционная система?");
                    filters.put("os", scanner.next());
                    System.out.println("Следующее действие");
                    break;
                case 4:
                    System.out.println("Цвет?");
                    filters.put("color", scanner.next());
                    System.out.println("Следующее действие");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        Set<Laptop> filteredLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("ram", 0) instanceof Integer && laptop.ram >= (int) filters.getOrDefault("ram", 0))
                .filter(laptop -> filters.getOrDefault("hdd", 0) instanceof Integer && laptop.storage  >= (int) filters.getOrDefault("hdd", 0))
                .filter(laptop -> filters.getOrDefault("os", "").equals("") || laptop.os.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
                .filter(laptop -> filters.getOrDefault("color", "").equals("") || laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .collect(Collectors.toSet());

        System.out.println("Отфильтрованные ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
}

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Device> devices = new ArrayList<>();
        
        Monitor monitor1 = new Monitor("Samsung", 25000.5f, "SN12345", 1920, 1080);
        Monitor monitor2 = new Monitor("LG", 32000.0f, "SN67890", 2560, 1440);
        Monitor monitor3 = new Monitor("Samsung", 25000.5f, "SN12345", 1920, 1080);
        
        EthernetAdapter adapter1 = new EthernetAdapter("TP-Link", 1500.0f, "EA12345", 1000, "00:1A:2B:3C:4D:5E");
        EthernetAdapter adapter2 = new EthernetAdapter("D-Link", 1200.0f, "EA67890", 100, "00:1B:3C:4D:5E:6F");
        EthernetAdapter adapter3 = new EthernetAdapter("TP-Link", 1500.0f, "EA12345", 1000, "00:1A:2B:3C:4D:5E");
        
        addUnique(devices, monitor1);
        addUnique(devices, monitor2);
        addUnique(devices, monitor3);
        addUnique(devices, adapter1);
        addUnique(devices, adapter2);
        addUnique(devices, adapter3);
        
        System.out.println("=== Список уникальных устройств ===");
        System.out.println("Всего устройств: " + devices.size() + "\n");
        
        for (int i = 0; i < devices.size(); i++) {
            System.out.println("Устройство " + (i + 1) + ":");
            Device device = devices.get(i);
            
            if (device instanceof Monitor) {
                Monitor monitor = (Monitor) device;
                System.out.println("  Тип: Монитор");
                System.out.println("  Производитель: " + monitor.getManufacturer());
                System.out.println("  Цена: " + monitor.getPrice());
                System.out.println("  Серийный номер: " + monitor.getSerialNumber());
                System.out.println("  Разрешение: " + monitor.getResolutionX() + "x" + monitor.getResolutionY());
                
                monitor.setPrice(monitor.getPrice() * 0.9f);
                System.out.println("  Цена со скидкой: " + monitor.getPrice());
                
            } else if (device instanceof EthernetAdapter) {
                EthernetAdapter adapter = (EthernetAdapter) device;
                System.out.println("  Тип: Сетевой адаптер");
                System.out.println("  Производитель: " + adapter.getManufacturer());
                System.out.println("  Цена: " + adapter.getPrice());
                System.out.println("  Серийный номер: " + adapter.getSerialNumber());
                System.out.println("  Скорость: " + adapter.getSpeed() + " Мбит/с");
                System.out.println("  MAC-адрес: " + adapter.getMAC());
                
                adapter.setSpeed(adapter.getSpeed() * 2);
                System.out.println("  Новая скорость: " + adapter.getSpeed() + " Мбит/с");
            }
            
            System.out.println("  Вызов методов:");
            device.detect();
            device.installDriver();
            
            System.out.println();
        }
        
        System.out.println("=== Проверка уникальности ===");
        System.out.println("Было создано 6 объектов (3 монитора и 3 адаптера)");
        System.out.println("В массив добавлено только " + devices.size() + " уникальных объектов");
        System.out.println("Дубликаты были отсеяны методом equals()");
    }
    
    public static void addUnique(ArrayList<Device> list, Device device) {
        if (!list.contains(device)) {
            list.add(device);
            System.out.println("Добавлен: " + device.getClass().getSimpleName() + 
                             " (" + device.getManufacturer() + ", SN: " + device.getSerialNumber() + ")");
        } else {
            System.out.println("Объект НЕ добавлен (дубликат): " + device.getClass().getSimpleName() + 
                             " (" + device.getManufacturer() + ", SN: " + device.getSerialNumber() + ")");
        }
    }
}

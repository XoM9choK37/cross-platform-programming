import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Система каршеринга с синхронизацией через synchronized/wait/notify
 */
public class CarSharingSystem {
    private int availableCars;
    private final int totalCars;
    private final Map<Integer, Integer> stats = new ConcurrentHashMap<>();
    private int totalClients = 0;

    public CarSharingSystem(int totalCars) {
        this.totalCars = totalCars;
        this.availableCars = totalCars;
        for (int h = 4; h <= 48; h += 2) {
            stats.put(h, 0);
        }
    }

    /**
     * Аренда машины. Если машин нет - ждём через wait()
     */
    public synchronized boolean rentCar(int clientId, int rentHours) throws InterruptedException {
        while (availableCars == 0) {
            System.out.printf("[Клиент %d] Нет машин, ожидание...\n", clientId);
            wait();
        }
        availableCars--;
        System.out.printf("[Клиент %d] Взял машину на %d ч. Свободно: %d/%d\n",
                clientId, rentHours, availableCars, totalCars);
        return true;
    }

    /**
     * Возврат машины с notify()
     */
    public synchronized void returnCar(int clientId) {
        availableCars++;
        System.out.printf("[Клиент %d] Вернул машину. Свободно: %d/%d\n",
                clientId, availableCars, totalCars);
        notify();
    }

    public synchronized void addToStats(int hours) {
        stats.merge(hours, 1, Integer::sum);
    }

    public synchronized void incrementClientCount() {
        totalClients++;
    }

    public synchronized void printStats() {
        System.out.println("\nСТАТИСТИКА КАРШЕРИНГА ЗА СУТКИ:");
        System.out.println("Всего клиентов: " + totalClients);
        System.out.println("Распределение по длительности аренды:");
        for (int h = 4; h <= 48; h += 2) {
            int count = stats.get(h);
            if (count > 0) {
                System.out.printf("  %d ч: %d клиент(ов)\n", h, count);
            }
        }
    }
}
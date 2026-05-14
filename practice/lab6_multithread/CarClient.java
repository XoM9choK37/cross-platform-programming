import java.util.Random;

/**
 * Клиент каршеринга - поток
 */
public class CarClient extends Thread {
    private final CarSharingSystem system;
    private final int clientId;
    private final int rentHours;

    public CarClient(CarSharingSystem system, int clientId) {
        this.system = system;
        this.clientId = clientId;
        Random rand = new Random();
        // чётное число от 4 до 48
        this.rentHours = 4 + 2 * rand.nextInt(23);
    }

    @Override
    public void run() {
        try {
            system.rentCar(clientId, rentHours);
            system.addToStats(rentHours);
            system.incrementClientCount();

            // Симуляция: 1 час = 100 мс
            Thread.sleep(rentHours * 100L);

            system.returnCar(clientId);
        } catch (InterruptedException e) {
            System.out.printf("[Клиент %d] Прерван\n", clientId);
            Thread.currentThread().interrupt();
        }
    }
}
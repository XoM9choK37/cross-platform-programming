import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BankQueue {
    private final BlockingQueue<Client> queue;
    private final AtomicInteger servedCount = new AtomicInteger(0);
    private final List<Integer> queueLengths = new ArrayList<>();
    private volatile boolean running = true;

    public BankQueue(int maxQueueSize) {
        this.queue = new LinkedBlockingQueue<>(maxQueueSize);
    }

    public void startSimulation(int durationMinutes, int arrivalIntervalAvgMinutes) throws InterruptedException {
        servedCount.set(0);
        queue.clear();
        queueLengths.clear();
        running = true;

        System.out.printf("\nСИМУЛЯЦИЯ: %d мин, клиент раз в %d мин:\n",
                durationMinutes, arrivalIntervalAvgMinutes);

        // Запуск 10 касс
        Cashier[] cashiers = new Cashier[10];
        for (int i = 0; i < 5; i++) {
            cashiers[i] = new Cashier(i + 1, this, 7);
            cashiers[i].start();
        }
        for (int i = 5; i < 10; i++) {
            cashiers[i] = new Cashier(i + 1, this, 10);
            cashiers[i].start();
        }

        // Генератор клиентов
        Thread generator = new Thread(() -> {
            Random rand = new Random();
            int currentTime = 0;
            int clientCounter = 0;

            while (running && currentTime <= durationMinutes) {
                try {
                    clientCounter++;
                    Client client = new Client(currentTime);
                    queue.put(client); // блокируется, если очередь полна

                    synchronized (queueLengths) {
                        queueLengths.add(queue.size());
                    }

                    System.out.printf("[%d мин] Пришёл %s. Очередь: %d\n",
                            currentTime, client, queue.size());

                    // Следующий клиент с вариацией ±50%
                    int nextInterval = arrivalIntervalAvgMinutes + rand.nextInt(arrivalIntervalAvgMinutes) - arrivalIntervalAvgMinutes / 2;
                    nextInterval = Math.max(1, nextInterval);

                    // Масштаб: 1 минута = 10 мс (для наглядности)
                    Thread.sleep(nextInterval * 10L);
                    currentTime += nextInterval;

                } catch (InterruptedException e) {
                    break;
                }
            }
            running = false;
        });
        generator.start();

        // Ждём симуляцию
        Thread.sleep(durationMinutes * 10L);

        // Остановка
        running = false;
        generator.interrupt();
        for (Cashier c : cashiers) {
            c.interrupt();
        }
        for (Cashier c : cashiers) {
            c.join();
        }

        // Статистика
        System.out.println("\nСТАТИСТИКА:");
        System.out.println("Обслужено клиентов: " + servedCount.get());
        if (!queueLengths.isEmpty()) {
            double avg = queueLengths.stream().mapToInt(Integer::intValue).average().orElse(0);
            int max = queueLengths.stream().max(Integer::compareTo).orElse(0);
            System.out.printf("Средняя длина очереди: %.2f\n", avg);
            System.out.println("Максимальная длина очереди: " + max);
        }
    }

    public void serveClient(Cashier cashier) throws InterruptedException {
        Client client = queue.take();
        Random rand = new Random();
        int serviceTime = cashier.getServiceTimeAvg() + rand.nextInt(3) - 1; // -1..+1 мин
        serviceTime = Math.max(3, serviceTime);

        System.out.printf("  [Касса %d] Обслуживает %s (%d мин)\n",
                cashier.getCashierId(), client, serviceTime);

        Thread.sleep(serviceTime * 10L);

        servedCount.incrementAndGet();
        System.out.printf("  [Касса %d] Закончил. Всего обслужено: %d\n",
                cashier.getCashierId(), servedCount.get());
    }

    public boolean isRunning() {
        return running;
    }
}
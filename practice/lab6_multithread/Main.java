public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("JOIN / YIELD / SLEEP:");

        FirstGenerator generator = new FirstGenerator();
        generator.start();
        generator.join(); // главный ждёт

        int number = generator.getNumber();
        System.out.println("[Main] Получил число: " + number);

        SleepWorker sleepWorker = new SleepWorker(number);
        YieldWorker yieldWorker = new YieldWorker(number);

        sleepWorker.start();
        yieldWorker.start();

        sleepWorker.join();
        yieldWorker.join();

        System.out.println("\nРЕЗУЛЬТАТ СОРЕВНОВАНИЯ:");
        long sleepTime = sleepWorker.getDuration();
        long yieldTime = yieldWorker.getDuration();

        if (sleepTime < yieldTime) {
            System.out.println("Победил SleepWorker (sleep)!");
        } else if (yieldTime < sleepTime) {
            System.out.println("Победил YieldWorker (yield)!");
        } else {
            System.out.println("Ничья!");
        }
        System.out.printf("SleepWorker: %d мс, YieldWorker: %d мс\n\n", sleepTime, yieldTime);

        System.out.println("КАРШЕРИНГ:");

        CarSharingSystem system = new CarSharingSystem(30);
        int simulationHours = 24;
        Thread[] clients = new Thread[simulationHours];

        long startTime = System.currentTimeMillis();

        for (int hour = 0; hour < simulationHours; hour++) {
            clients[hour] = new CarClient(system, hour + 1);
            clients[hour].start();

            System.out.printf("[Час %d] Пришёл клиент %d\n", hour + 1, hour + 1);

            // 1 час модельного времени = 100 мс реального
            Thread.sleep(100);
        }

        // Ждём всех клиентов
        for (Thread client : clients) {
            client.join();
        }

        system.printStats();
    }
}
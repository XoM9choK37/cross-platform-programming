/**
 * Рабочий с использованием yield()
 */
public class YieldWorker extends Thread {
    private final int number;
    private long result = 0;
    private long startTime = 0;
    private long endTime = 0;

    public YieldWorker(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        System.out.printf("[YieldWorker] Начал вычисление суммы квадратов от 1 до %d\n", number);

        for (int i = 1; i <= number; i++) {
            result += (long) i * i;
            Thread.yield();
        }

        endTime = System.currentTimeMillis();
        System.out.printf("[YieldWorker] Закончил. Результат = %d, время = %d мс\n",
                result, (endTime - startTime));
    }

    public long getResult() {
        return result;
    }

    public long getDuration() {
        return endTime - startTime;
    }
}
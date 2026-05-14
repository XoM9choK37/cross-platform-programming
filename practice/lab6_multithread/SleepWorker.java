/**
 * Рабочий с использованием sleep(1)
 */
public class SleepWorker extends Thread {
    private final int number;
    private long result = 0;
    private long startTime = 0;
    private long endTime = 0;

    public SleepWorker(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        System.out.printf("[SleepWorker] Начал вычисление суммы квадратов от 1 до %d\n", number);

        for (int i = 1; i <= number; i++) {
            result += (long) i * i;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        endTime = System.currentTimeMillis();
        System.out.printf("[SleepWorker] Закончил. Результат = %d, время = %d мс\n",
                result, (endTime - startTime));
    }

    public long getResult() {
        return result;
    }

    public long getDuration() {
        return endTime - startTime;
    }
}
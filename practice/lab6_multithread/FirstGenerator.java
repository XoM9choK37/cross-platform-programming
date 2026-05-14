import java.util.Random;

/**
 * Первый рабочий - генерирует число для join
 */
public class FirstGenerator extends Thread {
    private int number = 0;

    @Override
    public void run() {
        Random rand = new Random();
        number = 10 + rand.nextInt(41); // 10..50
        System.out.println("[Generator] Сгенерировал число: " + number);
    }

    public int getNumber() {
        return number;
    }
}
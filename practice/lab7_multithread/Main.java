public class Main {
    public static void main(String[] args) throws InterruptedException {
        BankQueue bank = new BankQueue(50);
        bank.startSimulation(60, 10); // 1 час, клиент раз в 10 мин

        BankQueue bank2 = new BankQueue(50);
        bank2.startSimulation(60, 5); // 1 час, клиент раз в 5 мин
    }
}
public class Cashier extends Thread {
    private final int cashierId;
    private final BankQueue bank;
    private final int serviceTimeAvg;

    public Cashier(int cashierId, BankQueue bank, int serviceTimeAvg) {
        this.cashierId = cashierId;
        this.bank = bank;
        this.serviceTimeAvg = serviceTimeAvg;
    }

    public int getCashierId() {
        return cashierId;
    }

    public int getServiceTimeAvg() {
        return serviceTimeAvg;
    }

    @Override
    public void run() {
        while (bank.isRunning() && !Thread.currentThread().isInterrupted()) {
            try {
                bank.serveClient(this);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
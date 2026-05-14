public class Client {
    private static int counter = 0;
    private final int id;
    private final int arrivalTimeMin; // время прихода в минутах от начала

    public Client(int arrivalTimeMin) {
        this.id = ++counter;
        this.arrivalTimeMin = arrivalTimeMin;
    }

    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTimeMin;
    }

    @Override
    public String toString() {
        return "Client{" + id + ", arrival=" + arrivalTimeMin + "min}";
    }
}
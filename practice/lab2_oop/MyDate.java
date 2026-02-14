public class MyDate {
    private int day, month, year;

    public MyDate(int d, int m, int y) {
        day=d; month=m; year=y;
    }

    public String getDate() {
        return String.valueOf(day)   + 
               String.valueOf(month) +
               String.valueOf(year);
    }
}

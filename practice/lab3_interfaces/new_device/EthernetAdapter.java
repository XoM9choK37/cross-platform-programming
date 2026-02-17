public class EthernetAdapter extends BaseDevice {
    private int speed;
    private String mac;
    
    public EthernetAdapter() {
        super();
    }
    
    public EthernetAdapter(String manufacturer, float price, String serialNumber,
                          int speed, String mac) {
        super(manufacturer, price, serialNumber);
        this.speed = speed;
        this.mac = mac;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public String getMAC() {
        return mac;
    }
    
    public void setMAC(String mac) {
        this.mac = mac;
    }
    
    @Override
    public void detect() {
        System.out.println("Сетевой адаптер распознан: " + getManufacturer() + 
                          ", скорость: " + speed + " Мбит/с, MAC: " + mac);
    }
    
    @Override
    public String toString() {
        return "EthernetAdapter [" + super.toString() + 
               ", speed=" + speed + 
               ", mac=" + mac + "]";
    }
}

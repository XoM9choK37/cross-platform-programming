public class Monitor extends Device {
    private int resolutionX;
    private int resolutionY;
    
    public Monitor() {
        super();
    }
    
    public Monitor(String manufacturer, float price, String serialNumber, 
                   int resolutionX, int resolutionY) {
        super(manufacturer, price, serialNumber);
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
    }
    
    public int getResolutionX() {
        return resolutionX;
    }
    
    public void setResolutionX(int resolutionX) {
        this.resolutionX = resolutionX;
    }
    
    public int getResolutionY() {
        return resolutionY;
    }
    
    public void setResolutionY(int resolutionY) {
        this.resolutionY = resolutionY;
    }
    
    @Override
    public void detect() {
        System.out.println("Монитор распознан: " + getManufacturer() + 
                          ", разрешение: " + resolutionX + "x" + resolutionY);
    }
    
    @Override
    public String toString() {
        return "Monitor [" + super.toString() + 
               ", resolution=" + resolutionX + "x" + resolutionY + "]";
    }
}

public abstract class BaseDevice implements IDeviceBasic, IDeviceOperations {
    private String manufacturer;
    private float price;
    private String serialNumber;
    
    public BaseDevice() {}
    
    public BaseDevice(String manufacturer, float price, String serialNumber) {
        this.manufacturer = manufacturer;
        this.price = price;
        this.serialNumber = serialNumber;
    }
    
    @Override
    public String getManufacturer() {
        return manufacturer;
    }
    
    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    @Override
    public float getPrice() {
        return price;
    }
    
    @Override
    public void setPrice(float price) {
        this.price = price;
    }
    
    @Override
    public String getSerialNumber() {
        return serialNumber;
    }
    
    @Override
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    @Override
    public void replace() {
        System.out.println("Устройство " + manufacturer + " (SN: " + serialNumber + ") заменено");
    }
    
    @Override
    public void installDriver() {
        System.out.println("Установка драйвера для устройства " + manufacturer);
    }
    
    @Override
    public void deleteDriver() {
        System.out.println("Удаление драйвера для устройства " + manufacturer);
    }
    
    @Override
    public String toString() {
        return "BaseDevice [manufacturer=" + manufacturer + 
               ", price=" + price + 
               ", serialNumber=" + serialNumber + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        BaseDevice device = (BaseDevice) obj;
        
        if (Float.compare(device.price, price) != 0) return false;
        if (manufacturer != null ? !manufacturer.equals(device.manufacturer) : device.manufacturer != null) return false;
        return serialNumber != null ? serialNumber.equals(device.serialNumber) : device.serialNumber == null;
    }
    
    @Override
    public int hashCode() {
        int result = manufacturer != null ? manufacturer.hashCode() : 0;
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (serialNumber != null ? serialNumber.hashCode() : 0);
        return result;
    }
}

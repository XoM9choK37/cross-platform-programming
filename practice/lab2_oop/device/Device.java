public class Device {
    private String manufacturer;
    private float price;
    private String serialNumber;
    
    public Device() {}
    
    public Device(String manufacturer, float price, String serialNumber) {
        this.manufacturer = manufacturer;
        this.price = price;
        this.serialNumber = serialNumber;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    public float getPrice() {
        return price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }
    
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void replace() {
        System.out.println("Устройство " + manufacturer + " (SN: " + serialNumber + ") заменено");
    }
    
    public void detect() {
        System.out.println("Устройство распознано: " + manufacturer + ", цена: " + price);
    }
    
    public void installDriver() {
        System.out.println("Установка драйвера для устройства " + manufacturer);
    }
    
    public void deleteDriver() {
        System.out.println("Удаление драйвера для устройства " + manufacturer);
    }
    
    @Override
    public String toString() {
        return "Device [manufacturer=" + manufacturer + 
               ", price=" + price + 
               ", serialNumber=" + serialNumber + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Device device = (Device) obj;
        
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

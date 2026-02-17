public interface IDeviceBasic {
    String getManufacturer();
    void setManufacturer(String manufacturer);
    
    float getPrice();
    void setPrice(float price);
    
    String getSerialNumber();
    void setSerialNumber(String serialNumber);
    
    String toString();
    boolean equals(Object obj);
    int hashCode();
}

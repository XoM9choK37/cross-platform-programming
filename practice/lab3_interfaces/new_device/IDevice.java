public interface IDevice {
    String getManufacturer();
    void setManufacturer(String manufacturer);
    
    float getPrice();
    void setPrice(float price);
    
    String getSerialNumber();
    void setSerialNumber(String serialNumber);
    
    void replace();
    void detect();
    void installDriver();
    void deleteDriver();
    
    String toString();
    boolean equals(Object obj);
    int hashCode();
}

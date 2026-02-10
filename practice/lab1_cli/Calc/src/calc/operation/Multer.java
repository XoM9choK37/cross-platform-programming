package calc.operation;

public class Multer {
  private int mul;
  public Multer() { mul=0; }
  public Multer(int a) { this.mul=a; }
  public void multiply(int b) { mul*=b; }
  public int getMul() { return mul;}
}

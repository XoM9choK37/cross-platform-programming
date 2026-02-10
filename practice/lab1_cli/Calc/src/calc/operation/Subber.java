package calc.operation;

public class Subber {
  private int sub;
  public Subber() { sub=0; }
  public Subber(int a) { this.sub=a; }
  public void subtract(int b) { sub-=b; }
  public int getSub() { return sub;}
}

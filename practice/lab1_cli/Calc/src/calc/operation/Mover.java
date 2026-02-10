package calc.operation;

public class Mover {
  private short mov;
  public Mover() { mov=0; }
  public Mover(short a) { this.mov=a; }
  public void move() { long tmp = mov; tmp <<= 1; mov = (short)tmp; }
  public int getMov() { return mov;}
}

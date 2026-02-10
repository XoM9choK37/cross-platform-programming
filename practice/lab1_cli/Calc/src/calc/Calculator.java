package calc;
import calc.operation.Adder;
import calc.operation.Subber;
import calc.operation.Multer;
import calc.operation.Diver;
import calc.operation.Mover;

public class Calculator {
  public int sum(int... a) {
    Adder adder=new Adder();
    for (int i:a) {
      adder.add(i);
    }
    return adder.getSum();
  }
}


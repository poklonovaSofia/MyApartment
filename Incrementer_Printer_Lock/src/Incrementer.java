public class Incrementer  extends Thread{
    private  Values values;

    Incrementer(Values values) {
        this.values = values;
    }

    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            values.increment();
        }
    }
}

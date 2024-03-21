public class Printer extends Thread {
    private  Values values;

    Printer(Values values) {
        this.values = values;
    }

    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            values.print();
        }
    }
}
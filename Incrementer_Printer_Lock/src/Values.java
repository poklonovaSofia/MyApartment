import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Values {
    private int i;
    private int j;
    private Lock lock;
    public Values()
    {
        i=0;
        j=0;
        lock=new ReentrantLock();
    }
    void increment() {
        lock.lock();
        try {
            i++;
            j++;
        } finally {
            lock.unlock();
        }

    }

    void print() {
        lock.lock();
        try {
            if (i != j)
                System.out.println("i=" + i + " j=" + j);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Values values = new Values();
        Incrementer incrementer = new Incrementer(values);
        Printer printer = new Printer(values);

        incrementer.start();
        printer.start();
}}

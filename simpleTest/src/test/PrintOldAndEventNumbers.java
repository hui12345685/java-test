package test;

public class PrintOldAndEventNumbers {
    // 共享变量，初始化为0（用于计算奇数和偶数）
    private int number = 1;
    final private int maxNum = 20;

    // 打印奇数的线程
    public class OddPrinter implements Runnable {
        @Override
        public void run() {
            while (number < maxNum) {
                synchronized (PrintOldAndEventNumbers.this) {
                    while (number % 2 == 0) {
                        try {
                            PrintOldAndEventNumbers.this.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " - Odd: " + number);
                    number++;
                    PrintOldAndEventNumbers.this.notifyAll();
                }
            }
        }
    }

    // 打印偶数的线程
    public class EvenPrinter implements Runnable {
        @Override
        public void run() {
            while (number < maxNum) {
                synchronized (PrintOldAndEventNumbers.this) {
                    while (number % 2 != 0) {
                        try {
                            PrintOldAndEventNumbers.this.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " - Even: " + number);
                    number++;
                    PrintOldAndEventNumbers.this.notifyAll();
                }
            }
        }
    }

}

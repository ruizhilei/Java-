package javahomework;

//通过实现Runnable接口，创建生产者和消费者线程，并通过共享变量MyNumber来实现通信。

class MyNumber {
    private int number = -1;
    private boolean isProduced = false;

    public synchronized void produce() throws InterruptedException {
        while (isProduced) {
            wait();  // 如果数字已经被消费，等待消费者取走
        }
        number = (int) (Math.random() * 10);  // 产生一个0-9的随机数
        System.out.println("生产者生产数字: " + number);
        isProduced = true;
        notify();  // 通知消费者可以取走数字
    }

    public synchronized void consume() throws InterruptedException {
        while (!isProduced) {
            wait();  // 如果没有新数字，等待生产者生产
        }
        
        System.out.println("消费者取走数字: " + number);
        isProduced = false;
        notify();  // 通知生产者可以继续生产
    }

    public int getNumber() {
        return number;
    }
}

class Producer implements Runnable {
    private MyNumber myNumber;

    public Producer(MyNumber myNumber) {
        this.myNumber = myNumber;
    }

    @Override
    public void run() {
        while (true) {
            try {
                myNumber.produce();
                Thread.sleep(100);  // 每隔100ms生产一个数
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private MyNumber myNumber;

    public Consumer(MyNumber myNumber) {
        this.myNumber = myNumber;
    }

    @Override
    public void run() {
        while (true) {
            try {
                myNumber.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        Thread producerThread = new Thread(new Producer(myNumber));
        Thread consumerThread = new Thread(new Consumer(myNumber));

        producerThread.start();
        consumerThread.start();
    }
}








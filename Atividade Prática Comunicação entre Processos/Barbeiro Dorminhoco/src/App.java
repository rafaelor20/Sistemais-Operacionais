import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class App {
    private static final int MAX_CLIENTS_IN_QUEUE = 10;
    private static final int MIN_CUTTING_TIME = 5000; // 5 seconds
    private static final int MAX_CUTTING_TIME = 10000; // 10 seconds
    private static final int MIN_CLIENT_ARRIVAL_TIME = 4000; // 4 seconds
    private static final int MAX_CLIENT_ARRIVAL_TIME = 6000; // 6 seconds

    private static Queue<Integer> waitingRoom = new LinkedList<>();
    private static int numClientsInQueue = 0;

    private static Object barberLock = new Object();
    private static Object queueLock = new Object();

    private static class Barber extends Thread {
        private int id;

        public Barber(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int customer;
                    synchronized (queueLock) {
                        while (numClientsInQueue == 0) {
                            System.out.println("Barber " + id + " is sleeping.");
                            queueLock.wait();
                        }
                        customer = waitingRoom.poll();
                        numClientsInQueue--;
                        queueLock.notifyAll();
                    }
                    System.out.println("Barber " + id + " is cutting hair of Customer " + customer + ".");
                    Thread.sleep(getRandomCuttingTime());
                    System.out.println("Barber " + id + " finished cutting hair of Customer " + customer + ".");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class CustomerGenerator extends Thread {
        @Override
        public void run() {
            int customerCount = 1;
            while (true) {
                try {
                    int customer;
                    synchronized (queueLock) {
                        if (numClientsInQueue < MAX_CLIENTS_IN_QUEUE) {
                            customer = customerCount++;
                            waitingRoom.offer(customer);
                            numClientsInQueue++;
                            System.out.println("Customer " + customer + " has entered the waiting room.");
                            queueLock.notifyAll();
                        } else {
                            System.out.println("Waiting room is full. Customer " + customerCount + " left.");
                        }
                    }
                    Thread.sleep(getRandomClientArrivalTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int getRandomCuttingTime() {
        return new Random().nextInt(MAX_CUTTING_TIME - MIN_CUTTING_TIME + 1) + MIN_CUTTING_TIME;
    }

    private static int getRandomClientArrivalTime() {
        return new Random().nextInt(MAX_CLIENT_ARRIVAL_TIME - MIN_CLIENT_ARRIVAL_TIME + 1) + MIN_CLIENT_ARRIVAL_TIME;
    }

    public static void main(String[] args) {
        Barber barber1 = new Barber(1);
        Barber barber2 = new Barber(2);
        CustomerGenerator customerGenerator = new CustomerGenerator();

        barber1.start();
        barber2.start();
        customerGenerator.start();
    }
}

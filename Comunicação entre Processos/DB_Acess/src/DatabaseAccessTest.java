public class DatabaseAccessTest {
    public static void main(String[] args) {
        final Database database = new Database();

        Thread writeThread = new Thread(() -> database.writeData("Hello, this is a test!"));
        Thread readThread1 = new Thread(() -> System.out.println("Read data 1: " + database.readData()));
        Thread readThread2 = new Thread(() -> System.out.println("Read data 2: " + database.readData()));

        writeThread.start();
        readThread1.start();
        readThread2.start();

        try {
            writeThread.join();
            readThread1.join();
            readThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

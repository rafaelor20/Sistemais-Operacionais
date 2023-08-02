class Database {
    private String data;

    public synchronized void writeData(String newData) {
        System.out.println("Writing data... (Thread: " + Thread.currentThread().getId() + ")");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = newData;
        System.out.println("Data written: " + newData + " (Thread: " + Thread.currentThread().getId() + ")");
    }

    public synchronized String readData() {
        System.out.println("Reading data... (Thread: " + Thread.currentThread().getId() + ")");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.data;
    }
}

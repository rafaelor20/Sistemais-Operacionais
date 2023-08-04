public class TestDatabase {
    public static void main(String[] args) {
        BancoDeDados database = new BancoDeDados();

        Thread queriesThread = new Thread(() -> {
            while (true) {
                database.select();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread writesThread = new Thread(() -> {
            int counter = 1;
            while (true) {
                database.insert("Key" + counter, "Value" + counter);
                counter++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread deletesThread = new Thread(() -> {
            int counter = 1;
            while (true) {
                database.delete("Key" + counter);
                counter++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread updatesThread = new Thread(() -> {
            int counter = 1;
            while (true) {
                database.update("Key" + counter, "UpdatedValue" + counter);
                counter++;
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        queriesThread.start();
        writesThread.start();
        deletesThread.start();
        updatesThread.start();
    }
}

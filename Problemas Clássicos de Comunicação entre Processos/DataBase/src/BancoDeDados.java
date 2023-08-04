import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class BancoDeDados {
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore readSem = new Semaphore(10);
    private final Map<String, String> data = new HashMap<>();

    public void insert(String key, String value) {
        try {
            mutex.acquire();
            data.put(key, value);
            System.out.println("Insert: " + key + " -> " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
        }
    }

    public void delete(String key) {
        try {
            mutex.acquire();
            if (data.containsKey(key)) {
                data.remove(key);
                System.out.println("Delete: " + key);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
        }
    }

    public void update(String key, String value) {
        try {
            mutex.acquire();
            if (data.containsKey(key)) {
                data.put(key, value);
                System.out.println("Update: " + key + " -> " + value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
        }
    }

    public void select() {
        try {
            readSem.acquire();
            mutex.acquire();
            System.out.println("Selecting data:");
            for (Map.Entry<String, String> entry : data.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            readSem.release();
        }
    }
}

import java.util.Random;

public class App {
    public static void main(String[] args) throws InterruptedException {
        int[] listSizes = { 1000, 10000, 100000 };
        int numThreads = 16;
        int numOperationsPerThread = 10000;

        System.out.println("Testing ThreadSafeArrayList:");
        for (int size : listSizes) {
            MyList<Integer> threadSafeList = new ThreadSafeArrayList<>();
            long totalTime = testList(threadSafeList, size, numThreads, numOperationsPerThread);
            printResults(size, numThreads, numOperationsPerThread, totalTime);
        }

        System.out.println("\nTesting SingleThreadArrayList:");
        for (int size : listSizes) {
            MyList<Integer> singleThreadList = new SingleThreadArrayList<>();
            long totalTime = testList(singleThreadList, size, 1, numOperationsPerThread);
            printResults(size, 1, numOperationsPerThread, totalTime);
        }
    }

    private static <E> long testList(MyList<E> list, int size, int numThreads, int numOperationsPerThread)
            throws InterruptedException {
        // Populate the list with initial data
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add((E) Integer.valueOf(random.nextInt(1000)));
        }

        // Create and start threads
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new TestThread<>(list, numOperationsPerThread);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        // Return the total time taken by all threads
        return TestThread.totalTime;
    }

    private static void printResults(int size, int numThreads, int numOperationsPerThread, long totalTime) {
        double totalOperations = (double) numThreads * numOperationsPerThread;
        double operationsPerSecond = totalOperations / (totalTime / 1000.0);
        System.out.printf(
                "List size: %d, Threads: %d, Operations per thread: %d, Total time (ms): %d, Operations per second: %.2f%n",
                size, numThreads, numOperationsPerThread, totalTime, operationsPerSecond);
    }
}

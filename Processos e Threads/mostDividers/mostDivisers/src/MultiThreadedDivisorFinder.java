public class MultiThreadedDivisorFinder {
    private static final int MAX_NUMBER = 20000;
    private static final int NUM_THREADS = 4;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int maxDivisors = 0;
        int numberWithMaxDivisors = 1;

        Thread[] threads = new Thread[NUM_THREADS];
        DivisorCountTask[] tasks = new DivisorCountTask[NUM_THREADS];

        int numbersPerThread = MAX_NUMBER / NUM_THREADS;
        int startNumber = 1;
        int endNumber = numbersPerThread;

        for (int i = 0; i < NUM_THREADS; i++) {
            if (i == NUM_THREADS - 1) {
                endNumber = MAX_NUMBER;
            }
            tasks[i] = new DivisorCountTask(startNumber, endNumber);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();

            startNumber = endNumber + 1;
            endNumber += numbersPerThread;
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (DivisorCountTask task : tasks) {
            if (task.getMaxDivisors() > maxDivisors) {
                maxDivisors = task.getMaxDivisors();
                numberWithMaxDivisors = task.getNumberWithMaxDivisors();
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Número com maior quantidade de divisores: " + numberWithMaxDivisors);
        System.out.println("Quantidade de divisores: " + maxDivisors);
        System.out.println("Tempo necessário (em milissegundos): " + (endTime - startTime));
    }

    private static class DivisorCountTask implements Runnable {
        private int startNumber;
        private int endNumber;
        private int maxDivisors;
        private int numberWithMaxDivisors;

        public DivisorCountTask(int startNumber, int endNumber) {
            this.startNumber = startNumber;
            this.endNumber = endNumber;
        }

        public int getMaxDivisors() {
            return maxDivisors;
        }

        public int getNumberWithMaxDivisors() {
            return numberWithMaxDivisors;
        }

        @Override
        public void run() {
            maxDivisors = 0;
            numberWithMaxDivisors = 1;

            for (int num = startNumber; num <= endNumber; num++) {
                int divisorCount = countDivisors(num);
                if (divisorCount > maxDivisors) {
                    maxDivisors = divisorCount;
                    numberWithMaxDivisors = num;
                }
            }
        }

        private int countDivisors(int number) {
            int count = 0;
            for (int i = 1; i <= number; i++) {
                if (number % i == 0) {
                    count++;
                }
            }
            return count;
        }
    }
}

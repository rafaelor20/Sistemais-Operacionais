public class App {
    private static int valor = 0;

    public static synchronized void increment() {
        valor = valor + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 16;
        int numIterations = 10000;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < numIterations; j++) {
                    increment();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }

        int valorEsperado = numThreads * numIterations;
        System.out.println("Valor final: " + valor);
        System.out.println("Valor esperado: " + valorEsperado);
        System.out.println("O valor está correto? " + (valor == valorEsperado));
    }
}

import java.time.LocalTime;

public class HelloWorldThreads {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new HelloWorldRunnable("Thread 1"));
        Thread thread2 = new Thread(new HelloWorldRunnable("Thread 2"));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalTime horarioTermino = LocalTime.now();
        System.out.println("Todas as threads terminaram às " + horarioTermino);

        System.out.println("A primeira thread a concluir foi: " + HelloWorldRunnable.getPrimeiraThreadConcluida());
    }
}

class HelloWorldRunnable implements Runnable {
    private static String primeiraThreadConcluida = null;
    private String nome;

    public HelloWorldRunnable(String nome) {
        this.nome = nome;
    }

    public static String getPrimeiraThreadConcluida() {
        return primeiraThreadConcluida;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Olá, sou a " + nome + "!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (primeiraThreadConcluida == null) {
            primeiraThreadConcluida = nome;
        }
    }
}


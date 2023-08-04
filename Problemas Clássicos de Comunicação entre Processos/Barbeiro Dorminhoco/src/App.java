import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class App {
    public static void main(String[] args) {
        Queue<Cliente> filaEspera = new LinkedList<>();
        Barbeiro barbeiro = new Barbeiro(filaEspera);
        Thread barbeiroThread = new Thread(barbeiro);
        barbeiroThread.start();

        Random random = new Random();
        int clienteId = 1;

        while (true) {
            try {
                int chegadaCliente = random.nextInt(5001) + 3000; // Tempo aleatório entre 3s e 7s
                Thread.sleep(chegadaCliente);

                if (filaEspera.size() < 10) {
                    Cliente cliente = new Cliente(clienteId++);
                    filaEspera.add(cliente);
                    System.out.println("Cliente " + cliente.getId() + " chegou. Fila: " + filaEspera.size());
                } else {
                    System.out.println("Cliente chegou, mas a fila está cheia. Fila: " + filaEspera.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Barbeiro implements Runnable {
    private Queue<Cliente> filaEspera;

    public Barbeiro(Queue<Cliente> filaEspera) {
        this.filaEspera = filaEspera;
    }

    @Override
    public void run() {
        while (true) {
            if (!filaEspera.isEmpty()) {
                Cliente cliente = filaEspera.poll();
                atenderCliente(cliente);
            } else {
                System.out.println("Barbeiro dormindo...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void atenderCliente(Cliente cliente) {
        System.out.println("Barbeiro está cortando o cabelo do Cliente " + cliente.getId());
        try {
            int tempoCorte = new Random().nextInt(10000) + 1000; // Tempo aleatório entre 1s e 10s
            Thread.sleep(tempoCorte);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Corte de cabelo do Cliente " + cliente.getId() + " finalizado.");
    }
}

class Cliente {
    private int id;

    public Cliente(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

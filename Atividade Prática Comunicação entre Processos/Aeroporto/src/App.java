import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    private static Queue<String> pousos = new LinkedList<>();
    private static Queue<String> decolagens = new LinkedList<>();
    private static final int MAX_POUSOS_AGUARDANDO = 3;
    private static final int MAX_DECOLAGENS_AGUARDANDO = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----- Aeroporto de Pequenopolis -----");
            System.out.println("1 - Solicitar pouso");
            System.out.println("2 - Solicitar decolagem");
            System.out.println("3 - Encerrar programa");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    solicitarPouso(scanner);
                    break;
                case 2:
                    solicitarDecolagem(scanner);
                    break;
                case 3:
                    System.out.println("Encerrando programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void solicitarPouso(Scanner scanner) {
        if (pousos.size() >= MAX_POUSOS_AGUARDANDO) {
            System.out.println("Limite de pousos aguardando atingido. Aguarde sua vez.");
            return;
        }

        System.out.print("Informe o nome da aeronave que deseja pousar: ");
        String aeronave = scanner.next();
        pousos.offer(aeronave);
        System.out.println("Aeronave " + aeronave + " aguardando para pousar.");
        exibirOperacoes();
    }

    private static void solicitarDecolagem(Scanner scanner) {
        if (decolagens.size() >= MAX_DECOLAGENS_AGUARDANDO) {
            System.out.println("Limite de decolagens aguardando atingido. Aguarde sua vez.");
            return;
        }

        System.out.print("Informe o nome da aeronave que deseja decolar: ");
        String aeronave = scanner.next();
        decolagens.offer(aeronave);
        System.out.println("Aeronave " + aeronave + " aguardando para decolar.");
        exibirOperacoes();
    }

    private static void exibirOperacoes() {
        System.out.println("----- Operações em andamento -----");
        if (!pousos.isEmpty()) {
            System.out.println("Pousos aguardando:");
            for (String aeronave : pousos) {
                System.out.println(" - " + aeronave);
            }
        } else {
            System.out.println("Nenhuma aeronave aguardando para pousar.");
        }

        if (!decolagens.isEmpty()) {
            System.out.println("Decolagens aguardando:");
            for (String aeronave : decolagens) {
                System.out.println(" - " + aeronave);
            }
        } else {
            System.out.println("Nenhuma aeronave aguardando para decolar.");
        }
        System.out.println("-----------------------------------");
    }
}


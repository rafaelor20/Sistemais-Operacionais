import java.util.Arrays;
import java.util.Scanner;

public class OrdenacaoVetor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Digite o tamanho do vetor (ou 0 para sair): ");
            int tamanho = scanner.nextInt();

            if (tamanho == 0) {
                System.out.println("Encerrando o programa...");
                break;
            }

            int[] vetor = new int[tamanho];

            for (int i = 0; i < tamanho; i++) {
                System.out.print("Digite o elemento " + (i + 1) + ": ");
                vetor[i] = scanner.nextInt();
            }

            Thread threadOrdenacao = new Thread(() -> {
                int[] vetorOrdenado = ordenarVetor(vetor);
                System.out.println("Vetor original: " + Arrays.toString(vetor));
                System.out.println("Vetor ordenado: " + Arrays.toString(vetorOrdenado));
            });

            threadOrdenacao.start();
        }
    }

    private static int[] ordenarVetor(int[] vetor) {
        int[] vetorOrdenado = Arrays.copyOf(vetor, vetor.length);
        Arrays.sort(vetorOrdenado);
        return vetorOrdenado;
    }
}

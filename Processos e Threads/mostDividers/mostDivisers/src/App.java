public class App {
    public static void main(String[] args) {
        System.out.println("Executando a versão com apenas uma thread:");
        SingleThreadedDivisorFinder.main(args);

        System.out.println("\nExecutando a versão com várias threads:");
        MultiThreadedDivisorFinder.main(args);
    }
}

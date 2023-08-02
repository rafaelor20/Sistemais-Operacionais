public class SingleThreadedDivisorFinder {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int maxDivisors = 0;
        int numberWithMaxDivisors = 1;

        for (int num = 1; num <= 20000; num++) {
            int divisorCount = countDivisors(num);
            if (divisorCount > maxDivisors) {
                maxDivisors = divisorCount;
                numberWithMaxDivisors = num;
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Número com maior quantidade de divisores: " + numberWithMaxDivisors);
        System.out.println("Quantidade de divisores: " + maxDivisors);
        System.out.println("Tempo necessário (em milissegundos): " + (endTime - startTime));
    }

    private static int countDivisors(int number) {
        int count = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
            }
        }
        return count;
    }
}

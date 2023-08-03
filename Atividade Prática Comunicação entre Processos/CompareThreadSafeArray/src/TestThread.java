import java.util.Random;

class TestThread<E> extends Thread {
    private final MyList<E> list;
    private final int numOperations;

    public static long totalTime = 0;

    public TestThread(MyList<E> list, int numOperations) {
        this.list = list;
        this.numOperations = numOperations;
    }

    @Override
    public void run() {
        Random random = new Random();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numOperations; i++) {
            int operation = random.nextInt(3);
            int element = random.nextInt(1000);

            switch (operation) {
                case 0:
                    list.add((E) Integer.valueOf(element));
                    break;
                case 1:
                    if (!list.isEmpty()) {
                        list.get(random.nextInt(list.size()));
                    }
                    break;
                case 2:
                    if (!list.isEmpty()) {
                        list.remove((E) Integer.valueOf(element));
                    }
                    break;
            }
        }

        long endTime = System.currentTimeMillis();
        totalTime += (endTime - startTime);
    }
}
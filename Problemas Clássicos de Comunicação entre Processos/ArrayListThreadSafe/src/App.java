import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        // Exemplo de uso da ThreadSafeArrayList
        ThreadSafeArrayList<String> threadSafeList = new ThreadSafeArrayList<>();

        // Adicionando elementos de forma threadsafe
        threadSafeList.add("Elemento 1");
        threadSafeList.add("Elemento 2");

        // Iterando a lista de forma threadsafe
        synchronized (threadSafeList) {
            for (String element : threadSafeList) {
                System.out.println(element);
            }
        }

        // Outras operações com a lista...
    }
}

// A classe ThreadSafeArrayList continua igual, não é necessário modificá-la.

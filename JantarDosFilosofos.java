import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore; // Importa a classe Semaphore


public class JantarDosFilosofos {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Mesa mesa = new Mesa(numFilosofos); // Cria a mesa para gerenciar o estado visual
        Lock[] garfos = new ReentrantLock[numFilosofos];

        // --- IMPLEMENTAÇÃO DO SEMÁFORO ---
        // Cria um semáforo que permite que no máximo (N-1) filósofos tentem comer ao mesmo tempo.
        // Isso garante que pelo menos um filósofo sempre conseguirá pegar os dois garfos.
        Semaphore garcom = new Semaphore(numFilosofos - 1);

        Thread[] filosofos = new Thread[numFilosofos];
        // Fim do Semáforo

        // Inicializar os garfos
        for (int i = 0; i < numFilosofos; i++) {
            garfos[i] = new ReentrantLock();
        }

        // Inicializar e iniciar as threads dos filósofos
        for (int i = 0; i < numFilosofos; i++) {
            // Passa a instância da mesa para cada filósofo
            filosofos[i] = new Thread(new Filosofo(i, garfos[i], garfos[(i + 1) % numFilosofos], mesa, garcom));
            filosofos[i].start();
        }

        // Iniciar a thread que desenha a mesa no console
        Thread desenhista = new Thread(mesa);
        desenhista.start();
    }
}

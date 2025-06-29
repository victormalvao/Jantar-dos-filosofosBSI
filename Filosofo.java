import java.util.concurrent.locks.Lock;
import java.util.concurrent.Semaphore;

// Classe Filosofo implementa Runnable para permitir que cada filósofo seja uma thread
class Filosofo implements Runnable {
    private final int id;
    private final Lock garfoEsquerdo;
    private final Lock garfoDireito;
    private final Mesa mesa;
    private final Semaphore garcom; // A variável final
    private final int garfoEsquerdoId;
    private final int garfoDireitoId;

    // Construtor que recebe o ID do filósofo, os garfos (locks), a mesa e o semáforo do garçom
    public Filosofo(int id, Lock garfoEsquerdo, Lock garfoDireito, Mesa mesa, Semaphore garcom) {
        this.id = id;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
        this.mesa = mesa;
        this.garcom = garcom; // Atribuição única e correta
        this.garfoEsquerdoId = id;
        this.garfoDireitoId = (id + 1) % 5;
    }
    // Método para simular o ato de pensar
    private void pensar() throws InterruptedException {
        mesa.setEstadoFilosofo(id, "PENSANDO");
        Thread.sleep((long) (Math.random() * 2000 + 1000));
    }
    // Método para simular o ato de comer
    private void comer() throws InterruptedException {
        mesa.setEstadoFilosofo(id, "COMENDO");
        Thread.sleep((long) (Math.random() * 2000 + 1000));
    }

    // Método run que implementa a lógica do filósofo
    @Override
    public void run() {
        try {
            // Loop infinito para simular o comportamento do filósofo
            while (true) {

                pensar();
                // Antes de tentar pegar os garfos, o filósofo deve estar faminto
                mesa.setEstadoFilosofo(id, "FAMINTO");

                // Pede permissão ao "garçom" (semáforo)
                garcom.acquire();

                try {
                    // Tenta pegar os garfos esquerdo e direito
                    garfoEsquerdo.lock();
                    mesa.pegarGarfo(garfoEsquerdoId);
                    // O filósofo tenta pegar o garfo direito
                    garfoDireito.lock();
                    mesa.pegarGarfo(garfoDireitoId);

                    comer();

                    // A thread é liberada e o filósofo libera o garfo direito e esquerdo
                    garfoDireito.unlock();
                    mesa.liberarGarfo(garfoDireitoId);

                    garfoEsquerdo.unlock();
                    mesa.liberarGarfo(garfoEsquerdoId);
                } finally {
                    // Garante que o filósofo sempre libere a permissão do semáforo,
                    // mesmo que ocorra um erro inesperado.
                    garcom.release();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
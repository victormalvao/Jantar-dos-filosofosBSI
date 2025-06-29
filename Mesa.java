public class Mesa implements Runnable {
    private final String[] estadosFilosofos;
    private final boolean[] garfosUsados; // Simplificado para um array
    private final int tamanho;

    public Mesa(int tamanho) {
        this.tamanho = tamanho;
        this.estadosFilosofos = new String[tamanho];
        this.garfosUsados = new boolean[tamanho];
        for (int i = 0; i < tamanho; i++) {
            estadosFilosofos[i] = "PENSANDO";
            garfosUsados[i] = false; // Garfo i pertence à direita do Filósofo i-1 e à esquerda do Filósofo i
        }
    }

    public void setEstadoFilosofo(int id, String estado) {
        estadosFilosofos[id] = estado;
    }


    // Agora o método aceita o ID do garfo e o utiliza
    public void pegarGarfo(int garfoId) {
        garfosUsados[garfoId] = true;
    }

    public void liberarGarfo(int garfoId) {
        garfosUsados[garfoId] = false;
    }
    // --- FIM DA CORREÇÃO ---

    private void desenharMesa() {
        // Limpa o console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("================= JANTAR DOS FILÓSOFOS =================");
        System.out.println("ESTADOS: PENSANDO 🤔 | FAMINTO 😐 | COMENDO 🍝\n");

        for (int i = 0; i < tamanho; i++) {
            String icone = "🤔";
            if ("COMENDO".equals(estadosFilosofos[i])) icone = "🍝";
            if ("FAMINTO".equals(estadosFilosofos[i])) icone = "😐";

            // O garfo 'i' está à esquerda do filósofo 'i'
            // O garfo '(i + 1) % tamanho' está à direita do filósofo 'i'
            String garfoEsquerdo = garfosUsados[i] ? "--> " : " --- ";
            String garfoDireito = garfosUsados[(i + 1) % tamanho] ? " <--" : " --- ";

            System.out.printf("Filósofo %d [%-8s] %s%s%s\n", i, estadosFilosofos[i], garfoEsquerdo, icone, garfoDireito);
        }
        System.out.println("\n========================================================");
    }

    @Override
    public void run() {
        try {
            while (true) {
                desenharMesa();
                Thread.sleep(500); // Atualiza a cada meio segundo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
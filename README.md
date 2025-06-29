# Jantar dos Filósofos

Este repositório contém uma implementação em Java do clássico problema de sincronização em ciência da computação conhecido como "Jantar dos Filósofos".

## O Problema

O problema do Jantar dos Filósofos descreve um cenário com cinco filósofos sentados em uma mesa redonda. Entre cada par de filósofos, há um garfo. Para comer, um filósofo precisa pegar os dois garfos, o da sua esquerda e o da sua direita. O desafio é projetar um algoritmo que permita que os filósofos comam sem que ocorra um impasse (deadlock), onde todos pegam um garfo e esperam indefinidamente pelo outro.

## A Solução Implementada

A solução neste projeto utiliza threads para representar os filósofos e `ReentrantLock` para os garfos. Para prevenir o deadlock, foi utilizado um `Semaphore`.

O semáforo (`garcom`) é inicializado com `N-1` permissões (onde N é o número de filósofos). Isso garante que, no máximo, quatro filósofos possam tentar pegar os garfos simultaneamente. Essa restrição impede a condição de espera circular que leva ao deadlock, pois sempre haverá pelo menos um garfo disponível na mesa quando um filósofo decidir comer.

## Estrutura dos Arquivos

O projeto está dividido em três classes principais:

* **`JantarDosFilosofos.java`**: A classe principal que inicializa a simulação. Ela cria os filósofos, os garfos (locks) e o semáforo que controla o acesso à mesa. Também inicia a thread responsável por desenhar o estado da mesa no console.
* **`Filosofo.java`**: Representa cada filósofo como uma `Runnable`. Cada filósofo alterna entre os estados de "PENSANDO", "FAMINTO" e "COMENDO". Para comer, um filósofo deve adquirir uma permissão do semáforo e, em seguida, bloquear os dois garfos (esquerdo e direito).
* **`Mesa.java`**: Responsável pela visualização do estado da simulação no console. Ela é executada em uma thread separada e atualiza a tela periodicamente, mostrando o estado de cada filósofo e quais garfos estão em uso.

## Como Executar

1.  **Pré-requisitos**:

    * Java Development Kit (JDK) instalado.

2.  **Compilação**:
    Abra um terminal na pasta raiz do projeto e compile os arquivos `.java`:

    ```bash
    javac *.java
    ```

3.  **Execução**:
    Após a compilação, execute a classe principal:

    ```bash
    java JantarDosFilosofos
    ```

## Saída Esperada

Ao executar o programa, você verá no terminal uma representação textual da mesa, que é atualizada continuamente. Ela mostrará cada filósofo e seu estado atual (🤔 PENSANDO, 😐 FAMINTO, ou 🍝 COMENDO), bem como a disponibilidade dos garfos entre eles.

```
================= JANTAR DOS FILÓSOFOS =================
ESTADOS: PENSANDO 🤔 | FAMINTO 😐 | COMENDO 🍝

Filósofo 0 [PENSANDO]  --- 🤔 --- 
Filósofo 1 [PENSANDO]  --- 🤔 --- 
Filósofo 2 [PENSANDO]  --- 🤔 --- 
Filósofo 3 [PENSANDO]  --- 🤔 --- 
Filósofo 4 [COMENDO ]  --> 🍝 <--

========================================================
```
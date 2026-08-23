# Jantar dos Filósofos em Java

Simulação concorrente do clássico problema dos filósofos jantando. Cada filósofo é executado em uma thread e precisa compartilhar dois garfos com os vizinhos sem causar deadlock.

## O que o projeto demonstra

- Uso de `Runnable` e `Thread` para representar concorrência.
- Uso de `ReentrantLock` para proteger os garfos.
- Uso de `Semaphore` com `N - 1` permissões para reduzir a possibilidade de espera circular.
- Atualização do estado dos filósofos e dos garfos no console.

## Como executar

Pré-requisito: JDK instalado e disponível no `PATH`.

```bash
git clone https://github.com/victormalvao/Jantar-dos-filosofosBSI.git
cd Jantar-dos-filosofosBSI
javac -d out *.java
java -cp out JantarDosFilosofos
```

A simulação continua em execução para representar os ciclos de pensamento e alimentação. Use `Ctrl+C` para encerrá-la.

## Estrutura

```text
JantarDosFilosofos.java  # Inicialização da simulação
Filosofo.java            # Thread e regras de acesso aos garfos
Mesa.java                # Visualização do estado no console
```

## Status

Projeto acadêmico de estudo de concorrência e sincronização. Não possui build automatizado nem testes automatizados; os parâmetros da simulação estão definidos diretamente no código.
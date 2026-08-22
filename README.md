# Jantar dos Filosofos em Java

Simulacao concorrente do classico problema dos filosofos jantando. Cada filosofo e executado em uma thread e precisa compartilhar dois garfos com os vizinhos sem causar deadlock.

## O que o projeto demonstra

- Uso de `Runnable` e `Thread` para representar concorrencia.
- Uso de `ReentrantLock` para proteger os garfos.
- Uso de `Semaphore` com `N - 1` permissoes para reduzir a possibilidade de espera circular.
- Atualizacao do estado dos filosofos e dos garfos no console.

## Como executar

Prerequisito: JDK instalado e disponivel no `PATH`.

```bash
git clone https://github.com/victormalvao/Jantar-dos-filosofosBSI.git
cd Jantar-dos-filosofosBSI
javac -d out *.java
java -cp out JantarDosFilosofos
```

A simulacao continua executando para representar os ciclos de pensamento e alimentacao. Use `Ctrl+C` para encerra-la.

## Estrutura

```text
JantarDosFilosofos.java  # Inicializacao da simulacao
Filosofo.java            # Thread e regras de acesso aos garfos
Mesa.java                # Visualizacao do estado no console
```

## Status

Projeto academico de estudo de concorrencia e sincronizacao. Nao possui build automatizado nem testes automatizados; os parametros da simulacao estao definidos diretamente no codigo.

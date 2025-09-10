import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nExercícios 1 e 2");
            System.out.println("1. Testar Pilha Dinâmica");
            System.out.println("2. Testar Fila Dinâmica");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    menuPilha(sc);
                    break;
                case 2:
                    menuFila(sc);
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        
        sc.close();
    }

    public static void menuPilha(Scanner sc) {
        Pilha pilha = new Pilha();
        int opcaoPilha;
        do {
            System.out.println("\nMenu Pilha");
            System.out.println("1. Inserir");
            System.out.println("2. Remover ");
            System.out.println("3. Imprimir");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoPilha = sc.nextInt();

            switch (opcaoPilha) {
                case 1:
                    System.out.print("Digite o valor para inserir na pilha: ");
                    int valor = sc.nextInt();
                    pilha.insere(valor);
                    break;
                case 2:
                    int valorRemovido = pilha.remove();
                    if (valorRemovido != -1) {
                        System.out.println(">> Elemento removido da pilha: " + valorRemovido);
                    } else {
                        System.out.println("A pilha está vazia. Não é possível remover.");
                    }
                    break;
                case 3:
                    pilha.imprime();
                    break;
            }
        } while (opcaoPilha != 0);
    }

    public static void menuFila(Scanner sc) {
        Fila fila = new Fila();
        int opcaoFila;
        do {
            System.out.println("\nMenu Fila");
            System.out.println("1. Inserir");
            System.out.println("2. Remover");
            System.out.println("3. Imprimir");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoFila = sc.nextInt();

            switch (opcaoFila) {
                case 1:
                    System.out.print("Digite o valor para inserir na fila: ");
                    int valor = sc.nextInt();
                    fila.insere(valor);
                    break;
                case 2:
                    int valorRemovido = fila.remove();
                    if (valorRemovido != -1) {
                         System.out.println(">> Elemento removido da fila: " + valorRemovido);
                    } else {
                        System.out.println("A fila está vazia. Não é possível remover.");
                    }
                    break;
                case 3:
                    fila.imprime();
                    break;
            }
        } while (opcaoFila != 0);
    }
}

class Node {
    int valor;
    Node proximo;

    public Node(int valor) {
        this.valor = valor;
        this.proximo = null;
    }
}

class Pilha {
    private Node topo;

    public Pilha() {
        this.topo = null;
    }

    public void insere(int valor) {
        Node novoNode = new Node(valor);
        novoNode.proximo = topo;
        this.topo = novoNode;
        System.out.println(">> Valor " + valor + " inserido na pilha.");
    }

    public int remove() {
        if (topo == null) {
            return -1;
        }
        int valorRemovido = topo.valor;
        topo = topo.proximo;
        return valorRemovido;
    }

    public void imprime() {
        if (topo == null) {
            System.out.println("A pilha está vazia.");
            return;
        }
        System.out.println("--- Elementos da Pilha (Topo -> Base) ---");
        Node atual = topo;
        while (atual != null) {
            System.out.println("[ " + atual.valor + " ]");
            atual = atual.proximo;
        }
        System.out.println("---------------------------------------");
    }
}

class Fila {
    private Node inicio;
    private Node fim;

    public Fila() {
        this.inicio = null;
        this.fim = null;
    }

    public void insere(int valor) {
        Node novoNode = new Node(valor);
        if (fim == null) {
            inicio = novoNode;
            fim = novoNode;
        } else {
            fim.proximo = novoNode;
            fim = novoNode;
        }
        System.out.println(">> Valor " + valor + " inserido na fila.");
    }

    public int remove() {
        if (inicio == null) {
            return -1;
        }
        int valorRemovido = inicio.valor;
        inicio = inicio.proximo;
        
        if (inicio == null) {
            fim = null;
        }
        return valorRemovido;
    }

    public void imprime() {
        if (inicio == null) {
            System.out.println("A fila está vazia.");
            return;
        }
        System.out.print("--- Elementos da Fila (Início -> Fim): ");
        Node atual = inicio;
        while (atual != null) {
            System.out.print(atual.valor + " -> ");
            atual = atual.proximo;
        }
        System.out.println("FIM ---");
    }
}

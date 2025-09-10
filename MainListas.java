import java.util.Scanner;

public class MainListas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Merge");
        
        System.out.println("\nCriando a Fila A:");
        Fila filaA = criarFilaInterativa(sc);
        filaA.ordena(); // Ordena a fila A
        
        System.out.println("\nCriando a Fila B:");
        Fila filaB = criarFilaInterativa(sc);
        filaB.ordena(); // Ordena a fila B
        
        System.out.println("\n-----------------------------------------");
        System.out.print("Fila A ordenada: ");
        filaA.imprime();
        
        System.out.print("Fila B ordenada: ");
        filaB.imprime();
        System.out.println("-----------------------------------------");
        
        System.out.println("\nRealizando o Merge com lista encadeada...");
        Fila filaC = Merge.mergeComListas(filaA, filaB);
        
        System.out.print("\nFila C: ");
        filaC.imprime();
        
        sc.close();
    }
    
    // Método para criar filas
    public static Fila criarFilaInterativa(Scanner sc){
        Fila fila = new Fila();
        System.out.print("Quantos elementos quer inserir? ");
        int tamanho = sc.nextInt();
        
        if (tamanho <= 0) {
            System.out.println("Nenhum elemento para inserir.");
            return fila;
        }
        
        System.out.println("Digite os números.");
        for(int i = 0; i < tamanho; i++){
            System.out.print("Digite o elemento " + (i + 1) + ": ");
            int valor = sc.nextInt();
            fila.insere(valor);
        }
        return fila;
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
    }
    
    public int remove() {
        if (estaVazia()) {
            return -1;
        }
        int valorRemovido = inicio.valor;
        inicio = inicio.proximo;
        
        if (inicio == null) {
            fim = null;
        }
        return valorRemovido;
    }
    
    public boolean estaVazia() {
        return inicio == null;
    }

    public int verInicio() {
        if (estaVazia()) {
            return -1;
        }
        return inicio.valor;
    }

    public void imprime() {
        if (estaVazia()) {
            System.out.println("A fila está vazia.");
            return;
        }
        
        Node atual = inicio;
        while (atual != null) {
            System.out.print(atual.valor + " -> ");
            atual = atual.proximo;
        }
        System.out.println("FIM");
    }

    // Método de ordenação
    public void ordena() {
        if (inicio == null || inicio.proximo == null) {
            return;
        }
        
        boolean trocou;
        Node atual;
        Node proximo = null;

        do {
            trocou = false;
            atual = inicio;

            while (atual.proximo != proximo) {
                if (atual.valor > atual.proximo.valor) {
                    // Troca os valores dos nós
                    int temp = atual.valor;
                    atual.valor = atual.proximo.valor;
                    atual.proximo.valor = temp;
                    trocou = true;
                }
                atual = atual.proximo;
            }
            proximo = atual;
        } while (trocou);
    }
}

class Merge {
    public static Fila mergeComListas(Fila filaA, Fila filaB) {
        Fila filaC = new Fila();
        
        while (!filaA.estaVazia() && !filaB.estaVazia()) {
            if (filaA.verInicio() <= filaB.verInicio()) {
                filaC.insere(filaA.remove());
            } else {
                filaC.insere(filaB.remove());
            }
        }
        
        while (!filaA.estaVazia()) {
            filaC.insere(filaA.remove());
        }

        while (!filaB.estaVazia()) {
            filaC.insere(filaB.remove());
        }
        
        return filaC;
    }
}

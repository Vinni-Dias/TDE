import java.util.Scanner;

public class MainVetores {

    private static final int TAMANHO_MAX = 100; // Define o tamanho máximo da fila

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("--- Merge com Vetores ---");
        
        System.out.println("\nCriando a Fila A:");
        FilaVetor filaA = criarFilaInterativaVetor(sc);
        filaA.ordena(); // Ordena a fila A
        
        System.out.println("\nCriando a Fila B:");
        FilaVetor filaB = criarFilaInterativaVetor(sc);
        filaB.ordena(); // Ordena a fila B
        
        System.out.println("\n-----------------------------------------");
        System.out.print("Fila A ordenada: ");
        filaA.imprime();
        
        System.out.print("Fila B ordenada: ");
        filaB.imprime();
        System.out.println("-----------------------------------------");
        
        System.out.println("\nRealizando o Merge com vetor...");
        FilaVetor filaC = MergeVetor.mergeComVetores(filaA, filaB);
        
        System.out.print("\nFila C: ");
        filaC.imprime();
        
        sc.close();
    }
    
    // Método para criar filas com entrada do usuário
    public static FilaVetor criarFilaInterativaVetor(Scanner sc){
        FilaVetor fila = new FilaVetor(TAMANHO_MAX);
        System.out.print("Quantos elementos quer inserir? (Max. " + TAMANHO_MAX + "): ");
        int tamanho = sc.nextInt();
        
        if (tamanho <= 0 || tamanho > TAMANHO_MAX) {
            System.out.println("Nenhum elemento válido para inserir.");
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

class FilaVetor {
    private int[] dados;
    private int inicio;
    private int fim;
    private int tamanhoAtual;

    public FilaVetor(int capacidade) {
        dados = new int[capacidade];
        inicio = 0;
        fim = -1; // -1 indica fila vazia
        tamanhoAtual = 0;
    }

    public void insere(int valor) {
        if (tamanhoAtual == dados.length) {
            System.out.println("A fila está cheia. Não é possível inserir.");
            return;
        }
        fim = (fim + 1) % dados.length;
        dados[fim] = valor;
        tamanhoAtual++;
    }
    
    public int remove() {
        if (estaVazia()) {
            return -1;
        }
        int valorRemovido = dados[inicio];
        inicio = (inicio + 1) % dados.length;
        tamanhoAtual--;
        return valorRemovido;
    }
    
    public boolean estaVazia() {
        return tamanhoAtual == 0;
    }

    public int verInicio() {
        if (estaVazia()) {
            return -1;
        }
        return dados[inicio];
    }
    
    public int getTamanhoAtual() {
        return tamanhoAtual;
    }

    public void imprime() {
        if (estaVazia()) {
            System.out.println("A fila está vazia.");
            return;
        }
        
        int i = inicio;
        int count = 0;
        System.out.print("--- Elementos da Fila (Início -> Fim): ");
        while(count < tamanhoAtual){
            System.out.print(dados[i] + " -> ");
            i = (i + 1) % dados.length;
            count++;
        }
        System.out.println("FIM");
    }

    public void ordena() {
        if (tamanhoAtual <= 1) {
            return;
        }
        
        boolean trocou;
        do {
            trocou = false;
            for (int i = 0; i < tamanhoAtual - 1; i++) {
                int indexAtual = (inicio + i) % dados.length;
                int indexProximo = (inicio + i + 1) % dados.length;

                if (dados[indexAtual] > dados[indexProximo]) {
                    int temp = dados[indexAtual];
                    dados[indexAtual] = dados[indexProximo];
                    dados[indexProximo] = temp;
                    trocou = true;
                }
            }
        } while (trocou);
    }
}

class MergeVetor {
    public static FilaVetor mergeComVetores(FilaVetor filaA, FilaVetor filaB) {
        FilaVetor filaC = new FilaVetor(filaA.getTamanhoAtual() + filaB.getTamanhoAtual());
        
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

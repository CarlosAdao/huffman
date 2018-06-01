package huffman;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author José Carlos Adão - Albert Paixão - Ricardo Neves
 * 
 */

public class Huffman {

    static ArrayList<No> lista = new ArrayList<>();
    static ArrayList<No> listaFolhas = new ArrayList<>();
    static int bitRepresentacao = 2;//usado para representar cada caracter

    public static void main(String[] args) {
        String msg, tx_compressao;

        String str = "abcbcdacbbbbca";//palavra de entrada
        String frs = str;
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            insereCaracter(c);//isiro cada caracter na lista de caracteres 
        }

        while (lista.size() >= 2) {
            
            /*chamo o método para ordenar a lista de caracter*/
            ordenaLista(lista);
           

            No noAux = new No();// crio o novo nó para receber a soma da quantidade dos dois nós 
            int indiceNewNo = lista.get(0).qtd + lista.get(1).qtd;
            noAux.setQtd(indiceNewNo);
            noAux.setCaracter(' ');
            inserir(noAux, lista.get(0), lista.get(1));
            
            lista.add(noAux);
            lista.remove(lista.get(1));
            lista.remove(lista.get(0));

        }
       
        lista.get(0).setBits("");//defino o bit da Raiz como vazio 
        insereBits(lista.get(0));
        msg = "[Folhas]";
        exibeLista(listaFolhas, msg);
        
        tx_compressao = String.valueOf(calculaCompressaoCaracter(listaFolhas))+"%";
        System.out.println("\nA taxa de compressão é de: "+tx_compressao);

    }

    //Metodo usado para inserir os caracteres da frase em um ArrayList
    public static void insereCaracter(char caracter) {
        int cont = 0;//contador usado para saber se não foi incrementado nenhum caracter 

        //caso a lista esteja vazia adiciona um caracter
        if (lista == null) {
            No no = new No(caracter, 1);
            lista.add(no);
        } else {
            for (No no : lista) {
                if (caracter == no.caracter) {
                    no.qtd = (no.qtd + 1);
                } else {
                    cont++;
                }
            }
        }

        if (cont == lista.size()) {
            No no = new No(caracter, 1);
            lista.add(no);
        }
    }
    
    /*Método usado para inserir o novo nó da árvore*/
    public static void inserir(No newNode, No node1, No node2) {

        if (node1.getQtd() >= node2.getQtd()) {

            if (newNode.noEsquerdo != null) {
                /*Usado para percorrer a arvore ate encontrar um nó nulo a esquerda*/
                inserir(newNode.noEsquerdo, node1, node2);
            } else {
                /*Insere o proximo nó a esquerda da arvore*/
                newNode.noEsquerdo = node1;
                newNode.noDireito = node2;
            }

        } else if (newNode.noDireito != null) {
            /*Usado para percorrer a arvore ate encontrar um nó nulo a direita*/
            inserir(newNode.noDireito, node1, node2);
        } else {

            /*Insere o proximo nó a direito da arvore*/
            newNode.noDireito = node1;
            newNode.noEsquerdo = node2;
        }
    }
    
    /*Método usado para obter a sequência de bits de cada caracter*/
    public static void insereBits(No node) {

        if (node.noEsquerdo != null) {

            String bits = node.getBits() + "0";
            node.noEsquerdo.setBits(bits);

            if (node.noEsquerdo.getCaracter() != ' ') {
                listaFolhas.add(node.noEsquerdo);
            }
            insereBits(node.noEsquerdo);

        }
        if (node.noDireito != null) {

            String bits = node.getBits() + "1";
            node.noDireito.setBits(bits);

            if (node.noDireito.getCaracter() != ' ') {
                listaFolhas.add(node.noDireito);
            }
            insereBits(node.noDireito);

        }

    }

    /*metodo usado para ordenar arraylist*/
    public static void ordenaLista(ArrayList<No> lista) {

        Collections.sort(lista, (Object o1, Object o2) -> {
            No no1 = (No) o1;
            No no2 = (No) o2;
            return no1.qtd < no2.qtd ? -1 : (no1.qtd > no2.qtd ? +1 : 0);
        });

    }
    
    /*Recebe como parametro uma lista contendo os nós folhas e calcula a texa de compresão*/
    public static double calculaCompressaoCaracter(ArrayList<No> lista){
        
        int qtd_bits;
        double soma = 0;
        int qtd_caracter;
        double taxaCompressao;
        int soma_qtd_caracter = 0;
        
        for(No no:lista){
            
            qtd_bits = no.getBits().length();
            qtd_caracter = no.getQtd();
            
            soma = soma + (qtd_bits * qtd_caracter);
            soma_qtd_caracter = soma_qtd_caracter + qtd_caracter;
        }   
       
        double caracter_sem_compressao = soma_qtd_caracter * bitRepresentacao;
        taxaCompressao = ((caracter_sem_compressao - soma) / caracter_sem_compressao)*100;
        
        return taxaCompressao;
    }

    /*Método usado para exibir a árvore*/
    public static void exibeArvore(No node) {

        if (node.noEsquerdo != null) {
            exibeArvore(node.noEsquerdo);

            System.out.println("\nCaracter: " + node.noEsquerdo.caracter);
            System.out.println("Quantidade: " + node.noEsquerdo.qtd);
        }
        if (node.noDireito != null) {
            exibeArvore(node.noDireito);

            System.out.println("\nCaracter: " + node.noDireito.caracter);
            System.out.println("Quantidade: " + node.noDireito.qtd);
        }
    }
    
    /*Método uesdo para exibir a lista*/
    public static void exibeLista(ArrayList<No> lista, String titulo) {

        System.out.print("\n" + titulo + "\n");
        lista.stream().forEach((no) -> {
            System.out.println("\nLetra: " + no.caracter + "\nquantidade: " + no.qtd + "\nBits: " + no.bits);
        });

    }

}

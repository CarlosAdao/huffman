/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * 
 */
public class No {

    char caracter;
    String pai;
    int qtd;
    String bits;

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    public No(char caracter, String pai, int qtd, String bits, No noEsquerdo, No noDireito) {
        this.caracter = caracter;
        this.pai = pai;
        this.qtd = qtd;
        this.bits = bits;
        this.noEsquerdo = noEsquerdo;
        this.noDireito = noDireito;
    }
    No noEsquerdo;
    No noDireito;

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    No() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public No getNoEsquerdo() {
        return noEsquerdo;
    }

    public void setNoEsquerdo(No noEsquerdo) {
        this.noEsquerdo = noEsquerdo;
    }

    public No(int qtd) {
        this.qtd = qtd;
    }

    public No getNoDireito() {
        return noDireito;
    }

    public void setNoDireito(No noDireito) {
        this.noDireito = noDireito;
    }

    public No(char caracter, int qtd, No noEsquerdo, No noDireito) {
        this.caracter = caracter;
        this.qtd = qtd;
        this.noEsquerdo = noEsquerdo;
        this.noDireito = noDireito;
    }

    public No(char caracter, int qtd) {
        this.caracter = caracter;
        this.qtd = qtd;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    
}

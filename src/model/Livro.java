package model;
import util.Util;

public class Livro implements Comparable<Livro>{
    private String titulo, autor, categoria;
    private int anoPub, qtde, codigo;
    
    public Livro(String titulo, String autor, String categoria, int anoPub, int qtde) {
        this.titulo = Util.formataString(titulo);
        this.autor = Util.formataString(autor);
        this.categoria = Util.formataString(categoria);
        this.anoPub = anoPub;
        this.qtde = qtde;
    }

    public String livroDesc() {
        return titulo + " de " + autor + " quantidade: " + qtde;
    }

    public String info() {
        return 
        "Título: " + titulo + "\n" +
        "Autor: " + autor + "\n" +
        "Categoria: " + categoria + "\n" +
        "Ano de publicação: " + anoPub + "\n" +
        "Quantidade atual: " + qtde + "\n" +
        "Código: " + codigo + "\n" + 
        "////////////////////////////\n";
    }

    public boolean reduzirEstoque(){
        if (qtde > 0) {
            qtde--;
            return true;
        }
        return false;
    } 

    public void aumentarEstoque(){
        qtde ++;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAnoPub() {
        return anoPub;
    }

    public void setAnoPub(int anoPub) {
        this.anoPub = anoPub;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public int compareTo(Livro livro) {
        return autor.compareTo(livro.getAutor());
    }
    
    
}

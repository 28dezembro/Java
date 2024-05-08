package controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import util.*;

import model.*;

public class BibliotecaController {
    private List<Livro> livros;
    private List<Usuario> usuarios;

    public BibliotecaController(List<Livro> livros, List<Usuario> usuarios) {
        this.livros = livros;
        this.usuarios = usuarios;
    }

    public int gerarCodigo(){
        int res = 1;

        for (Livro l : livros) {
            if(l.getCodigo() > res) res = l.getCodigo();
        }
        return ++res;
    }

    public void adicionarUsuario(Usuario novoUsuario){
        if (buscarUsuario(novoUsuario.getCpf(), usuarios) == null) {
            if (Util.validaEmail(novoUsuario.getEmail())){
                usuarios.add(novoUsuario);
            }
        }
    }

    public void adicionarLivro(Livro novoLivro){
        if (buscarLivro(String.valueOf(novoLivro.getCodigo()), livros) == null){
            novoLivro.setCodigo(gerarCodigo());
            livros.add(novoLivro);
        }
    }   

    public void imprimirListaLivro(List<Livro> livros) {
        livros.forEach(livro -> {
            System.out.println(livro.info());
        });
    }

    public List<Livro> buscarListaLivro(String parametro, List<Livro> livros) {
        return livros.stream()
            .filter(l -> {
                return String.valueOf(l.getCodigo()).contains(parametro)
                        || l.getTitulo().contains(parametro)
                        || l.getAutor().contains(parametro)
                        || l.getCategoria().contains(parametro);
            })
            .collect(Collectors.toList());
    }

    //sobrecarga
    public Livro buscarLivro(String parametro, List<Livro> livros) {
        return livros.stream()
            .filter(l -> {
                return l.getTitulo().contains(parametro)
                    || l.getAutor().contains(parametro)
                    || l.getCategoria().contains(parametro);
            })
            .findFirst()
            .orElse(null);
    }

    public Livro buscarLivro(int codigo){
        for (Livro l : livros) {
            if (l.getCodigo()==codigo) return l;
        }
        return null;
    }

    public Usuario buscarUsuario(String parametro, List<Usuario> usuarios) {
        return usuarios.stream()
            .filter(u -> {
                return u.getNome().contains(parametro)
                    || u.getEndereco().contains(parametro)
                    || u.getEmail().contains(parametro)
                    || u.getTelefone().contains(parametro);
            })
            .findFirst()
            .orElse(null);
    }


    public List<Livro> emprestarLivro(List<Livro> livros){
        return livros.stream()
            .filter(l -> l.reduzirEstoque())
            .map(l -> new Livro(l.getTitulo(), l.getAutor(), l.getCategoria(), l.getAnoPub(), 1, l.getCodigo()))
            .collect(Collectors.toList());
    }

    public void devolverLivros(List<Livro> livros){
        livros
        .forEach(livro -> this.livros
        .forEach( l -> {
            if (l.getCodigo() == livro.getCodigo()) {
                l.aumentarEstoque();
            }
        }));
    }

    public String relatorio(){
        Collections.sort(livros);
        return "Biblioteca AlexAndré: " + "\n" +
        "\nUsuários: \n" + usuarios.stream().map(u -> u.info()).collect(Collectors.joining("\n")) +
        "\n\nLivros: \n" + livros.stream().map(l -> l.info()).collect(Collectors.joining("\n"));
    } 

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    } 

}

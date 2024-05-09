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

    public int gerarCodigo() throws Exception{
        int res = 1;
        try {
            for (Livro l : livros) {
                if(l.getCodigo() > res) res = l.getCodigo();
            }
            return ++res;
        } catch (Exception e) {
            throw new Exception("\nNão foi possível gerar o código" + e.getMessage());
        }  
    }

    public void adicionarUsuario(Usuario novoUsuario) throws Exception{
        try {
            if (buscarUsuario(novoUsuario.getCpf()) == null) {
                if (Util.validaEmail(novoUsuario.getEmail())){
                    usuarios.add(novoUsuario);
                }
            }
        } catch (Exception e) {
            throw new Exception("\nNão foi possível adicionar um novo usuário" + e.getMessage());
        }
        
    }

    public void adicionarLivro(Livro novoLivro) throws Exception{
        try {
            if (buscarLivro(String.valueOf(novoLivro.getCodigo())) == null){
                novoLivro.setCodigo(gerarCodigo());
                livros.add(novoLivro);
            }
        } catch (Exception e) {
            throw new Exception("\nNão foi possível adicionar um novo livro" + e.getMessage());
        }

    }   

    public void imprimirListaLivro(List<Livro> livros) throws Exception{
        try {
            livros.forEach(livro -> {
                System.out.println(livro.info());
            });
        } catch (Exception e) {
            throw new Exception("\nNão foi possível imprimir a lista de livros" + e.getMessage());
        }
        
    }

    public List<Livro> buscarListaLivro(String parametro) throws Exception{
        try {
            return livros.stream()
            .filter(l -> {
                return String.valueOf(l.getCodigo()).contains(parametro)
                        || l.getTitulo().contains(parametro)
                        || l.getAutor().contains(parametro)
                        || l.getCategoria().contains(parametro);
            })
            .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("\nNão possível buscar a lista de livros desejada" + e.getMessage());
        }
       
    }

    //sobrecarga
    public Livro buscarLivro(String parametro) throws Exception{
        return livros.stream()
            .filter(l -> {
                return l.getTitulo().contains(parametro)
                    || l.getAutor().contains(parametro)
                    || l.getCategoria().contains(parametro);
            })
            .findFirst()
            .orElse(null);
    }

    public Livro buscarLivro(int codigo) throws Exception{
        try {
            for (Livro l : livros) {
                if (l.getCodigo()==codigo) return l;
            }
        } catch (Exception e) {
            throw new Exception("\nNão foi possível encontrar o livro pelo código inserido");
        }
        return null;
    }

    private Usuario buscarUsuario(String parametro) throws Exception{
        return usuarios.stream()
            .filter(u -> {
                return u.getCpf().contains(parametro);
            })
            .findFirst()
            .orElse(null);
    }


    public List<Livro> emprestarLivro(List<Livro> livros) throws Exception{
        try {
            return livros.stream()
            .filter(l -> l.reduzirEstoque())
            .map(l -> new Livro(l.getTitulo(), l.getAutor(), l.getCategoria(), l.getAnoPub(), 1, l.getCodigo()))
            .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("\nNão foi possível emprestar o livro desejado" + e.getMessage());
        }
        
    }

    public void devolverLivros(List<Livro> livros) throws Exception{
        try {
            livros
        .forEach(livro -> this.livros
        .forEach( l -> {
            if (l.getCodigo() == livro.getCodigo()) {
                l.aumentarEstoque();
            }
        }));
        } catch (Exception e) {
            throw new Exception("\nNão foi possível devolver os livros" + e.getMessage());
        }
        
    }

    public String relatorio() throws Exception{
        try {
            Collections.sort(livros);
            return "Biblioteca AlexAndré: " + "\n" +
            "\nUsuários: \n" + usuarios.stream().map(u -> u.info()).collect(Collectors.joining("\n")) +
            "\n\nLivros: \n" + livros.stream().map(l -> l.info()).collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new Exception("\nErro ao gerar relatório" + e.getMessage());
        }
        
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

package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;
import controller.BibliotecaController;

public class MenuClienteView {
    public static void main(String[] args, Scanner in, BibliotecaController controller, Cliente usuarioSelecionado) {
        boolean menu = true, emprestar;
        int op;
        String parametroPesquisa, resposta;
        List<Livro> livrosEmprestar = new ArrayList<>();
        List<Livro> livroBuscar = new ArrayList<>();
    
        while (menu) {
            System.out.println("Olá! "+usuarioSelecionado.getNome()+", selecione o que deseja fazer: ");
            System.out.println
            ("1 - Emprestar livros" + 
            "\n2 - Devolver livros" + 
            "\n3 - Pesquisar livro" + 
            "\n4 - Livros disponíveis para empréstimo" + 
            "\n5 - Selecionar outro usuário");
            op = in.nextInt();
            in.nextLine(); // consumir o caractere de quebra de linha

            switch (op) {
                case 1:
                emprestar = true;
                while (emprestar) {
                    System.out.println("Que livro gostaria de emprestar?");
                    parametroPesquisa = in.nextLine();
                    Livro livro = controller.buscarLivro(parametroPesquisa, controller.getLivros());
                        if (livro != null) {
                            System.out.println(parametroPesquisa + " emprestado com sucesso");
                            livrosEmprestar.add(livro);
                        } else {
                            System.out.println("Livro não encontrado.");
                        }

                    System.out.println("Deseja adicionar outro livro à lista de empréstimo? (sim/não)");
                    resposta = in.nextLine();
                        if (!resposta.equalsIgnoreCase("sim")) {
                            emprestar = false;
                            usuarioSelecionado.setLivros(controller.emprestarLivro(livrosEmprestar));
                        }   
                }
                break;

                case 2:
                    if (!usuarioSelecionado.getLivros().isEmpty()) {
                        System.out.println("Você tem os seguintes livros para devolver: ");
                            for (Livro livro : usuarioSelecionado.getLivros()) {
                                System.out.println(livro.livroDesc());
                            }

                        System.out.println("Deseja confirmar a devolução? (sim/não)");
                        resposta = in.nextLine();
                            if (resposta.equalsIgnoreCase("sim")){
                                controller.devolverLivros(usuarioSelecionado.getLivros());
                            }

                        }else{
                            System.out.println("Você não tem livros para devolver\n");
                        }
                break;

                case 3:
                        System.out.println("O que está procurando?"); 
                        parametroPesquisa = in.nextLine();
                        livroBuscar.addAll(controller.buscarListaLivro(parametroPesquisa, controller.getLivros()));
                        if (livroBuscar.isEmpty()) {
                            System.out.println("Não há nenhum livro com com este parâmetro");
                        }
                        livroBuscar.forEach(livro -> System.out.print(livro.info()));
                        livroBuscar.clear();
                break;

                case 4:                   
                    System.out.println("Livros disponíveis para empréstimo");
                    for (Livro livro : controller.getLivros()) {
                        if (livro.getQtde()>0) {
                            System.out.println(livro.livroDesc());
                        }
                    }
                break;

                case 5:
                    menu = false;
                    MenuSelecionaUsuario.main(args, in, controller);
                break;

                default:
                    System.out.println("Selecione uma opção válida");
                break;
            }
        }   
    }
}

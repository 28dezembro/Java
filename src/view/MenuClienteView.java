package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;
import controller.BibliotecaController;

public class MenuClienteView {
    public static void main(String[] args, Scanner in, BibliotecaController controller, Cliente usuarioSelecionado) throws Exception{
        boolean menu = true, emprestar;
        int op;
        String parametroPesquisa, resposta;
        List<Livro> livrosEmprestar = new ArrayList<>();
        List<Livro> livroBuscar = new ArrayList<>();
    
        while (menu) {
            System.out.println("\nOlá! "+usuarioSelecionado.getNome()+", selecione o que deseja fazer: ");
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
                        System.out.println("\nQue livro gostaria de emprestar?");
                        parametroPesquisa = in.nextLine();

                        try {
                            Livro livro = controller.buscarLivro(parametroPesquisa);
                            if (livro != null) {
                                System.out.println(parametroPesquisa + " emprestado com sucesso");
                                livrosEmprestar.add(livro);
 
                            } else {
                                System.out.println("\nLivro não encontrado.");
                            } 
                        } catch (Exception e) {
                            throw new Exception("\nErro ao emprestar o livro desejado" + e.getMessage());
                        }

                    System.out.println("\nDeseja adicionar outro livro à lista de empréstimo? (sim/não)");
                    resposta = in.nextLine();
                        if (!resposta.equalsIgnoreCase("sim")) {
                            emprestar = false;
                            try {
                                usuarioSelecionado.setLivros(controller.emprestarLivro(livrosEmprestar));
                                livrosEmprestar.clear();
                            } catch (Exception e) {
                                throw new Exception("\nErro ao emprestar o livro desejado" + e.getMessage());
                            }
                        }
                }
                break;

                case 2:
                    if (!usuarioSelecionado.getLivros().isEmpty()) {
                        System.out.println("\nVocê tem os seguintes livros para devolver: ");
                            for (Livro livro : usuarioSelecionado.getLivros()) {
                                System.out.println(livro.livroDesc());
                            }

                        System.out.println("\nDeseja confirmar a devolução? (sim/não)");
                        resposta = in.nextLine();

                            try {
                                if (resposta.equalsIgnoreCase("sim")){
                                    controller.devolverLivros(usuarioSelecionado.getLivros());
                                    usuarioSelecionado.getLivros().clear();
                                }
                            } catch (Exception e) {
                                throw new Exception("\nErro ao devolver os livros" + e.getMessage());
                            }

                        }else{
                            System.out.println("\nVocê não tem livros para devolver");
                        }
                break;

                case 3:
                        System.out.println("\nO que está procurando?"); 
                        parametroPesquisa = in.nextLine();
                        try {
                            livroBuscar.addAll(controller.buscarListaLivro(parametroPesquisa));
                            if (livroBuscar.isEmpty()) {
                                System.out.println("\nNão há nenhum livro com o parâmetro fornecido");
                            }
                            livroBuscar.forEach(livro -> System.out.print(livro.info()));
                            livroBuscar.clear(); 
                        } catch (Exception e) {
                            throw new Exception("\nErro buscar livro desejado" + e.getMessage());
                        }
                        
                break;

                case 4:                   
                    System.out.println("\nLivros disponíveis para empréstimo");
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
                    System.out.println("\nSelecione uma opção válida");
                break;
            }
        }   
    }
}

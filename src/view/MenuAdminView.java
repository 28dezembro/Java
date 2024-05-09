package view;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import controller.*;
import model.*;

public class MenuAdminView {
    public static void main(String[] args, Scanner in, BibliotecaController controller, Admin usuarioSelecionado) throws Exception {
        boolean menu = true;
        int op, anoPub, qtde, admin;
        String titulo, autor, categoria, parametroPesquisa, nome, endereco, email, telefone, cpf;
        List<Livro> livroBuscar = new ArrayList<>();

        while (menu==true) {
            System.out.println("Olá! "+usuarioSelecionado.getNome()+", selecione o que deseja fazer: ");
            System.out.println(
            "1 - Cadastrar um novo livro" + 
            "\n2 - Cadastrar um novo usuário" + 
            "\n3 - Pesquisar livro" + 
            "\n4 - Relatórios"+
            "\n5 - Selecionar outro usuário");
            op = in.nextInt();
            in.nextLine(); // consumir o caractere de quebra de linha

            switch (op) {
                case 1:
                System.out.println("Cadastro de novo livro: \n");
                    try {
                        System.out.println("Qual o nome do livro?");
                        titulo = in.nextLine();
                        System.out.println("Qual o autor?");
                        autor = in.nextLine();
                        System.out.println("Em que categoria se encontra?");
                        categoria = in.nextLine();
                        System.out.println("Em que ano foi publicado?");
                        anoPub = in.nextInt();
                        System.out.println("Quantidade?");
                        qtde = in.nextInt();
                        Livro novoLivro = new Livro(titulo, autor, categoria, anoPub, qtde);
                        controller.adicionarLivro(novoLivro);
                    } catch (Exception e) {
                            throw new Exception("\nErro ao adicionar livro" + e.getMessage());
                        }       
                break;
        
                case 2:
                    System.out.println("Cadastro de novo usuário: \n");
                    System.out.println("Tipo de usuário:\n1 - Admin\n2 - Cliente");
                    admin = in.nextInt();
                    in.nextLine(); // consumir o caractere de quebra de linha

                    switch (admin) {
                        case 1:
                            System.out.println("Nome: ");
                            nome = in.nextLine();
                            System.out.println("Endereço: ");
                            endereco = in.nextLine();
                            System.out.println("Email: ");
                            email = in.nextLine();   
                            System.out.println("Telefone: ");
                            telefone = in.nextLine();
                            System.out.println("CPF: ");
                            cpf = in.nextLine();
                            Admin novoAdmin = new Admin(nome, endereco, email, telefone, cpf, true);
                                try {
                                    if (novoAdmin != null) {
                                        controller.adicionarUsuario(novoAdmin);
                                    }
                                } catch (Exception e) {
                                throw new Exception("\nNão foi possivel adicionar o novo usuário, verifique os dados e tente novamente" + e.getMessage()); 
                                }                             
                        break;

                        case 2:
                            System.out.println("Nome: ");
                            nome = in.nextLine();
                            System.out.println("Endereço: ");
                            endereco = in.nextLine();
                            System.out.println("Email: ");
                            email = in.nextLine();   
                            System.out.println("Telefone: ");
                            telefone = in.nextLine();
                            System.out.println("CPF: ");
                            cpf = in.nextLine();
                            Cliente novoCliente = new Cliente(nome, endereco, email, telefone, new ArrayList<Livro>(), cpf, false);
                                try {
                                    if (novoCliente != null) {
                                        controller.adicionarUsuario(novoCliente);
                                    } 
                                } catch (Exception e) {
                                    throw new Exception("\nNão foi possivel adicionar o novo usuário, verifique os dados e tente novamente" + e.getMessage());
                                }
                        break;
                    
                        default:
                            System.out.println("\nSelecione uma opção válida. \n");
                        break;
                    }
                break;
        
                case 3:
                    System.out.println("O que está procurando?"); 
                    parametroPesquisa = in.nextLine();
                    try {
                        livroBuscar.addAll(controller.buscarListaLivro(parametroPesquisa));
                        if (livroBuscar.isEmpty()) {
                            System.out.println("\nNão há nenhum livro com com este parâmetro");
                        }
                        livroBuscar.forEach(livro -> System.out.print(livro.info()));
                        livroBuscar.clear();
                    } catch (Exception e) {
                        throw new Exception("\nErro ao buscar livro" + e.getMessage());
                    }
                                         
                break;
        
                case 4:
                    try {
                        System.out.println(controller.relatorio());     
                    } catch (Exception e) {
                        throw new Exception("\nErro ao gerar relatório" + e.getMessage());
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

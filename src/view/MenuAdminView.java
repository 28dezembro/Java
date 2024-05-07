package view;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import controller.*;
import model.*;

public class MenuAdminView {
    public static void main(String[] args, Scanner in, BibliotecaController controller, Admin usuarioSelecionado) {
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
                break;
        
                case 2:
                    System.out.println("Cadastro de novo usuário: \n");
                    System.out.println("Tipo de usuário: 1 - Admin \n 2 - Cliente");
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
                            if (novoAdmin != null) {
                                controller.adicionarUsuario(novoAdmin);
                            }
                            System.out.println("Não foi possivel adicionar o novo usuário, verifique os dados e tente novamente");
                             
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
                                if (novoCliente != null) {
                                    controller.adicionarUsuario(novoCliente);
                                }
                                System.out.println("Não foi possivel adicionar o novo usuário, verifique os dados e tente novamente");
                        break;
                    
                        default:
                            System.out.println("Selecione uma opção válida. \n");
                        break;
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
                    System.out.println(controller.relatorio());        
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

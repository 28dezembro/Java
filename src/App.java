import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import controller.BibliotecaController;
import model.*;
import view.MenuSelecionaUsuario;

public class App {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        BibliotecaController controller = new BibliotecaController(new ArrayList<Livro>(), new ArrayList<Usuario>());

        List<Livro> populacaoLivros = new ArrayList<>();
        List<Usuario> populacaoUsuarios = new ArrayList<>();

        populacaoUsuarios.add(new Cliente("Cliente", "Rua 1", "Email@gmail.com", "(12)30891-2308", new ArrayList<Livro>(), "736.823.283.10", false));
        populacaoUsuarios.add(new Admin("Admin", "Rua 2", "Email@hotmail.com", "(41)99999-9999", "123.456.798-10", true));

        populacaoLivros.add(new Livro("Neuromancer", "William Gibson", "Ficção Cientifica", 1984, 2));
        populacaoLivros.add(new Livro("O Mito de Sisifo", "Albert Camus", "Filosofia", 1942, 1));
        populacaoLivros.add(new Livro("O Estrangeiro", "Albert Camus", "Filosofia", 1942, 2));

        controller.buscarLivro("o mito de sisifo", populacaoLivros).setCodigo(1);
        controller.buscarLivro("o estrangeiro", populacaoLivros).setCodigo(2);
        controller.buscarLivro("neuromancer", populacaoLivros).setCodigo(3);

        controller.getLivros().addAll(populacaoLivros);
        controller.getUsuarios().addAll(populacaoUsuarios);

        System.out.println("Bem vindo a Biblioteca de AlexAndré");
    
        MenuSelecionaUsuario.main(args, in, controller);
                
    }
}

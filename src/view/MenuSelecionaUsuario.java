package view;

import java.util.InputMismatchException;
import java.util.Scanner;


import controller.BibliotecaController;
import model.*;
import util.Util;

public class MenuSelecionaUsuario {
    public static void main(String[] args, Scanner in, BibliotecaController controller) {
        boolean selecionaUsuario = true;
        String usuario;
        Usuario usuarioSelecionado;

        try {
            while (selecionaUsuario) {
                System.out.println("Selecione um usuário: \n");
                for (Usuario u : controller.getUsuarios()) {
                    System.out.println("Usuario: " + u.getNome());
                }
                    usuario = in.nextLine();
                    for (Usuario user : controller.getUsuarios()) {
                        if (user.getNome().equals(Util.formataString(usuario))) {
                            usuarioSelecionado = user;
                            if (user.isAdmin()) {
                                MenuAdminView.main(args, in, controller, (Admin) usuarioSelecionado);;
                            }   else{
                                    MenuClienteView.main(args, in, controller, (Cliente) usuarioSelecionado);;
                                }    
                        }
                          
                    }
                    System.out.println("Usuário não encotrado, tente novamente."); 
            }  
        }   catch (InputMismatchException e) {
                System.out.println("Erro de entrada. Tente novamente.");
                in.next(); // clear the invalid input
            }
    }
    
}

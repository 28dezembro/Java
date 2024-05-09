package view;

import java.util.InputMismatchException;
import java.util.Scanner;


import controller.BibliotecaController;
import model.*;
import util.Util;

public class MenuSelecionaUsuario {
    public static void main(String[] args, Scanner in, BibliotecaController controller) throws Exception{
        boolean selecionaUsuario = true;
        String usuario;
        Usuario usuarioSelecionado;

            while (selecionaUsuario) {
                System.out.println("\nSelecione um usuário: \n");
                for (Usuario u : controller.getUsuarios()) {
                    System.out.println("Usuario: " + u.getNome());
                }
                    usuario = in.nextLine();
                        for (Usuario user : controller.getUsuarios()) {
                            if (user.getNome().equals(Util.formataString(usuario))) {
                                usuarioSelecionado = user;

                                try {
                                    if (user.isAdmin()) {
                                        MenuAdminView.main(args, in, controller, (Admin) usuarioSelecionado);;
                                    }   else{
                                            MenuClienteView.main(args, in, controller, (Cliente) usuarioSelecionado);;
                                        }    
                                } catch (InputMismatchException e) {
                                    throw new Exception("Erro ao selecionar usuário" + e.getMessage());
                                }
                                
                            }
                        }
            }  
    }
    
}

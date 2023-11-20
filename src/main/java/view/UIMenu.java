package view;

import controller.BiblotecaController;
import controller.UsuarioController;
import model.Biblioteca;
import model.Libro;

import java.util.List;
import java.util.Scanner;

public class UIMenu {

    public static void showMenu() {

        //bibloteca.mostrarLibros();
        //bibloteca.mostrarUsuarios();
        System.out.println("*****  Book Warden *****");
        System.out.println("Seleccione una opción");
        Scanner sc = new Scanner(System.in);
        int respuesta = 0;

        do {
            System.out.println();
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear usuario");
            System.out.println("0. Salir");
            System.out.print(">>> ");

            int value = 0;
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 2) {
                    System.out.println("Ingrese un número entre 0 y 2\n");
                }
            } else {
                System.out.println("Ingrese un número entre 0 y 2\n");
            }
            respuesta = value;

            switch (respuesta) {
                case 1:
                    UIMenuUsuario.showIniciarSesion();
                    break;
                case 2:
                    UIMenuUsuario.showCrearUsuario();
                    break;
            }
        } while (respuesta != 0);
    }



}

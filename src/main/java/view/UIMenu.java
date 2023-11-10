package view;

import controller.BiblotecaController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UIMenu {

    private static BiblotecaController bibloteca = new BiblotecaController();

    public static void showMenu() {

        bibloteca.mostrarLibros();
        bibloteca.mostrarUsuarios();
        System.out.println("*****  Book Warden *****");
        System.out.println("Seleccione una opción");
        Scanner sc = new Scanner(System.in);
        int respuesta = 0;

        do {
            System.out.println();
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear usuario");
            System.out.println("0. Salir");

            int value = 0;
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 2) {
                    System.out.println("Ingrese un número entre 0 y 2\n");
                    UIMenu.showMenu();
                }
            } else {
                System.out.println("Ingrese un número entre 0 y 2\n");
                UIMenu.showMenu();
            }
            respuesta = value;

            switch (respuesta) {
                case 1:
                    showIniciarSesion();
                    break;
                case 2:
                    showCrearUsuario();
                    break;
            }
        } while (respuesta != 0);


    }

    public static void showIniciarSesion() {
        Scanner sc = new Scanner(System.in);
        String username = "";
        String password = "";

        System.out.println("Ingresa tu usuario");
        System.out.print(">>> ");
        username = sc.nextLine();

        System.out.println("Ingresa tu contraseña");
        System.out.print(">>> ");
        password = sc.nextLine();

        boolean sesion = bibloteca.iniciarSesion(username, password);
        if (sesion) {
            UIMenu.showMenuUsuario();
        } else {
            UIMenu.showIniciarSesion();
        }
    }

    private static void showCrearUsuario() {
        Scanner sc = new Scanner(System.in);
        String username = "";
        String password = "";

        System.out.println("Ingresa tu usuario");
        System.out.print(">>> ");
        username = sc.nextLine();

        System.out.println("Ingresa tu contraseña");
        System.out.print(">>> ");
        password = sc.nextLine();

        boolean sesion = bibloteca.crearUsuario(username, password);
        if (sesion) {
            UIMenu.showMenu();
        } else {
            System.out.println("No se pudo crear el usuario");
            UIMenu.showCrearUsuario();
        }

    }

    private static void showMenuUsuario() {
        System.out.println("1. Agregar libro");
        System.out.println("2. Prestar libro");
        System.out.println("3. Devolver libro");
        System.out.println("0. Salir");
    }


    private static void showAgregarLibro() {
        Scanner sc = new Scanner(System.in);

        String titulo = "";
        String autor = "";
        int anoPublicacion = 0;

        System.out.println("Titulo del libro:");
        System.out.print(">>>");
        titulo = sc.nextLine();

        System.out.println("Autor del libro");
        System.out.print(">>>");
        autor = sc.nextLine();

        System.out.println("Año de publicación del libro");
        System.out.print(">>>");
        anoPublicacion = Integer.parseInt(sc.nextLine());

        bibloteca.agregarLibro(titulo, autor, anoPublicacion);

    }

}

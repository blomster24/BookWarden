package view;

import controller.BiblotecaController;
import controller.UsuarioController;
import model.Biblioteca;
import model.Libro;

import java.util.List;
import java.util.Scanner;

public class UIMenu {

    private static BiblotecaController bibloteca = new BiblotecaController();

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
            System.out.println(UsuarioController.usuarioActivo);
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
        Scanner sc = new Scanner(System.in);
        int respuesta = 0;

        do {
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar libro");
            System.out.println("3. Mis libros prestados");
            System.out.println("4. Devolver libro");
            System.out.println("5. Ver libros");
            System.out.println("0. Salir");
            System.out.print(">>> ");

            int value = 10;

            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 5) {
                    System.out.println("Ingrese un número entre 0 y 5\n");
                    UIMenu.showMenuUsuario();
                }
            } else {
                System.out.println("Ingrese un número entre 0 y 5\n");
                UIMenu.showMenuUsuario();
            }
            respuesta = value;

            switch (respuesta) {
                case 1:
                    showAgregarLibro();
                    break;
                case 2:
                    showBuscarLibro();
                    break;
                case 3:
                    showMisLibrosPrestados();
                    break;
                case 4:
                    showDevolverLibro();
                    break;
                case 5:
                    showVerLibros();
                    break;
                case 0:
                    UsuarioController.usuarioActivo = null;
                    System.out.println("UsuarioController.usuarioActivo = " + UsuarioController.usuarioActivo);
                    break;
            }
        } while (respuesta != 0);
    }


    private static void showAgregarLibro() {
        Scanner sc = new Scanner(System.in);

        String titulo = "";
        String autor = "";
        int anoPublicacion = 0;

        System.out.println();
        System.out.println("Titulo del libro:");
        System.out.print(">>> ");
        titulo = sc.nextLine();

        System.out.println("Autor del libro");
        System.out.print(">>> ");
        autor = sc.nextLine();

        System.out.println("Año de publicación del libro");
        System.out.print(">>> ");
        try {
            anoPublicacion = Integer.parseInt(sc.nextLine());
            bibloteca.agregarLibro(titulo, autor, anoPublicacion);
        } catch (Exception e) {
            System.out.println("Ingresa un número valido");
        }

        UIMenu.showMenuUsuario();
    }

    private static void showBuscarLibro() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el titulo del libro");
        String titulo = sc.nextLine();
        bibloteca.buscarLibro(titulo);
        int respuesta = 0;

        do {
            System.out.println("1. Prestar libro");
            System.out.println("0. Volver");
            System.out.print(">>> ");

            int value = 0;

            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 1) {
                    System.out.println("Ingrese un número entre 0 y 1\n");
                    UIMenu.showMenuUsuario();
                }
            } else {
                System.out.println("Ingrese un número entre 0 y 1\n");

            }
            respuesta = value;

            switch (respuesta) {
                case 1:
                    showPrestarLibro();
                    respuesta = 0;
                    break;
            }
        } while (respuesta != 0);
    }

    private static void showPrestarLibro() {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("Para prestar un libro escribe su ID");
        int idLibro = 0;
        try {
            idLibro = sc.nextInt();
        } catch (NumberFormatException e) {
            System.out.println("Escribe un número de ID valido");
        }
        if (idLibro > 0) {
            bibloteca.prestarLibro(idLibro, UsuarioController.usuarioActivo.getId());
        } else {
            System.out.println("ID invalido");
        }
    }

    private static void showMisLibrosPrestados() {
        bibloteca.misLibrosPrestados();
        System.out.println();
    }

    private static void showDevolverLibro() {
        System.out.println();
        System.out.println("Para devolver un libro escribe su ID");
        Scanner sc = new Scanner(System.in);
        int idLibro = 0;
        try {
            idLibro = sc.nextInt();
        } catch (NumberFormatException e) {
            System.out.println("Escribe un número de ID valido");
        }
        if (idLibro > 0) {
            bibloteca.devolverLibro(idLibro, UsuarioController.usuarioActivo.getId());
        } else {
            System.out.println("ID invalido");
        }
    }

    private static void showVerLibros() {
        bibloteca.verLibros();
    }

}

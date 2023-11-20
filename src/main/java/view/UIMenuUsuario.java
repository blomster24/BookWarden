package view;

import controller.BiblotecaController;
import controller.UsuarioController;

import java.util.Scanner;

public class UIMenuUsuario {
    private static BiblotecaController bibloteca = new BiblotecaController();
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
            //System.out.println(UsuarioController.usuarioActivo);
            System.out.println("Sesión iniciada");
            System.out.println();
            showMenuUsuario();
        } else {
            System.out.println("Datos incorrectos");
            System.out.println();
        }
    }

    public static void showCrearUsuario() {
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
            System.out.println("Usuario creado, ya puedes iniciar sesion");
            System.out.println();
            UIMenu.showMenu();
        } else {
            System.out.println("No se pudo crear el usuario");
            showCrearUsuario();
        }

    }

    public static void showMenuUsuario() {
        Scanner sc = new Scanner(System.in);
        int respuesta = 0;

        do {
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar por titulo");
            System.out.println("3. Buscar por autor");
            System.out.println("4. Mis libros prestados");
            System.out.println("5. Devolver libro");
            System.out.println("6. Ver libros");
            System.out.println("0. Salir");
            System.out.print(">>> ");

            int value = 10;

            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 5) {
                    System.out.println("Ingrese un número entre 0 y 6\n");
                    showMenuUsuario();
                }
            } else {
                System.out.println("Ingrese un número entre 0 y 6\n");
                showMenuUsuario();
            }
            respuesta = value;

            switch (respuesta) {
                case 1:
                    showAgregarLibro();
                    break;
                case 2:
                    showBuscarPorTitulo();
                    break;
                case 3:
                    showBuscarPorAutor();
                    break;
                case 4:
                    showMisLibrosPrestados();
                    break;
                case 5:
                    showDevolverLibro();
                    break;
                case 6:
                    showVerLibros();
                    break;
            }
        } while (respuesta != 0);
        UsuarioController.usuarioActivo = null;
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

    }

    private static void showBuscarPorTitulo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el titulo del libro");
        String titulo = sc.nextLine();
        bibloteca.buscarTitulo(titulo);


        System.out.println("1. Prestar libro");
        System.out.println("0. Volver");
        System.out.print(">>> ");

        int value = 0;

        if (sc.hasNextInt()) {
            value = sc.nextInt();
            if (value > 1) {
                System.out.println("Ingrese un número entre 0 y 1\n");
            }
        } else {
            System.out.println("Ingrese un número entre 0 y 1\n");

        }

        switch (value) {
            case 1:
                showPrestarLibro();

                break;
            case 0:
                break;
        }
        System.out.println();
    }


    // TODO
    private static void showBuscarPorAutor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el autor del libro");
        String autor = sc.nextLine();
        bibloteca.buscarAutor(autor);


        System.out.println("1. Prestar libro");
        System.out.println("0. Volver");
        System.out.print(">>> ");

        int value = 0;

        if (sc.hasNextInt()) {
            value = sc.nextInt();
            if (value > 1) {
                System.out.println("Ingrese un número entre 0 y 1\n");
            }
        } else {
            System.out.println("Ingrese un número entre 0 y 1\n");

        }

        switch (value) {
            case 1:
                showPrestarLibro();
                break;
            case 0:
                break;
        }
        System.out.println();
    }
    private static void showPrestarLibro() {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("Para prestar un libro escribe su ID");
        System.out.print(">>> ");
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

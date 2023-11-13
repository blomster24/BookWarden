package controller;

import model.Biblioteca;
import model.Libro;
import model.Usuario;
import util.UtilEntitity;
import view.UIMenu;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class BiblotecaController {

    EntityManager em = UtilEntitity.getEntityManager();
    static UsuarioController usuarioController = new UsuarioController();
    private LibroController libroController = new LibroController();

    public void mostrarLibros() {
        System.out.println("Mostrando libros");
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
        libros.forEach(System.out::println);
    }

    public void mostrarUsuarios() {
        List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        usuarios.forEach(System.out::println);
    }


    public void agregarLibro(String titulo, String autor, int anoPublicacion) {
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setAnoPublicacion(anoPublicacion);

        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
            System.out.println("Libro agregado");
            System.out.println();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("No se pudo agregar el libro");
            System.out.println();
        }

    }

    public void prestarLibro(int idLibro, int idUsuario) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setIdLibro(idLibro);
        biblioteca.setIdUsuario(idUsuario);
        biblioteca.setFechaPrestamo(new Date());

        try {
            em.getTransaction().begin();
            em.persist(biblioteca);
            em.getTransaction().commit();

            Libro libro = em.find(Libro.class, idLibro);
            libro.setEstado(true);

            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
            System.out.println("Libro prestado");
            System.out.println();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("No se pudo prestar el libro");
            System.out.println();
        }
    }

    public void devolverLibro(int idLibro, int idUsuario) {
        TypedQuery<Biblioteca> biblioteca = em.createQuery("SELECT b FROM Biblioteca b WHERE b.idUsuario = :idUsuario AND b.idLibro = : idLibro", Biblioteca.class);
        biblioteca.setParameter("idUsuario", idUsuario);
        biblioteca.setParameter("idLibro", idLibro);
        List<Biblioteca> resultados = biblioteca.getResultList();

        try {
            for (Biblioteca b : resultados) {
                b.setFechaDevolucion(new Date());
                em.getTransaction().begin();
                em.merge(b);
                em.getTransaction().commit();

                Libro libro = em.find(Libro.class, idLibro);
                libro.setEstado(false);

                em.getTransaction().begin();
                em.merge(libro);
                em.getTransaction().commit();
                System.out.println("Libro devuelto a la biblioteca");
                System.out.println();
            }
        } catch (PersistenceException e) {
            System.out.println("No se pudo devolver el libro");
        }
    }


    public static boolean iniciarSesion(String username, String password) {
        return usuarioController.iniciarSesion(username, password);
    }

    public boolean crearUsuario(String username, String password) {
        return usuarioController.crearUsuario(username, password);
    }

    public void verLibros() {
        List<Libro> libros = libroController.verLibros();
        for (Libro libro :
                libros) {
            System.out.println(libro.getId() + ". " + libro.getTitulo() + ", " + libro.getAutor() + ", " + libro.getAnoPublicacion() + ".");
        }
        System.out.println();
    }

    public void buscarLibro(String titulo) {
        List<Libro> librosEncontrados = libroController.buscarLibro(titulo);
        if (!librosEncontrados.isEmpty()) {
            for (Libro libro :
                    librosEncontrados) {
                System.out.println(libro.getId() + ". " + libro.getTitulo() + ", " + libro.getAutor() + ", " + libro.getAnoPublicacion() + ".");
            }
        } else {
            System.out.println("No se han encontrado libros con el titulo: " + titulo);
            System.out.println();
            UIMenu.showMenuUsuario();
        }
        System.out.println();
    }

    public void misLibrosPrestados() {
        TypedQuery<Biblioteca> consulta =
                em.createQuery("SELECT b FROM Biblioteca b WHERE b.idUsuario = :idUsuario AND b.fechaDevolucion IS NULL", Biblioteca.class);
        consulta.setParameter("idUsuario", UsuarioController.usuarioActivo.getId());
        List<Biblioteca> misLibros = consulta.getResultList();
        if (misLibros.isEmpty()) {
            System.out.println("No tienes libros prestados");
        } else {
            for (Biblioteca b :
                    misLibros) {
                Libro l = em.find(Libro.class, b.getIdLibro());
                System.out.println(b.getIdLibro() + ". " + l.getTitulo() + " " + b.getFechaPrestamo());
            }
        }
    }
}

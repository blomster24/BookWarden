package controller;

import model.Libro;
import util.UtilEntitity;
import view.UIMenu;

import javax.persistence.EntityManager;
import java.util.List;

public class BiblotecaController {

    EntityManager em = UtilEntitity.getEntityManager();
    static UsuarioController usuarioController = new UsuarioController();

    public void mostrarLibros() {
        System.out.println("Mostrando libros");
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
        libros.forEach(System.out::println);

    }


    public void agregarLibro(String titulo, String autor, int anoPublicacion) {
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setAnoPublicacion(anoPublicacion);

        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }

    public void prestarLibro(int idLibro, int idUsuario) {
        Libro libro = em.find(Libro.class, idLibro);
        libro.setEstado(true);

        em.getTransaction().begin();
        em.merge(libro);
        em.getTransaction().commit();
    }

    public void devolverLibro(int id) {
        Libro libro = em.find(Libro.class, id);
        libro.setEstado(false);

        em.getTransaction().begin();
        em.merge(libro);
        em.getTransaction().commit();
    }


    public static boolean iniciarSesion(String username, String password) {
        return usuarioController.iniciarSesion(username, password);
    }
}

package controller;

import model.Libro;
import util.UtilEntitity;

import javax.persistence.EntityManager;
import java.util.List;

public class LibroController {

    EntityManager em = UtilEntitity.getEntityManager();

    public List<Libro> verLibros() {
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l",Libro.class).getResultList();
        return libros;
    }
}

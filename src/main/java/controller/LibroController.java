package controller;

import model.Libro;
import util.UtilEntitity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class LibroController {

    EntityManager em = UtilEntitity.getEntityManager();

    public List<Libro> verLibros() {
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.estado = 0", Libro.class).getResultList();
        return libros;
    }

    public List<Libro> buscarLibro(String titulo) {
        TypedQuery<Libro> consulta = em.createQuery("SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(:titulo) AND l.estado = 0", Libro.class);
        consulta.setParameter("titulo", "%" + titulo + "%");
        List<Libro> libros = consulta.getResultList();
        if (!libros.isEmpty()) {
            return libros;
        } else {
            return null;
        }
    }

}

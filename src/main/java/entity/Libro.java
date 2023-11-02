package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String autor;
    @Column(name = "ano_publicacion")
    private Date anoPublicacion;
    private boolean estado;

    public Libro() {
    }

    public Libro(int id, String titulo, String autor, Date anoPublicacion, boolean estado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(Date anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", titulo= '" + titulo + '\'' +
                ", autor= '" + autor + '\'' +
                ", anoPublicacion= " + anoPublicacion +
                ", estado= " + estado;
    }
}

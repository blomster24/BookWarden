package model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "libros")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String autor;
    @Column(name = "ano_publicacion")
    private int anoPublicacion;
    private boolean estado;

    public Libro(String titulo, String autor, int anoPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
        this.estado = false;
    }
}

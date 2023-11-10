package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "prestamos")
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "usuario_ID")
    public int idUsuario;

    @Column(name = "libro_ID")
    public int idLibro;

    @Column(name = "fecha_prestamo")
    public Date fechaPrestamo;

    @Column(name = "fecha_devolucion")
    public Date fechaDevolucion;

    public Biblioteca() {
        fechaPrestamo = new Date();
        fechaDevolucion = new Date();
    }

    public Biblioteca(int idUsuario, int idLibro, Date fechaPrestamo) {
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        fechaDevolucion = new Date();
    }

    public Biblioteca(int idUsuario, int idLibro, Date fechaPrestamo, Date fechaDevolucion) {
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idLibro=" + idLibro +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }
}

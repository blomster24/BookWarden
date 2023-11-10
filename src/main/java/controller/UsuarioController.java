package controller;

import model.Usuario;
import util.UtilEntitity;

import javax.persistence.EntityManager;
import java.util.List;

public class UsuarioController {

    EntityManager em = UtilEntitity.getEntityManager();

    public boolean iniciarSesion(String username, String password) {
        List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        for (Usuario u :
                usuarios) {
            if (u.getNombreUsuario().equals(username)) {
                if (u.getContrasena().equals(password)) {
                    System.out.println("Sesión iniciada");
                    System.out.println();
                    return true;
                }
            } else {
                System.out.println("Usuario no existe");
            }
        }
        return false;
    }

    public boolean crearUsuario(String username, String password) {
        Usuario usuario = new Usuario();
        if (username.length() == 0) {
            System.out.println("Ingresa un nombre de usuario");
            return false;
        } else {
            usuario.setNombreUsuario(username);
        }
        if (password.length() == 0) {
            System.out.println("Ingresa una contraseña");
            return false;
        } else {
            usuario.setContrasena(password);
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            return true;
        }
    }
}

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
                    return true;
                }
            } else {
                System.out.println("Usuario no existe");
            }
        }
        return false;
    }
}

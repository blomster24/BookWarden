package controller;

import model.Usuario;
import util.UtilEntitity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UsuarioController {

    EntityManager em = UtilEntitity.getEntityManager();
    public static Usuario usuarioActivo = null;

    public boolean iniciarSesion(String username, String password) {
        TypedQuery<Usuario> consulta =
                em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :username AND u.contrasena = :password",
                        Usuario.class);
        consulta.setParameter("username", username);
        consulta.setParameter("password", password);

        List<Usuario> usuarios = consulta.getResultList();
        if (usuarios.size() == 1) {
            usuarioActivo = usuarios.get(0);
            return true;
        } else {
            return false;
        }
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

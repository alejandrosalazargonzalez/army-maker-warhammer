
package es.alejandrosalazargonzalez.army_maker_warhammer.model;

import java.util.Objects;

/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class UsuarioEntity {

    private String usuario;
    private String email;
    private String nombre;
    private String contrasenia;

    /**
     * Constructor vacio
     */
    public UsuarioEntity() {
    }

    /**
     * Constructor completo
     *
     * @param usuario     del usuario
     * @param email       del usuario
     * @param nombre      del usuario
     * @param contrasenia del usuario
     * @throws Exception
     */
    public UsuarioEntity(String usuario, String email, String nombre, String contrasenia) throws ExceptionInInitializerError{
        if (!email.contains("@") || !email.contains(".") ) {
            throw new ExceptionInInitializerError("El email debe tener un formato correcto");
        }
        this.usuario = usuario;
        this.email = email;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    //Getters y Setters
    public String getUsuario(){
        return this.usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) throws ExceptionInInitializerError {
        if (!email.contains("@") || !email.contains(".com") ) {
            throw new ExceptionInInitializerError("El email debe tener un formato correcto");
        }
        this.email = email;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity usuarioModel = (UsuarioEntity) o;
        return Objects.equals(email, usuarioModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "{" + "usuario" + getUsuario() +
                ", email=" + getEmail() +
                ", nombre=" + getNombre() +
                ", contrasenia=" + getContrasenia() +
                "}";
    }

}

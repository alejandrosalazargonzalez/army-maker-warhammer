
package es.alejandrosalazargonzalez.army_maker_warhammer.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.alejandrosalazargonzalez.army_maker_warhammer.model.abstractas.Conexion;


/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class UsuarioServiceModel extends Conexion {

    /** 
     * constructor vacio
     */
    public UsuarioServiceModel() {
    }

    /**
     * Constructor completo con la ruta de la base de datos
     * 
     * @param unaRutaArchivoBD
     * @throws SQLException
     */
    public UsuarioServiceModel(String unaRutaArchivoBD) throws SQLException {
        super(unaRutaArchivoBD);
    }

    /**
     * Saca todos los usuarios
     * @return ArrayList<UsuarioEntity>
     * @throws SQLException
     */
    public ArrayList<UsuarioEntity> obtenerUsarios() throws SQLException {
        String sql = "SELECT * FROM Usuario";
        return leerSql(sql);
    }

    /**
     * Obtiene todos los usuarios por su email
     * @param email del usuario
     * @return UsuarioEntity
     */
    public UsuarioEntity obtenerUsuarioPorEmail(String email) {
        try {
            String sql = "SELECT * FROM Usuario " + "where email='"+email+"'";
        ArrayList<UsuarioEntity> usuarios = leerSql(sql);
        if (usuarios.isEmpty()) {
            return null;
        }
        return usuarios.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    /**
     * Obtiene todos los usuarios por su usuario
     * @param usuario a buscar
     * @return UsuarioEntity
     */
    public UsuarioEntity obtenerUsuarioPorUsuario(String usuario) {
        try {
            String sql = "SELECT * FROM Usuario " + "where email='"+usuario+"'";
        ArrayList<UsuarioEntity> usuarios = leerSql(sql);
        if (usuarios.isEmpty()) {
            return null;
        }
        return usuarios.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    /**
     * aniade un usuario a la base de datos
     * @param usuario a agregar
     * @return true/false
     * @throws SQLException 
     */
    public boolean addUsuario(UsuarioEntity usuario) throws SQLException{
        if (usuario == null) {
            return false;
        }
        String sql = "INSERT INTO Usuario (nombreUsuario, email, nombre, contrasenia) VALUES (?, ?, ?, ?)";
        return ejecutarUpdate(sql, usuario);
    }

    /**
     * Ejecuta la sencuencia sql introducida
     *
     * @param sql a ejecutar
     * @return ArrayList<UsuarioEntity>
     * @throws SQLException
     */
    public ArrayList<UsuarioEntity> leerSql(String sql) throws SQLException {
        ArrayList<UsuarioEntity> usuarios = new ArrayList<>();
        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String usuarioStr = resultado.getString("nombreUsuario");
                String nombreStr = resultado.getString("nombre");
                String contraseniaStr = resultado.getString("contrasenia");
                String emailStr = resultado.getString("email");
                UsuarioEntity usuarioModel = new UsuarioEntity(usuarioStr,emailStr, nombreStr, contraseniaStr);
                usuarios.add(usuarioModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return usuarios;
    }

    /**
     * modifica la base de datos segun el sql insertado
     * @param sql
     * @param usuario
     * @return
     * @throws SQLException
     */
    public boolean ejecutarUpdate(String sql, UsuarioEntity usuario) throws SQLException {
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getNombre());
            stmt.setString(4, usuario.getContrasenia());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }
    
}

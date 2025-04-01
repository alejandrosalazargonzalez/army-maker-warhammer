
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
     * Obtiene todos los usuarios por su email
     * @param email
     * @return
     */
    public UsuarioEntity obtenerUsuarioPorEmail(String email) {
        try {
            String sql = "SELECT * FROM Usuario " + "where email='"+email+"'";
        ArrayList<UsuarioEntity> usuarios = ejecutarSql(sql);
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
     * Saca todos los usuarios
     * @return
     * @throws SQLException
     */
    public ArrayList<UsuarioEntity> obtenerUsarios() throws SQLException {
        String sql = "SELECT * FROM Usuario";
        return ejecutarSql(sql);
    }

    /**
     * Ejecuta la sencuencia sql introducida
     * 
     * @param sql a ejecutar
     * @return ArrayList<UsuarioEntity>
     * @throws SQLException
     */
    public ArrayList<UsuarioEntity> ejecutarSql(String sql) throws SQLException {
        ArrayList<UsuarioEntity> usuarios = new ArrayList<>();
        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String nombreStr = resultado.getString("nombre");
                String contraseniaStr = resultado.getString("contrasenia");
                String emailStr = resultado.getString("email");
                UsuarioEntity usuarioModel = new UsuarioEntity(emailStr, nombreStr, contraseniaStr);
                usuarios.add(usuarioModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return usuarios;
    }

}

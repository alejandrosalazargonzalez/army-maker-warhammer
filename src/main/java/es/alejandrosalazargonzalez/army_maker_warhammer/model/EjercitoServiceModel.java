package es.alejandrosalazargonzalez.army_maker_warhammer.model;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.abstractas.Conexion;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.abstractas.EjercitoRepository;

import java.sql.*;
import java.util.ArrayList;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;
/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
@Component
public class EjercitoServiceModel extends Conexion  {
    private EjercitoRepository ejercitoRepository;

    public EjercitoServiceModel(String pathDb) throws SQLException {
        super(pathDb);
    }

    //@AutoWired
    public void setEjercitoRepository(EjercitoRepository ejercitoRepository){
        this.ejercitoRepository=ejercitoRepository;
    }


    /**
     * agrega un ejercito a bbdd
     * 
     * @param ejercito a insertar
     * @return true/false
     */
    public boolean insertarEjercito(EjercitoEntity ejercito) {
        if (ejercito == null) {
            return false;
        }
        ejercitoRepository.save(ejercito);
        return true;
    }

    /**
     * Actualiza un ejército existente en la base de datos
     * 
     * @param ejercito el ejército con la información actualizada
     * @return true/false
     * @throws SQLException
     */
    public boolean actualizarEjercito(EjercitoEntity ejercito) throws SQLException {
        boolean actualizado = false;
        
        try {
            actualizarGeneral(ejercito.getGeneral());
            String sqlEjercito = "UPDATE ejercito SET nombre=?, faccion=?, sub_faccion=?, puntos=? WHERE nombre=?";
            try (PreparedStatement stmt = getConnection().prepareStatement(sqlEjercito)) {
                stmt.setString(1, ejercito.getNombre());
                stmt.setString(2, ejercito.getFaccion());
                stmt.setString(3, ejercito.getSubFaccion());
                stmt.setInt(4, ejercito.getPuntos());
                stmt.setString(5, ejercito.getNombre());
                
                actualizado = stmt.executeUpdate() > 0;
                
                if (actualizado) {
                    actualizarUnidades(ejercito);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return actualizado;
    }

    /**
     * Actualiza la información de un general en la base de datos
     * 
     * @param general el general con la información actualizada
     * @throws SQLException si hay un error en la base de datos
     */
    private void actualizarGeneral(GeneralEntity general) throws SQLException {
        String sql = "UPDATE general SET puntos=? WHERE nombre=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, general.getPuntos());
            stmt.setString(2, general.getNombre());
            stmt.executeUpdate();
        }
    }

    /**
     * Actualiza las unidades de un ejército
     * 
     * @param ejercito el ejército con las unidades actualizadas
     * @throws SQLException si hay un error en la base de datos
     */
    private void actualizarUnidades(EjercitoEntity ejercito) throws SQLException {
        int ejercitoId = obtenerEjercitoId(ejercito.getNombre());
        
        eliminarUnidades(ejercitoId);
        
        for (UnidadEntity unidad : ejercito.getEjercito()) {
            insertarUnidad(unidad, ejercitoId);
        }
    }

    /**
     * Obtiene el ID de un ejército por su nombre
     * 
     * @param nombreEjercito el nombre del ejército
     * @return el ID del ejército
     * @throws SQLException si hay un error en la base de datos
     */
    private int obtenerEjercitoId(String nombreEjercito) throws SQLException {
        String sql = "SELECT id FROM ejercito WHERE nombre = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, nombreEjercito);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new SQLException("No se encontró el ejército con nombre: " + nombreEjercito);
    }

    /**
     * Elimina todas las unidades de un ejército
     * 
     * @param ejercitoId el ID del ejército
     * @throws SQLException si hay un error en la base de datos
     */
    private void eliminarUnidades(int ejercitoId) throws SQLException {
        String sql = "DELETE FROM unidad WHERE ejercito_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, ejercitoId);
            stmt.executeUpdate();
        }
    }

    /**
     * inserta un general al ejercito
     * @param general a insertar
     * @return int
     * @throws SQLException
     */
    private int insertarGeneral(GeneralEntity general) throws SQLException {
        String sql = "INSERT INTO general (nombre, puntos) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, general.getNombre());
            stmt.setInt(2, general.getPuntos());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        throw new SQLException("Error al insertar general");
    }

    /**
     * inserta una unidad al ejercito
     * @param unidad a insertar
     * @param ejercitoId id del ejercito
     * @throws SQLException
     */
    private void insertarUnidad(UnidadEntity unidad, int ejercitoId) throws SQLException {
        String sql = "INSERT INTO unidad (nombre, puntos, numero_modelos, tipo, ejercito_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, unidad.getNombre());
            stmt.setInt(2, unidad.getPuntos());
            stmt.setInt(3, unidad.getNumeroModelos());
            stmt.setString(4, unidad.getTipo());
            stmt.setInt(5, ejercitoId);
            stmt.executeUpdate();
        }
    }

    /**
     * saca todos los ejercitos
     * @return ArrayList<EjercitoEntity>
     * @throws SQLException
     */
    public ArrayList<EjercitoEntity> listarEjercitos() throws SQLException {
        ArrayList<EjercitoEntity> ejercitos = new ArrayList<>();
        String sql = "SELECT * FROM ejercito";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String faccion = rs.getString("faccion");
                String subFaccion = rs.getString("sub_faccion");
                int puntos = rs.getInt("puntos");
                int generalId = rs.getInt("general_id");

                GeneralEntity general = obtenerGeneralPorId(generalId);
                ArrayList<UnidadEntity> unidades = obtenerUnidadesPorEjercitoId(id);

                EjercitoEntity ejercito = new EjercitoEntity(nombre, faccion, subFaccion, general, unidades, puntos);
                ejercitos.add(ejercito);
            }
        }
        return ejercitos;
    }

    /**
     * buca un ejercito por su nombre
     * @param nombreBuscado nombre del ejercito
     * @return EjercitoEntity
     * @throws SQLException
     */
    public EjercitoEntity buscarPorNombre(String nombreBuscado) throws SQLException {
        String sql = "SELECT * FROM ejercito WHERE nombre = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, nombreBuscado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String faccion = rs.getString("faccion");
                String subFaccion = rs.getString("sub_faccion");
                int puntos = rs.getInt("puntos");
                int generalId = rs.getInt("general_id");

                GeneralEntity general = obtenerGeneralPorId(generalId);
                ArrayList<UnidadEntity> unidades = obtenerUnidadesPorEjercitoId(id);

                return new EjercitoEntity(nombreBuscado, faccion, subFaccion, general, unidades, puntos);
            }
        }
        return null;
    }

    /**
     * elimina un ejercito
     * @param ejercitoId id del ejercito a eliminar
     * @throws SQLException
     */
    public void eliminarEjercito(int ejercitoId) throws SQLException {
        String sql = "DELETE FROM ejercito WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, ejercitoId);
            stmt.executeUpdate();
        }
    }

    /**
     * saca el general por su id
     * @param id del general
     * @return GeneralEntity
     * @throws SQLException
     */
    private GeneralEntity obtenerGeneralPorId(int id) throws SQLException {
        String sql = "SELECT * FROM general WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                int puntos = rs.getInt("puntos");
                return new GeneralEntity(nombre, puntos);
            }
        }
        return null;
    }

    /**
     * lee todos los ejercitos con una id
     * @param ejercitoId id del ejercito
     * @return ArrayList<UnidadEntity>
     * @throws SQLException
     */
    private ArrayList<UnidadEntity> obtenerUnidadesPorEjercitoId(int ejercitoId) throws SQLException {
        ArrayList<UnidadEntity> unidades = new ArrayList<>();
        String sql = "SELECT * FROM unidad WHERE ejercito_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, ejercitoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int puntos = rs.getInt("puntos");
                int numeroModelos = rs.getInt("numero_modelos");
                String tipo = rs.getString("tipo");

                unidades.add(new UnidadEntity(nombre, puntos, numeroModelos, tipo));
            }
        }
        return unidades;
    }

    /**
     * Obtiene todos los ejercitos de un usuario especifico
     * 
     * @param usuario el usuario del que queremos obtener los ejercitos
     * @return ArrayList<EjercitoEntity>
     * @throws SQLException si hay un error en la base de datos
     */
    public ArrayList<EjercitoEntity> obtenerEjercitosDeUsuario(UsuarioEntity usuario) throws SQLException {
        if (usuario == null) {
            return new ArrayList<>();
        }
    
        ArrayList<EjercitoEntity> ejercitos = new ArrayList<>();
        String sql = "SELECT * FROM ejercito WHERE usuario = ?";
    
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsuario());
            ResultSet resultado = stmt.executeQuery();
    
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String faccion = resultado.getString("faccion");
                String subFaccion = resultado.getString("sub_faccion");
                int puntos = resultado.getInt("puntos");
                int generalId = resultado.getInt("general_id");
                
                GeneralEntity general = obtenerGeneralPorId(generalId);
                ArrayList<UnidadEntity> unidades = obtenerUnidadesPorEjercitoId(id);
    
                EjercitoEntity ejercito = new EjercitoEntity(nombre, faccion, subFaccion, general, unidades, puntos);
                ejercitos.add(ejercito);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return ejercitos;
    }
}

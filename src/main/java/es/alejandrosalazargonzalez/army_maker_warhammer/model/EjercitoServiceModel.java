package es.alejandrosalazargonzalez.army_maker_warhammer.model;

import es.alejandrosalazargonzalez.army_maker_warhammer.model.abstractas.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class EjercitoServiceModel extends Conexion {
    private final Connection connection;

    public EjercitoServiceModel(Connection connection) {
        this.connection = connection;
    }

    /**
     * agrega un ejercito a bbdd
     * @param ejercito
     * @throws SQLException
     */
    public void insertarEjercito(EjercitoEntity ejercito) throws SQLException {
        int generalId = insertarGeneral(ejercito.getGeneral());

        String sqlEjercito = "INSERT INTO ejercito (nombre, faccion, sub_faccion, general_id, puntos) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlEjercito, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ejercito.getNombre());
            stmt.setString(2, ejercito.getFaccion());
            stmt.setString(3, ejercito.getSubFaccion());
            stmt.setInt(4, generalId);
            stmt.setInt(5, ejercito.getPuntos());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int ejercitoId = rs.getInt(1);

                for (UnidadEntity unidad : ejercito.getEjercito()) {
                    insertarUnidad(unidad, ejercitoId);
                }
            }
        }
    }

    private int insertarGeneral(GeneralEntity general) throws SQLException {
        String sql = "INSERT INTO general (nombre, puntos) VALUES (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

    private void insertarUnidad(UnidadEntity unidad, int ejercitoId) throws SQLException {
        String sql = "INSERT INTO unidad (nombre, puntos, numero_modelos, tipo, ejercito_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, unidad.getNombre());
            stmt.setInt(2, unidad.getPuntos());
            stmt.setInt(3, unidad.getNumeroModelos());
            stmt.setString(4, unidad.getTipo());
            stmt.setInt(5, ejercitoId);
            stmt.executeUpdate();
        }
    }

    public ArrayList<EjercitoEntity> listarEjercitos() throws SQLException {
        ArrayList<EjercitoEntity> ejercitos = new ArrayList<>();
        String sql = "SELECT * FROM ejercito";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql);
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
    
    public EjercitoEntity buscarPorNombre(String nombreBuscado) throws SQLException {
        String sql = "SELECT * FROM ejercito WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
    
    public void eliminarEjercito(int ejercitoId) throws SQLException {
        String sql = "DELETE FROM ejercito WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ejercitoId);
            stmt.executeUpdate();
        }
    }
    private GeneralEntity obtenerGeneralPorId(int id) throws SQLException {
        String sql = "SELECT * FROM general WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
    
    private ArrayList<UnidadEntity> obtenerUnidadesPorEjercitoId(int ejercitoId) throws SQLException {
        ArrayList<UnidadEntity> unidades = new ArrayList<>();
        String sql = "SELECT * FROM unidad WHERE ejercito_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
        
}

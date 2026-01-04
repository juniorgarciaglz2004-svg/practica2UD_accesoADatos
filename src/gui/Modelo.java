package gui;

import modelo_Clases.Empresa;
import modelo_Clases.Kit_Educativo;
import modelo_Clases.Producto;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class Modelo {
    private String ip;
    private String user;
    private String password;
    private String db;
    private Connection conexion;

    public Modelo() {
        getPropValues();
    }

    private void getPropValues() {
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = new FileInputStream(propFileName);

            prop.load(inputStream);
            ip = prop.getProperty("ip");
            user = prop.getProperty("user");
            password = prop.getProperty("pass");
            db = prop.getProperty("db");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void conectar() {

        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":3306/"+db,user, password);
        } catch (SQLException sqle) {
            try {
                conexion = DriverManager.getConnection(
                        "jdbc:mysql://"+ip+":3306/",user, password);

                PreparedStatement statement = null;

                String code = leerFichero();
                String[] query = code.split("__");
                for (String aQuery : query) {
                    statement = conexion.prepareStatement(aQuery);
                    statement.executeUpdate();
                }
                assert statement != null;
                statement.close();

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String leerFichero() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("mysql.sql")) ;
        String linea;
        StringBuilder stringBuilder = new StringBuilder();
        while ((linea = reader.readLine()) != null) {
            stringBuilder.append(linea);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    void desconectar() {
        try {
            conexion.close();
            conexion = null;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //PARTE PRODUCTO

    public ResultSet obtenerProductos() throws SQLException {
     String sql =   "SELECT " +
        "id_producto as id, " +
               " nombre, " +
               " descripcion, " +
               " estado, " +
               " modelo, " +
               " marca " +
        " FROM producto";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sql);
        resultado = sentencia.executeQuery();
        return resultado;

    }


    public void adicionarProducto(Producto p) throws SQLException {
    String sql = "INSERT INTO producto" +
            "(" +
            "nombre," +
            "descripcion," +
            "estado," +
            "modelo," +
            "marca)" +
            "VALUES" +
            "(" +
            "?," +
            "?," +
            "?," +
            "?," +
            "?)";
    PreparedStatement statement = conexion.prepareStatement(sql);
    statement.setString(1,p.getNombre());
    statement.setString(2,p.getDescripcion());
    statement.setString(3,p.getEstado().name());
    statement.setString(4,p.getModelo());
    statement.setString(5,p.getMarca());
    statement.executeUpdate();
    }

    public void actualizarProducto(Producto p) throws SQLException {
        String sql = "update producto set " +
                "nombre = ?," +
                "descripcion = ?," +
                "estado = ?," +
                "modelo = ?," +
                "marca = ?" +
                "WHERE id_producto = ?";

        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1,p.getNombre());
        statement.setString(2,p.getDescripcion());
        statement.setString(3,p.getEstado().name());
        statement.setString(4,p.getModelo());
        statement.setString(5,p.getMarca());
        statement.setInt(6,p.getId());
        statement.executeUpdate();
    }


    void eliminarProducto(int id) {
        String sentenciaSql = "DELETE FROM producto WHERE id_producto = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }


    //PARTE KIT EDUCATIVO

    public void adicionarKitEducativo(Kit_Educativo k) throws SQLException {
        String sql = "INSERT INTO kit_educativo" +
                "(" +
                "nombre," +
                "descripcion," +
                "cantidad," +
                "fecha_de_creacion," +
                "fecha_de_actualizacion)" +
                "precio)" +
                "valoracion)" +
                "VALUES" +
                "(" +
                "?," +
                "?," +
                "?," +
                "?," +
                "?)";
        PreparedStatement statement = conexion.prepareStatement(sql);

        statement.executeUpdate();


    }

    public ResultSet obtenerKitEducativo() throws SQLException {
        String sql =   "SELECT " +
                "id_kit as id, " +
                "nombre," +
                "descripcion," +
                "cantidad," +
                "fecha_de_creacion," +
                "fecha_de_actualizacion," +
                "precio, " +
                "valoracion " +
                " FROM kit_educativo";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sql);
        resultado = sentencia.executeQuery();
        return resultado;



    }

    //PARTE EMPRESA



    public void adicionarEmpresas(Empresa em) throws SQLException {
        String sql = "INSERT INTO empresa" +
                "(" +
                " nombre, " +
                " descripcion, " +
                " fecha_de_creacion, " +
                " ubicacion, " +
                " valoracion) " +
                "VALUES" +
                "(" +
                "?," +
                "?," +
                "?," +
                "?," +
                "?)";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1,em.getNombre());
        statement.setString(2,em.getDescripcion());
        statement.setDate(3,Date.valueOf(em.getFechaCreacion()));
        statement.setString(4,em.getUbicacion());
        statement.setInt(5,em.getValoracion());
        statement.executeUpdate();

    }

    public ResultSet obtenerEmpresas() throws SQLException {
        String sql =   "SELECT " +
                "id_empresa as id, " +
                " nombre, " +
                " descripcion, " +
                " fecha_de_creacion, " +
                " ubicacion, " +
                " valoracion " +
                " FROM empresa";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sql);
        resultado = sentencia.executeQuery();
        return resultado;
    }


    public void actualizarEmpresa(Empresa em) throws SQLException {
        String sql = "update empresa set " +
                "nombre = ?," +
                "descripcion = ?," +
                "fecha_de_creacion = ?," +
                "ubicacion = ?," +
                "valoracion = ? " +
                "WHERE id_empresa = ?";

        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1,em.getNombre());
        statement.setString(2,em.getDescripcion());
        statement.setDate(3,Date.valueOf(em.getFechaCreacion()));
        statement.setString(4,em.getUbicacion());
        statement.setInt(5,em.getValoracion());
        statement.setInt(6,em.getId());
        statement.executeUpdate();
    }

    void eliminarEmpresa(int id) {
        String sentenciaSql = "DELETE FROM empresa WHERE id_empresa = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

}

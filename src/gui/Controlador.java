package gui;

import modelo_Clases.EstadoProducto;
import modelo_Clases.Producto;
import util.Util;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Controlador {
private boolean refrescar;
private Modelo modelo;
private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        modelo.conectar();
        adicionarActionListeners();
        refrescarTodo();
    }

    private void refrescarTodo()  {
        resfrescarProductos();
//        refrescarEmpresas();
//        refrescarKits();
          refrescar = false;
    }


    private void adicionarActionListeners()
    {
        vista.anadir_PRODUCTOSButton.addActionListener(e -> adicionarProducto());
    }

    //Parte de productos

    void resfrescarProductos () {
        try {
            vista.tablaProductos.setModel(construirTableModelProductos(modelo.obtenerProductos()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void adicionarProducto()  {
        Producto p = new Producto();
        p.setNombre(vista.nombreProducto.getText());
        p.setDescripcion(vista.descripcionProducto.getText());
        p.setMarca(vista.marcaProducto.getText());
        p.setModelo(vista.modeloProducto.getText());
        if (vista.productoEstadoUsado.isSelected())
        {
            p.setEstado(EstadoProducto.USADO) ;
        }
        if (vista.productosEstadoNuevos.isSelected())
        {
            p.setEstado(EstadoProducto.NUEVO) ;
        }
        if (vista.productosEstadoReacondicionados.isSelected())
        {
            p.setEstado(EstadoProducto.REACONDICIONADO) ;
        }

        if (p.valido())
        {
            try {
                modelo.adicionarProducto(p);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resfrescarProductos();
            vista.nombreProducto.setText("");
            vista.descripcionProducto.setText("");
            vista.marcaProducto.setText("");
            vista.modeloProducto.setText("");
            vista.productoEstadoUsado.setSelected(true);
        }
        else{
            Util.showErrorAlert("Rellene todos los campos");
        }

    }

    private DefaultTableModel construirTableModelProductos(ResultSet rs)
            throws SQLException {

        Vector<String> columnNames = new Vector<>();
        columnNames.add("id");
        columnNames.add("nombre");
        columnNames.add("descripcion");
        columnNames.add("estado");
        columnNames.add("modelo_Clases");
        columnNames.add("marca");
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnNames.size(), data);

        vista.dtmProductos.setDataVector(data, columnNames);

        return vista.dtmProductos;

    }

    //parte de empresa

    void resfrecarEmpresa()
    {
        try {
            vista.tablaEmrpresa.setModel(construirTableModelEmpresa(modelo.obtenerEmpresa()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void adicionarEmpresas()
    {

    }

    private DefaultTableModel construirTableModelEmpresa(ResultSet rs)
    {

        return vista.dtmEmpresa;
    }

    //parte de kits

    void resfrecarKits()
    {
        try {
            vista.tablaKits.setModel (construirTableModelKits(modelo.obtenerKitEducativo()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void adicionarKits()
    {

    }

    private DefaultTableModel construirTableModelKits(ResultSet rs)
    {

        return vista.dtmKits;
    }

    private void setDataVector(ResultSet rs, int columnCount, Vector<Vector<Object>> data) throws SQLException {
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
    }

}

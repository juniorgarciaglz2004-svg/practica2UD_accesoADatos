package gui;

import modelo_Clases.Empresa;
import modelo_Clases.EstadoProducto;
import modelo_Clases.Producto;
import util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Vector;

public class Controlador {
private boolean refrescar;
private Modelo modelo;
private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        modelo.conectar();
        adicionarActionListenersProductos();
        adicionarActionListenersEmpresas();

        refrescarTodo();
    }

    private void refrescarTodo()  {
        resfrescarProductos();
        resfrecarEmpresa();
        resfrecarKits();
          refrescar = false;
    }


    private void adicionarActionListenersProductos()
    {
        vista.anadir_PRODUCTOSButton.addActionListener(e -> adicionarProducto());
        vista.eliminar_ProductosButton.addActionListener(e -> eliminarProducto());
        vista.modificar_ProductosButton.addActionListener(e -> modificarProducto());
        vista.tablaProductos.setCellSelectionEnabled(true);
        ListSelectionModel productoModelSeleccion = vista.tablaProductos.getSelectionModel();
        productoModelSeleccion.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()
                        && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
                    if (e.getSource().equals(vista.tablaProductos.getSelectionModel())) {
                        int row = vista.tablaProductos.getSelectedRow();
                        vista.nombreProducto.setText(String.valueOf(vista.tablaProductos.getValueAt(row, 1)));
                        vista.descripcionProducto.setText(String.valueOf(vista.tablaProductos.getValueAt(row, 2)));
                        EstadoProducto estadoProducto = EstadoProducto.valueOf(String.valueOf(vista.tablaProductos.getValueAt(row,3)));
                        if (estadoProducto==EstadoProducto.NUEVO)
                        {
                            vista.productosEstadoNuevos.setSelected(true);
                        }
                        else if (estadoProducto==EstadoProducto.USADO)
                        {
                            vista.productoEstadoUsado.setSelected(true);
                        }
                        else {
                            vista.productosEstadoReacondicionados.setSelected(true);
                        }
                        vista.modeloProducto.setText(String.valueOf(vista.tablaProductos.getValueAt(row, 4)));
                        vista.marcaProducto.setText(String.valueOf(vista.tablaProductos.getValueAt(row, 5)));


                    } else if (e.getValueIsAdjusting()
                            && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                        if (e.getSource().equals(vista.tablaKits.getSelectionModel())) {
                            borrarCamposKits();
                        } else if (e.getSource().equals(vista.tablaEmrpresa.getSelectionModel())) {
                            borrarCamposEmpresa();
                        } else if (e.getSource().equals(vista.tablaProductos.getSelectionModel())) {
                            borrarCamposProductos();
                        }
                    }}});




    }


    private void adicionarActionListenersEmpresas()
    {
        vista.anadir_EmpresaButton.addActionListener(e -> adicionarEmpresas());
        vista.eliminar_EmpresaButton.addActionListener(e -> eliminarEmpresas());
        vista.modificar_EmpresaButton.addActionListener(e -> modificarEmpresas());
        vista.tablaEmrpresa.setCellSelectionEnabled(true);
        ListSelectionModel empresaModelSeleccion = vista.tablaEmrpresa.getSelectionModel();
        empresaModelSeleccion.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()
                    && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
                if (e.getSource().equals(vista.tablaEmrpresa.getSelectionModel())) {
                    int row = vista.tablaEmrpresa.getSelectedRow();
                    vista.nombreEmpresa.setText(String.valueOf(vista.tablaEmrpresa.getValueAt(row, 1)));
                    vista.descripcionEmpresa.setText(String.valueOf(vista.tablaEmrpresa.getValueAt(row, 2)));

                    vista.fecha_creacion_Empresa.setDate(((Date) vista.tablaEmrpresa.getValueAt(row, 3)).toLocalDate());

                    vista.ubicacionEmpresa.setText(String.valueOf(vista.tablaEmrpresa.getValueAt(row, 4)));
                    vista.valoracionSliderEmpresa.setValue((Integer)vista.tablaEmrpresa.getValueAt(row, 5));


                } else if (e.getValueIsAdjusting()
                        && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                    if (e.getSource().equals(vista.tablaKits.getSelectionModel())) {
                        borrarCamposKits();
                    } else if (e.getSource().equals(vista.tablaEmrpresa.getSelectionModel())) {
                        borrarCamposEmpresa();
                    } else if (e.getSource().equals(vista.tablaProductos.getSelectionModel())) {
                        borrarCamposProductos();
                    }
                }}});




    }




    private void modificarProducto() {

        if (vista.tablaProductos.getSelectedRow()==-1)
        {
            //no se ha seleccionado ninguna fila
            return;
        }

        Producto p = new Producto();
        p.setId((Integer) vista.tablaProductos.getValueAt(vista.tablaProductos.getSelectedRow(),0));
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
                modelo.actualizarProducto(p);
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

    private void eliminarProducto() {
        if (vista.tablaProductos.getSelectedRow()==-1)
        {
            //no se ha seleccionado ninguna fila
            return;
        }
        int id = Integer.parseInt(String.valueOf(vista.tablaProductos.getValueAt(vista.tablaProductos.getSelectedRow(),0)));
        modelo.eliminarProducto(id);
        borrarCamposProductos();
        resfrescarProductos();


    }

    private void borrarCamposProductos() {
        vista.nombreProducto.setText("");
        vista.descripcionProducto.setText("");
        vista.productoEstadoUsado.setSelected(true);


        vista.modeloProducto.setText("");
        vista.marcaProducto.setText("");
    }

    private void borrarCamposEmpresa() {
    vista.nombreEmpresa.setText("");
    vista.descripcionEmpresa.setText("");
    vista.ubicacionEmpresa.setText("");
    vista.fecha_creacion_Empresa.setDate(LocalDate.now());
    vista.valoracionSliderEmpresa.setValue(0);

    }

    private void borrarCamposKits() {
    vista.nombreKit.setText("");
    vista.descripcionKit.setText("");
    vista.cantidadKit.setText("");
    vista.comboBoxEmpresaKit.setSelectedIndex(-1);
    vista.comboBoxProductoKits.setSelectedIndex(-1);
    vista.fecha_CreacionKits.setDate(LocalDate.now());
    vista.fecha_ActualizacionKits.setDate(LocalDate.now());
    vista.precioKit.setText("");
    vista.valoracionSliderKit.setValue(0);
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

    private void eliminarEmpresas() {

        if (vista.tablaEmrpresa.getSelectedRow()==-1)
        {
            //no se ha seleccionado ninguna fila
            return;
        }
        int id = Integer.parseInt(String.valueOf(vista.tablaEmrpresa.getValueAt(vista.tablaEmrpresa.getSelectedRow(),0)));
        modelo.eliminarEmpresa(id);
        borrarCamposEmpresa();
        resfrecarEmpresa();

    }

    private void modificarEmpresas() {
        if (vista.tablaEmrpresa.getSelectedRow()==-1)
        {
            //no se ha seleccionado ninguna fila
            return;
        }

        Empresa p = new Empresa();
        p.setId((Integer) vista.tablaEmrpresa.getValueAt(vista.tablaEmrpresa.getSelectedRow(),0));
        p.setNombre(vista.nombreEmpresa.getText());
        p.setDescripcion(vista.descripcionEmpresa.getText());
        p.setFechaCreacion(vista.fecha_creacion_Empresa.getDate());
        p.setUbicacion(vista.ubicacionEmpresa.getText());
        p.setValoracion(vista.valoracionSliderEmpresa.getValue());


        if (p.valido())
        {
            try {
                modelo.actualizarEmpresa(p);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resfrecarEmpresa();
            borrarCamposEmpresa();

        }
        else{
            Util.showErrorAlert("Rellene todos los campos");
        }

    }

    void resfrecarEmpresa()
    {
        try {
            vista.tablaEmrpresa.setModel(construirTableModelEmpresa(modelo.obtenerEmpresas()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void adicionarEmpresas()
    {

        Empresa p = new Empresa();
        p.setNombre(vista.nombreEmpresa.getText());
        p.setDescripcion(vista.descripcionEmpresa.getText());
        p.setFechaCreacion(vista.fecha_creacion_Empresa.getDate());
        p.setUbicacion(vista.ubicacionEmpresa.getText());
        p.setValoracion(vista.valoracionSliderEmpresa.getValue());


        if (p.valido())
        {
            try {
                modelo.adicionarEmpresas(p);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resfrecarEmpresa();
            borrarCamposEmpresa();
        }
        else{
            Util.showErrorAlert("Rellene todos los campos");
        }


    }

    private DefaultTableModel construirTableModelEmpresa(ResultSet rs) throws SQLException {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("id");
        columnNames.add("nombre");
        columnNames.add("descripcion");
        columnNames.add("fecha_de_creacion");
        columnNames.add("ubicacion");
        columnNames.add("valoracion");
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnNames.size(), data);

        vista.dtmEmpresa.setDataVector(data, columnNames);

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

package gui;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Vista extends JFrame{

     JPanel panel1;
     JTextField nombreProducto;
     JTextField descripcionProducto;
     JButton anadir_PRODUCTOSButton;
     JButton eliminar_ProductosButton;
     JButton modificar_ProductosButton;
     JButton anadir_KitsButton;
     JButton eliminar_KitsButton;
     JButton modificar_KitsButton;
     JButton anadir_EmpresaButton;
     JButton eliminar_EmpresaButton;
     JButton modificar_EmpresaButton;
     JTextField nombreKit;
     JTextField descripcionKit;
     JTextField nombreEmpresa;
     JTextField descripcionEmpresa;
     JTextField cantidadKit;
    DatePicker fecha_creacion_Empresa;
    DatePicker fecha_CreacionKits;
     DatePicker fecha_ActualizacionKits;
     JComboBox comboBoxEmpresaKit;
     JComboBox comboBoxProductoKits;
     JCheckBox productoEstadoUsado;
     JCheckBox productosEstadoNuevos;
     JSlider valoracionSliderEmpresa;
     JCheckBox productosEstadoReacondicionados;
     JTable tablaProductos;
     JTable tablaKits;
     JTable tablaEmrpresa;
     JTextField modeloProducto;
     JTextField marcaProducto;
     JSlider valoracionSliderKit;
     JTextField precioKit;
     JTextField ubicacionEmpresa;


    DefaultTableModel dtmProductos;
    DefaultTableModel dtmKits;
    DefaultTableModel dtmEmpresa;


    public Vista() {
        super("TITULO");
        initFrame();
    }

    public void initFrame() {
        this.setContentPane(panel1);
        //al clickar en cerrar no hace nada
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        //doy dimension
        this.setSize(new Dimension(this.getWidth()+100,this.getHeight()));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        setTableModels();


    }
    private void setTableModels() {

        this.dtmProductos=new DefaultTableModel();
        this.tablaProductos.setModel(dtmProductos);

        this.dtmKits=new DefaultTableModel();
        this.tablaKits.setModel(dtmKits);

        this.dtmEmpresa=new DefaultTableModel();
        this.tablaEmrpresa.setModel(dtmEmpresa);
    }
}

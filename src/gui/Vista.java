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
     JButton eliminar_CONDICIONButton;
     JButton modificarButton;
     JButton anadir_KITSButton;
     JButton eliminar_KITSButton;
     JButton modificar_KITSButton;
     JButton anadir_CLASIFICACIONButton;
     JButton ELIMINARButton;
     JButton MODIFICARButton;
     JTextField textField2;
     JTextField textField4;
     JTextField textField5;
     JTextField textField6;
     JTextField textField7;
    DatePicker date_creacion_clasificacion;
     DatePicker date_actualizacion_clasificacion;
     DatePicker date_creacion_kits;
     DatePicker date_actualizacion_kits;
     JComboBox comboBox1;
     JComboBox comboBox2;
     JCheckBox productoEstadoUsado;
     JCheckBox productosEstadoNuevos;
     JSlider slider1;
     JCheckBox productosEstadoReacondicionados;
     JTable tablaProductos;
     JTable tablaKits;
     JTable tablaEmrpresa;
     JTextField modeloProducto;
     JTextField marcaProducto;


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

        this.dtmProductos=new DefaultTableModel();
        this.tablaProductos.setModel(dtmProductos);
    }
}

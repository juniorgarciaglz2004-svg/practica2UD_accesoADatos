package gui;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame{

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField3;
    private JButton anadir_CONDICIONButton;
    private JButton eliminar_CONDICIONButton;
    private JButton modificarButton;
    private JButton anadir_KITSButton;
    private JButton eliminar_KITSButton;
    private JButton modificar_KITSButton;
    private JButton anadir_CLASIFICACIONButton;
    private JButton ELIMINARButton;
    private JButton MODIFICARButton;
    private JTextField textField2;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private DatePicker date_creacion_condicion;
    private DatePicker date_actualizacion_condicion;
    private DatePicker date_creacion_clasificacion;
    private DatePicker date_actualizacion_clasificacion;
    private DatePicker date_creacion_kits;
    private DatePicker date_actualizacion_kits;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JCheckBox USADOSCheckBox;
    private JCheckBox NUEVOSCheckBox;
    private JSlider slider1;
    private JCheckBox REACONDICIONADOCheckBox;
    private JTable table1;
    private JTable table2;
    private JTable table3;

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

        //creo cuadro dialogo

    }

}

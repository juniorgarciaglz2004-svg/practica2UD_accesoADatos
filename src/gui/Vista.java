package gui;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame{

    public Vista() {
        super("TITULO");
        initFrame();
    }

    public void initFrame() {
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

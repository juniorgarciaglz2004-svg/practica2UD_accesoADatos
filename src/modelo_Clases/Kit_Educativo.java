package modelo_Clases;

import java.util.Date;

public class Kit_Educativo {

    // ARRAY DE EMPRESAS KIT Y PRODUCTOS KIT
    private int id;
    private String nombre;
    private String descripcion;
    private String cantidad;
    private String empresasKit;
    private String productoKit;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private float precio;
    private int valoracion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getEmpresasKit() {
        return empresasKit;
    }

    public void setEmpresasKit(String empresasKit) {
        this.empresasKit = empresasKit;
    }

    public String getProductoKit() {
        return productoKit;
    }

    public void setProductoKit(String productoKit) {
        this.productoKit = productoKit;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public Kit_Educativo(int id, String nombre, String descripcion, String cantidad, String empresasKit, String productoKit, Date fechaCreacion, Date fechaActualizacion, float precio, int valoracion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.empresasKit = empresasKit;
        this.productoKit = productoKit;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.precio = precio;
        this.valoracion = valoracion;
    }

    public Kit_Educativo() {
    }
}

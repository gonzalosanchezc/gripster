package com.gripster.model;

public class DetalleVenta {
    private int idDetalle;
    private int idVenta;
    private int idLlanta;
    private int cantidad;
    private double precioUnitario;

    public DetalleVenta() {}

    public DetalleVenta(int idDetalle, int idVenta, int idLlanta, int cantidad, double precioUnitario) {
        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.idLlanta = idLlanta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdLlanta() {
        return idLlanta;
    }

    public void setIdLlanta(int idLlanta) {
        this.idLlanta = idLlanta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    
}

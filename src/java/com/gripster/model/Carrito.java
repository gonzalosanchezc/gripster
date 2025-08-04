package com.gripster.model;

import java.sql.Timestamp;

public class Carrito {
    private int idCarrito;
    private String idSesion;
    private int idLlanta;
    private Llanta llanta;  // Nuevo campo
    private int cantidad;
    private Timestamp fechaAgregado;

    // Constructores
    public Carrito() {
    }

    // Constructor original (para compatibilidad)
    public Carrito(int idCarrito, String idSesion, int idLlanta, int cantidad, Timestamp fechaAgregado) {
        this.idCarrito = idCarrito;
        this.idSesion = idSesion;
        this.idLlanta = idLlanta;
        this.cantidad = cantidad;
        this.fechaAgregado = fechaAgregado;
    }

    // Nuevo constructor con objeto Llanta
    public Carrito(int idCarrito, String idSesion, Llanta llanta, int cantidad, Timestamp fechaAgregado) {
        this.idCarrito = idCarrito;
        this.idSesion = idSesion;
        this.llanta = llanta;
        this.idLlanta = llanta.getIdLlanta(); // Asigna el id de la llanta automáticamente
        this.cantidad = cantidad;
        this.fechaAgregado = fechaAgregado;
    }

    // Getters y Setters (mantén los existentes y añade)
    public Llanta getLlanta() {
        return llanta;
    }

    public void setLlanta(Llanta llanta) {
        this.llanta = llanta;
        this.idLlanta = llanta != null ? llanta.getIdLlanta() : 0;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
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

    public Timestamp getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(Timestamp fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }
    
    
}
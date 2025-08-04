package com.gripster.model;

public class Llanta {
    private int idLlanta;
    private int idTipo;
    private int idCategoria;
    private String marcaNombre;  
    private String modeloNombre; 
    private int ancho;
    private int perfil;
    private int diametro;
    private double precio;
    private int stock;
    private String imagen;

    // Constructor
    public Llanta(int idLlanta, int idTipo, int idCategoria, String marcaNombre, String modeloNombre,
                  int ancho, int perfil, int diametro, double precio, int stock, String imagen) {
        this.idLlanta = idLlanta;
        this.idTipo = idTipo;
        this.idCategoria = idCategoria;
        this.marcaNombre = marcaNombre;
        this.modeloNombre = modeloNombre;
        this.ancho = ancho;
        this.perfil = perfil;
        this.diametro = diametro;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getIdLlanta() {
        return idLlanta;
    }

    public void setIdLlanta(int idLlanta) {
        this.idLlanta = idLlanta;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }

    public String getModeloNombre() {
        return modeloNombre;
    }

    public void setModeloNombre(String modeloNombre) {
        this.modeloNombre = modeloNombre;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public int getDiametro() {
        return diametro;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
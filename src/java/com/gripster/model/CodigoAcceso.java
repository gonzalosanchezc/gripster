package com.gripster.model;

import java.sql.Timestamp;

public class CodigoAcceso {
    private int idCodigo;
    private String correo;
    private String codigo;
    private Timestamp fechaGeneracion;
    private boolean expirado;

    // Constructor vacío
    public CodigoAcceso() {}

    // Constructor completo
    public CodigoAcceso(int idCodigo, String correo, String codigo, Timestamp fechaGeneracion, boolean expirado) {
        this.idCodigo = idCodigo;
        this.correo = correo;
        this.codigo = codigo;
        this.fechaGeneracion = fechaGeneracion;
        this.expirado = expirado;
    }

    // Getters y Setters
    public int getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(int idCodigo) {
        this.idCodigo = idCodigo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Timestamp getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Timestamp fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public boolean isExpirado() {
        return expirado;
    }

    public void setExpirado(boolean expirado) {
        this.expirado = expirado;
    }
}

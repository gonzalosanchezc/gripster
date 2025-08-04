package com.gripster.model;

import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Time;

public class Venta {
    private int idVenta;
    private String codigoVenta;
    private int idUsuario;
    private String idSesion;
    private int idSucursal;
    private Date fechaCita;
    private Time horaCita;
    private String metodoPago;
    private String comprobantePago;
    private String estado;
    private double total;
    private Timestamp fechaRegistro;

    public Venta() {}

    public Venta(int idVenta, String codigoVenta, int idUsuario, String idSesion, int idSucursal,
                 Date fechaCita, Time horaCita, String metodoPago, String comprobantePago,
                 String estado, double total, Timestamp fechaRegistro) {
        this.idVenta = idVenta;
        this.codigoVenta = codigoVenta;
        this.idUsuario = idUsuario;
        this.idSesion = idSesion;
        this.idSucursal = idSucursal;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.metodoPago = metodoPago;
        this.comprobantePago = comprobantePago;
        this.estado = estado;
        this.total = total;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(String codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Time getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(Time horaCita) {
        this.horaCita = horaCita;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getComprobantePago() {
        return comprobantePago;
    }

    public void setComprobantePago(String comprobantePago) {
        this.comprobantePago = comprobantePago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    
}

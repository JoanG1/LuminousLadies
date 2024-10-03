package com.luminousladies.investorSystem.Model;

import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false)
    //private Long producto;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String telefono;

    @Column(name = "fecha_venta", updatable = false)
    private LocalDateTime fecha_Venta;

    @Column(name = "total_venta", nullable = false)
    private BigDecimal totalVenta;

    //@Column(nullable = false)
    //private int cantidad;

    //@Column(nullable = false)
    //private String descripcion;

    @Lob
    @Column(columnDefinition = "BLOB", nullable = false)
    private ArrayList productos;

    private String ids;

// Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombre;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombre = nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefono;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefono = telefonoCliente;
    }

    public LocalDateTime getFechaVenta() {
        return fecha_Venta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fecha_Venta = fechaVenta;
    }

    public ArrayList getProductos() {
        return productos;
    }

    public void setProductos(ArrayList productos) {
        this.productos = productos;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }
}

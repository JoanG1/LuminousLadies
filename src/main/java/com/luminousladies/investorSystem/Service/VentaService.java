package com.luminousladies.investorSystem.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luminousladies.investorSystem.Model.Venta;
import com.luminousladies.investorSystem.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoService productoService;

    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> getVentaById(Long id) {
        return ventaRepository.findById(id);
    }

    public Venta saveVenta(Venta venta) {

        if (venta.getNombreCliente() == null || venta.getNombreCliente().isEmpty()) {
            System.out.println(venta.getNombreCliente() + " - " + venta.getTelefonoCliente());
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }

        ArrayList Productos = venta.getProductos();

        String json = Productos.toString();
        Gson gson = new Gson();

        // Define el tipo de la lista de mapas
        Type type = new TypeToken<List<Map<String, Object>>>() {}.getType();

        // Convertir JSON a lista de mapas
        List<Map<String, Object>> list = gson.fromJson(json, type);

        // Iterar sobre la lista y obtener el valor de "producto"
        for (Map<String, Object> item : list) {
            Double producto = (Double) item.get("producto");
            Double cantidad = (Double) item.get("cantidad");
            System.out.println("Producto: " + producto.intValue());
            System.out.println("Cantidad: "+ cantidad.intValue());
            productoService.updateProductoQuantity((long) producto.intValue(), cantidad.intValue());
        }

        return ventaRepository.save(venta);

    }

    public void deleteVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}

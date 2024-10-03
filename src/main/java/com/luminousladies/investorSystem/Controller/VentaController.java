package com.luminousladies.investorSystem.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luminousladies.investorSystem.Model.Venta;
import com.luminousladies.investorSystem.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        Optional<Venta> venta = ventaService.getVentaById(id);
        return venta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {

        ////////////////////////////////////////////////////////////////////////

        String ids = "";

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

            ids += producto.intValue()+" - ";

            venta.setIds(ids);
        }


        ////////////////////////////////////////////////////////////////////////
        Venta savedVenta = ventaService.saveVenta(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVenta);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        if (!ventaService.getVentaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}

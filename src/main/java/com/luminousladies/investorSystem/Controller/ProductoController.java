package com.luminousladies.investorSystem.Controller;

import com.luminousladies.investorSystem.Model.Producto;
import com.luminousladies.investorSystem.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.getProductoById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<BigDecimal> getProductPrice(@PathVariable Long id) {
        BigDecimal price = productoService.getProductPriceById(id);
        if (price != null) {
            return ResponseEntity.ok(price);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/priceMore")
    public ResponseEntity<BigDecimal> getProductPriceMore(@PathVariable Long id) {
        BigDecimal price = productoService.getProductPriceMoreById(id);
        if (price != null) {
            return ResponseEntity.ok(price);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<Integer> getProductStock(@PathVariable Long id) {
        Integer cantidad = productoService.getProductStockById(id);
        if (cantidad != null) {
            return ResponseEntity.ok(cantidad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto savedProducto = productoService.saveProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        if (!productoService.getProductoById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        producto.setId(id);
        Producto updatedProducto = productoService.saveProducto(producto);
        return ResponseEntity.ok(updatedProducto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (!productoService.getProductoById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
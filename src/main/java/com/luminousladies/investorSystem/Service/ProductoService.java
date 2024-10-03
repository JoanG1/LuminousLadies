package com.luminousladies.investorSystem.Service;

import com.luminousladies.investorSystem.Model.Producto;
import com.luminousladies.investorSystem.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public BigDecimal getProductPriceById(Long id) {
        return productoRepository.findById(id)
                .map(Producto::getPrecio)
                .orElse(null);
    }

    public BigDecimal getProductPriceMoreById(Long id){
        return productoRepository.findById(id)
                .map(Producto::getPrecioMayor)
                .orElse(null);
    }

    public Integer getProductStockById(Long id) {
        return productoRepository.findById(id)
                .map(Producto::getCantidad)
                .orElse(null);
    }



    public Producto saveProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        return productoRepository.save(producto);
    }

    public Producto findProductoById(Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void updateProductoQuantity(Long productoId, int cantidadVendida) {
        Producto producto = findProductoById(productoId);
        if (producto.getCantidad() < cantidadVendida) {
            throw new RuntimeException("No hay suficiente cantidad del producto");
        }
        producto.setCantidad(producto.getCantidad() - cantidadVendida);
        productoRepository.save(producto);
    }



    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
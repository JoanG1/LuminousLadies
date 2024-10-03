package com.luminousladies.investorSystem.Repository;

import com.luminousladies.investorSystem.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

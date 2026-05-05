package com.salesianostriana.dam.EjemploWebData.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.salesianostriana.dam.EjemploWebData.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}

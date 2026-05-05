package com.salesianostriana.dam.EjemploWebData.utils;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.EjemploWebData.model.Categoria;
import com.salesianostriana.dam.EjemploWebData.repos.CategoriaRepository;
import com.salesianostriana.dam.EjemploWebData.repos.ProductoRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeed {

	private final CategoriaRepository categoriaRepository;
	private final ProductoRepository productoRepository;

	@PostConstruct
	public void insertData() {

		Categoria categoriaElectronica = Categoria.builder()
				.nombre("Electrónica")
				.build();

		Categoria categoriaHogar = Categoria.builder()
				.nombre("Hogar")
				.build();

		categoriaRepository.save(categoriaHogar);
		categoriaRepository.save(categoriaElectronica);

	}

}

package com.salesianostriana.dam.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.modelo.Tag;

public interface TagRepository 
	extends JpaRepository<Tag, Long>{

}

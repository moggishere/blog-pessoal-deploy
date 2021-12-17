package com.generation.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blog.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> { // recebe objeto da classe postagem e a chave (Long)

	public List <Postagem> findAllByTituloContainingIgnoreCase(String Titulo); // encontre pelo titulo, com "esse pedaco de string" e ignore maisculos/minusculos
	
	// select * from tb_postagens where titulo like "%titulo%";
}

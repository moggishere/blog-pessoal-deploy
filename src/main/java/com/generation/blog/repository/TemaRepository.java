package com.generation.blog.repository;

import java.util.List;

import com.generation.blog.model.Tema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {

    public List <Tema> findAllByDescricaoContainingIgnoreCase (String descricao);

}

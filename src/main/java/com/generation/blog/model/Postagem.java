package com.generation.blog.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // diz para o spring gerar tabela no banco == create table
@Table(name = "tb_postagens") // == tb_postagens
public class Postagem {

	@Id // indica que se trata de PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // permite o db cuidar do auto_increment
	private Long id; // Long = objeto | long = atributo

	@NotBlank(message = "Obrigatório colocar título") // impede input em branco e retorna msg de erro
	@Size(min = 5, max = 100, message = "Deve ter no mínimo 5 e no máximo 100 caractéres") // define tam. minimo
	private String titulo;

	@NotNull(message = "Input obrigatório!")
	@Size(min = 0, max = 5000, message = "Escreve algo, bicho")
	private String texto;

	@UpdateTimestamp // automaticamente coloca a data de acordo com a config do sistema
	private LocalDate data;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
}

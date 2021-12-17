package com.generation.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blog.model.Postagem;
import com.generation.blog.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity <List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
		
		//select * from tb_postagens;
	}
	
	@GetMapping("/{id}") // precisa de outro nome "/{id}" | serve para buscar por id unico do item dentro do database 
	public ResponseEntity <Postagem> getById(@PathVariable Long id) {
		return postagemRepository.findById(id) // executa o metodo finById
			.map(resp -> ResponseEntity.ok(resp)) // mapeia a respoista do metodo na variavel resp, se encontra mostra o que achou
				.orElse(ResponseEntity.notFound().build()); // se nao encontra, mostra notFound
		
		//SELECT * FROM tb_postagens WHERE id = id;
	}
	
	@GetMapping("/titulo/{titulo}") // primeiro titulo serve para nao se confundir com "/{id}
	public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity <Postagem> postPostagem(@Valid @RequestBody Postagem postagem) { //@Valid serve para ver se o objeto esta de conforme com os parametros
		
		// ao inves de variavel ele vai ser um objeto com titulo, texto e data proprios
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem)); // .body serve como corpo da resposta
	}
	
	@PutMapping
	public ResponseEntity <Postagem> putPostagem(@Valid @RequestBody Postagem postagem) { // equivalente update do mysql
	
		return postagemRepository.findById(postagem.getId()).map (resposta -> ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
//	@PutMapping
//	public ResponseEntity <Postagem> putPostagem(@Valid @RequestBody Postagem postagem) { // equivalente update do mysql
//		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePostagem(@PathVariable Long id) {
		postagemRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

//	@DeleteMapping("/{id}")
//	public void deletePostagem(@PathVariable Long id) {
//		postagemRepository.deleteById(id);
////		return ResponseEntity.noContent().build();
//		return ResponseEntity.noContent().build();
//	}
//}

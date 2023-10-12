package br.com.mizack.sorteador.controller;

import br.com.mizack.sorteador.repository.GrupoRepository;
import br.com.mizack.sorteador.model.Grupo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("grupo")
public class GrupoResource {
	@Autowired
	private GrupoRepository grupoRepository;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Grupo cadastrar(@RequestBody Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@PutMapping("{TOKEN}")
	public void atualizar(@RequestBody Grupo grupo, @PathVariable String token) {
		grupo.setToken(token);
	}
	
	@PostMapping("{TOKEN}")
	public void sortear(@RequestBody Grupo grupo, @PathVariable String token) {
		grupo.setToken(token);
	}
	
	@DeleteMapping("{TOKEN}")
	public void deletar(@RequestBody Grupo grupo, @PathVariable String token) {
		grupo.setToken(token);
	}
	
	@GetMapping("/detalhar")
	public void detalhar(@RequestBody Grupo grupo, @PathVariable String token) {
		grupo.setToken(token);
	}
	
	@GetMapping("/listarUsuarios")
	public void listarUsuarios(@RequestBody Grupo grupo, @PathVariable String token) {
		grupo.setToken(token);
	}
}

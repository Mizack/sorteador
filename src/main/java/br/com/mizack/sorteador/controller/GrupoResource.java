package br.com.mizack.sorteador.controller;

import br.com.mizack.sorteador.repository.GrupoRepository;
import br.com.mizack.sorteador.model.Grupo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

@ControllerAdvice
@RestController
@RequestMapping("grupo")
public class GrupoResource {
	@Autowired
	private GrupoRepository grupoRepository;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Grupo cadastrar(@RequestBody Grupo grupo) {
		grupo.setToken(grupo.gerarToken());
		return grupoRepository.save(grupo);
	}
	
	@PutMapping("{TOKEN}")
	public Grupo atualizar(@RequestBody Grupo grupo, @PathVariable String token) {
		grupo.setToken(token);
		return grupoRepository.save(grupo);
	}
	
	@PostMapping("{TOKEN}")
	public void sortear(@RequestBody Grupo grupo, @PathVariable String token) {
		grupo.setToken(token);
	}
	
	@DeleteMapping("{TOKEN}")
	public void deletar(@RequestBody Grupo grupo, @PathVariable String token) {
		grupo.setToken(token);
		grupoRepository.deleteByToken(grupo.getToken());
	}
	
	@GetMapping("/detalhar")
	public List<Grupo> detalhar(@RequestParam String token) {
		Grupo grupo = new Grupo();
		grupo.setToken(token);
	    return grupoRepository.findByToken(grupo.getToken());
	}
	
}

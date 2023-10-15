package br.com.mizack.sorteador.controller;

import br.com.mizack.sorteador.repository.GrupoRepository;
import br.com.mizack.sorteador.model.Grupo;
import br.com.mizack.sorteador.model.Participante;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@PutMapping
	public Grupo atualizar(@RequestBody Grupo grupo) {
		List<Grupo> grupoDetalhado = grupoRepository.findByToken(grupo.getToken());
		grupo.setCodigo(grupoDetalhado.get(0).getCodigo());
		return grupoRepository.save(grupo);
	}
	
	@PostMapping("/sortear")
	public void sortear(@RequestBody Map<String, Object> jsonRequest) throws InterruptedException {
		List<Participante> listaParticipantes;
		List<Grupo> detalhamentoDoGrupo;
		Grupo grupo = new Grupo();
		
		grupo.setToken(jsonRequest.get("token").toString());
		detalhamentoDoGrupo = grupoRepository.findByToken(grupo.getToken());
		listaParticipantes = grupoRepository.findByGrupoAll(detalhamentoDoGrupo.get(0));
		
		grupo.sortear(listaParticipantes);
	}
	
	@DeleteMapping
	public void deletar(@RequestBody Grupo grupo) {
		grupoRepository.deleteByToken(grupo.getToken());
	}
	
	@GetMapping("/detalhar")
	public List<Grupo> detalhar(@RequestParam String token) {
		Grupo grupo = new Grupo();
		grupo.setToken(token);
	    return grupoRepository.findByToken(grupo.getToken());
	}
	
	@GetMapping("/listarUsuarios")
	public List<Object[]> listarUsuarios(@RequestParam String token) {
		List<Grupo> detalhamentoDoGrupo;
		Grupo grupo = new Grupo();
		grupo.setToken(token);
		detalhamentoDoGrupo = grupoRepository.findByToken(grupo.getToken());
		return grupoRepository.findByGrupo(detalhamentoDoGrupo.get(0));
	}
	
}

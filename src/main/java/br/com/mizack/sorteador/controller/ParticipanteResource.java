package br.com.mizack.sorteador.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mizack.sorteador.model.Grupo;
import br.com.mizack.sorteador.model.Participante;
import br.com.mizack.sorteador.repository.GrupoRepository;
import br.com.mizack.sorteador.repository.ParticipanteRepository;

@RestController
@RequestMapping("participante")
public class ParticipanteResource {
	@Autowired
	private ParticipanteRepository participanteRepository;
	@Autowired
	private GrupoRepository grupoRepository;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping()
	public Participante cadastrar(@RequestBody Map<String, Object> jsonRequest) {
		String token = jsonRequest.get("token").toString();
		Participante participante = new Participante();
		List<Grupo> gruposDetalhados;
		Grupo grupo = new Grupo();
		
		participante.setEmail(jsonRequest.get("email").toString());
		participante.setNome(jsonRequest.get("nome").toString());
		grupo.setToken(token);
		gruposDetalhados = grupoRepository.findByToken(grupo.getToken());
		
		for (Grupo grupoDetalhado : gruposDetalhados) {
			participante.setCodigoGrupo(grupoDetalhado);
			break;
		}
		
		return participanteRepository.save(participante);
	}
	
	@PostMapping("/importar")
	public Participante importar(@RequestBody Participante participante) {
		return participanteRepository.save(participante);
	}
	
	@PutMapping
	public void atualizar(@RequestBody Participante participante) {
		
	}

	@DeleteMapping
	public void deletar(@RequestBody Participante participante) {
		
	}
	
	@PostMapping("/reenviarEmail")
	public void reenviarEmail(@RequestBody Participante participante) {
		
	}
}

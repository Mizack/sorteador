package br.com.mizack.sorteador.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import br.com.mizack.sorteador.utils.EmailSender;

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
		participante.setCodigoGrupo(gruposDetalhados.get(0));
		
		return participanteRepository.save(participante);
	}
	
	@PutMapping
	public Participante atualizar(@RequestBody Map<String, Object> jsonRequest) {
		String token = jsonRequest.get("token").toString();
		Participante participante = new Participante();
		List<Participante> participanteDetalhado;
		List<Grupo> gruposDetalhados;
		Grupo grupo = new Grupo();

		grupo.setToken(token);
		gruposDetalhados = grupoRepository.findByToken(grupo.getToken());
		
		if (gruposDetalhados.size() == 0) {
			return participante;
		}
		
		participante.setPrimeiraOpcao(jsonRequest.get("primeira_opcao").toString());
		participante.setSegundaOpcao(jsonRequest.get("segunda_opcao").toString());
		participante.setTerceiraOpcao(jsonRequest.get("terceira_opcao").toString());
		participante.setEmail(jsonRequest.get("email").toString());
		participante.setNome(jsonRequest.get("nome").toString());
		participante.setCodigoGrupo(gruposDetalhados.get(0));
		
		participanteDetalhado = participanteRepository.findByEmailAndGrupo(participante.getEmail(), participante.getCodigoGrupo());
		
		if (participanteDetalhado.size() == 0) {
			return participante;
		}

		participante.setCodigo(participanteDetalhado.get(0).getCodigo());
		
		return participanteRepository.save(participante);
	}

	@DeleteMapping
	public void deletar(@RequestBody Map<String, Object> jsonRequest) {
		String token = jsonRequest.get("token").toString();
		Participante participante = new Participante();
		List<Participante> participanteDetalhado;
		List<Grupo> gruposDetalhados;
		Grupo grupo = new Grupo();
		grupo.setToken(token);
		gruposDetalhados = grupoRepository.findByToken(grupo.getToken());
		participante.setEmail(jsonRequest.get("email").toString());
		participante.setCodigoGrupo(gruposDetalhados.get(0));
		participanteDetalhado = participanteRepository.findByEmailAndGrupo(participante.getEmail(), participante.getCodigoGrupo());
		participante.setCodigo(participanteDetalhado.get(0).getCodigo());
		participanteRepository.deleteById(participante.getCodigo());
		
	}
	
	@PostMapping("/reenviarEmail")
	public void reenviarEmail(@RequestBody Map<String, Object> jsonRequest) {
		String token = jsonRequest.get("token").toString();
		Participante participante = new Participante();
		List<Participante> participanteAmigoSorteadoDetalhado;
		List<Participante> participanteDetalhado;
		EmailSender email = new EmailSender();
		List<Grupo> gruposDetalhados;
		Grupo grupo = new Grupo();
		
		grupo.setToken(token);
		gruposDetalhados = grupoRepository.findByToken(grupo.getToken());
		participante.setEmail(jsonRequest.get("email").toString());
		participante.setCodigoGrupo(gruposDetalhados.get(0));
		
		participanteDetalhado = participanteRepository.findByEmailAndGrupo(participante.getEmail(), participante.getCodigoGrupo());
		participanteAmigoSorteadoDetalhado = participanteRepository.findByCodigoAmigoSorteado(participanteDetalhado.get(0).getAmigoSorteado());
		
		email.enviarEmailSorteio(participanteDetalhado.get(0), participanteAmigoSorteadoDetalhado.get(0));
	}
	
}

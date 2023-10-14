package br.com.mizack.sorteador.repository;

import br.com.mizack.sorteador.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {

}
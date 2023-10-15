package br.com.mizack.sorteador.repository;

import br.com.mizack.sorteador.model.Grupo;
import br.com.mizack.sorteador.model.Participante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
	@Query("SELECT p FROM Participante p WHERE p.email = :email AND p.codigoGrupo = :grupo")
	List<Participante> findByEmailAndGrupo(@Param("email") String email, @Param("grupo") Grupo grupo);
	List<Participante> findByCodigoAmigoSorteado(Integer codigoAmigoSorteado);
}
package br.com.mizack.sorteador.repository;

import java.util.List;
import br.com.mizack.sorteador.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
	
	@Query("SELECT p.email, p.nome FROM Grupo g INNER JOIN Participante p ON p.codigoGrupo = g.codigoGrupo WHERE p.token = :token")
	List<Object[]> listarUsers(@Param("token") String token);
	
}
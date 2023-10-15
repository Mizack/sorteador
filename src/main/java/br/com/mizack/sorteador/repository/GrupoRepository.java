package br.com.mizack.sorteador.repository;
import br.com.mizack.sorteador.model.Grupo;
import br.com.mizack.sorteador.model.Participante;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;


public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
	List<Grupo> findByToken(String token);
	@Transactional
	void deleteByToken(String token);
	@Query("SELECT p.nome, p.email FROM Participante p WHERE p.codigoGrupo = :grupo")
	List<Object[]> findByGrupo(@Param("grupo") Grupo grupo);
	@Query("SELECT p FROM Participante p WHERE p.codigoGrupo = :grupo")
	List<Participante> findByGrupoAll(@Param("grupo") Grupo grupo);
}

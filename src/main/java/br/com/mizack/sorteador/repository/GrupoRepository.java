package br.com.mizack.sorteador.repository;
import br.com.mizack.sorteador.model.Grupo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
	List<Grupo> findByToken(String token);
	void deleteByToken(String token);
}

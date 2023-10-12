package br.com.mizack.sorteador.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;


@Entity
@Table(name = "PARTICIPANTE")
public class Participante {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_PARTICIPANTE")
    private int codigo;
	
	@Column(name = "NOME", nullable = false, length = 100)
    private String nome;
	
	@Column(name = "EMAIL", nullable = false, length = 100)
    private String email;
	
	@ManyToOne
	@JoinColumn(name = "CODIGO_GRUPO", referencedColumnName = "CODIGO_GRUPO", nullable = false)
    private Grupo codigoGrupo;
	
	public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Grupo getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(Grupo codigo) {
        this.codigoGrupo = codigo;
    }
}

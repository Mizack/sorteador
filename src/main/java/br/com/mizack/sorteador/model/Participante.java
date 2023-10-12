package br.com.mizack.sorteador.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PARTICIPANTE")
public class Participante {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_PARTICIPANTE")
    private int codigo;
	
	@Column(name = "NOME")
    private String nome;
	
	@Column(name = "EMAIL")
    private String email;
	
	@Column(name = "CODIGO_GRUPO")
    private int codigoGrupo;
	
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
    
    public int getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(int codigo) {
        this.codigoGrupo = codigo;
    }
}

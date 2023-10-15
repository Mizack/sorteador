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
	
	@Column(name = "PRIMEIRA_OPCAO", length = 500)
	private String primeiraOpcao;
	
	@Column(name = "SEGUNDA_OPCAO", length = 500)
	private String segundaOpcao;
	
	@Column(name = "TERCEIRA_OPCAO", length = 500)
	private String terceiraOpcao;
	
	@Column(name = "AMIGO_SORTEADO")
    private int codigoAmigoSorteado;
	
	public void main() {
		primeiraOpcao = null;
		segundaOpcao = null;
		terceiraOpcao = null;
	}
	
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
    
    public String getPrimeiraOpcao() {
    	return primeiraOpcao;
    }

    public void setPrimeiraOpcao(String primeiraOpcao) {
    	this.primeiraOpcao = primeiraOpcao;
    }

    public String getSegundaOpcao() {
    	return segundaOpcao;
    }

    public void setSegundaOpcao(String segundaOpcao) {
    	this.segundaOpcao = segundaOpcao;
    }

    public String getTerceiraOpcao() {
    	return terceiraOpcao;
    }

    public void setTerceiraOpcao(String terceiraOpcao) {
    	this.terceiraOpcao = terceiraOpcao;
    }
    
	public int getAmigoSorteado() {
        return codigoAmigoSorteado;
    }

    public void setAmigoSorteado(int codigo) {
        this.codigoAmigoSorteado = codigo;
    }
}

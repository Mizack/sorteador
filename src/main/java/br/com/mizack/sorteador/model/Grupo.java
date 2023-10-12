package br.com.mizack.sorteador.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.security.SecureRandom;
import java.time.LocalDate;

@Entity
@Table(name = "GRUPO")
public class Grupo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_GRUPO")
    private int codigo;
	
	@Column(name = "NOME", nullable = false, length = 50)
    private String nome;
	
	@Column(name = "DATA")
    private LocalDate data;
	
	@Column(name = "VALOR_SUGERIDO")
    private double valorSugerido;

	@Column(name = "TOKEN", nullable = false, length = 75)
    private String token;
	
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorSugerido() {
        return valorSugerido;
    }

    public void setValorSugerido(double valorSugerido) {
        this.valorSugerido = valorSugerido;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public String gerarToken() {
    	String escolhasPossiveis = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    	SecureRandom random = new SecureRandom();
    	String result = "";
    	for (int i = 0; i < 75; i++) {
            int randomIndex = random.nextInt(escolhasPossiveis.length());
            char randomChar = escolhasPossiveis.charAt(randomIndex);
            result += randomChar;
        }
    	return result;
    }
}

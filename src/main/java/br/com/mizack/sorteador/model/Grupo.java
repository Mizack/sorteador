package br.com.mizack.sorteador.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.mizack.sorteador.repository.ParticipanteRepository;
import br.com.mizack.sorteador.utils.EmailSender;

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
    	token = token.replaceAll("[^a-zA-Z0-9]", "").replace(" ", "");
    	if (token == null || token.length() != 75) {
            token = null;
        }
        this.token = token;
    }
    
    private ParticipanteRepository participanteRepository;

    public void sortear(List<Participante> listaParticipantes) throws InterruptedException {
        List<Participante> listaParticipantesRecebedores = new ArrayList<>(listaParticipantes);
        SecureRandom random = new SecureRandom();
        Participante participanteRecebedor = null;
        EmailSender email = new EmailSender();
        Integer indiceParticipanteAtual = 0;
        Boolean controle = false;

        for (Participante participante : listaParticipantes) {
            controle = false;
            while (!controle) {
                int randomIndex = random.nextInt(listaParticipantesRecebedores.size());
                participanteRecebedor = listaParticipantesRecebedores.get(randomIndex);

                if (!participante.getEmail().equals(participanteRecebedor.getEmail()) && (listaParticipantesRecebedores.size() != 2
                        || listaParticipantesRecebedores.size() == 2 && this.validarSeOProximoUsuarioFicaraSozinho(
                                listaParticipantes, listaParticipantesRecebedores, indiceParticipanteAtual,
                                randomIndex))) {
                    listaParticipantesRecebedores.remove(randomIndex);
                    controle = true;
                }
            }
            
            if (email.enviarEmailSorteio(participante, participanteRecebedor)) {
            	participante.setAmigoSorteado(participanteRecebedor.getCodigo());
            	participanteRepository.save(participante);
            }
            
            TimeUnit.SECONDS.sleep(2);
            indiceParticipanteAtual++;
        }
    }

    private Boolean validarSeOProximoUsuarioFicaraSozinho(List<Participante> listaParticipantes,
            List<Participante> listaParticipantesRecebedores, Integer indiceParticipanteAtual, Integer indiceSorteado) {
        String proximoParticipante = listaParticipantes.get(indiceParticipanteAtual + 1).getEmail();
        Integer indiceParticipante = 0;
        Boolean jaFoiSorteado = false;
        
        for (Participante participante : listaParticipantesRecebedores) {
        	if (!participante.getEmail().equals(proximoParticipante) && indiceParticipante == 1
        			|| participante.getEmail().equals(proximoParticipante) && indiceSorteado == indiceParticipante) {
        		jaFoiSorteado = true;
        		break;
			}
        	indiceParticipante++;
        }
        
        return jaFoiSorteado;
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

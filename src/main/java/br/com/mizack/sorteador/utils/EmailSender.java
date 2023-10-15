package br.com.mizack.sorteador.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import br.com.mizack.sorteador.model.Participante;
import io.github.cdimascio.dotenv.Dotenv;
public class EmailSender {
	private Dotenv dotenv;
	private String email;
	private String senha;
	
	public void main() {
		dotenv = Dotenv.load();
		email = dotenv.get("EMAIL_ENVIO");
		senha = dotenv.get("SENHA_ENVIO");
	}
	
	public Boolean enviarEmailSorteio(Participante recebedor, Participante amigoSorteado) {
		return this.enviarEmail(recebedor.getEmail(), "", "");
	}
	
	private Boolean enviarEmail(String recebedor, String titulo, String corpoMensagem) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, senha);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recebedor));
            message.setSubject(titulo);
            message.setText(corpoMensagem);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
	
}

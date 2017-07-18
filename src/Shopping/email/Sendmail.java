package Shopping.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Shopping.model.vo.User;

/**
 * @ClassName: Sendmail
 * @Description: Sendmail��̳�Thread�����Sendmail����һ���߳��࣬����߳������ڸ�ָ�����û�����Email
 * @author: �°�����
 * @date: -- ����::
 * 
 */
public class Sendmail extends Thread {
	// ���ڸ��û������ʼ�������
	private String from = "332827407@qq.com";
	// ������û���
	private String username = "332827407@qq.com";
	// ���������
	private String password = "";
	// �����ʼ��ķ�������ַ
	private String host = "smtp.qq.com";
	private User user;

	public Sendmail(User user) {
		this.user = user;
	}

	/*
	 * ��дrun������ʵ�֣���run�����з����ʼ���ָ�����û�
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			Properties prop = new Properties();
			prop.setProperty("mail.host", host);
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			prop.setProperty("mail.smtp.port", "465");
			prop.setProperty("mail.smtp.socketFactory.port", "465"); 
			prop.setProperty("mail.smtp.auth", "true");
			prop.setProperty("mail.imap.auth.login.disable", "true");
	//		prop.setProperty("mail.smtp.ssl.enable", "true");
		//	MyAuthenticator myauth = new MyAuthenticator(username,password);
			Session session = Session.getInstance(prop);
			session.setDebug(true);
			Transport ts = session.getTransport();
			
			ts.connect(host, username, password);
			Message message = createEmail(session, user);
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Method: createEmail
	 * @Description: ����Ҫ���͵��ʼ�
	 * @Anthor:�°�����
	 * 
	 * @param session
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Message createEmail(Session session, User user) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(user
				.getEmail()));
		message.setSubject("�û�ע���ʼ�");
		String info = "��ϲ��ע��ɹ��������û�����" + user.getUserName() + ",�������룺"
				+ user.getPassword() + "�������Ʊ��ܣ�������������ϵ��վ�ͷ�!!";
		message.setContent(info, "text/html;charset=UTF-8");
		message.saveChanges();
		return message;
	}
}

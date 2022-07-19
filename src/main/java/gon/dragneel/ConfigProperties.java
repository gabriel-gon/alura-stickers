package gon.dragneel;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class ConfigProperties {

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		InputStream file = ConfigProperties.class.getResourceAsStream("/config.properties");
		props.load(file);
		return props;
	}

	public static void  main(String args[]) throws IOException {
		String login; //Variavel que guardará o login do servidor.
		String host; //Variavel que guardará o host do servidor.
		String password; //Variável que guardará o password do usúario.
		System.out.println("************Teste de leitura do arquivo de propriedades************");

		Properties prop = getProp();

		login = prop.getProperty("prop.server.login");
		host = prop.getProperty("prop.server.host");
		password = prop.getProperty("prop.server.password");

		System.out.println("Login = " + login);
		System.out.println("Host = " + host);
		System.out.println("Password = " + password);
	}
}
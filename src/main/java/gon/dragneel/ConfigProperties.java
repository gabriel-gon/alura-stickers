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
} 

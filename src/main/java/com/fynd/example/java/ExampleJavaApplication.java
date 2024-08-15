package com.fynd.example.java;


import com.fynd.extension.model.Extension;
import com.fynd.extension.model.ExtensionCallback;
import com.fynd.extension.model.ExtensionProperties;
import com.fynd.extension.session.Session;
import com.fynd.extension.storage.SQLiteStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fynd.**","com.sdk.**"})
public class ExampleJavaApplication {

	private static final String REDIS_KEY = "REDIS";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ExtensionProperties extensionProperties;

	@Value("${sqlite.db.url}")
	private String dbUrl;


	ExtensionCallback callbacks = new ExtensionCallback((request) -> {
		Session fdkSession = (Session) request.getAttribute("session");
		if(request.getParameter("application_id") != null){
			return extensionProperties.getBaseUrl() + "/company/" + fdkSession.getCompanyId() + "/application/" + request.getParameter("application_id");
		}
		else {
			return extensionProperties.getBaseUrl() + "/company/" + fdkSession.getCompanyId();
		}
	}, (context) -> {
		logger.info("In install callback");
		return  extensionProperties.getBaseUrl();

	}, (fdkSession) -> {
		logger.info("In uninstall callback");
		return extensionProperties.getBaseUrl();

	}, (fdkSession) -> {
		logger.info("In auto-install callback");
		return extensionProperties.getBaseUrl();
	});

	public static void main(String[] args) {
		SpringApplication.run(ExampleJavaApplication.class, args);
	}

	@Bean
	public com.fynd.extension.model.Extension getExtension() throws ClassNotFoundException {
		Extension extension = new Extension();
		return extension.initialize(
				extensionProperties,
				new SQLiteStorage(dbUrl, REDIS_KEY),
				callbacks
		);
	}

}
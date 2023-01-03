package com.fynd.groot.java;


import com.fynd.extension.model.Extension;
import com.fynd.extension.model.ExtensionCallback;
import com.fynd.extension.model.ExtensionProperties;
import com.fynd.extension.storage.RedisStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import redis.clients.jedis.JedisPool;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.fynd.**", "com.fynd.**","com.gofynd","com.sdk.**"})
public class GrootJavaApplication {

	private static final String REDIS_KEY = "ext_sample";
	@Autowired
	ExtensionProperties extensionProperties;

	@Autowired
	@Qualifier("redis-pool")
	JedisPool jedis;

	ExtensionCallback callbacks = new ExtensionCallback(
			(request) ->
			{
				System.out.println("in auth callback");
				String company_id = (String) request.get("company_id");
				return extensionProperties.getBase_url() + "/company/" + company_id;
			},
			(request) ->
			{
				System.out.println("in install callback");
				return "";
			},
			(request) ->
			{
				System.out.println("in uninstall callback");
				return "";
			}
	);


	public static void main(String[] args) {
		SpringApplication.run(GrootJavaApplication.class, args);
	}

	@Bean
	@DependsOn({"redis-pool"})
	public Extension getExtension() {
		Extension extension = new Extension();
		return extension.initialize(
				extensionProperties,
				new RedisStorage(jedis, REDIS_KEY),
				callbacks
		);
	}

}
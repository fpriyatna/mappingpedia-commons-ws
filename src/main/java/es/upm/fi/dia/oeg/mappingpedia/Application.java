package es.upm.fi.dia.oeg.mappingpedia;

import java.io.IOException;
import java.io.InputStream;

//import org.apache.jena.ontology.OntModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartProperties;

@SpringBootApplication
public class Application {


	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger("Application");
		logger.info("Working Directory = " + System.getProperty("user.dir"));
		logger.info("Starting Mappingpedia Engine Commons WS version 1.0.0 ...");

		SpringApplication.run(Application.class, args);
		MultipartProperties multipartProperties = new MultipartProperties();
		multipartProperties.setLocation("./mpe-commons-temp");
		String multiPartPropertiesLocation = multipartProperties.getLocation();
		logger.info("multiPartPropertiesLocation = " + multiPartPropertiesLocation);
		logger.info("Mappingpedia Engine Commons WS started.\n\n\n");
	}
}

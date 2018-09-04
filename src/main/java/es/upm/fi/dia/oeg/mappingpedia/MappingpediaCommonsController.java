package es.upm.fi.dia.oeg.mappingpedia;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.annotation.MultipartConfig;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import es.upm.fi.dia.oeg.mappingpedia.model.*;
import es.upm.fi.dia.oeg.mappingpedia.model.result.*;
import es.upm.fi.dia.oeg.mappingpedia.utility.*;
import eu.trentorise.opendata.jackan.model.CkanOrganization;
import org.apache.commons.io.FileUtils;
//import org.apache.jena.ontology.OntModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
//@RequestMapping(value = "/mappingpedia")
@MultipartConfig(fileSizeThreshold = 20971520)
public class MappingpediaCommonsController {
    static Logger logger = LoggerFactory.getLogger("MappingpediaCommonsController");
    private MpcCkanUtility ckanClient = MpcCkanUtility.apply();

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/greeting", method= RequestMethod.GET)
    public GreetingJava getGreeting(@RequestParam(value="name", defaultValue="World") String name) {
        logger.info("/greeting(GET) ...");
        return new GreetingJava(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(value="/", method= RequestMethod.GET, produces={"application/ld+json"})
    public CommonsInbox get() {
        logger.info("GET / ...");
        return new CommonsInbox();
    }

    @RequestMapping(value="/organization_list", method= RequestMethod.GET)
    public ListResult<Agent> getCKANAnnotatedResourcesIds(
    ) {
        logger.info("GET /organization_list ...");
        logger.info("this.ckanClient = " + this.ckanClient);

        try {
            ListResult<Agent> result = this.ckanClient.getOrganizations();
            return result;
        } catch(Exception e) {
            e.printStackTrace();
            ListResult<Agent> result = new ListResult<Agent>();
            result.statusCode_$eq(500);
            result.statusMessage_$eq(e.getMessage());
            return result;
        }
    }


}
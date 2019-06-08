package cloud.socify.controller;

import cloud.socify.model.ApiError;
import cloud.socify.model.ApiVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/api")
public class ApiVersionController {

    private final static Logger LOG = LoggerFactory.getLogger(ApiVersionController.class);

    @RequestMapping(value = "/version", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getApiVersion() {
        try {
            return new ResponseEntity<>(new ApiVersion("v1"), HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getApiVersion() error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(500, "error"));
        }
    }
}

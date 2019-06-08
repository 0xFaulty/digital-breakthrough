package cloud.socify.controller;

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

    private class ApiVersion {

        private String version;

        public ApiVersion(String version) {
            this.version = version;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    private class ApiError {

        private int errorCode;
        private String errorMessage;

        public ApiError(int errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

}

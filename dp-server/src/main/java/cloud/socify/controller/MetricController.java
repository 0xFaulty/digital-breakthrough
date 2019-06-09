package cloud.socify.controller;

import cloud.socify.model.UserMetric;
import cloud.socify.service.IMetricService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/metric")
public class MetricController {

    @Autowired
    private IMetricService metricService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMetric> getUser(@PathVariable String id) {
        return metricService.getUserMetrics(id);
    }
}

package cloud.socify.service;

import cloud.socify.model.UserMetric;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MetricService implements IMetricService {

    @Override
    public List<UserMetric> getUserMetrics(String userId) {
        List<UserMetric> metrics = new ArrayList<>();

        metrics.add(new UserMetric("Gain Score Model", 0.6, ""));
        metrics.add(new UserMetric("Value-Added Models (VAM)", 0.2, ""));
        metrics.add(new UserMetric("Student Growth Percentile Model (SGP)", 0.4, ""));
        metrics.add(new UserMetric("Computer-Adaptive Approaches", 0.7, ""));
        metrics.add(new UserMetric("Skill common rating", 0.14, ""));

        return metrics;
    }
}

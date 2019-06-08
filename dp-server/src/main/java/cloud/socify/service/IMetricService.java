package cloud.socify.service;

import cloud.socify.model.UserMetric;
import java.util.List;

public interface IMetricService {

    List<UserMetric> getUserMetrics(String userId);
}

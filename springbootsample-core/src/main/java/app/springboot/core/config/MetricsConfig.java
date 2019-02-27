package app.springboot.core.config;

import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.exporter.MetricsServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
/**
 * Created by: deep.patel on 27/02/19
 */
@Configuration
public class MetricsConfig extends MetricsConfigurerAdapter {

    @Autowired
    private CollectorRegistry collectorRegistry;

    @Bean
    public CollectorRegistry collectorRegistry() {
        return new CollectorRegistry();
    }

    @Bean
    @DependsOn("collectorRegistry")
    public ServletRegistrationBean metricsServletRegistrationBean() {
        return new ServletRegistrationBean(new MetricsServlet(collectorRegistry()),
                "/prometheusMetrics");
    }

    @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        collectorRegistry.register(new DropwizardExports(metricRegistry));
    }
}

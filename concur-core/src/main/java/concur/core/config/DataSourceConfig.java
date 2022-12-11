package concur.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("classpath:mysql.properties")
@EnableJpaRepositories(basePackages = "concur.core.repository")
@EntityScan("concur.core.entity")
public class DataSourceConfig {
}

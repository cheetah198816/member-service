import api.Resources;
import config.Configurations;
import javafx.application.Application;
import mappers.Mappers;
import model.MemberEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.Repositories;
import service.Services;

/**
 * Created by chetan on 20.01.2018.
 */

@SpringBootApplication
@ComponentScan(basePackageClasses = {Resources.class, Mappers.class, Services.class, Configurations.class})
@EntityScan(basePackageClasses = MemberEntity.class)
@EnableJpaRepositories(basePackageClasses = Repositories.class)
public class MemberApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class);
    }
}

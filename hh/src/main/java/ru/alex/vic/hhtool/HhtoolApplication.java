package ru.alex.vic.hhtool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ru.alex.vic.hhtool.jpa.HHLocatinsUtil;
import ru.alex.vic.hhtool.jpa.repos.HHLocationRepo;
import ru.alex.vic.hhtool.json.HHLocationJson;
import ru.alex.vic.hhtool.json.JsonClient;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.alex.vic.**"})
@EnableJpaAuditing
@PropertySource("classpath:application.properties")
public class HhtoolApplication implements CommandLineRunner {
    @Autowired
    JsonClient jsonClient;
    @Autowired
    HHLocationRepo locationRepo;


    public static void main(String[] args) {
        SpringApplication.run(HhtoolApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length > 0 && args[0].equals("updateHHCountry")) {

            final HHLocationJson[] locations = jsonClient.getLocations();

            HHLocatinsUtil.iterate(locations, loc -> {
                locationRepo.save(loc);
            });
            locationRepo.flush();

        }

    }
}


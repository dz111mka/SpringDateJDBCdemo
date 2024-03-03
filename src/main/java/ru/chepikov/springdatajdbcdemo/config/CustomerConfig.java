package ru.chepikov.springdatajdbcdemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import ru.chepikov.springdatajdbcdemo.repository.BookRepository;

@EnableJdbcRepositories(basePackageClasses = BookRepository.class)
@Configuration
@ComponentScan("ru.chepikov.springdatajdbcdemo")
public class CustomerConfig {

}

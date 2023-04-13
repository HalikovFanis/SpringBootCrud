package com.english.springcrud;

import com.english.springcrud.dto.PhraseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringcrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcrudApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

package br.com.agenda.luna_dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * SpringBootApplication vai dar o start na aplicação
 * vai ler todos os arquivos. ('run')
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
    	
        SpringApplication.run(Application.class, args); 
        //linha principal que roda o projeto Java SpringBoot
    }
    
}

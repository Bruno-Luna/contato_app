package br.com.agenda.luna_dev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.luna_dev.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /* IC, CI ou CDI - INJEÇÃO DE DEPENDÊNCIA */
	private UsuarioRepository userRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Agenda: " + name + "!";
    } 
    
    @RequestMapping(value = "/ola/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String ola(@PathVariable String nome) {
    	return "Olá, " + nome;
    }
}

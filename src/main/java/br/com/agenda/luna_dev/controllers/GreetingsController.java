package br.com.agenda.luna_dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.luna_dev.model.Usuario;
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
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	userRepository.save(usuario); 
    	// salva no banco de dados
    	
    	
    	return "Olá, " + nome;
    }
    
    @GetMapping(value = "/listarTodos")  /*Primeiro metódo da API*/
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
     List<Usuario> usuarios = userRepository.findAll();
     //executa a consulta no banco de dados

     return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
//     return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /*Retorna a lista em formato JSON*/
     
    }
    
    @PostMapping(value = "/salvar") /*Mapeia a URL*/
    @ResponseBody  /*Descrição da resposta*/
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){ /* Recebe os dados para salvar*/
    	
    	Usuario user = userRepository.save(usuario);

    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED); /*CREATED = status 201*/
    }
}

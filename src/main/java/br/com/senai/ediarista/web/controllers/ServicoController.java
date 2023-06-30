package br.com.senai.ediarista.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senai.ediarista.core.enums.Icone;
import br.com.senai.ediarista.core.models.Servico;
import br.com.senai.ediarista.core.repositories.ServicoRepository;


@Controller
@RequestMapping("/admin/servicos")
public class ServicoController {

    //injeção de dependencia no repositorio
    @Autowired
    private ServicoRepository repository;


    //Listar
    @GetMapping
    public ModelAndView buscarTodos(){
       var modelAndView = new ModelAndView("admin/servicos/lista");

       modelAndView.addObject("servicos", repository.findAll());
       
       return modelAndView;
    }

    


    //rota para a cadastrar
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
       var modelAndView = new ModelAndView("admin/servicos/form");

       modelAndView.addObject("servico", new Servico());
       
       return modelAndView;
    }

    //recebe pelo metodo post o metodo cadastrar
    @PostMapping("/cadastrar")
    public String cadastrar(Servico servico){
        
        repository.save(servico);

        return "redirect:/admin/servicos/cadastrar";
    }


    //Excluir
    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){

        repository.deleteById(id);

        return "redirect:/admin/servicos";
    }

    //gera a lista de icones enum
    @ModelAttribute("icones")
    public Icone[] getIcones(){
        return Icone.values();
    }


}
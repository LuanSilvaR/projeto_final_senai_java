package br.com.senai.ediarista.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senai.ediarista.core.enums.Icone;
import br.com.senai.ediarista.web.dtos.ServicoForm;
import br.com.senai.ediarista.web.services.WebServicoService;



@Controller
@RequestMapping("/admin/servicos")
public class ServicoController {

    @Autowired
    private WebServicoService service;

    //Listar
    @GetMapping
    public ModelAndView buscarTodos(){
       var modelAndView = new ModelAndView("admin/servicos/lista");

       modelAndView.addObject("servicos", service.buscarTodos());
       
       return modelAndView;
    }

    


    //rota para a cadastrar
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
       var modelAndView = new ModelAndView("admin/servicos/form");

       modelAndView.addObject("form", new ServicoForm());
       
       return modelAndView;
    }

    //recebe pelo metodo post o metodo cadastrar
    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form")  ServicoForm form, BindingResult result){
        
        if (result.hasErrors()){
            return "admin/servicos/form" ;
        }
        service.cadastrar(form);

        return "redirect:/admin/servicos/cadastrar";
    }

    //Editar
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){
       var modelAndView = new ModelAndView("admin/servicos/form");

       modelAndView.addObject("form", service.buscarPorId(id));
       
       return modelAndView;
    }

    //recebe pelo metodo post o metodo editar
    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id,@Valid @ModelAttribute("form") ServicoForm form, BindingResult result){
        if(result.hasErrors()){
            return "admin/servicos/form";
        }
        
        service.editar(form, id);

        return "redirect:/admin/servicos";
    }



    //Excluir
    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){

       service.excluirPorId(id);

        return "redirect:/admin/servicos";
    }

    //gera a lista de icones enum
    @ModelAttribute("icones")
    public Icone[] getIcones(){
        return Icone.values();
    }


}

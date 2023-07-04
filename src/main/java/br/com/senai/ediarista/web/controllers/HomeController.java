package br.com.senai.ediarista.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/home")
public class HomeController {
    
    //Listar
    @GetMapping
    public ModelAndView buscar(){
       var modelAndView = new ModelAndView("admin/home/index");

      
       return modelAndView;
    }
}




package org.example.atividade1.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.atividade1.model.entity.Product;
import org.example.atividade1.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequiredArgsConstructor
@RequestMapping("produtos")
public class ProductController {
    private final ProductService service;

    @GetMapping("/form")
    public String form(Product produto) {
        return "/produto/form";
    }

    @GetMapping("/list")
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("produtos", service.listAll());
        return new ModelAndView("produto/list");
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastro(Product produto) {
        service.save(produto);
        return new ModelAndView("redirect:list");
    }

    @PostMapping("/update")
    public ModelAndView update(Product produto) {
        service.update(produto);
        return new ModelAndView("redirect:/produtos/list");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produto", service.findById(id));
        return new ModelAndView("produto/form", model);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        service.remove(id);
        return new ModelAndView("redirect:/produtos/list");
    }
}

package com.example.ProductManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private  ProductService service;
    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Product> listeProduct=service.listAll();
        model.addAttribute("listeProducts",listeProduct);
        return "index";
    }
    @RequestMapping("/new")
    public String createProduct(Model model){
        Product product=new Product();
    model.addAttribute("product",product);
        return "new_Product";
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@ModelAttribute("product") Product product){
        service.save(product);
        return"redirect:/";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id){
        ModelAndView mav=new ModelAndView("edit_Product");
        Product product=service.get(id);
        mav.addObject("product",product);
        return mav;
    }
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
      service.delete(id);
        return "redirect:/";
    }
}

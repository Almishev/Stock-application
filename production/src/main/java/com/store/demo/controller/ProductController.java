package com.store.demo.controller;

import com.store.demo.model.Product;
import com.store.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/login")
    public String showLoginPage(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "redirect:/";
    }

    @RequestMapping("/")
    public String viewHomePage(Model model,@Param("keyWord") String keyWord) {

        return viewPage(model,1, "name","asc",keyWord);
    }

    @RequestMapping("/page/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum, @Param("sortField") String sortField,
                           @Param("sortDir") String sortDir,@Param("keyWord") String keyWord) {

        Page<Product> page = productService.listAll(pageNum,sortField,sortDir,keyWord);

        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("keyWord",keyWord);

        model.addAttribute("listProducts", listProducts);

        return "index";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {

        if(result.hasErrors()){
            return "new_product";
        }
        productService.save(product);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit-product");
        Optional<Product> product = productService.get(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        productService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable int id) {


         Optional<Product> product = productService.get(id);

        model.addAttribute("product", product.isPresent() ? product.get() : new Product());
        model.addAttribute("id", id);

        return "view";
    }
}

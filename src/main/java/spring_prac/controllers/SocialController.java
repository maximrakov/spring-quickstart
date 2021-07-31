package spring_prac.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring_prac.dao.PersonService;
import spring_prac.models.User;

@Controller
@RequestMapping("/social")
public class SocialController {
    private final PersonService personService;

    @Autowired
    public SocialController(PersonService personService){
        this.personService = personService;
    }
    @GetMapping("/index")
    public String indexPage(Model model) {
        model.addAttribute("users", personService.getAll());
        return "social/index";
    }

    @GetMapping("/new")
    public String registerPage(@ModelAttribute("user")User user){
        return "social/register";
    }

    @PostMapping("")
    public String savePage(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "social/register";
        }
        personService.save(user);
        return "redirect:/social/index";
    }
}

package com.example.Controller;

import com.example.Model.Form;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller //一定要用 @Controller 本來用 @RestController 失敗@@
public class FormController {

    @GetMapping("/form")
    public String showForm(@NotNull Model model) {
        Form form = new Form();
        model.addAttribute("form", form);
        return "form";
    }

    @PostMapping("/process-form")
    public String processForm(@NotNull Model model, @ModelAttribute("form") Form form) {
        model.addAttribute("form", form);
        return "result";
    }
}

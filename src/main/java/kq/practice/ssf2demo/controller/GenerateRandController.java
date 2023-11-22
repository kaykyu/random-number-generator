package kq.practice.ssf2demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GenerateRandController {
    
    @GetMapping(path={"/", "/index.html"})
    public String showRandomForm(Model model) {
        return "generate";
    }

    @GetMapping(path="/generate")
    public String generateRand(@RequestParam Integer num, Model model) {
        return printNums(model, num);
    }

    private String printNums(Model model, int num) {

        if (num < 1 || num > 31) {
            return "error";
        }

        List<String> imageList = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            imageList.add(String.format("number%d.jpg", i));
        }

        Random r = new Random();
        Set<Integer> selectedNum = new HashSet<>();
        while (selectedNum.size() < num) {
            Integer n = r.nextInt(30);
            if (n != null && n >= 0 && n < 31) {
                selectedNum.add(n);
            }
        }
        
        List<String> images = new ArrayList<>();
        for (Integer i : selectedNum) {
            images.add(imageList.get(i));
        }
        
        model.addAttribute("number", num);
        model.addAttribute("images", images);

        return "result";
    }
}

package com.example.demo.controllers;

import com.example.demo.models.Cities;
import com.example.demo.repo.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class CitiesController {
    @Autowired
    CitiesRepository citiesRepository;

    @GetMapping("/city")
    public String citiesMain(Model model) {
        Iterable<Cities> cities = citiesRepository.findAll();
        model.addAttribute("cities", cities);
        return "city/city-main";
    }

    @GetMapping("/city/add")
    public String citiesAdd(Model model) {
        return "city/city-add";
    }

    @PostMapping("/city/add")
    public String citiesPostAdd(@RequestParam String name,
                                @RequestParam Date birthdate,
                                @RequestParam int citizens,
                                @RequestParam double size,
                                @RequestParam Boolean isCapital,
                                Model model) {
        Cities cities = new Cities(name,
                birthdate,
                citizens,
                size,
                isCapital);
        citiesRepository.save(cities);
        return "redirect:/city";
    }

    @GetMapping("/city/filter")
    public String citiesFilter(Model model) {
        return "city/city-filter";
    }
    @GetMapping("/city/filterStrong")
    public String citiesFilterStrong(Model model) {
        return "city/city-filter";
    }


    @PostMapping("/city/filter/result")
    public String citiesResult(@RequestParam String title, Model model) {
        List<Cities> result = citiesRepository.findBynameContains(title);
        model.addAttribute("result", result);
        return "city/city-filter";
    }
    @PostMapping("/city/filterStrong/result")
    public String citiesResultStrong(@RequestParam String title, Model model) {
        List<Cities> result = citiesRepository.findByname(title);
        model.addAttribute("result", result);
        return "city/city-filter";
    }

}

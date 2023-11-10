package com.fusionhub.reciperealm.webservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fusionhub.reciperealm.webservices.dto.IngredientsDto;
import com.fusionhub.reciperealm.webservices.services.IngredientsService;
import com.fusionhub.reciperealm.webservices.validation.IngredientsValidation;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/ingredients")
public class IngredientsController {
    
    @Autowired
    private IngredientsService ingredientsService;

    @Autowired
    private IngredientsValidation ingredientsValidation;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<IngredientsDto> register(@RequestBody IngredientsDto request) {
        ingredientsValidation.validateIngredients(request.getName());
        IngredientsDto registeredIngredients = ingredientsService.save(request);
        return new ResponseEntity<IngredientsDto>(registeredIngredients, HttpStatus.CREATED);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<IngredientsDto>> findByName(@PathVariable String name) {
        List<IngredientsDto> ingredientsDto = ingredientsService.findByName(name);
        return new ResponseEntity<>(ingredientsDto, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/findById")
    public ResponseEntity<IngredientsDto> findById(@RequestBody Long id) {
        IngredientsDto ingredientsDto = ingredientsService.findById(id).get();
        return new ResponseEntity<IngredientsDto>(ingredientsDto, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteById(@RequestBody Long id) {
        ingredientsService.deleteById(id);
        return new ResponseEntity<String>("Deleted", HttpStatus.OK);
    }
    
}

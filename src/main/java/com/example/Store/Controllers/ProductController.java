package com.example.Store.Controllers;


import com.example.Store.DTO.ProductResponseDTO;
import com.example.Store.Models.Product;
import com.example.Store.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;




    @GetMapping("/login")
    public String login() {
        return "login";
    }
}



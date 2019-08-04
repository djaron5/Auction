package com.example.Auction.controllers;

import com.example.Auction.domain.Auction;
import com.example.Auction.domain.User;
import com.example.Auction.repos.AuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private AuctionRepo auctionRepo;


    @GetMapping("/")
    public String startPage(Map<String, Object> model) {
        return "home";
    }


    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        model.put("auctions", auctionRepo.findAll());
        return "main";
    }




}

package com.example.Auction.controllers;

import com.example.Auction.domain.Auction;
import com.example.Auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auction")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;

    @GetMapping("{id}")
    private String getAuction(@PathVariable Long id, Model model) {
        Auction auction = auctionService.findAuctionById(id);
        System.out.println(auction.getAuctionName());
        return "auction";
    }
}

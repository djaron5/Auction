package com.example.Auction.controllers;

import com.example.Auction.domain.Auction;
import com.example.Auction.domain.User;
import com.example.Auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.util.Set;

@Controller
@RequestMapping("/auction")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;


    @GetMapping("{id}")
    public String getAuction(@PathVariable Long id,
                              Model model,
                              @AuthenticationPrincipal User user) {
        Auction auction = auctionService.findAuctionById(id);
        model.addAttribute("auction", auction);
        model.addAttribute("user", user);
        model.addAttribute("beginningAuctionTime",
                auction.getBeginningAuctionTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "auction";
    }

    @GetMapping("/subscribe/{id}")
    public String subscribeToAuction(@PathVariable Long id,
                                     Model model,
                                     @AuthenticationPrincipal User user) {
        Auction auction = auctionService.findAuctionById(id);
        auctionService.addParticipant(auction, user);
        model.addAttribute("auction", auction);
        model.addAttribute("user", user);
        model.addAttribute("beginningAuctionTime",
                auction.getBeginningAuctionTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "redirect:/auction/" + auction.getId();
    }

    @GetMapping("/unsubscribe/{id}")
    public String unsubscribeFromAuction(@PathVariable Long id,
                                     Model model,
                                     @AuthenticationPrincipal User user) {
        Auction auction = auctionService.findAuctionById(id);
        auctionService.removeParticipant(auction, user);
        model.addAttribute("auction", auction);
        model.addAttribute("user", user);
        model.addAttribute("beginningAuctionTime",
                auction.getBeginningAuctionTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "redirect:/auction/" + auction.getId();
    }
}

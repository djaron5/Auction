package com.example.Auction.service;

import com.example.Auction.domain.Auction;
import com.example.Auction.domain.User;
import com.example.Auction.repos.AuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuctionService {
    @Autowired
    private AuctionRepo auctionRepo;

    public Auction findAuctionById(Long id) {
        Optional<Auction> optional = auctionRepo.findById(id);
        return optional.orElse(null);
    }

    public Auction addParticipant(Auction auction, User user) {
        auction.getParticipants().add(user);
        auctionRepo.save(auction);
        return auction;
    }

    public Auction removeParticipant(Auction auction, User user) {
        auction.getParticipants().remove(user);
        auctionRepo.save(auction);
        return auction;
    }
}

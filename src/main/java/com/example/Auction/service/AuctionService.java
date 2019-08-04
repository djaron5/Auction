package com.example.Auction.service;

import com.example.Auction.domain.Auction;
import com.example.Auction.repos.AuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuctionService {
    @Autowired
    private AuctionRepo auctionRepo;

    public Auction findAuctionById(Long id) {
        Optional<Auction> optional = auctionRepo.findById(id);
        return optional.orElse(null);
    }
}

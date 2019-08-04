package com.example.Auction.repos;

import com.example.Auction.domain.Auction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepo extends CrudRepository<Auction, Long> {
    List<Auction> findAll();
}

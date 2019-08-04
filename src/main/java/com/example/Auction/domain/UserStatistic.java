package com.example.Auction.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usrStatistic")
class UserStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int createdAuctions;
    private int wonAuctions;
    private int tookPart;
}

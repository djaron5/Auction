package com.example.Auction.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "participants")
@ToString(exclude = "participants")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please, enter auction name")
    private String auctionName;
//    @NotBlank(message = "Please, enter beginning price")
    private BigDecimal beginningPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;
    private String filename;
//    @Transient
//    private List<String> tags;
    private AuctionStatus auctionStatus;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime beginningAuctionTime;
    private boolean privateAuction;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "auction_participants",
            joinColumns = {@JoinColumn(name = "auction_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> participants = new HashSet<>();

    public Auction() {}

    public Auction(String auctionName, BigDecimal beginningPrice, User owner) {
        this.auctionName = auctionName;
        this.beginningPrice = beginningPrice;
        this.owner = owner;
    }
}

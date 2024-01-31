package com.bit.auction.goods.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "bidding")
public class Bidding {

//@ManyToOne(cascade = CascadeType.ALL)
//@JoinColumn(name = "auction_id")
//private Auction auction;
//
//@ManyToOne(cascade = CascadeType.ALL)
//@JoinColumn(name = "user_id")
//private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long biddingId;

    private int biddingPrice;

    private int status;

    private int payment;

    private LocalDateTime date;

    private Long auctionId;

    private String userId;

//    public BiddingDTO toDTO(){
//        return BiddingDTO.builder()
//                .auctionId(this.auctionId.getId())
//                .userId(this.userId.getUserId())
//                .biddingPrice(this.biddingPrice)
//                .date(this.date)
//                .status(this.status)
//                .build();
//    }


}


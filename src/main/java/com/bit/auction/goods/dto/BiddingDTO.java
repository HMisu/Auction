package com.bit.auction.goods.dto;

import com.bit.auction.goods.entity.Bidding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BiddingDTO {

    private Long biddingId;

    private int biddingPrice;

    private int payment;

    private LocalDateTime date;

    private int status = 1;

    private Long auctionId;

    private String userId;


    public Bidding toEntity() {
        return Bidding.builder()
                .auctionId(auctionId)
                .userId(userId)
                .biddingPrice(biddingPrice)
                .payment(payment)
                .status(status)
                .date(LocalDateTime.now())
                .build();
    }
}

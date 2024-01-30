package com.bit.auction.goods.service;

import com.bit.auction.goods.dto.AuctionDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface AuctionService {
    AuctionDTO getAuctionGoods(Long id);

    public Page<AuctionDTO> getAuctionList(Pageable pageable, Long categoryId, String categoryOption, String sortOption, List<String> target, List<Character> status);

    Page<AuctionDTO> getMyAuctionList(Pageable pageable, String regUserId, String status);

    void insertAuction(AuctionDTO auctionDTO, Long categoryId);

    void updateAuction(AuctionDTO auctionDTO, Long categoryId);

    void removeDescriptionImg(String description, String originDescription, List<String> temporaryImageList);

    void removeDescriptionImg(List<String> temporaryImageList);

    void updateView(Long id, HttpServletRequest request, HttpServletResponse response);

    void cancelAuction(Long id);

    Page<AuctionDTO> searchAuctions(Pageable pageable, String searchQuery, List<Character> status);

    List<AuctionDTO> findByForRecentList();

    List<AuctionDTO> findByForFinalList();

    List<Map<String, Long>> getLikeSumList();
}

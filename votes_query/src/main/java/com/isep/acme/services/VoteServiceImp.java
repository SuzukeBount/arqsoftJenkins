package com.isep.acme.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.dto.VoteDTO;
import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.VoteRepository;

@Service
public class VoteServiceImp implements IVoteService {
    
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public VoteDTO getVotesByReviewId(Long reviewId){

        Optional<Review> findReview = reviewRepository.findByReviewId(reviewId);

        if(!findReview.isPresent())
            return null;

        Review review = findReview.get(); 
        VoteDTO voteDto = new VoteDTO(review.getUpVote().size(), review.getDownVote().size(),review.getReviewId());
                
        return voteDto;
    }
}

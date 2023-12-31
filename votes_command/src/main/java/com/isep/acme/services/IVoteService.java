package com.isep.acme.services;

import com.isep.acme.model.VoteReviewDTO;

public interface IVoteService {
    
        VoteReviewDTO upvoteReview(Long reviewId, Long userId);
    
        VoteReviewDTO downvoteReview(Long reviewId, Long userId);
}

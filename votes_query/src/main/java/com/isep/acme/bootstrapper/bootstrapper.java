package com.isep.acme.bootstrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.isep.acme.dto.ReviewDTO;
import com.isep.acme.model.Review;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.VoteRepository;
import com.isep.acme.services.RestService;

@Component
public class bootstrapper implements ApplicationRunner {
    
    @Autowired
    RestService restService;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public void run(ApplicationArguments    args) throws Exception {
        fetchDataFromMicroservice();
    }

    private void fetchDataFromMicroservice() {

        List<ReviewDTO> reviewDTOs = new ArrayList<ReviewDTO>();

        try{
            reviewDTOs = restService.fetchDataFromMicroservice();
        }
        catch(Exception e){
            System.out.println("Error fetching data from microservice");
            return;
        }

        for (ReviewDTO reviewDTO : reviewDTOs) {
            Optional<Review> review = reviewRepository.findByReviewId(reviewDTO.getIdReview());

            if(review.isPresent()){
                continue;
            }

            Review reviewToSave = new Review(reviewDTO.getIdReview(), reviewDTO.getReviewText(), reviewDTO.getPublishingDate(), reviewDTO.getApprovalStatus(), reviewDTO.getFunFact(), reviewDTO.getRating());
            voteRepository.save(reviewToSave);
        }
    }
}

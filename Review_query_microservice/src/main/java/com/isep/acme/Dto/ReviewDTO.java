package com.isep.acme.Dto;

import java.time.LocalDate;

public class ReviewDTO {

    private Long idReview;
    private String reviewText;
    private LocalDate publishingDate;
    private String approvalStatus;
    private String funFact;
    private Double rating;
    private Integer vote;
    private Integer numberApproved;

    public ReviewDTO(Long idReview, String reviewText, LocalDate publishingDate, String approvalStatus, String funFact, Double rating, Integer vote, Integer numberApproved) {
        this.idReview = idReview;
        this.reviewText = reviewText;
        this.publishingDate = publishingDate;
        this.approvalStatus = approvalStatus;
        this.funFact = funFact;
        this.rating = rating;
        this.vote = vote;
        this.numberApproved = numberApproved;
    }

    public void setIdReview(Long idReview) {
        this.idReview = idReview;
    }

    public Long getIdReview() {
        return this.idReview;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getFunFact() {
        return funFact;
    }

    public void setFunFact(String funFact) {
        this.funFact = funFact;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public Integer getNumberApproved() {
        return numberApproved;
    }

    public void setNumberApproved(Integer numberApproved) {
        this.numberApproved = numberApproved;
    }
}

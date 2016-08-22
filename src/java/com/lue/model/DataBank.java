/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lue.model;

import java.util.List;

/**
 *
 * @author Lue Infoservices
 */
public class DataBank {
    
   private List<String> postDescription;
    private List<String> id;
    private List<Double>  date;
    private List<String> imageUrl;
    private List<Integer> numberOfLikes;
    private List<Integer> numberOfComments;
    private List<String> linkToFullPost;
    private String errorMessage="success";
    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<Double> getDate() {
        return date;
    }

    public void setDate(List<Double> date) {
        this.date = date;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Integer> getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(List<Integer> numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public List<Integer> getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(List<Integer> numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public List<String> getLinkToFullPost() {
        return linkToFullPost;
    }

    public void setLinkToFullPost(List<String> linkToFullPost) {
        this.linkToFullPost = linkToFullPost;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(List<String> postDescription) {
        this.postDescription = postDescription;
    }

      
}

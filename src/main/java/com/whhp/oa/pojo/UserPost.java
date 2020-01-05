package com.whhp.oa.pojo;

public class UserPost {
    private Long userId;

    private Long postId;

    public UserPost(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public UserPost() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
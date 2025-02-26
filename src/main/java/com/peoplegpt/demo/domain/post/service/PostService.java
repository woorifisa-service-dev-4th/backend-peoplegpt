package com.peoplegpt.demo.domain.post.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.peoplegpt.demo.domain.global.model.response.GlobalApiResponse;
import com.peoplegpt.demo.domain.post.model.dto.request.CreateMentorPostRequest;
import com.peoplegpt.demo.domain.post.model.dto.request.CreatePostRequest;
import com.peoplegpt.demo.domain.post.model.dto.request.DeleteMentorPostRequest;
import com.peoplegpt.demo.domain.post.model.dto.request.DeletePostRequest;
import com.peoplegpt.demo.domain.post.model.dto.request.GetPostListRequest;
import com.peoplegpt.demo.domain.post.model.dto.request.UpdateMentorPostRequest;
import com.peoplegpt.demo.domain.post.model.dto.request.UpdatePostRequest;
import com.peoplegpt.demo.domain.post.model.dto.response.MentorPostDto;
import com.peoplegpt.demo.domain.post.model.dto.response.PostDto;


public interface PostService {
    
    ResponseEntity<GlobalApiResponse<PostDto>> createQNAPost(CreatePostRequest request);

    ResponseEntity<GlobalApiResponse<PostDto>> updateQNAPost(UpdatePostRequest request);
    
    ResponseEntity<GlobalApiResponse<Long>> deleteQNAPost(DeletePostRequest request);
    
    ResponseEntity<GlobalApiResponse<MentorPostDto>> createMentorPost(CreateMentorPostRequest request);

    ResponseEntity<GlobalApiResponse<MentorPostDto>> updateMentorPost(UpdateMentorPostRequest request);

    ResponseEntity<GlobalApiResponse<Long>> deleteMentorPost(DeleteMentorPostRequest request);

    ResponseEntity<GlobalApiResponse<List<PostDto>>> getPostsByCategory(GetPostListRequest request);
    
    ResponseEntity<GlobalApiResponse<PostDto>> getPostByPostId(long postId);
}

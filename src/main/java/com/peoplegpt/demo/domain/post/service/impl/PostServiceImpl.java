package com.peoplegpt.demo.domain.post.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.logging.Filter;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peoplegpt.demo.domain.global.model.entity.ClassFilter;
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
import com.peoplegpt.demo.domain.post.model.entity.Category;
import com.peoplegpt.demo.domain.post.model.entity.Post;
import com.peoplegpt.demo.domain.post.repository.PostRepository;
import com.peoplegpt.demo.domain.post.service.PostService;
import com.peoplegpt.demo.domain.user.model.entity.UserRole;
import com.peoplegpt.demo.domain.user.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {

 
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<GlobalApiResponse<PostDto>> createQNAPost(CreatePostRequest request) {
        try {
            // 사용자 존재 여부 확인
            if (!userRepository.existsById(request.getUserId())) {
                return ResponseEntity.status(404)
                        .body(GlobalApiResponse.fail("해당 사용자를 찾을 수 없습니다."));
            }

            Post result = postRepository.saveQNAPost(request.getUserId(), request.getTitle(), request.getContent(), Category.QNA, request.getFilter(), request.getTag());
            
            return ResponseEntity.status(201)
                    .body(GlobalApiResponse.success("게시물이 성공적으로 생성되었습니다.", PostDto.from(result)));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(GlobalApiResponse.fail("게시물 생성 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }

    @Override
    @Transactional
    public ResponseEntity<GlobalApiResponse<PostDto>> updateQNAPost(UpdatePostRequest request) {
        try {
            // 게시물 존재 여부 확인
            Post post = postRepository.findPostByPostId(request.getPostId());
            if (post == null) {
                return ResponseEntity.status(404)
                        .body(GlobalApiResponse.fail("해당 게시물을 찾을 수 없습니다."));
            }
            
            // 게시물 소유자 확인
            if (post.getUserId() != request.getUserId()) {
                return ResponseEntity.status(403)
                        .body(GlobalApiResponse.fail("해당 게시물의 수정 권한이 없습니다."));
            }
            
            // 저장 로직
            Post updatedPost = postRepository.updateQNAPost(request.getPostId(), request.getTitle(), request.getContent(), Category.QNA, request.getFilter(), request.getTag());
            
            return ResponseEntity.ok(GlobalApiResponse.success("게시물이 성공적으로 수정되었습니다.", PostDto.from(updatedPost)));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(GlobalApiResponse.fail("게시물 수정 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }

    @Override
    @Transactional
    public ResponseEntity<GlobalApiResponse<Long>> deleteQNAPost(DeletePostRequest request) {
        try {
            // 게시물 존재 여부 확인
            Post post = postRepository.findPostByPostId(request.getPostId());
            if (post == null) {
                return ResponseEntity.status(404)
                        .body(GlobalApiResponse.fail("해당 게시물을 찾을 수 없습니다."));
            }
            
            // 게시물 소유자 확인
            if (post.getUserId() != request.getUserId()) {
                return ResponseEntity.status(403)
                        .body(GlobalApiResponse.fail("해당 게시물의 삭제 권한이 없습니다."));
            }
            
            // 저장 로직
            postRepository.deletePost(post.getPostId());
            
            return ResponseEntity.ok(GlobalApiResponse.success("게시물이 성공적으로 삭제되었습니다.", post.getPostId()));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(GlobalApiResponse.fail("게시물 삭제 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }

    @Override
    @Transactional
    public ResponseEntity<GlobalApiResponse<MentorPostDto>> createMentorPost(CreateMentorPostRequest request) {
        try {
            // 사용자 존재 여부 확인
            if (!userRepository.existsById(request.getUserId())) {
                return ResponseEntity.status(404)
                        .body(GlobalApiResponse.fail("해당 사용자를 찾을 수 없습니다."));
            }
            
            // 사용자 역할 확인 (멘토인지)
            if (!(userRepository.getUserRole(request.getUserId()) == UserRole.MENTOR)) {
                return ResponseEntity.status(403)
                        .body(GlobalApiResponse.fail("멘토 권한이 없습니다."));
            }
            // 저장 로직
            Post savedPost = postRepository.saveMentorPost(request.getUserId(), request.getTitle(), request.getContent(), request.getCategory(), request.getFilter());
            
            return ResponseEntity.status(201)
                    .body(GlobalApiResponse.success("게시물이 성공적으로 생성되었습니다.", MentorPostDto.from(savedPost)));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(GlobalApiResponse.fail("게시물 생성 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }

    @Override
    @Transactional
    public ResponseEntity<GlobalApiResponse<MentorPostDto>> updateMentorPost(UpdateMentorPostRequest request) {
        try {
            // 게시물 존재 여부 확인
            Post post = postRepository.findPostByPostId(request.getPostId());
            if (post == null) {
                return ResponseEntity.status(404)
                        .body(GlobalApiResponse.fail("해당 게시물을 찾을 수 없습니다."));
            }
            
            // 게시물 소유자 확인
            if (post.getUserId() != request.getUserId()) {
                return ResponseEntity.status(403)
                        .body(GlobalApiResponse.fail("해당 게시물의 수정 권한이 없습니다."));
            }
            
            // 사용자 역할 확인 (멘토인지)
            if (!(userRepository.getUserRole(request.getUserId()) == UserRole.MENTOR)) {
                return ResponseEntity.status(403)
                        .body(GlobalApiResponse.fail("멘토 권한이 없습니다."));
            }

            // 저장 로직
            Post updatedPost = postRepository.updatePost(request.getPostId(), request.getTitle(), request.getContent());
            
            return ResponseEntity.ok(GlobalApiResponse.success("게시물이 성공적으로 수정되었습니다.", MentorPostDto.from(updatedPost)));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(GlobalApiResponse.fail("게시물 수정 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }

    @Override
    @Transactional
    public ResponseEntity<GlobalApiResponse<Long>> deleteMentorPost(DeleteMentorPostRequest request) {
        try {
            // 게시물 존재 여부 확인
            Post post = postRepository.findPostByPostId(request.getPostId());
            if (post == null) {
                return ResponseEntity.status(404)
                        .body(GlobalApiResponse.fail("해당 게시물을 찾을 수 없습니다."));
            }
            
            // 사용자 역할 확인 (멘토인지)
            UserRole requesterRole = userRepository.getUserRole(request.getUserId());
            if (!((requesterRole == UserRole.MENTOR) || requesterRole == UserRole.ADMIN)) {
                return ResponseEntity.status(403)
                        .body(GlobalApiResponse.fail("권한이 없습니다."));
            }

            // 저장 로직
            postRepository.deletePost(post.getPostId());
            
            return ResponseEntity.ok(GlobalApiResponse.success("게시물이 성공적으로 삭제되었습니다.", post.getPostId()));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(GlobalApiResponse.fail("게시물 삭제 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
    
    @Override
    public ResponseEntity<GlobalApiResponse<List<PostDto>>> getPostsByCategory(GetPostListRequest request) {
        try {
            Category category = request.getCategory();
            ClassFilter filter;

            
            List<Post> posts = postRepository.getPostsByCategory(category);
            if (posts == null || posts.isEmpty()) {
                return ResponseEntity.status(404)
                        .body(GlobalApiResponse.fail("해당 카테고리의 게시물이 존재하지 않습니다."));
            }
            
            List<PostDto> postDtoPostDtos = posts.stream()
                    .map(PostDto::from)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(GlobalApiResponse.success("게시물 목록 조회에 성공했습니다.", postDtoPostDtos));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(GlobalApiResponse.fail("게시물 목록 조회 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
    
    @Override
    public ResponseEntity<GlobalApiResponse<PostDto>> getPostByPostId(long postId) {
        try {
            Post post = postRepository.findPostByPostId(postId);
            if (post == null) {
                return ResponseEntity.status(404)
                        .body(GlobalApiResponse.fail("해당 게시물을 찾을 수 없습니다."));
            }
            
            return ResponseEntity.ok(GlobalApiResponse.success("게시물 조회에 성공했습니다.", PostDto.from(post)));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(GlobalApiResponse.fail("게시물 조회 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
}
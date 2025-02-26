package com.peoplegpt.demo.domain.global.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalApiResponse<T> {
    private String message;
    private T data;
    
    /**
     * 성공 응답 생성
     * @param message 성공 메시지
     * @param data 응답 데이터
     * @return ApiResponse 객체
     * @param <T> 데이터 타입
     */
    public static <T> GlobalApiResponse<T> success(String message, T data) {
        return GlobalApiResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }
    
    /**
     * 실패 응답 생성
     * @param message 실패 메시지
     * @return ApiResponse 객체
     * @param <T> 데이터 타입
     */
    public static <T> GlobalApiResponse<T> fail(String message) {
        return GlobalApiResponse.<T>builder()
                .message(message)
                .data(null)
                .build();
    }
}
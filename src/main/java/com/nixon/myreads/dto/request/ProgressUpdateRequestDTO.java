package com.nixon.myreads.dto.request;

public record ProgressUpdateRequestDTO(Long userId, Long progressId, Double completion) {
}

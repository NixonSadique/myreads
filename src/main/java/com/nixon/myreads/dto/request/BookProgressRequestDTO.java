package com.nixon.myreads.dto.request;

public record BookProgressRequestDTO(Double completion, Long bookId, Long userId) {
}

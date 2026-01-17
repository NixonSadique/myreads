package com.nixon.myreads.dto.response;

import com.nixon.myreads.client.response.AuthorsResponse;

import java.util.List;

public record BookResponseDTO(Long id, String title, String image, List<AuthorsResponse> authors) {
}

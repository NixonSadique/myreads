package com.nixon.myreads.client.response;

import com.nixon.myreads.client.BookResponse;

import java.util.List;

public record SimilarBooksResponse(List<BookResponse> similar_books) {
}

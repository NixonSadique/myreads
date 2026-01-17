package com.nixon.myreads.client.response;

import com.nixon.myreads.client.BookResponse;

import java.util.List;

public record BigBookResponse(int available, int number, int offset,List<List<BookResponse>> books) {
}


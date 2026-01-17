package com.nixon.myreads.client.response;

import java.util.List;

public record BigAuthorsResponse(List<AuthorsResponse> authors) {
}

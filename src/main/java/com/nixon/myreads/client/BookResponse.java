package com.nixon.myreads.client;

import com.nixon.myreads.client.response.AuthorsResponse;

import java.util.List;

public record BookResponse(int id, String title, String image, List<AuthorsResponse> authors){
    
}

package com.nixon.myreads.exception.handler;

public record ValidationError(String field, String message) {
}

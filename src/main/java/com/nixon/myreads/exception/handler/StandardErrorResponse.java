package com.nixon.myreads.exception.handler;

import java.time.OffsetDateTime;

public record StandardErrorResponse(int status, OffsetDateTime timestamp, String path, String title) {
}

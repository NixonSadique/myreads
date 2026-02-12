package com.nixon.myreads.exception.handler;

import java.time.OffsetDateTime;
import java.util.List;

public record StandardErrorResponse(int status, OffsetDateTime timestamp, String path, String title, List<ValidationError> validationError) {
}

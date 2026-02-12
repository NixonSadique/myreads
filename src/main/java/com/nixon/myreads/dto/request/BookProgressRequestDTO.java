package com.nixon.myreads.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record BookProgressRequestDTO
        (@Max(value = 100, message = "You can't read more than 100% of a book") Double completion,
         @NotNull(message = "A book ID is needed") Long bookId,
         @NotNull(message = "The UserID is needed") Long userId) {
}

package com.nixon.myreads.service.impl;

import com.nixon.myreads.dto.request.BookProgressRequestDTO;
import com.nixon.myreads.dto.response.BookProgressResponseDTO;
import com.nixon.myreads.dto.response.BookResponseDTO;
import com.nixon.myreads.dto.response.UserResponseDTO;
import com.nixon.myreads.entity.Book;
import com.nixon.myreads.entity.BookProgress;
import com.nixon.myreads.entity.User;
import com.nixon.myreads.exception.BadRequestException;
import com.nixon.myreads.exception.EntityNotFoundException;
import com.nixon.myreads.repository.BookProgressRepository;
import com.nixon.myreads.repository.BookRepository;
import com.nixon.myreads.repository.UserRepository;
import com.nixon.myreads.service.BookProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookProgressServiceImpl implements BookProgressService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookProgressRepository progressRepository;

    @Override
    public String createProgress(BookProgressRequestDTO progressRequestDTO) {
        BookProgress bookProgress = new BookProgress();

        if (!progressExists(progressRequestDTO.userId(), progressRequestDTO.bookId()))
            throw new BadRequestException("Book Progress already exists");

        bookProgress.setCompletion(progressRequestDTO.completion());
        bookProgress.setBook(bookRepository.findById(progressRequestDTO.bookId()).orElseThrow());
        bookProgress.setUser(userRepository.findById(progressRequestDTO.userId()).orElseThrow());

        progressRepository.save(bookProgress);

        return "Book progress added for user!";
    }


    public Boolean progressExists(Long userId, Long bookId) {
        var progresses = progressRepository.findByUserId(userId);

        for (BookProgress progress : progresses) {

            if (progress.getUser().getId().equals(userId) &&
                    progress.getBook().getId().equals(bookId)
            ) {
                return false;
            }

        }

        return true;

    }

    @Override
    public String updateCompletion(Long id, Double completion) {
        var progress = progressRepository.findById(id).orElseThrow();
        progress.setCompletion(completion);
        progressRepository.save(progress);
        return "Update complete. The completion for" + progress.getBook().getTitle() + "is at:" + completion +"%.";
    }

    @Override
    public BookProgressResponseDTO getBookProgressById(Long id) {
        BookProgress progress = progressRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("The Progress with id " + id + " was not found")
                );


        BookResponseDTO book = new BookResponseDTO(progress.getBook().getId(), progress.getBook().getTitle(), progress.getBook().getImage(), null);
        UserResponseDTO user = new UserResponseDTO(progress.getUser().getId(),progress.getUser().getEmail(),progress.getUser().getUsername());

        return new BookProgressResponseDTO(progress.getId(), progress.getCompletion(), user, book);
    }

    @Override
    public UserResponseDTO getUserByProgressId(Long id) {
        return null;
    }

    @Override
    public BookResponseDTO getBookByProgressId(Long id) {
        return null;
    }
}

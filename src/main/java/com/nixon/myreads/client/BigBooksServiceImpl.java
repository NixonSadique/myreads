package com.nixon.myreads.client;

import com.nixon.myreads.client.response.BigAuthorsResponse;
import com.nixon.myreads.client.response.BigBookResponse;
import com.nixon.myreads.client.response.SimilarBooksResponse;
import com.nixon.myreads.dto.response.AuthorsResponseDTO;
import com.nixon.myreads.dto.response.BookResponseDTO;
import com.nixon.myreads.entity.Author;
import com.nixon.myreads.entity.Book;
import com.nixon.myreads.repository.AuthorRepository;
import com.nixon.myreads.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BigBooksServiceImpl implements BigBooksService {
    private final RestTemplate restTemplate;
    private final String baseUrl = "https://api.bigbookapi.com/";
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    @Value("${api.key}")
    private String API_KEY;

    @Override
    public List<BookResponseDTO> searchBooks(String query) {


        BigBookResponse apiResponse = restTemplate.getForObject(
                baseUrl.concat("search-books?number=20" +
                        "&api-key=" + API_KEY +
                        "&query=" + query
                ),
                BigBookResponse.class
        );

        List<BookResponseDTO> responseDTOList = new ArrayList<>();
        BookResponse bookResponse;
        BookResponseDTO bookResponseDTO;

        for (int i = 0; i < apiResponse.books().size(); i++) {

            bookResponse = apiResponse.books().get(i).getFirst();

            if (!bookRepository.existsById(bookResponse.id())) {

                var authors = bookResponse.authors().stream().map(
                        authorsResponse -> new Author(authorsResponse.id(), authorsResponse.name())
                ).toList();

                    authorRepository.saveAll(authors);

                Book bookSave = new Book(
                        bookResponse.id(),
                        bookResponse.title(),
                        bookResponse.image(),
                        bookResponse.authors().stream().map(
                                authorsResponse -> new Author(authorsResponse.id(), authorsResponse.name())
                        ).toList());

                bookRepository.save(bookSave);
            }

            bookResponseDTO = new BookResponseDTO(
                    bookResponse.id(),
                    bookResponse.title(),
                    bookResponse.image(),
                    bookResponse.authors());

            responseDTOList.add(bookResponseDTO);
        }
        return responseDTOList;
    }

    @Override
    public List<AuthorsResponseDTO> searchAuthors(String query) {
        BigAuthorsResponse apiResponse = restTemplate.getForObject(
                baseUrl.concat("search-authors?number=20" +
                        "&api-key=" + API_KEY +
                        "&name=" + query
                ),
                BigAuthorsResponse.class
        );

        List<AuthorsResponseDTO> responseDTO = new ArrayList<>();
        for (int i = 0; i < Objects.requireNonNull(apiResponse).authors().size(); i++) {
            Long id = apiResponse.authors().get(i).id();
            var name = apiResponse.authors().get(i).name();
            responseDTO.add(new AuthorsResponseDTO(id, name));
            authorRepository.save(new Author(id, name));
        }
        System.out.println(apiResponse.authors());
        System.out.println(responseDTO);
        return responseDTO;
    }

    @Override
    public List<BookResponseDTO> getSimilarBooks(Long id) {
        SimilarBooksResponse apiResponse = restTemplate.getForObject(
                baseUrl.concat(id + "/similar?api-key=" + API_KEY + "&number=20"), SimilarBooksResponse.class);

        List<BookResponseDTO> responseDTO = new ArrayList<>();
        for (int i = 0; i < apiResponse.similar_books().size(); i++) {

            var response = apiResponse.similar_books().get(i);
            responseDTO.add(new BookResponseDTO((long) response.id(), response.title(), response.image(), null));
        }
        System.out.println(apiResponse.similar_books());
        System.out.println(responseDTO);
        return responseDTO;
    }
}

package com.codegym.library.repository;

import com.codegym.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends PagingAndSortingRepository<Book, Long> {
    Page<Book> findAllByNameContaining(String firstname, Pageable pageable);

    Page<Book> findAllByCategory_Id(Long id, Pageable pageable);
}
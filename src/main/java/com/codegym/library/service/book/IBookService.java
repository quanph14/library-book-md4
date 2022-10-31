package com.codegym.library.service.book;

import com.codegym.library.model.Book;
import com.codegym.library.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService extends IGeneralService<Book> {
    Page<Book> findAll(Pageable pageable);
    Page<Book> findAllByNameContaining(String firstname, Pageable pageable);

    Page<Book> findAllByCategory_Id(Long id, Pageable pageable);
}
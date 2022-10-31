package com.codegym.library.service.book;

import com.codegym.library.model.Book;
import com.codegym.library.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class BookService implements IBookService{
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findAllByNameContaining(String firstname, Pageable pageable) {
        return bookRepository.findAllByNameContaining(firstname, pageable);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findAllByCategory_Id(Long id, Pageable pageable) {
        return bookRepository.findAllByCategory_Id(id,pageable);
    }
}
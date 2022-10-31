package com.codegym.library.controller;

import com.codegym.library.model.Book;
import com.codegym.library.model.Category;
import com.codegym.library.service.book.IBookService;
import com.codegym.library.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private IBookService bookService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/books")
    public ModelAndView listBooks(@RequestParam("search") Optional<Long> search, Pageable pageable){
        Page<Book> books;
        if (search.isPresent()){
            books = bookService.findAllByCategory_Id(search.get(), pageable);
        }else{
            books = bookService.findAll(pageable);
        }
        ModelAndView modelAndView =new ModelAndView("/book/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }
    @GetMapping("/create-book")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/book/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }
    @PostMapping("/create-book")
    public ModelAndView saveBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("/book/create");
        modelAndView.addObject("book", new Book());
        modelAndView.addObject("message", "New book created successfully");
        return modelAndView;
    }
    @GetMapping("/create-code")
    public String createCode(){
return null;
    }
    @GetMapping("/edit-book/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        ModelAndView modelAndView;
        if (book.isPresent()) {
            modelAndView = new ModelAndView("/book/edit");
            modelAndView.addObject("book", book.get());
        } else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }

    @PostMapping("/edit-book")
    public ModelAndView updateBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("message", "book updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-book/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        ModelAndView modelAndView;
        if (book.isPresent()) {
            modelAndView = new ModelAndView("/book/delete");
            modelAndView.addObject("book", book.get());

        } else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }

    @PostMapping("/delete-book")
    public String deleteBook(@ModelAttribute("book") Book book) {
        bookService.remove(book.getId());
        return "redirect:books";
    }

}
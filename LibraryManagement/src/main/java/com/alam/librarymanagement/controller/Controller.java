package com.alam.librarymanagement.controller;

import com.alam.librarymanagement.model.Book;
import com.alam.librarymanagement.service.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller
{
    private final Service bookService;


    public Controller(Service bookSerice) {
        this.bookService = bookSerice;
    }

    //show all books
    @GetMapping
    public String getBooks(Model model) {
        List<Book> bookList = bookService.findAll();
        model.addAttribute("books", bookList);
        model.addAttribute("book", new Book());
        return "books";

    }

    // Add the book
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book.getBookName(), book.getAuthor());
        return "redirect:/";
    }

    //add the book
    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable long id)
    {
        bookService.returnBook(id);
        return "redirect:/";
    }

    // borrow the book
    @PostMapping("/borrow/{id}")
    public String borrowBook(@PathVariable long id)
    {
        bookService.borrowBook(id);
        return "redirect:/";
    }
}

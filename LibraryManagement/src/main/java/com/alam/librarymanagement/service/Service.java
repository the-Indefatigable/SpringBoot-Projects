package com.alam.librarymanagement.service;


import com.alam.librarymanagement.model.Book;
import com.alam.librarymanagement.respository.LibraryRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service
{
    private final LibraryRepository libraryRepository;


    public Service(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    //Adding books
    public void addBook(String bookName , String author)
    {
        Book book = new Book();
        book.setBookName(bookName);
        book.setAvailable(true);
        book.setAuthor(author);
        libraryRepository.save(book);
    }

    //Finding all books
    public List<Book> findAll()
    {
        return libraryRepository.findAll();
    }

    //Borrow

    public void borrowBook(Long bookId)
    {
        Optional<Book> book = libraryRepository.findById(bookId);
        book.ifPresent(value -> value.setAvailable(false));
//        if (book.isPresent())
//        {
//            book.get().setAvailable(false);
//        }
        libraryRepository.save(book.get());
    }

    //Return
    public void returnBook(Long bookId)
    {
        Optional<Book> book = libraryRepository.findById(bookId);
        book.ifPresent(value -> value.setAvailable(true));
//        if (book.isPresent())
//        {
//            book.get().setAvailable(true);
//        }

        libraryRepository.save(book.get());
    }


}

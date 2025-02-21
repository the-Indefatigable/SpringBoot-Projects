package com.alam.librarymanagement.respository;

import com.alam.librarymanagement.model.Book;
//import com.alam.librarymanagement.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Book, Long> {
}

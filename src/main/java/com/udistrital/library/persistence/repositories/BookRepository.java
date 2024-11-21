package com.udistrital.library.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udistrital.library.persistence.entities.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Short> {

	@Query(value = "SELECT * FROM book WHERE UPPER(unaccent(title)) LIKE '%' || UPPER(unaccent(:title)) || '%'", nativeQuery = true)
	List<Book> findByTitle(@Param("title") String title);

	@Query(value = "SELECT b.* FROM book b JOIN category c ON b.category_id = c.id WHERE UPPER(unaccent(c.name)) = UPPER(unaccent(:category))", nativeQuery = true)
	List<Book> findByCategory(@Param("category") String category);

	@Query(value = "SELECT b.* FROM book b JOIN author a ON b.author_id = a.id WHERE UPPER(unaccent(a.name)) LIKE '%' || UPPER(unaccent(:author)) || '%'", nativeQuery = true)
	List<Book> findByAuthor(@Param("author") String author);

	@Query(value = "SELECT b.* FROM book b JOIN loan_book lb ON b.id = lb.book_id JOIN loan l ON l.id = lb.loan_id JOIN app_user u ON l.user_id = u.id WHERE UPPER(unaccent(u.name)) LIKE '%' || UPPER(unaccent(:client)) || '%'", nativeQuery = true)
	List<Book> findLoanedByClient(@Param("client") String client);
}

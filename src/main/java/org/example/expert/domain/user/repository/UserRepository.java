package org.example.expert.domain.user.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT t FROM Todo t LEFT JOIN fetch t.user u ORder By t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user WHERE t.id = :todoId")
    Optional<Todo> findByIdWithinUser(@Param("todoId") Long todoId);

    int countById(Long todoId);
//    @EntityGraph(attributePaths = {"todo"})
//    List<User> findAll();
}

//스텐다드
//public interface BookRepository extends JpaRepository<Book, Long> {
//    @EntityGraph(attributePaths = {"reviews"})
//    List<Book> findAll();
//}
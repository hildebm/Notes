package com.hildebrandt.notes.repositories;

import com.hildebrandt.notes.models.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//documentation for JpaRep.
//https://docs.spring.io/autorepo/docs/spring-data-jpa/current/api/org/springframework/data/jpa/repository/support/SimpleJpaRepository.html

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
}

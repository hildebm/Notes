package com.hildebrandt.notes.service;

import com.hildebrandt.notes.models.Note;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface NoteService {


    Set<Note> getAll();

    Note findById(Long id);

    void update(Long id, Note note);

    void delete(Long id);

    Long create(Note note);
}

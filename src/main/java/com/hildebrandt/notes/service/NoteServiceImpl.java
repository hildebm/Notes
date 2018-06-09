package com.hildebrandt.notes.service;
import com.hildebrandt.notes.models.Note;
import com.hildebrandt.notes.repositories.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Set<Note> getAll(){
        Set<Note> bookSet = new HashSet<>();
        noteRepository.findAll().iterator().forEachRemaining(bookSet::add);
        return bookSet;
    }

    @Override
    public Note findById(Long id){
        Optional<Note> bookOptional = noteRepository.findById(id);

        if (!bookOptional.isPresent()) {
            throw new RuntimeException("Note Not Found!");
        }

        return bookOptional.get();

    }

    @Override
    public void update(Long id, Note note){
        Note currentNote = findById(id);
        currentNote.setTitle(note.getTitle());
        currentNote.setContent(note.getContent());
        currentNote.setUpdatedAt(new Date());
        noteRepository.save(currentNote);
    }

    @Override
    public void delete(Long id){
        noteRepository.deleteById(id);
    }

    @Override
    public Long create(Note note){
        noteRepository.save(note);
        note.setCreatedAt(new Date());
        return note.getId();
    }
}

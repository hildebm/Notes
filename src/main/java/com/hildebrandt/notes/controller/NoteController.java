package com.hildebrandt.notes.controller;

import com.hildebrandt.notes.exception.ResourceNotFoundException;
import com.hildebrandt.notes.models.Note;
import com.hildebrandt.notes.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api") // declares that the url for all the apis in this controller will start with /api.
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    // Get All Notes:  (GET /api/notes)
    @GetMapping("/notes")
    public String getAllNotes(Model model) {
        Set<Note> noteSet = new HashSet<>();
        noteRepository.findAll().iterator().forEachRemaining(noteSet::add);
        model.addAttribute("notes", noteSet);
        return "index";
    }

    // Create a new Note
    //@RequestBody annotation is used to bind the request body with a method parameter.
    //@Valid--> @NotBlank
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    // Get a Single Note
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    // Update a Note
    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }

}
package com.hildebrandt.notes.controller;

import com.hildebrandt.notes.models.Note;
import com.hildebrandt.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    //Get All notes
    @GetMapping("/notes")
    public String getAllNotes(Model model) {
        model.addAttribute("notes", noteService.getAll());
        return "index";
    }

    // Get a Single Note by Id
    @GetMapping("/note/{id}")
    public String getNoteById(@PathVariable(value = "id") Long noteId, Model model) {
    Note note = noteService.findById(noteId);
    model.addAttribute("note", note);
    return "edit";
    }

    /*---Site for new book---*/
    @RequestMapping( path = "/note/create")
    public String loadForm(Model model) {
        model.addAttribute("note", new Note());
        return "create";
    }

    /*---Add new Note---*/
    @RequestMapping(path = "/note", method = RequestMethod.POST)
    public String save(Note note) {
        long id = noteService.create(note);
        return "redirect:/notes";
    }

    /*---Update a book by id---*/
    @RequestMapping(path = "/note/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") long id, Note note) {
        noteService.update(id, note);
        return "redirect:/notes";
    }

    /*---Delete a book by id---*/
    @RequestMapping(path = "/note/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") long id) {
        noteService.delete(id);
        return "redirect:/notes";
    }

}
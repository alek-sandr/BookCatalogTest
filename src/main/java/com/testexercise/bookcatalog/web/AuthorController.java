package com.testexercise.bookcatalog.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.testexercise.bookcatalog.domain.Author;
import com.testexercise.bookcatalog.events.author.*;
import com.testexercise.bookcatalog.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET)
    public String listAuthors(Model model) {
        AllAuthorsEvent aae = authorService
                .requestAllAuthors(new RequestAllAuthorsEvent());
        model.addAttribute("authorsList", aae.getAuthors());
        return "authors";
    }

    @RequestMapping("/{authorId}/books")
    public String listAuthorBooks(@PathVariable("authorId") Long authorId, Model model) {
        AuthorEvent ae = authorService
                .requestAuthorWithBooks(new RequestAuthorEvent(authorId));
        if (!ae.isEntityFound()) {
            return "404";
        }
        model.addAttribute("booksList", ae.getAuthor().getBooks());
        model.addAttribute("author", true);
        return "books";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String newAuthor() {
        return "authorForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAuthor(@Valid @ModelAttribute("author") Author author,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "authorForm";
        } else {
            authorService.createAuthor(new CreateAuthorEvent(author));
            return "redirect:/authors";
        }
    }

    @RequestMapping("/del/{authorId}")
    public String deleteAuthor(@PathVariable("authorId") Long authorId, Model model) {
        AuthorDeletedEvent ade = authorService
                .deleteAuthor(new DeleteAuthorEvent(authorId));
        if (ade.isDeletionCompleted()) {
            return "redirect:/authors";
        } else if (!ade.isEntityFound()) {
            return "404";
        } else {
            model.addAttribute("author", ade.getAuthor());
            return "authorDelError";
        }
    }

    @RequestMapping(value = "/edit/{authorId}", method = RequestMethod.GET)
    public String showAuthorForm(@PathVariable("authorId") Long authorId, Model model) {
        AuthorEvent ae = authorService.requestAuthor(new RequestAuthorEvent(authorId));
        if (ae.isEntityFound()) {
            model.addAttribute("edit", true);
            model.addAttribute("author", ae.getAuthor());
            return "authorForm";
        } else {
            return "404";
        }
    }

    @RequestMapping(value = "/edit/{authorId}", method = RequestMethod.POST)
    public String updateAuthor(@Valid @ModelAttribute("author") Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("edit", true);
            return "authorForm";
        }
        AuthorUpdatedEvent aue = authorService
                .updateAuthor(new UpdateAuthorEvent(author.getId(), author));
        if (aue.isEntityFound()) {
            return "redirect:/authors";
        } else {
            return "404";
        }
    }

    @RequestMapping(value = "deleteAuthor", method = RequestMethod.POST,
            consumes="application/json", produces="application/json")
    @ResponseBody
    public String deleteAuthorAJAX(@RequestBody String request) {
        try {
            JsonNode data = objectMapper.readTree(request);
            Long id = data.get("id").asLong(0L);
            AuthorDeletedEvent ade = authorService
                    .deleteAuthor(new DeleteAuthorEvent(id));
            ObjectNode answer = new ObjectNode(JsonNodeFactory.instance);
            if (ade.isDeletionCompleted()) {
                answer.put("success", true);
            } else if (!ade.isEntityFound()) {
                answer.put("success", false);
                answer.put("message", "Unable to delete author. Author not found!");
            } else {
                answer.put("success", false);
                answer.put("message", "Unable to delete author. It has books co-authored with other authors.");
            }
            return objectMapper.writeValueAsString(answer);
        } catch (IOException e) {
            //e.printStackTrace();
            return "{\"success\":0, \"message\":\"Bad request!\"}";
        }
    }
}

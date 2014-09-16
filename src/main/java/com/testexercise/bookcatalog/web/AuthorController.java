package com.testexercise.bookcatalog.web;

import com.testexercise.bookcatalog.domain.Author;
import com.testexercise.bookcatalog.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping(method = RequestMethod.GET)
    public String listAuthors(Model model) {
        model.addAttribute("authorsList", authorService.listAuthors());
        return "authors";
    }

    @RequestMapping("/{authorId}/books")
    public String listAuthorBooks(@PathVariable("authorId") Long authorId, Model model) {
        model.addAttribute("booksList", authorService.getAuthorBooks(authorId));
        model.addAttribute("author", true);
        return "books";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String newAuthor(Model model) {
        return "authorForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAuthor(@Valid @ModelAttribute("author") Author author,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "authorForm";
        }
        authorService.addAuthor(author);
        return "redirect:/authors";
    }

    @RequestMapping("/del/{authorId}")
    public String deleteAuthor(@PathVariable("authorId") Long authorId, Model model) {
        boolean result = authorService.deleteAuthor(authorId);
        if (result) {
            return "redirect:/authors";
        }
        model.addAttribute("author", authorService.getAuthor(authorId));
        return "authorDelError";
    }

    @RequestMapping(value = "/edit/{authorId}", method = RequestMethod.GET)
    public String showAuthorForm(@PathVariable("authorId") Long authorId, Model model) {
        Author author = authorService.getAuthor(authorId);
        if (author == null) {
            return "redirect:/authors";
        }
        model.addAttribute("edit", true);
        model.addAttribute("author", author);
        return "authorForm";
    }

    @RequestMapping(value = "/edit/{authorId}", method = RequestMethod.POST)
    public String updateAuthor(@Valid @ModelAttribute("author") Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("edit", true);
            return "authorForm";
        }
        authorService.updateAuthor(author);
        return "redirect:/authors";
    }
}

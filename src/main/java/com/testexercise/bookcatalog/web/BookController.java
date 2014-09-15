package com.testexercise.bookcatalog.web;

import com.testexercise.bookcatalog.domen.Author;
import com.testexercise.bookcatalog.domen.Book;
import com.testexercise.bookcatalog.service.AuthorService;
import com.testexercise.bookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @RequestMapping(method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("booksList", bookService.listBooks());
        return "books";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String newBook(Model model) {
        model.addAttribute("allAuthors", authorService.listAuthors());
        return "bookForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allAuthors", authorService.listAuthors());
            return "bookForm";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }

    @RequestMapping("/del/{bookId}")
    public String deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }

    @RequestMapping(value = "/edit/{bookId}", method = RequestMethod.GET)
    public String showEditBookForm(@PathVariable("bookId") Long bookId, Model model) {
        Book book = bookService.getBook(bookId);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("edit", true);
        model.addAttribute("book", book);
        model.addAttribute("allAuthors", authorService.listAuthors());
        return "bookForm";
    }

    @RequestMapping(value = "/edit/{bookId}", method = RequestMethod.POST)
    public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("allAuthors", authorService.listAuthors());
            return "bookForm";
        }
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchBook(@RequestParam(value = "query", required = false) String query) {
        if (query == null || "".equals(query)) {
            return new ModelAndView("redirect:/books");
        }
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("search", true);
        mav.addObject("booksList", bookService.searchBook(query));
        return mav;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "authors", new CustomCollectionEditor(List.class) {
            @Override
            protected Object convertElement(Object element) {
                Long id = null;

                if(element instanceof String && !((String)element).equals("")) {
                    //from the JSP 'element' will be a String
                    try{
                        id = Long.parseLong((String) element);
                    } catch (NumberFormatException e) {
                        System.out.println("Element was " + ((String) element));
                        e.printStackTrace();
                    }
                }
                else if(element instanceof Long) {
                    id = (Long) element;
                }

                return id != null ? authorService.getAuthor(id) : null;
            }
        });
    }
}

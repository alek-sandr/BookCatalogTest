package com.testexercise.bookcatalog.web;

import com.testexercise.bookcatalog.domain.Book;
import com.testexercise.bookcatalog.events.author.AllAuthorsEvent;
import com.testexercise.bookcatalog.events.author.AuthorEvent;
import com.testexercise.bookcatalog.events.author.RequestAllAuthorsEvent;
import com.testexercise.bookcatalog.events.author.RequestAuthorEvent;
import com.testexercise.bookcatalog.events.book.*;
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
        List<Book> allBooks = bookService.listBooks(new RequestAllBooksEvent()).getBooks();
        model.addAttribute("booksList", allBooks);
        return "books";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String newBook(Model model) {
        AllAuthorsEvent aae = authorService.requestAllAuthors(new RequestAllAuthorsEvent());
        model.addAttribute("allAuthors", aae.getAuthors());
        return "bookForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            AllAuthorsEvent aae = authorService.requestAllAuthors(new RequestAllAuthorsEvent());
            model.addAttribute("allAuthors", aae.getAuthors());
            return "bookForm";
        }
        bookService.createBook(new CreateBookEvent(book));
        return "redirect:/books";
    }

    @RequestMapping("/del/{bookId}")
    public String deleteBook(@PathVariable("bookId") Long bookId) {
        BookDeletedEvent bde = bookService.deleteBook(new DeleteBookEvent(bookId));
        if (bde.isDeletionCompleted()) {
            return "redirect:/books";
        } else {
            return "404";
        }
    }

    @RequestMapping(value = "/edit/{bookId}", method = RequestMethod.GET)
    public String showEditBookForm(@PathVariable("bookId") Long bookId, Model model) {
        BookEvent be = bookService.getBook(new RequestBookEvent(bookId));
        if (!be.isEntityFound()) {
            return "404";
        }
        Book book = be.getBook();
        model.addAttribute("edit", true);
        model.addAttribute("book", book);
        AllAuthorsEvent aae = authorService.requestAllAuthors(new RequestAllAuthorsEvent());
        model.addAttribute("allAuthors", aae.getAuthors());
        return "bookForm";
    }

    @RequestMapping(value = "/edit/{bookId}", method = RequestMethod.POST)
    public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("edit", true);
            AllAuthorsEvent aae = authorService.requestAllAuthors(new RequestAllAuthorsEvent());
            model.addAttribute("allAuthors", aae.getAuthors());
            return "bookForm";
        }
        BookUpdatedEvent bue = bookService.updateBook(new UpdateBookEvent(book.getId(), book));
        if (bue.isEntityFound()) {
            return "redirect:/books";
        } else {
            return "404";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchBook(@RequestParam(value = "query", required = false) String query) {
        if (query == null || "".equals(query)) {
            return new ModelAndView("redirect:/books");
        }
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("search", true);
        BooksEvent be = bookService.searchBook(new SearchBookEvent(query));
        mav.addObject("booksList", be.getBooks());
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
                AuthorEvent ae = authorService.requestAuthor(new RequestAuthorEvent(id));
                return ae.getAuthor();
            }
        });
    }
}

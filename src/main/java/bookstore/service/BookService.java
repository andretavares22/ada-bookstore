package service;

import dto.BookDTO;
import model.Book;
import repository.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.listAll();
    }

    public Book findById(Long id) {
        return bookRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Livro com id " + id + " não encontrado."));
    }

    @Transactional
    public Book create(BookDTO bookDTO) {
        Book newBook = new Book();
        newBook.setName(bookDTO.getName());
        newBook.setAuthor(bookDTO.getAuthor());
        newBook.setDescription(bookDTO.getDescription());
        newBook.setPrice(bookDTO.getPrice());

        bookRepository.persist(newBook);
        return newBook;
    }

    @Transactional
    public Book update(Long id, BookDTO bookDTO) {
        Book bookToUpdate = findById(id);

        bookToUpdate.setName(bookDTO.getName());
        bookToUpdate.setDescription(bookDTO.getDescription());
        bookToUpdate.setPrice(bookDTO.getPrice());

        return bookToUpdate;
    }

    @Transactional
    public void delete(Long id) {
        Book bookToDelete = findById(id);
        bookRepository.delete(bookToDelete);
    }
}
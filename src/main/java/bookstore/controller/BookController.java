package controller;

import dto.BookDTO;
import model.Book;
import service.BookService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController {

    @Inject
    BookService bookService;

    @GET
    @RolesAllowed({"user", "admin"})
    public Response listAll() {
        List<Book> books = bookService.findAll();
        return Response.ok(books).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "admin"})
    public Response findById(@PathParam("id") Long id) {
        try {
            Book book = bookService.findById(id);
            return Response.ok(book).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @RolesAllowed("admin")
    public Response create(@Valid BookDTO bookDTO) {
        Book createdBook = bookService.create(bookDTO);
        return Response.status(Response.Status.CREATED).entity(createdBook).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response update(@PathParam("id") Long id, @Valid BookDTO bookDTO) {
        try {
            Book updatedBook = bookService.update(id, bookDTO);
            return Response.ok(updatedBook).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response delete(@PathParam("id") Long id) {
        try {
            bookService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
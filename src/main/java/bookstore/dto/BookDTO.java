package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BookDTO {

    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(max = 100, message = "O nome não pode ter mais que 100 caracteres")
    private String name;

    @NotBlank(message = "O autor não pode ser nulo ou vazio")
    @Size(max = 100, message = "O autor não pode ter mais que 100 caracteres")
    private String author;

    @Size(max = 2000, message = "A descrição não pode ter mais que 2000 caracteres")
    private String description;

    @NotNull(message = "O preço não pode ser nulo")
    @Positive(message = "O preço deve ser maior que zero")
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
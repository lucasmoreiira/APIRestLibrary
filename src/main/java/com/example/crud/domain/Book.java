package com.example.crud.domain;


import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;


@Table(name="book")
@Entity(name="book")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {

        @Id @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        private String name;

        private String genre;

        private String author;

        private String ISBN;

        private Boolean active;

        public Book(RequestBookDTO requestBook) {
                this.name = requestBook.name();
                this.genre = requestBook.genre();
                this.author = requestBook.author();
                this.ISBN = requestBook.ISBN();
                this.active = true;
        }

        public Boolean getActive() {
                return active;
        }

        public void deactivate(){
                this.active=false;
        }

        public String getISBN() {
                return ISBN;
        }

        public void setISBN(String ISBN) {
                this.ISBN = ISBN;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public String getGenre() {
                return genre;
        }

        public void setGenre(String genre) {
                this.genre = genre;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getId() {
                return id;
        }

        public Book() {}

}

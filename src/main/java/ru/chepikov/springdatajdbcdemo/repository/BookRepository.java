package ru.chepikov.springdatajdbcdemo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.chepikov.springdatajdbcdemo.entity.Book;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class  BookRepository {

    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/itrum";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "321678";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Book";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                Book book = new Book();

                book.setId(resultSet.getLong("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublicationYear(resultSet.getDate("publication_year ").toLocalDate());

                people.add(book);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return people;
    }

    public void addBook(Book book) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO book VALUES(" + (++PEOPLE_COUNT) + ", '" + book.getTitle() + "', '" + book.getAuthor() + "', '" + book.getPublicationYear() + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteBook(Long id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM book WHERE book_id = " + id;
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(Long id, Book book) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "UPDATE book SET title = '" + book.getTitle() + "', author = '"+ book.getAuthor() +"' WHERE book_id = " + id;
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}

package com.bluesoft.javahibernateorm.services;

import com.bluesoft.javahibernateorm.domain.Book;
import com.bluesoft.javahibernateorm.domain.Chapter;
import com.bluesoft.javahibernateorm.domain.Publisher;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookStoreService {
    private Connection connection;

    private void connect(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "bstoreuser", "password#1234");
        }catch (SQLException e) {
          e.printStackTrace();
        }
    }

    public void persistObjectGraph(Book book){
        try{
            connect();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO PUBLISHER (CODE, PUBLISHER_NAME) VALUES (?, ?)");
            stmt.setString(1,book.getPublisher().getCode());
            stmt.setString(2,book.getPublisher().getName());
            stmt.executeUpdate();
            stmt.close();

            stmt = connection.prepareStatement("INSERT INTO BOOK (ISBN, BOOK_NAME, PUBLISHER_CODE) VALUES (?, ?, ?)");
            stmt.setString(1,book.getIsbn());
            stmt.setString(2,book.getName());
            stmt.setString(3,book.getPublisher().getCode());

            stmt.executeUpdate();
            stmt.close();

            stmt = connection.prepareStatement("INSERT INTO CHAPTER (BOOK_ISBN, CHAPTER_NUM, TITLE) VALUES (?, ?, ?)");
            for (Chapter chapter : book.getChapters()){
                stmt.setString(1,book.getIsbn());
                stmt.setInt(2,chapter.getChapterNumber());
                stmt.setString(3,chapter.getTitle());
                stmt.executeUpdate();
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Optional<Book> retrieveObjectGraph(String isbn){
        Book book = null;

        try{
            connect();

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM BOOK, PUBLISHER WHERE BOOK.PUBLISHER_CODE = PUBLISHER.CODE AND BOOK.ISBN = ?");
            stmt.setString(1,isbn);

            ResultSet rs = stmt.executeQuery();

            book = new Book();

            if(rs.next()){
              book.setIsbn(rs.getString("ISBN"));
              book.setName(rs.getString("BOOK_NAME"));

                Publisher publisher = new Publisher();
                publisher.setCode(rs.getString("CODE"));
                publisher.setName(rs.getString("PUBLISHER_NAME"));
                book.setPublisher(publisher);
            }else {
                rs.close();
                stmt.close();
                return Optional.empty();
            }

            rs.close();
            stmt.close();

            List<Chapter> chapters = new LinkedList<>();
            stmt = connection.prepareStatement("SELECT * FROM CHAPTER WHERE BOOK_ISBN = ?");
            stmt.setString(1,isbn);
            rs = stmt.executeQuery();

            while (rs.next()){
                Chapter chapter = new Chapter();
                chapter.setTitle(rs.getString("TITLE"));
                chapter.setChapterNumber(rs.getInt("CHAPTER_NUM"));
                chapters.add(chapter);
            }

            book.setChapters(chapters);

            rs.close();
            stmt.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Optional.of(book);
    }
}

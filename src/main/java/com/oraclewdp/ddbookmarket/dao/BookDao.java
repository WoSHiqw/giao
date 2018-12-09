package com.oraclewdp.ddbookmarket.dao;

import com.oraclewdp.ddbookmarket.model.Book;

import java.util.List;

public interface BookDao {

	boolean save(Book bookAdd);

	List<Book> findAll(int currentPage, String name, int sid);

	int total(String name, int sid);

	boolean del(int id);

    Book find(int id);

    boolean update(Book book);
}

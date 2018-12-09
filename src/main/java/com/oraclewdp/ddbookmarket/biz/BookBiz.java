package com.oraclewdp.ddbookmarket.biz;

import com.oraclewdp.ddbookmarket.model.Book;

import java.util.List;

public interface BookBiz {

	boolean save(Book bookAdd);

	List<Book> findAll(int currentPage, String name, int sid);

	int totalRow(String name, int sid);

	boolean delById(int id);

    Book findBookById(int id);

    boolean update(Book book);
}

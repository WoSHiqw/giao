package com.oraclewdp.ddbookmarket.biz.impl;

import com.oraclewdp.ddbookmarket.biz.BookBiz;
import com.oraclewdp.ddbookmarket.dao.BookDao;
import com.oraclewdp.ddbookmarket.dao.impl.BookDaoJdbcImpl;
import com.oraclewdp.ddbookmarket.model.Book;

import java.util.List;

public class BookBizImpl implements BookBiz {

	@Override
	public boolean save(Book bookAdd) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.save(bookAdd);
	}

	@Override
	public List<Book> findAll(int currentPage,String name,int sid) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.findAll(currentPage,name,sid);
	}

	@Override
	public int totalRow(String name,int sid) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.total(name,sid);
	}

	@Override
	public boolean delById(int id) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.del(id);
	}

	@Override
	public Book findBookById(int id) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.find(id);
	}

	@Override
	public boolean update(Book book) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.update(book);
	}

}

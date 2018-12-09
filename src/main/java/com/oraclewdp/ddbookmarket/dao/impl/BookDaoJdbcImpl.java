package com.oraclewdp.ddbookmarket.dao.impl;

import com.oraclewdp.ddbookmarket.dao.BookDao;
import com.oraclewdp.ddbookmarket.model.Book;
import com.oraclewdp.ddbookmarket.util.DBUtil;
import com.oraclewdp.ddbookmarket.util.PageConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoJdbcImpl implements BookDao {

	@Override
	public boolean save(Book bookAdd) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("insert into book values(default,?,?,?,?,?,?,?,?)");
			stmt.setString(1, bookAdd.getName());
			stmt.setDouble(2, bookAdd.getPrice());
			stmt.setString(3, bookAdd.getAuthor());
			stmt.setString(4, bookAdd.getCbs());
			stmt.setDate(5, new java.sql.Date(bookAdd.getCbDate().getTime()));
			stmt.setString(6, bookAdd.getDescri());
			stmt.setInt(7, bookAdd.getSid());
			stmt.setString(8, bookAdd.getPhoto());
			int ret = stmt.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(stmt, conn);
		}
		return false;
	}

	@Override
	public List<Book> findAll(int currentPage,String name,int sid) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();
			/*String sql="select * from book order by id desc limit ";
			//四种情况
			if((name==null||name.equals(""))&&sid==-1) {
				//什么都不做
			}
			//限定name不限定sid
			if ((name!=null||!name.equals(""))&&(sid==-1)) {
				sql+="where name like  '%"+name+"%' ";
			}
			//限定sid不限定name
			if ((name==null||name.equals(""))&&(sid!=-1)) {
				sql+="where sid="+sid;
			}
			//两个都限定
			if ((name!=null||!name.equals(""))&&(sid!=-1)) {
				sql+="where name like '%"+name+"%' and sid="+sid;
			}*/
			String sql="select * from book where 1=1 ";
			if (name!=null&&!name.equals("")) {
				sql+=" and name like  '%"+name+"%' ";
			}
			if (sid!=-1) {
				sql+=" and sid="+sid;
			}
			sql+=" order by id desc limit "+((currentPage-1)*PageConstant.Page_size+1-1)+","+PageConstant.Page_size;
			rs=stmt.executeQuery(sql);
			List<Book> ls=new ArrayList<>();
			while (rs.next()) {
				Book book=new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setCbDate(rs.getDate("cbDate"));
				book.setCbs(rs.getString("cbs"));
				book.setDescri(rs.getString("descri"));
				book.setPhoto(rs.getString("photo"));
				book.setPrice(rs.getDouble("price"));
				book.setSid(rs.getInt("sid"));
				ls.add(book);
			}
			return ls;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free(rs, stmt, conn);
		}
		return null;
	}

	@Override
	public int total(String name,int sid) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();
			String sql=" select count(*) from book where 1=1 ";
			if (name!=null&&!name.equals("")) {
				sql+=" and name like  '%"+name+"%' ";
			}
			if (sid!=-1) {
				sql+=" and sid="+sid;
			}
			rs=stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free(rs, stmt, conn);
		}
		return 0;
	}

	@Override
	public boolean del(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("delete from book where id="+id);
			
			int ret = stmt.executeUpdate();
			
			if (ret > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(stmt, conn);
		}
		return false;
	}

	@Override
	public Book find(int id) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();
			String sql="select * from book where id= "+id;
			rs=stmt.executeQuery(sql);
			if (rs.next()) {
				Book book=new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setCbDate(rs.getDate("cbDate"));
				book.setCbs(rs.getString("cbs"));
				book.setDescri(rs.getString("descri"));
				book.setPhoto(rs.getString("photo"));
				book.setPrice(rs.getDouble("price"));
				book.setSid(rs.getInt("sid"));
				return book;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free(rs, stmt, conn);
		}
		return null;
	}

	@Override
	public boolean update(Book book) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			if(book.getPhoto()==null){
				stmt = conn.prepareStatement(" update book set name=?,price=?,author=?,cbs=?,cbDate=?,descri=?,sid=? where id=? ");
				stmt.setString(1, book.getName());
				stmt.setDouble(2, book.getPrice());
				stmt.setString(3, book.getAuthor());
				stmt.setString(4, book.getCbs());
				stmt.setDate(5, new java.sql.Date(book.getCbDate().getTime()));
				stmt.setString(6, book.getDescri());
				stmt.setInt(7, book.getSid());
				stmt.setInt(8, book.getId());
			}else {
				stmt = conn.prepareStatement(" update book set name=?,price=?,author=?,cbs=?,cbDate=?,descri=?,sid=?,photo=? where id=? ");
				stmt.setString(1, book.getName());
				stmt.setDouble(2, book.getPrice());
				stmt.setString(3, book.getAuthor());
				stmt.setString(4, book.getCbs());
				stmt.setDate(5, new java.sql.Date(book.getCbDate().getTime()));
				stmt.setString(6, book.getDescri());
				stmt.setInt(7, book.getSid());
				stmt.setString(8, book.getPhoto());
				stmt.setInt(9, book.getId());
			}

			int ret = stmt.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(stmt, conn);
		}
		return false;
	}
}

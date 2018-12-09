package com.oraclewdp.ddbookmarket.dao.impl;

import com.oraclewdp.ddbookmarket.dao.SmallTypeDao;
import com.oraclewdp.ddbookmarket.model.SmallType;
import com.oraclewdp.ddbookmarket.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SmallTypeDaoJdbcImpl implements SmallTypeDao {

	@Override
	public boolean save(SmallType smallType) {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.prepareStatement("insert into small values(default,?,?)");
			stmt.setString(1, smallType.getName());
			stmt.setInt(2, smallType.getBid());
			int ret=stmt.executeUpdate();
			if (ret>0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free(stmt, conn);
		}
		return false;
	}

	@Override
	public List<SmallType> findAllByBid(int bid) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();
			String sql="select * from small where bid="+bid;
			rs=stmt.executeQuery(sql);
			List<SmallType> ls=new ArrayList<>();
			while (rs.next()) {
				SmallType smalltype=new SmallType();
				smalltype.setId(rs.getInt("id"));
				smalltype.setName(rs.getString("name"));
				ls.add(smalltype);
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
	public int findBidById(int sid) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();
			String sql="select bid from small where id="+sid;
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
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

}

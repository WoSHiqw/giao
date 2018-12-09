package com.oraclewdp.ddbookmarket.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oraclewdp.ddbookmarket.dao.BigTypeDao;
import com.oraclewdp.ddbookmarket.model.BigType;
import com.oraclewdp.ddbookmarket.util.DBUtil;

public class BigTypeDaoJdbcImpl implements BigTypeDao {

	@Override
	public boolean save(String name) {
		Connection coon=null;
		PreparedStatement stmt=null;
				try {
					coon=DBUtil.getConnection();
					String sql="insert into big values(default,?)";
					stmt=coon.prepareStatement(sql);
					stmt.setString(1, name);
					int ret=stmt.executeUpdate();
					if (ret>0) {
						return true;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBUtil.free(stmt,coon);
				}
		return false;
	}

	@Override
	public List<BigType> findAll() {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();
			String sql="select * from big";
			rs=stmt.executeQuery(sql);
			List<BigType> ls=new ArrayList<>();
			while (rs.next()) {
				BigType bigtype=new BigType();
				bigtype.setId(rs.getInt("id"));
				bigtype.setName(rs.getString("name"));
				ls.add(bigtype);
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

}

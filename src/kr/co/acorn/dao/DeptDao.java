package kr.co.acorn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import kr.co.acorn.dto.DeptDto;
import kr.co.acorn.util.ConnLocator;
public class DeptDao {
	// 1 singleton pattern
	//single변수는 static 메서드에서 사용해야 하기때문에 static 변수로
	//설정해야 한다.
	private static DeptDao single;
	//외부에서 객체를 생성할 수 없다.
	private DeptDao() {
	}
	//외부에서 DeptDao 객체를 생성하기 위해서는 getInstance() 메서드를
	//이용해야 한다. 따라서 이메서드에서는 DeptDao 객체를 하나만 생성할 수 있게
	//코딩해야 한다.(Singleton)
	public static DeptDao getInstance() {
		if (single == null) {
			single = new DeptDao();
		}
		return single;
	}

	public boolean insert(DeptDto dto) {
		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnLocator.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO dept(deptno, dname, loc) VALUES(?,?,?)");
			pstmt = con.prepareStatement(sql.toString());

			int index = 0;
			pstmt.setInt(++index, dto.getNo());
			pstmt.setString(++index, dto.getName());
			pstmt.setString(++index, dto.getLoc());

			pstmt.executeUpdate();// insert, update, delete

			isSuccess = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				close(con,pstmt,null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return isSuccess;
	}

	public boolean update(DeptDto dto) {
		boolean isSuccess = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnLocator.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE dept ");
			sql.append("SET dname = ?, loc=? ");
			sql.append("WHERE deptno = ?");

			pstmt = con.prepareStatement(sql.toString());

			int index = 0;
			pstmt.setString(++index, dto.getName());
			pstmt.setString(++index, dto.getLoc());
			pstmt.setInt(++index, dto.getNo());

			pstmt.executeUpdate();// insert, update, delete

			isSuccess = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				close(con,pstmt,null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return isSuccess;
	}

	public boolean delete(int deptNo) {
		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnLocator.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM dept ");
			sql.append("WHERE deptno = ?");

			pstmt = con.prepareStatement(sql.toString());

			int index = 0;
			pstmt.setInt(++index, deptNo);

			pstmt.executeUpdate();

			isSuccess = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				close(con,pstmt,null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isSuccess;
	}

	public ArrayList<DeptDto> select() {
		ArrayList<DeptDto> list = new ArrayList<DeptDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT deptno, dname, loc ");
			sql.append("FROM dept ");
			sql.append("ORDER BY deptno");

			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();// select문을 때 SQL문을 전송하는 메서드

			while (rs.next()) {
				int index = 0;
				int deptno = rs.getInt(++index);
				String dname = rs.getString(++index);
				String loc = rs.getString(++index);
				list.add(new DeptDto(deptno, dname, loc));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				close(con, pstmt, rs);
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return list;
	}

	private void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}

	public DeptDto select(int deptNo) {
		DeptDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT deptno, dname, loc ");
			sql.append("FROM dept ");
			sql.append("where deptno = ?");

			pstmt = con.prepareStatement(sql.toString());

			int index = 0;
			pstmt.setInt(++index, deptNo);

			rs = pstmt.executeQuery();// select문을 때 SQL문을 전송하는 메서드

			if (rs.next()) {
				index = 0;
				int deptno = rs.getInt(++index);
				String dname = rs.getString(++index);
				String loc = rs.getString(++index);
				dto = new DeptDto(deptno, dname, loc);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				close(con, pstmt, rs);
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return dto;

	}

}

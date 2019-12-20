package kr.co.acorn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.acorn.dto.DeptDto;
import kr.co.acorn.dto.EmpDto;
import kr.co.acorn.util.ConnLocator;

public class EmpDao {
	private static EmpDao single;

	private EmpDao() {
	}

	public static EmpDao getInstance() {
		if (single == null) {
			single = new EmpDao();
		}
		return single;
	}
	
	public int getTotalRows() {
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(empno) ");
			sql.append("FROM emp ");
	
			pstmt = con.prepareStatement(sql.toString());
			int index = 0;
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				index = 0;
				count = rs.getInt(++index);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return count;
	}
	
	public ArrayList<EmpDto> select(int start, int len){
		ArrayList<EmpDto> list = new ArrayList<EmpDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT empno, ename, job, mgr, dname, e.deptno, DATE_FORMAT(hiredate,'%Y/%m/%d') ");
			sql.append("FROM emp e, dept d ");
			sql.append("WHERE d.deptno = e.deptno ");
			sql.append("ORDER BY hiredate DESC , ename asc ");
			sql.append("LIMIT ? , ? ");
			pstmt = con.prepareStatement(sql.toString());
			
			int index = 0;
			pstmt.setInt(++index, start);
			pstmt.setInt(++index, len);
			
			rs = pstmt.executeQuery();
			DeptDto deptDto = null;
			while(rs.next()) {
				index = 0;
				int no = rs.getInt(++index);
				String name = rs.getString(++index);
				String job = rs.getString(++index);
				int mgr = rs.getInt(++index);
				
				String dname = rs.getString(++index);
				int deptNo = rs.getInt(++index);
				deptDto = new DeptDto(deptNo,dname,null);
				
				String hiredate = rs.getString(++index);
				list.add(new EmpDto(no,name,job,mgr,hiredate,deptDto));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
	public int getMaxNextNo() {
		int result = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ifnull(MAX(empno) + 1 , 1) ");
			sql.append("FROM emp ");
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 0;
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				index = 0;
				result = rs.getInt(++index);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return result;
	}
	
	public boolean insert(EmpDto dto) {
		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO emp(empno, ename, job, mgr,hiredate,sal, comm, deptno) ");
			sql.append("VALUES(?,?,?,?,CURDATE(),?,?,?)");
			sql.append("");
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 0;
			pstmt.setInt(++index, dto.getNo() );
			pstmt.setString(++index,  dto.getName());
			pstmt.setString(++index,  dto.getJob());
			pstmt.setInt(++index, dto.getMgr() );
			pstmt.setInt(++index, dto.getSal() );
			pstmt.setInt(++index, dto.getComm() );
			pstmt.setInt(++index, dto.getDeptDto().getNo() );
			
			pstmt.executeUpdate();
			
			isSuccess = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		
		return isSuccess;
	}
	
	public EmpDto select(int no) {
		EmpDto dto = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT empno, ename, job, mgr, ");
			sql.append("DATE_FORMAT(hiredate, '%Y/%m/%d'), sal, comm, deptno ");
			sql.append("FROM emp ");
			sql.append("WHERE empno = ?");
			pstmt = con.prepareStatement(sql.toString());
			int index = 0;
			pstmt.setInt(++index, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				index = 0;
				no = rs.getInt(++index);
				String name = rs.getString(++index);
				String job = rs.getString(++index);
				int mgr = rs.getInt(++index);
				String hiredate = rs.getString(++index);
				int sal = rs.getInt(++index);
				int comm = rs.getInt(++index);
				int deptNo = rs.getInt(++index);
				DeptDto deptDto = new DeptDto(deptNo,null,null);
				dto = new EmpDto(no,name,job,mgr,hiredate,sal,comm,deptDto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	
	public boolean update(EmpDto dto) {
		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE emp ");
			sql.append("SET ename=?, job=?, mgr=?, sal=?, comm=?, deptno=? ");
			sql.append("WHERE empno = ?");
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 0;
			pstmt.setString(++index, dto.getName());
			pstmt.setString(++index, dto.getJob());
			pstmt.setInt(++index, dto.getMgr());
			pstmt.setInt(++index, dto.getSal());
			pstmt.setInt(++index, dto.getComm());
			pstmt.setInt(++index, dto.getDeptDto().getNo());
			pstmt.setInt(++index, dto.getNo());
			
			pstmt.executeUpdate();
			
			isSuccess = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
			}
		}
		return isSuccess;
	}
	
	public boolean delete(int no) {
		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete from emp where empno = ?");
						
			pstmt = con.prepareStatement(sql.toString());
			int index = 0;
			pstmt.setInt(++index, no);
						
			pstmt.executeUpdate();
			
			isSuccess = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
			}
		}
		return isSuccess;
	}
	
}












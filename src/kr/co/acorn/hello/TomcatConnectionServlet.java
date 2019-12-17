package kr.co.acorn.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/hey")
public class TomcatConnectionServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Context context = new InitialContext();
			// java:comp/env => 고정
			// jdbc/acorn => context.xml의 name 속성 값과 일치해야 한다.
			ds = (DataSource) context.lookup("java:comp/env/jdbc/acorn");
			//ds.getConnection()은 Connection Pool에서 이미 만들어진 Connection 
			//객체를 가져온다. 다시 말해서 이 시점에서 Connnection 객체를 생성하는
			//것이 아니라 이미 만들어진 Connection 객체를 사용한다는 것이다.
			con = ds.getConnection();
			StringBuffer sql = new StringBuffer("select deptno, dname, loc from dept");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int index = 0;
				int deptno = rs.getInt(++index);
				String dname = rs.getString(++index);
				String loc = rs.getString(++index);
				out.println(deptno + ","+dname+","+loc+"<br>");
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();//Connection 객체를 종료하는 것이 아니라 반납
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		
		
		
		
		
		
		
		
	}
}




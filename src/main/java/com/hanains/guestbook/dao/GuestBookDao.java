package com.hanains.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.guestbook.vo.GuestBookVo;


/*
 * 
 * DAO -> MyBatis 까지 수정.
 * XML 설정시 resultType ParameterType 신경쓰기.
 * Getter와 Setter 이용시 필드네임 신경쓰기.
 * 
 * 
 * */
@Repository
public class GuestBookDao {
	
	
	@Autowired
	private OracleDataSource oracleDataSource;
	
	@Autowired
	private SqlSession sqlSession;
	/*
	private Connection getConnection() throws SQLException{
		
		Connection connection =null;
		try{
			//1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.커넥션 만들기 db 연결.
			String dbURL="jdbc:oracle:thin@localhost:1521:xe";
			connection = DriverManager.getConnection(dbURL,"webdb","webdb");
			
		}catch(ClassNotFoundException ex){
			System.out.println("드라이버 로딩 실패 :"+ex);
		}
		return connection;
	}
	*/
	public List<GuestBookVo> getList(){
		List<GuestBookVo> list = sqlSession.selectList("guestbook.list");
		return list;
	}
	/*
	public List<GuestBookVo> getList(){
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		Connection connection = null;
		Statement stmt= null;
		ResultSet rs= null;
		
		try{
			
			//connection =getConnection();
			connection = oracleDataSource.getConnection();
			//3.statement 생성
			stmt = connection.createStatement();
			
			String sql="select no, name, password, message, to_char(reg_date,'YYYY-MM-DD HH:MI:SS') from guestbook";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no=rs.getLong(1);
				String name=rs.getString(2);
				String password=rs.getString(3);
				String message = rs.getString(4);
				String reg_date = rs.getString(5);
				
				GuestBookVo vo= new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setMessage(message);
				vo.setReg_date(reg_date);
				
				list.add(vo);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
				if(connection != null){
					connection.close();
				}
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return list;
	}
	*/
	public void insert(GuestBookVo vo){
		sqlSession.insert("guestbook.insert",vo);
		
	}
	
	/*
	public void insert(GuestBookVo vo){
		Connection connection =null;
		PreparedStatement pstmt=null;
		try{
			
			//connection = getConnection();
			connection = oracleDataSource.getConnection();
			
			//3.statement 준비
			String sql="insert into guestbook values(GUESTBOOK_SEQ.nextval,?,?,?,SYSDATE)";
			pstmt = connection.prepareStatement(sql);
			
			//4.binding
			pstmt.setString(1,vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
			//5.SQL실행
			pstmt.executeUpdate();
			
			
			
		}catch(SQLException ex){
			System.out.println("SQL error : "+ ex);
		}
		finally{
			try{
				if(pstmt !=null){
					pstmt.close();
				}
				if(connection != null){
					connection.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}
	*/
	
	public void delete(GuestBookVo vo){
		sqlSession.delete("guestbook.delete",vo);
	}
	
	/*
	public void delete(GuestBookVo vo){
		Connection connection = null;
		PreparedStatement pstmt= null;
		
		try{
			
			//connection = getConnection();
			connection = oracleDataSource.getConnection();
			
			//3.statement 준비
			String sql="delete from guestbook where no = ? and password=?";
			pstmt = connection.prepareStatement(sql);
			
			//4.binding
			pstmt.setLong(1,vo.getNo());
			pstmt.setString(2,vo.getPassword());
			
			//5.SQL 실행
			pstmt.executeUpdate();
			
			
		}catch(SQLException ex){
			System.out.println("에러 : "+ex);
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(connection != null){
					connection.close();
				}
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}*/
}

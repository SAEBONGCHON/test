package test;

import java.sql.Connection;
import java.util.List;



public class TestService {
	public int insert(TestVO vo){
		int result = 0;
		Connection conn = JDBCTemplate.getConnection();
		result = new TestDAO().insert(conn, vo);
		JDBCTemplate.close(conn);
		return result;
	}
	
	public int update(TestVO vo){
		int result = 0;
		Connection conn = JDBCTemplate.getConnection();
		result = new TestDAO().update(conn, vo);
		JDBCTemplate.close(conn);
		return result;
	}

	//수정 겸 수정글읽
	public TestVO update2(TestVO vo){
		int result = 0;
		TestVO result2 = null;
		Connection conn = JDBCTemplate.getConnection();
		result = new TestDAO().update(conn, vo);
		if(result > 0) {
			result2 = new TestDAO().selectOne(conn, vo.getBoardNum());
		}
		JDBCTemplate.close(conn);
		
		return result2;
	}
	
	public int delete(TestVO vo){
		int result = 0;
		Connection conn = JDBCTemplate.getConnection();
		result = new TestDAO().delete(conn, vo);
		JDBCTemplate.close(conn);
		return result;
	}
	
	//글읽
	public TestVO selectOne(int boardNum){
		TestVO result = null;
		Connection conn = JDBCTemplate.getConnection();
		result = new TestDAO().selectOne(conn, boardNum);
		JDBCTemplate.close(conn);
		return result;
	}
}

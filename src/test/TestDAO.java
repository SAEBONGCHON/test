package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDAO {
	//보드 메서드 한개의 보드 읽는 select..총 6개
//	@ -0,0 +1,175 @@
//	DROP TsABLE TEST_MEMBER CASCADE CONSTRAINTS;
//	CREATE TABLE TEST_MEMBER(
//	 ID CHAR(1),
//	 PASSWD VARCHAR2(15 BYTE) NOT NULL,
//	 NAME VARCHAR2(20 BYTE) NOT NULL,
//	 EMAIL VARCHAR2(30 BYTE),
//	 CONSTRAINT PK_MEMBER PRIMARY KEY (ID)
//	);
//	INSERT ALL
//	    INTO TEST_MEMBER VALUES('admin','admin', '관리자','admin@test.or.kr')
//	    INTO TEST_MEMBER VALUES('user11','pass11','사용자1','user11@test.or.kr')
//	    INTO TEST_MEMBER VALUES('user22','pass22','사용자2','user22@test.or.kr')
//	SELECT * FROM DUAL;
//	COMMIT;
//	DROP TABLE BOARD CASCADE CONSTRAINTS;
//	CREATE TABLE BOARD (
//	BOARD_NUM NUMBER, -- 게시글 번호
//	BOARD_TITLE VARCHAR2(50), -- 게시글 제목
//	BOARD_WRITER VARCHAR2(15), -- 게시글 작성자
//	BOARD_CONTENT VARCHAR2(2000), -- 게시글 내용
//	BOARD_ORIGINAL_FILENAME VARCHAR2(100), -- 업로드한 원래 파일명
//	BOARD_RENAME_FILENAME VARCHAR2(100), -- 바뀐 파일명
//	BOARD_DATE DATE DEFAULT SYSDATE,-- 게시글 올린 날짜
//	BOARD_LEVEL NUMBER DEFAULT 0,-- 글레벨 : 원글 0, 원글의 답글 1, 답글의 답글 2
//	BOARD_REF NUMBER, -- 원글일때는 자기번호, 답글일 때 참조하는 원글 번호
//	BOARD_REPLY_SEQ NUMBER DEFAULT 0, -- 답글의 순번
//	BOARD_READCOUNT NUMBER DEFAULT 0, -- 조회수
//	CONSTRAINT PK_BOARD PRIMARY KEY (BOARD_NUM),
//	CONSTRAINT FK_BOARD_WRITER FOREIGN KEY (BOARD_WRITER) REFERENCES TEST_MEMBER (ID) ON DELETE SET NULL,
//	CONSTRAINT FK_BOARD_REF FOREIGN KEY (BOARD_REF) REFERENCES BOARD (BOARD_NUM) ON DELETE CASCADE
//	);
	public int update(Connection conn, TestVO vo) {
		int result = 0;
		String sql = "update from board set board_title=?, board_content=? where board_num=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBoardTitle());
			pstmt.setString(2, vo.getBoardContent());
			pstmt.setInt(3, vo.getBoardNum());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insert(Connection conn, TestVO vo) {
		int result = 0;
		String sql = "insert into board values (?,?,?,?,?, ?,DEFAULT,DEFAULT,?,DEFAULT, DEFAULT)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, vo.getBoardTitle());
			pstmt.setString(3, vo.getBoardWriter());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TODO: close(pstmt);
		}
		
		return result;
	}
	public int delete(Connection conn, TestVO vo) {
		int result = 0;
		String sql = "delete from board where board_num=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBoardNum());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TODO: close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	public TestVO selectOne(Connection conn, int boardNum) {
		TestVO result = null;
		String sql = "select * from board where board_num=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new TestVO();
				result.setBoardNum(rs.getInt(1));
				result.setBoardTitle(rs.getString("board_Title"));	
				result.setBoardContent(rs.getString("board_Content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TODO: close(rs);
//				  close(pstmt);
		}
		return result;
	}
	
}

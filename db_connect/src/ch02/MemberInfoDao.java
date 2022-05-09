package ch02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


// memberInfo의 기능들을 정의
public class MemberInfoDao implements IMemberInfoDao{

	private static final String TABLE_NAME = "membertbl";
	// DBClient 를 통해서 DB 접속처리를 하자
	private DBClient dbClient;
	private Connection conn;
	
	public MemberInfoDao() {
		dbClient = DBClient.getInstance();
		conn = dbClient.getConnection();
	}
	
	@Override
	public synchronized ArrayList<MemberDto> select() {
		
		//모든 dto를 담는 자료구조를 생성
		ArrayList<MemberDto> dataResult = new ArrayList<MemberDto>();
		
		String sqlFormat;
		String sql;
		
		sqlFormat = "SELECT * FROM %s";
		sql = String.format(sqlFormat, TABLE_NAME);
		
		Statement stmt = null;
		ResultSet rs = null; // 밑의 결과에 따라서 오류가 나거나 데이터가 없을 수 있기 때문에
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				MemberDto dto = new MemberDto();
				dto.setMemberId(rs.getString("memberId"));
				dto.setMemberName(rs.getString("memberName"));
				dto.setMemberAddress(rs.getString("memberAddress"));
				dataResult.add(dto); // 사라지지 않게 자료구조에 하나씩 넣어준다.
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				//만들어준 객체 닫아줌.
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dataResult;
	}

	@Override
	public int insert(MemberDto dto) {
		String sqlFormat = "INSERT INTO %s VALUES('%s', '%s', '%s')";
		String sql = String.format(sqlFormat, TABLE_NAME, dto.getMemberId(), dto.getMemberName(), dto.getMemberAddress());
		
		Statement stmt = null;
		int result = 0;
		
		try {
			System.out.println("kkkk");
			stmt = conn.createStatement();
//			result = stmt.executeUpdate(sql);
			stmt.executeQuery(sql);
			System.out.println("result : 행(레코드) 갯수 " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public int update(MemberDto dto) {
		// 해당 레코드로 존재 여부를 먼저 검사 select
//		if(dto.getMemberAddress() != null ) {
//			
//		} else {
//			
//		} 연습할 때 해보기
		String sqlFormat = "UPDATE %s SET memberName = '%s' WHERE memberId = '%s' ";
		String sql = String.format(sqlFormat, TABLE_NAME, dto.getMemberName(), dto.getMemberId());
		int result = 0;
		
		try(Statement stmt = conn.createStatement()){
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int delete(String memberId) {
		// 일반적으로 delete는 존재여부 확인하지 않는다
		String sqlFormat = "DELETE FROM %s WHERE memberId = '%s'";
		String sql = String.format(sqlFormat, TABLE_NAME, memberId);
		int result = 0;
		
		try(Statement stmt = conn.createStatement()) {
			result = stmt.executeUpdate(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}

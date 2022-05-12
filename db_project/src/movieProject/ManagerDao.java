package movieProject;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDao implements IManager {

	private DBClient dbClient;
	private Connection connection;

	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public ManagerDao() {
		dbClient = DBClient.getInstance();
		connection = dbClient.getConnection();
	}
	
	// 영화 정보 등록시 영화정보 삽입메서드와 스코어 정보 삽입 메서드 같이 실행 
	// 새로운 영화정보 삽입
	@Override
	public void insertMovieInfo(String title, String date, float StarScore, String genre, String imageFileName) {

		String insertQuery = "INSERT INTO movie VALUES (?, ?, ?, ?, ?);";

		try {
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, date);
			preparedStatement.setFloat(3, StarScore);
			preparedStatement.setString(4, genre);
			preparedStatement.setString(5, imageFileName);

			resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		

	}
	
	// 스코어 정보 삽입
	@Override
	public void insertScoreInfo(String title, int audience, BigDecimal sales) {
		
		String insertQuery = "INSERT INTO score VALUES (?, ?, ?)";
		
		try {
			preparedStatement = connection.prepareStatement(insertQuery);
			
			preparedStatement.setString(1, title);
			preparedStatement.setInt(2, audience);
			preparedStatement.setBigDecimal(3, sales);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	// 영화 제목 받아서 삭제
	@Override
	public void deleteMovieInfo(String title) {
		String deleteQuery = "DELETE FROM movie WHERE title = ? ";

		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setString(1, title);

			resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	// 수정할 평점과 제목 받아서 평점 수정
	@Override
	public void updateStarScore(float starScore, String title) {

		String updateQuery = "UPDATE movie SET starScore = ? WHERE title = ? ";

		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setFloat(1, starScore);
			preparedStatement.setString(2, title);

			resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	// 수정할 매출액과 제목 받아서 매출액 수정
	@Override
	public void updateSales(java.math.BigDecimal sales, String title) {
		String updateQuery = "UPDATE score SET sales = ? WHERE title = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setBigDecimal(1, sales);
			preparedStatement.setString(2, title);
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	

}

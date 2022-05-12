package movieProject;

public interface IManager {

	// 새로운 영화 정보 삽입
	void insertMovieInfo(String title, String date, float StarScore, String genre, String imageFileName);

	// 영화 정보 스코어 삽입
	void insertScoreInfo(String title, int audience, java.math.BigDecimal sales);

	// 영화 매출 수정
	void updateSales(java.math.BigDecimal sales, String title);

	// 영화 평점 수정
	void updateStarScore(float starScore, String title);

	// 영화 삭제
	void deleteMovieInfo(String title);
}

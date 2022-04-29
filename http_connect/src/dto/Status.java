package dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
class Movies {
	private int id;
	private String url;
	private String imdb_code;
	private String title;
	private String title_english;
	private String title_long;
	private String slug;
	private int year;
	private double rating;
	private int runtime;
	private List<String> genres = new ArrayList<String>();
	private String summary;
	private String description_full;
	private String synopsis;
	private String yt_trailer_code;
	private String language;
	private String mpa_rating;
	private String background_image;
	private String background_image_original;
	private String small_cover_image;
	private String medium_cover_image;
	private String large_cover_image;
	private String state;
	
}

@Getter
@RequiredArgsConstructor
@ToString
class Data {
	private int movie_count;
	private int limit;
	private int page_number;
	private List<Movies> moviesList = new ArrayList<Movies>();
	
	public void setMoviesList(List<Movies> moviesList) {
		this.moviesList = moviesList;
	}
	
	
}

@Getter
@RequiredArgsConstructor
@ToString
public class Status {
	
	private String status;
	private String status_message;
	private List<Data> dataList = new ArrayList<Data>();
	
	public void setDataList(List<Data> dataList) {
		this.dataList = dataList;
	}
	

}




package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import classes.Quiz;
import classes.Result;
import classes.Review;
import database.MySQLUtil;
import factory.ClassFactory;

public class QuizDAOImpl implements QuizDAO {

	/*
	 * NOTE: mysql commands do not use column name or table name constants.
	 * And that is ok.
	 */
	
	private DataSource dataSource;
	private ClassFactory modelFactory;
	
	public QuizDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		modelFactory = new ClassFactory(); 
	}

	@Override
	public Quiz getQuiz(int quizId) {
		Quiz quiz = null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT "
							+ "quizzes.id, username, name, quizzes.description, is_random, is_one_page, immediate_correction, practice_mode, creation_time, time, max_score, category "
							+ "FROM quizzes "
							+ "LEFT JOIN users "
							+ "ON users.id = quizzes.creator_id "
							+ "LEFT JOIN categories "
							+ "ON categories.id = quizzes.category_id "
							+ "WHERE quizzes.id = ?;"
							);
			preparedStatement.setInt(1, quizId);
			ResultSet rs = preparedStatement.executeQuery();
			
			// we need only one row (there should not be more)
			if(rs.next()) quiz = loadIntoQuiz(rs);
			
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {	e.printStackTrace();}
		return quiz;
	}

	private Quiz loadIntoQuiz(ResultSet rs) throws SQLException {
		Quiz quiz = modelFactory.getQuiz(
				rs.getString("username"), 
				rs.getString("name"), 
				rs.getString("description")
				);
		quiz.setId(rs.getInt("id"));
		quiz.setRandom(rs.getBoolean("is_random"));
		quiz.setOnePage(rs.getBoolean("is_one_page"));
		quiz.setImmediatelyCorrected(rs.getBoolean("immediate_correction"));
		quiz.setHasPracticeMode(rs.getBoolean("practice_mode"));
		quiz.setDateCreated(rs.getLong("creation_time"));
		quiz.setQuizTime(rs.getInt("time"));
		quiz.setMaxScore(rs.getInt("max_score"));
		quiz.setCategory(rs.getString("category"));
		return quiz;
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		Quiz oldQuiz = null;
		oldQuiz = getQuiz(quiz.getId());
		if(oldQuiz == null) return null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"UPDATE quizzes "
							+ "SET "
							+ "creator_id = (SELECT id FROM users WHERE username LIKE ?), "
							+ "name = ?, "
							+ "description = ?, "
							+ "is_random = ?, "
							+ "is_one_page = ?, "
							+ "immediate_correction = ?, "
							+ "practice_mode = ?, "
							+ "creation_time = ?, "
							+ "time = ?, "
							+ "max_score = ?, "
							+ "category_id = (SELECT id FROM categories WHERE category LIKE ?) "
							+ "WHERE id = ?");
			preparedStatement.setString(1, quiz.getUserName());
			preparedStatement.setString(2, quiz.getQuizName());
			preparedStatement.setString(3, quiz.getDescription());
			preparedStatement.setBoolean(4, quiz.isRandom());
			preparedStatement.setBoolean(5, quiz.isOnePage());
			preparedStatement.setBoolean(6, quiz.isImmediatelyCorrected());
			preparedStatement.setBoolean(7, quiz.hasPracticeMode());
			preparedStatement.setLong(8, quiz.getDateCreated());
			preparedStatement.setInt(9, quiz.getQuizTime());
			preparedStatement.setInt(10, quiz.getMaxScore());
			preparedStatement.setString(11, quiz.getCategory());
			preparedStatement.setInt(12, quiz.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {e.printStackTrace();	}
		return oldQuiz;
	}

	@Override
	public int addQuiz(Quiz quiz) {
		int id = 0;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"INSERT INTO quizzes "
							+ "("
							+ "creator_id, "
							+ "name, "
							+ "description, "
							+ "is_random, "
							+ "is_one_page, "
							+ "immediate_correction, "
							+ "practice_mode, "
							+ "creation_time, "
							+ "time, "
							+ "max_score,"
							+ "category_id "
							+ ") VALUES ( "
							+ "(SELECT id FROM users WHERE username LIKE ?), "
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, "
							+ "(SELECT id FROM categories WHERE category LIKE ?)"
							+ ");"
							);
			preparedStatement.setString(1, quiz.getUserName());
			preparedStatement.setString(2, quiz.getQuizName());
			preparedStatement.setString(3, quiz.getDescription());
			preparedStatement.setBoolean(4, quiz.isRandom());
			preparedStatement.setBoolean(5, quiz.isOnePage());
			preparedStatement.setBoolean(6, quiz.isImmediatelyCorrected());
			preparedStatement.setBoolean(7, quiz.hasPracticeMode());
			preparedStatement.setLong(8, quiz.getDateCreated());
			preparedStatement.setInt(9, quiz.getQuizTime());
			preparedStatement.setInt(10, quiz.getMaxScore());
			preparedStatement.setString(11, quiz.getCategory());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			id = MySQLUtil.getLastInsertId(con);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Quiz deleteQuiz(int quizId) {
		Quiz oldQuiz = null;
		oldQuiz = getQuiz(quizId);
		if(oldQuiz == null) return null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"DELETE FROM quizzes WHERE id = ?;"
							);
			preparedStatement.setInt(1, quizId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {e.printStackTrace();}
		return oldQuiz;
	}

	@Override
	public List<Quiz> getRecentQuizzes(int n) {
		List<Quiz> recentQuizzes = new ArrayList<Quiz>();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT "
							+ "quizzes.id, username, name, quizzes.description, is_random, is_one_page, immediate_correction, practice_mode, creation_time, time, max_score, category "
							+ "FROM quizzes "
							+ "LEFT JOIN users "
							+ "ON users.id = quizzes.creator_id "
							+ "LEFT JOIN categories "
							+ "ON categories.id = quizzes.category_id "
							+ "ORDER BY creation_time DESC "
							+ "LIMIT ?;"
							);
			preparedStatement.setInt(1, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Quiz quiz = loadIntoQuiz(rs);
				recentQuizzes.add(quiz);
			}
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recentQuizzes;
	}

	@Override
	public List<Quiz> getCreatedQuizzes(String userName) {
		List<Quiz> userQuizes = new ArrayList<Quiz>();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT "
							+ "quizzes.id, username, name, quizzes.description, is_random, is_one_page, immediate_correction, practice_mode, creation_time, time, max_score, category_id, is_random, is_one_page, immediate_correction, practice_mode, creation_time, category, time, max_score "
							+ "FROM quizzes "
							+ "LEFT JOIN users "
							+ "ON quizzes.creator_id = users.id "
							+ "LEFT JOIN categories "
							+ "ON quizzes.category_id = categories.id "
							+ "WHERE username LIKE ? "
							+ "ORDER BY creation_time DESC;"
							);
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Quiz quiz = loadIntoQuiz(rs);
				userQuizes.add(quiz);
			}
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return userQuizes;
	}

	@Override
	public List<Quiz> getSeatchedQuizzes(int numResults, String parameter) {
		List<Quiz> userQuizes = new ArrayList<Quiz>();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT "
							+ "quizzes.id, username, name, quizzes.description, is_random, is_one_page, immediate_correction, practice_mode, creation_time, time, max_score, category, is_random, is_one_page, immediate_correction, practice_mode, creation_time, time, max_score "
							+ "FROM quizzes "
							+ "LEFT JOIN users "
							+ "ON users.id = quizzes.creator_id "
							+ "LEFT JOIN tags "
							+ "ON tags.quiz_id = quizzes.id "
							+ "LEFT JOIN categories "
							+ "ON categories.id = quizzes.category_id "
							+ "WHERE name LIKE ? "
							+ "OR quizzes.description LIKE ? "
							+ "OR username LIKE ? "
							+ "OR tag LIKE ? "
							+ "ORDER BY creation_time DESC "
							+ "LIMIT ?;"
							);
			preparedStatement.setString(1, "%" + parameter + "%");
			preparedStatement.setString(2, "%" + parameter + "%");
			preparedStatement.setString(3, "%" + parameter + "%");
			preparedStatement.setString(4, "%" + parameter + "%");
			preparedStatement.setInt(5, numResults);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Quiz quiz = loadIntoQuiz(rs);
				userQuizes.add(quiz);
			}
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return userQuizes;
	}

	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void addTag(int quizId, String tag) {
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"INSERT INTO tags(quiz_id, tag) "
							+ "VALUES(?, ?);"
							);
			preparedStatement.setInt(1, quizId);
			preparedStatement.setString(2, tag);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Set<String> getTag(int quizId) {
		Set<String> tags = new HashSet<String> ();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT tag "
							+ "FROM tags "
							+ "WHERE quiz_id = ?"
							);
			preparedStatement.setInt(1, quizId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) tags.add(rs.getString("tag"));
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tags;
	}

	@Override
	public void removeTag(int quizId, String tag) {
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"DELETE FROM tags "
							+ "WHERE quiz_id = ? "
							+ " AND "
							+ "tag LIKE ?;"
							);
			preparedStatement.setInt(1, quizId);
			preparedStatement.setString(2, tag);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public Set<String> getCategories() {
		Set<String> categories = new HashSet<String> ();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT category "
							+ "FROM categories;"
							);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) categories.add(rs.getString("category"));
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public void addCategory(String category) {
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"INSERT INTO categories (category) "
							+ "values(?);"
							);
			preparedStatement.setString(1, category);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removeCategory(String category) {
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"DELETE FROM categories "
							+ "WHERE category LIKE ?;"
							);
			preparedStatement.setString(1, category);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public List<Quiz> getQuizzesByCategory(String category, int n) {
		List<Quiz> userQuizes = new ArrayList<Quiz>();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT "
							+ "quizzes.id, username, name, quizzes.description, is_random, is_one_page, immediate_correction, practice_mode, creation_time, time, max_score, category, is_random, is_one_page, immediate_correction, practice_mode, creation_time, time, max_score "
							+ "FROM quizzes "
							+ "LEFT JOIN users "
							+ "ON users.id = quizzes.creator_id "
							+ "LEFT JOIN tags "
							+ "ON tags.quiz_id = quizzes.id "
							+ "LEFT JOIN categories "
							+ "ON categories.id = quizzes.category_id "
							+ "WHERE category LIKE ? "
							+ "ORDER BY creation_time DESC "
							+ "LIMIT ?;"
							);
			preparedStatement.setString(1, category);
			preparedStatement.setInt(2, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Quiz quiz = loadIntoQuiz(rs);
				userQuizes.add(quiz);
			}
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return userQuizes;
	}

	@Override
	public List<Quiz> getQuizzesOfUsers(Set<String> userNames, int n) {
		List<String> friendNames = new ArrayList<String>();
		friendNames.addAll(userNames);
		int nFriends = friendNames.size();
		
		List<Quiz> latestFriendsQuizzes = new ArrayList<Quiz> (); 
		
		// special cases:
		if(nFriends == 0) return latestFriendsQuizzes;	// in case there are no friends
		if(nFriends == 1) {	// case one friend
			latestFriendsQuizzes = getCreatedQuizzes(friendNames.get(0));
			for(int idx = latestFriendsQuizzes.size() - 1; idx >= n; idx--)
				latestFriendsQuizzes.remove(idx);
			return latestFriendsQuizzes;
		}
		// case polyfriendism:
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(friendsQuizzesCommand(nFriends));
			for(int i = 0; i < nFriends; i++)
				preparedStatement.setString(i + 1, friendNames.get(i));
			preparedStatement.setInt(nFriends + 1, n);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) 
				latestFriendsQuizzes.add(loadIntoQuiz(rs));
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return latestFriendsQuizzes;
	}

	private String friendsQuizzesCommand(int nFriends) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"SELECT "
				+ "quizzes.id, username, name, quizzes.description, is_random, is_one_page, immediate_correction, practice_mode, creation_time, time, max_score, category, is_random, is_one_page, immediate_correction, practice_mode, creation_time, time, max_score "
				+ "FROM quizzes "
				+ "LEFT JOIN users "
				+ "ON users.id = quizzes.creator_id "
				+ "LEFT JOIN tags "
				+ "ON tags.quiz_id = quizzes.id "
				+ "LEFT JOIN categories "
				+ "ON categories.id = quizzes.category_id "
				+ "WHERE "
				);
		for(int i = 0; i < nFriends - 1; i++)
			sb.append("creator_id = (SELECT id FROM users WHERE username LIKE ?) OR ");
		sb.append(
				"creator_id = (SELECT id FROM users WHERE username LIKE ?) "
				+ "ORDER BY creation_time DESC "
				+ "LIMIT ?;"
				);
		return sb.toString();
	}

	
	
	
	// review part
	
	@Override
	public void addReview(Review review) {
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"INSERT INTO reviews (user_id, quiz_id, date, rating, text) "
							+ "VALUES((SELECT id FROM users WHERE username LIKE ?), ?, ?, ?, ?);"
							);
			preparedStatement.setString(1, review.getUserName());
			preparedStatement.setInt(2, review.getQuizId());
			preparedStatement.setLong(3, review.getDate());
			preparedStatement.setInt(4, review.getRating());
			preparedStatement.setString(5, review.getText());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Review> getReviews(int quizId) {
		List<Review> reviews = new ArrayList<Review>();
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT users.username, reviews.text, reviews.quiz_id, reviews.date, reviews.rating "
							+ "FROM reviews "
							+ "LEFT JOIN users "
							+ "ON users.id = reviews.user_id "
							+ "WHERE reviews.quiz_id = ? "
							+ "ORDER BY rating DESC, date DESC;"
							);
			preparedStatement.setInt(1, quizId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) reviews.add(buildReviewFromRS(rs));
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviews;
	}

	private Review buildReviewFromRS(ResultSet rs) 
			throws SQLException {
		
		Review review = new Review(
				rs.getString("text"),
				rs.getLong("date"),
				rs.getString("username"),
				rs.getInt("quiz_id"),
				rs.getInt("rating"));
		return review;
	}

	@Override
	public double getAverageRating(int quizId) {
		double avg = 0;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT AVG(rating) AS avg "
							+ "FROM reviews "
							+ "WHERE quiz_id = ?;"
							);
			preparedStatement.setInt(1, quizId);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next(); avg = rs.getDouble("avg");
			preparedStatement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avg;
	}

	@Override
	public boolean reviewExists(String userName, int quizId) {
		boolean exists = false;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"SELECT COUNT(1) AS cnt "
							+ "FROM reviews "
							+ "WHERE quiz_id = ? "
							+ " AND "
							+ "user_id = (SELECT id FROM users WHERE username LIKE ?);"
							);
			preparedStatement.setInt(1, quizId);
			preparedStatement.setString(2, userName);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next(); exists = rs.getInt("cnt") > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;
	}

	@Override
	public void deleteReview(String userName, int quizId) {
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement preparedStatement =
					con.prepareStatement(
							"DELETE FROM reviews "
							+ "WHERE quiz_id = ? "
							+ " AND "
							+ "user_id = (SELECT id FROM users WHERE username LIKE ?);"
							);
			preparedStatement.setInt(1, quizId);
			preparedStatement.setString(2, userName);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

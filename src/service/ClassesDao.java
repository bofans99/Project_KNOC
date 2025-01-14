package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.Classes;
import util.MyBatisConnection;

public class ClassesDao {
	private final static String ns = "classes.";
	private static Map<String, Object> map = new HashMap<>();
	
	// 전체 classes 리스트 생성하여 한 페이지당 classes 객체를 12개씩 반환
	public List<Classes> classList(int pageInt, int limit) {
		SqlSession sqlSession = MyBatisConnection.getConnection();
		map.clear();
		map.put("start", (pageInt - 1) * limit + 1);
		map.put("end", pageInt * limit);
		try {
			return sqlSession.selectList(ns + "classList", map);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			MyBatisConnection.close(sqlSession);
		}
		
		return null;
	}
	
	// 신규 클래스 아이디 생성을 위해 시퀀스 다음 번호 반환
	public int newClassNum() {
		SqlSession sqlSession = MyBatisConnection.getConnection();
		
		try {
			return sqlSession.selectOne(ns + "newClassNum");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisConnection.close(sqlSession);
		}
		
		return 0;
	}
	
	// 신규 클래스 등록
	public int classUpload(Classes newClass) {
		SqlSession sqlSession = MyBatisConnection.getConnection();
		
		try {
			return sqlSession.insert(ns + "classUpload", newClass);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisConnection.close(sqlSession);
		}

		return 0;
	}

	// 클래스 아이디를 매개변수로 특정 클래스 조회
	public Classes classOne(String classId) {
		SqlSession sqlSession = MyBatisConnection.getConnection();

		try {
			return sqlSession.selectOne(ns + "classOne", classId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisConnection.close(sqlSession);
		}

		return null;
	}
	
	// 특정 컬럼 기준 내림차순으로 테이블을 정렬하여, 상위 4개 객체만 리스트로 반환
	public List<Classes> sortedClassList(String columnName) {
		SqlSession sqlSession = MyBatisConnection.getConnection();
		
		try {
			return sqlSession.selectList(ns + "sortedClassList", columnName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisConnection.close(sqlSession);
		}

		return null;
	}
	
	// 특정 카테고리에 맞는 클래스만 한 페이지당 classes 객체를 12개씩 반환
	public List<Classes> classifiedList(String value, int pageInt, int limit) {
		SqlSession sqlSession = MyBatisConnection.getConnection();
		
		map.clear();
		map.put("value", value);
		map.put("start", (pageInt - 1) * limit + 1);
		map.put("end", pageInt * limit);
		try {
			return sqlSession.selectList(ns + "classifiedList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisConnection.close(sqlSession);
		}

		return null;
	}
	
	// 검색어를 입력하여 해당 검색어를 제목에 포함하는 클래스만 한 페이지당 classes 객체를 12개씩 반환
	public List<Classes> searchedList(String value, int pageInt, int limit) {
		SqlSession sqlSession = MyBatisConnection.getConnection();
		String keyword = value.trim();
		
		map.put("value", keyword);
		map.put("start", (pageInt - 1) * limit + 1);
		map.put("end", pageInt * limit);
		try {
			return sqlSession.selectList(ns + "searchedList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisConnection.close(sqlSession);
		}

		return null;
	}

	// 검색어를 입력하여 해당 검색어를 제목에 포함하는 클래스만 리스트로 반환
	public int favoriteCntUp(String classId) {
		SqlSession sqlSession = MyBatisConnection.getConnection();
		
		try {
			return sqlSession.update(ns + "favoriteCntUp", classId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisConnection.close(sqlSession);
		}

		return 0;
	}
	
	public int favoriteCntDown(String classId) {
		SqlSession sqlSession = MyBatisConnection.getConnection();
		
		try {
			return sqlSession.update(ns + "favoriteCntDown", classId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyBatisConnection.close(sqlSession);
		}

		return 0;
	}

	/* DAO 테스트 코드
	public static void main(String[] args) {
		SqlSession sqlSession = MyBatisConnection.getConnection();
		String value = "  하하   ";
		String keyword = value.trim();
		int num = sqlSession.update(ns + "favoriteCntUp", "class24");
		MyBatisConnection.close(sqlSession);
		System.out.println(num);
	}
	*/
}

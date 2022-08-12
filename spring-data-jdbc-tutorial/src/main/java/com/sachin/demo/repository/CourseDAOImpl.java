package com.sachin.demo.repository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sachin.demo.entity.Course;
import com.sachin.demo.entity.CourseChangeBO;
import com.sachin.demo.entity.Student;
import com.sachin.demo.error.NoDataFoundException;

@Repository
@Transactional
public class CourseDAOImpl implements DAO<Course> {

	private static Logger LOGGER = LoggerFactory.getLogger(CourseDAOImpl.class);
	private JdbcTemplate jdbcTemplate;

	// As there is only one constructor, Spring will automatically inject
	// JdbcTemplate while executing this construction, no need to do explicit
	// autowiring. When we have a class with multiple constructors, we need to
	// explicitly add the @Autowired annotation to any one of the constructors so
	// that Spring knows which constructor to use to inject the dependencies.
	public CourseDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Method to fetch all courses
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<Course> list() {
		LOGGER.info("inside CourseDAOImpl.list() method");
		String sql = "select cr.courseId,cr.title,cr.credit,cr.courseMaterialId, cm.url, tc.teacherFirstName, tc.teacherLastName "
				+ "from course_jdbc_tb cr, coursematerial_jdbc_tb cm, teacher_jdbc_tb tc "
				+ "where cr.courseMaterialId=cm.courseMaterialId and cr.teacherId=tc.teacherId";
		List<Course> courseList = jdbcTemplate.query(sql, new CourseRowMapper());
		for (Course course : courseList) {
			String students_sql = "select st.* from student_course_mapping_tb scm,student_jdbc_tb st where \r\n"
					+ "scm.courseId =? and scm.studentId = st.studentId";
			Object[] inputs = new Object[] { course.getCourseId() };
			List<Student> listStudents = jdbcTemplate.query(students_sql, inputs, new StudentRowMapper());
			LOGGER.info("List of students :" + listStudents);
			course.setStudent(listStudents);
		}

		return courseList;
	}

	/**
	 * Method to fetch course based on course id
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Optional<Course> get(int id) {
		LOGGER.info("inside CourseDAOImpl.get() method");
		Object[] inputs = new Object[] { id };
		String sql = "select cr.courseId,cr.title,cr.credit,cr.courseMaterialId, cm.url, tc.teacherFirstName, tc.teacherLastName "
				+ "from course_jdbc_tb cr, coursematerial_jdbc_tb cm, teacher_jdbc_tb tc "
				+ "where cr.courseId=? and cr.courseMaterialId=cm.courseMaterialId and cr.teacherId=tc.teacherId";
		Course objCourse = jdbcTemplate.queryForObject(sql, inputs, new CourseRowMapper());
		if (objCourse != null) {
			String students_sql = "select st.* from student_course_mapping_tb scm,student_jdbc_tb st where \r\n"
					+ "scm.courseId =? and scm.studentId = st.studentId";
			List<Student> listStudents = jdbcTemplate.query(students_sql, inputs, new StudentRowMapper());
			objCourse.setStudent(listStudents);
		}

		return Optional.ofNullable(objCourse);
	}

	/**
	 * Method to create a course
	 */
	@Override
	public void create(Course t) {
		LOGGER.info("inside CourseDAOImpl.create() method");
		String sql_ins_coursematerial = "insert into coursematerial_jdbc_tb values (?,?)";
		String sql_ins_course = "insert into course_jdbc_tb values (?,?,?,?)";
		int insert_cm_count = jdbcTemplate.update(sql_ins_coursematerial, t.getCourseMaterial().getCourseMaterialId(),
				t.getCourseMaterial().getUrl());
		if (insert_cm_count == 1) {
			int insert_c_count = jdbcTemplate.update(sql_ins_course, t.getCourseId(), t.getTitle(), t.getCredit(),
					t.getCourseMaterial().getCourseMaterialId());
			if (insert_c_count == 1) {
				LOGGER.info("new course added with course name " + t.getTitle());
			}
		}

	}

	/**
	 * Method to update a course
	 */
	@Override
	public void update(CourseChangeBO t, int id) {
		LOGGER.info("inside CourseDAOImpl.update() method");
		String sql = "update course_jdbc_tb set title=?, credit=?, coursematerialid=? where courseid=?";
		Object[] inputs = new Object[] { t.getTitle(), t.getCredit(), t.getCourseMaterialId(), id };
		int rowsImpacted = jdbcTemplate.update(sql, inputs);
		if (rowsImpacted > 0) {
			LOGGER.info("course updated having course id " + id);
		} else {
			throw new NoDataFoundException("No such data present");
		}

	}

	/**
	 * Method to delete a course
	 */
	@Override
	public void delete(int id) {
		LOGGER.info("inside CourseDAOImpl.delete() method");
		Object[] inputs = new Object[] { id };
		String sql = "delete from course_jdbc_tb where courseid=?";
		int rowsImpacted = jdbcTemplate.update(sql, inputs);
		if (rowsImpacted > 0) {
			LOGGER.info("course deleted having course id " + id);
		} else {
			throw new NoDataFoundException("No such data present");
		}
	}

}

import dao.StudentDao;
import dao.TeacherDao;
import entities.Student;
import entities.Teacher;
import util.JpaUtil;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;


public class App {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        StudentDao studentDao = new StudentDao(entityManager);
        TeacherDao teacherDao = new TeacherDao(entityManager);

        entityManager.getTransaction().begin();

        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Karim");
        teacher1.setLastName("Mohammadi");
        teacher1.setTeacherCode("T1");
        teacher1.setSalary(2500.0);
        teacher1.setBirthday(parseDate("1980-10-22"));
        teacherDao.save(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Karim2");
        teacher2.setLastName("Mohammadi2");
        teacher2.setTeacherCode("T2");
        teacher2.setSalary(3000.0);
        teacher2.setBirthday(parseDate("1980-10-22"));

        teacherDao.save(teacher2);


        Student student = new Student();
        student.setFirstName("Ali");
        student.setLastName("Karimi");
        student.setStudentCode("S1");
        student.setBirthday(parseDate("1996-05-06"));
        Set<Teacher> teachers = new HashSet<Teacher>();
        teachers.add(teacher1);
        teachers.add(teacher2);
        student.setTeachers(teachers);

        studentDao.save(student);


        entityManager.getTransaction().commit();

        entityManager.close();
        JpaUtil.shutdown();
    }

    private static Date parseDate(String date){
        try {
            return new Date(DATE_FORMAT.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

}

import dao.AddressDao;
import dao.StudentDao;
import dao.TeacherDao;
import entities.Address;
import entities.Student;
import entities.Teacher;
import util.JpaUtil;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;
public class App {

    private static StudentDao studentDao;
    private static TeacherDao teacherDao;
    private static AddressDao addressDao;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        initializeDao(entityManager);

        entityManager.getTransaction().begin();


        createTeacher("Kazem", "Asadi", "t2", 30000.0, "1995-06-22",
         createAddress(7778899, "tehran",
                 "tehran", "kh lesani- k jam", "356-789-55"));


        entityManager.getTransaction().commit();

        entityManager.close();
        JpaUtil.shutdown();
    }

    private static void initializeDao(EntityManager entityManager) {
        studentDao = new StudentDao(entityManager);
        teacherDao = new TeacherDao(entityManager);
        addressDao = new AddressDao(entityManager);
    }

    private static Student createStudent(String fName, String lName,
             String code, String date, Set<Teacher> teachers, Set<Address> addresses){
        Student student = new Student();
        student.setFirstName(fName);
        student.setLastName(lName);
        student.setStudentCode(code);
        student.setBirthday(parseDate(date));
        student.setTeachers(teachers);
        student.setAddresses(addresses);
        studentDao.save(student);
        return student;
    }

    private static Teacher createTeacher(String fName, String lName,
         String code, Double salary, String date, Address address){
        Teacher teacher = new Teacher();
        teacher.setFirstName(fName);
        teacher.setLastName(lName);
        teacher.setTeacherCode(code);
        teacher.setSalary(salary);
        teacher.setBirthday(parseDate(date));
        teacher.setAddress(address);
        teacherDao.save(teacher);
        return teacher;
    }

    private static Address createAddress(Integer number, String state,
               String city, String postalAddress, String postalCode){
        Address address = new Address();
        address.setNumber(number);
        address.setState(state);
        address.setCity(city);
        address.setPostalAddress(postalAddress);
        address.setPostalCode(postalCode);
        addressDao.save(address);
        return address;
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

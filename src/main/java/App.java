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
import java.util.HashSet;
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

        createTeacher("Ali", "Karimi", "t1", 25000.0, "1990-11-02",
                createAddress(1112233, "tehran",
                        "tehran", "kh rahim- k yas", "444-189-33"));

        createTeacher("Kazem", "Asadi", "t2", 30000.0, "1995-06-22",
         createAddress(7778899, "tehran",
                 "tehran", "kh lesani- k jam", "356-789-55"));

        Set<Teacher> teachers = new HashSet<Teacher>();
        teachers.add(teacherDao.load(4));
        teachers.add(teacherDao.load(5));

        createAddress(6644555, "tehran",
                "tehran", "kh ebadi- k sadegh", "222-336-97");

        createAddress(8822457, "tehran",
                "tehran", "kh sharifi- k adel", "695-284-37");

        Set<Address> addresses = new HashSet<Address>();
        addresses.add(addressDao.load(6));
        addresses.add(addressDao.load(7));
        createStudent("Hasan", "Soltani",
                "s1", "2000-10-05", teachers, addresses);


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

    private static Student returnStudent(Integer id){
        return studentDao.load(id);
    }

    private static Teacher returnTeacher(Integer id){
        return teacherDao.load(id);
    }

    private static Address returnAddress(Integer id){
        return addressDao.load(id);
    }

    private static void deleteStudent(Integer id){
        if (studentDao.load(id) != null) {
            Student student = studentDao.load(id);
            studentDao.delete(student);
        }
        else System.out.println("Doesn't exist student .");
    }

    private static void deleteTeacher(Integer id){
        if (teacherDao.load(id) != null) {
            Teacher teacher = teacherDao.load(id);
            teacherDao.delete(teacher);
        }
        else System.out.println("Doesn't exist teacher .");
    }

    private static void deleteAddress(Integer id){
        if (addressDao.load(id) != null) {
            Address address = addressDao.load(id);
            addressDao.delete(address);
        }
        else System.out.println("Doesn't exist address .");
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

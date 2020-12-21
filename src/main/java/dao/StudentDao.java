package dao;

import entities.Student;

import javax.persistence.EntityManager;

public class StudentDao extends EntityDao<Student, Integer> {

    public StudentDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Student> getEntityClass() {
        return Student.class;
    }
}

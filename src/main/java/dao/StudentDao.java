package dao;

import entities.Student;

import javax.persistence.EntityManager;

public class StudentDao extends EntityDao<Student> {

    public StudentDao(EntityManager entityManager) {
        super(entityManager);
    }
}

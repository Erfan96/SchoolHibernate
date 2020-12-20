package dao;

import entities.Teacher;

import javax.persistence.EntityManager;

public class TeacherDao extends EntityDao<Teacher>{

    public TeacherDao(EntityManager entityManager) {
        super(entityManager);
    }
}

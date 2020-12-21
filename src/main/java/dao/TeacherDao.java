package dao;

import entities.Teacher;

import javax.persistence.EntityManager;

public class TeacherDao extends EntityDao<Teacher, Integer>{

    public TeacherDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }
}

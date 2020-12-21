package dao;

import javax.persistence.EntityManager;

public abstract class EntityDao<T, U> {
   private EntityManager entityManager;

    public EntityDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(T entity){
       entityManager.persist(entity);
    }

    public T load(U id){
        return entityManager.find(getEntityClass(), id);
    }

    public abstract Class<T> getEntityClass();
}

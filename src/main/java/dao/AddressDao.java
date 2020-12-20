package dao;

import entities.Address;
import javax.persistence.EntityManager;

public class AddressDao extends EntityDao<Address>{

    public AddressDao(EntityManager entityManager) {
        super(entityManager);
    }
}

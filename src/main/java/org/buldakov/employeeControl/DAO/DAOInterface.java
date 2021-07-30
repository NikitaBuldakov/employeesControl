package org.buldakov.employeeControl.DAO;

import java.util.List;

public interface DAOInterface<E>{
    E findById(long id);

    void save(E e);

    void update(E e);

    void delete(E e);

    List<E> findAll();
}

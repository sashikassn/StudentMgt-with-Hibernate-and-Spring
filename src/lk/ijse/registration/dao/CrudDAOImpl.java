package lk.ijse.registration.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CrudDAOImpl<T,ID> implements CrudDAO<T,ID>{

    @Autowired
    private SessionFactory sessionFactory;

//    protected Session session;
    private Class<T> entity;

    public CrudDAOImpl(){
        entity = (Class<T>)(((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void save(T entity) throws Exception {
        getSession().persist(entity);
    }

    @Override
    public void delete(ID id) throws Exception {
        T t = getSession().find(entity, id);
        getSession().remove(t);
    }


    @Override
    public void update(T entity) throws Exception {
        getSession().persist(entity);
    }

    @Override
    public List<T> getAll() throws Exception {
        return getSession().createQuery("FROM "+ entity.getName()).list();
    }

    @Override
    public T find(ID id) throws Exception {
       return getSession().find(entity, id);

    }

//    @Override
//    public void setSession(Session session) {
//    this.session = session;
//    }
}
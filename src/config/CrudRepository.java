package config;


import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public abstract class CrudRepository<Entity> {

    private Session getSession(){
        return HibernateUtil.getSession();
    }

    protected abstract Class<Entity> getEntityClass();


    //CRUD

    //C: Create (save)
    public void save(Entity entity){
        getSession().beginTransaction();
        getSession().save(entity);
        getSession().getTransaction().commit();
    }

    //R: Read (get)
    public Entity findById(int id){
        getSession().beginTransaction();
        Entity entity = getSession().get(getEntityClass(), id);
        getSession().getTransaction().commit();
        return entity;
    }

    //U: Update
    public Entity update(Entity entity){
        getSession().beginTransaction();
        getSession().update(entity);
        getSession().getTransaction().commit();
        return entity;
    }

    //D: Delete (remove)
    public void remove(Entity entity){
        getSession().beginTransaction();
        getSession().remove(entity);
        getSession().getTransaction().commit();
    }

    //some other useful methods

    //find all records of a table
    public List<Entity> findAll(){
        getSession().beginTransaction();
        Query<Entity> query = getSession()
                .createQuery("from " + getEntityClass().getName(), getEntityClass());
        List<Entity> entities = query.list();
        getSession().getTransaction().commit();
        return entities;
    }

    //find all records between two rows
    public List<Entity> findAll(int from, int to){
        getSession().beginTransaction();
        Query<Entity> query = getSession()
                .createQuery("from" + getEntityClass().getName(), getEntityClass());
        query.setFirstResult(from);
        query.setMaxResults(to);
        List<Entity> entities =query.list();
        getSession().getTransaction().commit();
        return entities;
    }

    //remove an entity by id
    public void removeById(int id){
        Entity entity = findById(id);
        if (entity != null){
            getSession().beginTransaction();
            getSession().remove(entity);
            getSession().getTransaction().commit();
        }
    }
}
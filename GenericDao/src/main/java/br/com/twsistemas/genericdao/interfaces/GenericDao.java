package br.com.twsistemas.genericdao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author José
 */
public interface GenericDao<T extends DatabaseEntity> {
    
    public boolean save(T entity) throws SQLException;
    public T saveWithReturn(T entity) throws SQLException;
    public boolean delete(T entity) throws SQLException;
    public T findById(Object... id) throws SQLException ;
    public List<T> findAll() throws SQLException ;
    public T extract(ResultSet rs) throws SQLException ;
    public void prepareInsert(T entity) throws SQLException;
    public void prepareUpdate(T entity) throws SQLException;
    public String getSqlSelect();
    public String getSqlWherePk();
    public String getSqlInsert();
    public String getSqlUpdate();
    public void closeConnections();
    
}

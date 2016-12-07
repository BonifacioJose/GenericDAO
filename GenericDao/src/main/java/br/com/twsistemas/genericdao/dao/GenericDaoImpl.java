package br.com.twsistemas.genericdao.dao;

import br.com.twsistemas.genericdao.interfaces.DatabaseEntity;
import br.com.twsistemas.genericdao.interfaces.GenericDao;
import br.com.twsistemas.genericdao.util.ArrayUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José
 */
public abstract class GenericDaoImpl<T extends DatabaseEntity> implements GenericDao<T> {

    private final Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;

    private final String table;
    private final String[] pkColumns;
    private final String[] columns;

    private String sqlSelect;
    private String sqlInsert;
    private String sqlUpdate;
    private String sqlWherePk;

    public GenericDaoImpl(Connection conexao, String table, String[] pkColumns, String[] columns) {
        this.conexao = conexao;
        this.table = table;
        this.pkColumns = pkColumns;
        this.columns = columns;
        setSqlSelect();
        setWherePk();
        setInsertSql();
        setUpdateSql();
    }

    @Override
    public boolean save(T entity) throws SQLException {
        if (entity.isNew()) {
            ps = conexao.prepareStatement(getSqlInsert());
            prepareInsert(entity);
        } else {
            ps = conexao.prepareStatement(getSqlUpdate());
            prepareUpdate(entity);
        }
        return ps.execute();
    }

    @Override
    public T findById(Object... id) throws SQLException {
        ps = conexao.prepareStatement(getSqlSelect() + getSqlWherePk());
        int index = 1;
        for (Object object : id) {
            ps.setObject(index++, object);
        }
        System.out.println("FindById: " + getSqlSelect() + getSqlWherePk());
        rs = ps.executeQuery();
        rs.next();
        return extract(rs);
    }

    @Override
    public List<T> findAll() throws SQLException {
        ps = conexao.prepareStatement(getSqlSelect());
        List<T> results = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            results.add(extract(rs));
        }
        return results;
    }

    @Override
    public boolean delete(T entity) throws SQLException {
        String sql = "delete from " + table + sqlWherePk;
        return ps.executeUpdate(sql) == 1;
    }

    @Override
    public String getSqlSelect() {
        return sqlSelect;
    }

    @Override
    public String getSqlInsert() {
        return sqlInsert;
    }

    @Override
    public String getSqlUpdate() {
        return sqlUpdate;
    }

    @Override
    public String getSqlWherePk() {
        return sqlWherePk;
    }

    @Override
    public void closeConnections() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenericDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setSqlSelect() {
        String columnsString = "";
        for (String column : this.pkColumns) {
            if (!columnsString.isEmpty()) {
                columnsString += ",";
            }
            columnsString += table + "." + column + " as " + table + "_" + column;
        }
        for (String column : this.columns) {
            if (!columnsString.isEmpty()) {
                columnsString += ",";
            }
            columnsString += table + "." + column + " as " + table + "_" + column;
        }
        String sql = "select "
                + columnsString
                + " from " + table + " as " + table;
        System.out.println("Select: " + sql);
        sqlSelect = sql;
    }

    private void setWherePk() {
        String where = "";
        if (pkColumns.length > 1) {
            for (int i = 0; i < pkColumns.length; i++) {
                if (!where.isEmpty()) {
                    where += ",";
                } else {
                    where += " where ";
                }
                where += table + "." + pkColumns[i] + " = ?";
            }
        } else {
            where = " where " + table + "." + pkColumns[0] + " = ?";
        }
        sqlWherePk = where;
    }

    private void setInsertSql() {
        String sql = "insert into " + table + "("
                + ArrayUtil.arrayToString(columns) + ") values ("
                + ArrayUtil.arrayToString(ArrayUtil.replaceArrayValues(columns, "?")) + ") "
                + " returning " + ArrayUtil.arrayToString(pkColumns);
        System.out.println("Insert: " + sql);
        sqlInsert = sql;
    }

    private void setUpdateSql() {
        String sql = "update " + table + " set ";
        String[] colunasUpdate = new String[columns.length];
        for (int i = 0; i < pkColumns.length; i++) {
            colunasUpdate[i] = columns[i] + " = ?";
        }
        for (int i = 0; i < columns.length; i++) {
            colunasUpdate[i] = columns[i] + " = ?";
        }
        sql += ArrayUtil.arrayToString(colunasUpdate);
        sql += sqlWherePk;
        System.out.println("Update: " + sql);
        sqlUpdate = sql;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

}

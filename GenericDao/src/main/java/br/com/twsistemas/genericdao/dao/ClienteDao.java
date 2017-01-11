package br.com.twsistemas.genericdao.dao;

import br.com.twsistemas.genericdao.entity.Cliente;
import br.com.twsistemas.genericdao.interfaces.GenericDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author José
 */
public class ClienteDao extends GenericDaoImpl<Cliente> implements GenericDao<Cliente> {

    public ClienteDao(Connection conexao) {
        super(conexao,
                "cliente",
                (new String[]{"id"}),
                (new String[]{"nome", "email", "ativo"}));
    }

    @Override
    public void prepareInsert(Cliente entity) throws SQLException {
        getPs().setString(1, entity.getNome());
        getPs().setString(2, entity.getEmail());
        getPs().setBoolean(3, entity.getAtivo());
        getPs().setLong(4, entity.getIdUsuarioCadastro());
    }

    @Override
    public void prepareUpdate(Cliente entity) throws SQLException {
        getPs().setString(1, entity.getNome());
        getPs().setString(2, entity.getEmail());
        getPs().setBoolean(3, entity.getAtivo());
        getPs().setLong(4, entity.getIdUsuarioCadastro());
        getPs().setLong(5, entity.getId());
    }

    @Override
    public Cliente extract(ResultSet rs) throws SQLException {
        Cliente entity = new Cliente();
        entity.setId(rs.getLong("cliente_id"));
        entity.setNome(rs.getString("cliente_nome"));
        entity.setEmail(rs.getString("cliente_email"));
        entity.setAtivo(rs.getBoolean("cliente_ativo"));
        entity.setIdUsuarioCadastro(rs.getLong("cliente_idUsuarioCadastro"));
        return entity;
    }

    @Override
    public Cliente saveWithReturn(Cliente entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

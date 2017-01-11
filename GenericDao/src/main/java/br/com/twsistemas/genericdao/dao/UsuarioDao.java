package br.com.twsistemas.genericdao.dao;

import br.com.twsistemas.genericdao.entity.Usuario;
import br.com.twsistemas.genericdao.interfaces.GenericDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author José
 */
public class UsuarioDao extends GenericDaoImpl<Usuario> implements GenericDao<Usuario> {

    public UsuarioDao(Connection conexao) {
        super(conexao,
                "usuario",
                (new String[]{"id"}),
                (new String[]{"nome", "email", "ativo", "idusuariocadastro"}));
    }

    @Override
    public Usuario saveWithReturn(Usuario entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prepareInsert(Usuario entity) throws SQLException {
        getPs().setString(1, entity.getNome());
        getPs().setString(2, entity.getEmail());
        getPs().setBoolean(3, entity.getAtivo());
    }

    @Override
    public void prepareUpdate(Usuario entity) throws SQLException {
        getPs().setString(1, entity.getNome());
        getPs().setString(2, entity.getEmail());
        getPs().setBoolean(3, entity.getAtivo());
        getPs().setLong(4, entity.getId());
    }

    @Override
    public Usuario extract(ResultSet rs) throws SQLException {
        Usuario entity = new Usuario();
        entity.setId(rs.getLong("usuario_id"));
        entity.setNome(rs.getString("usuario_nome"));
        entity.setEmail(rs.getString("usuario_email"));
        entity.setAtivo(rs.getBoolean("usuario_ativo"));
        return entity;
    }

}

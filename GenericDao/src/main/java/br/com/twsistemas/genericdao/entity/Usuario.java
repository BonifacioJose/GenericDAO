package br.com.twsistemas.genericdao.entity;

import br.com.twsistemas.genericdao.interfaces.DatabaseEntity;

/**
 *
 * @author José
 */
public class Usuario implements DatabaseEntity {

    private Long id;
    private String nome;
    private String email;
    private Boolean ativo;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String[] pk() {
        String[] pk = {""};
        if (id != null) {
            pk[0] = String.valueOf(id);
            return pk;
        }
        return null;
    }

    @Override
    public boolean isNew() {
        return pk() == null;
    }

}

package br.com.twsistemas.genericdao.interfaces;

import java.io.Serializable;

/**
 *
 * @author José
 */
public interface DatabaseEntity extends Serializable {
    
    public String[] pk();
    public boolean isNew();
}

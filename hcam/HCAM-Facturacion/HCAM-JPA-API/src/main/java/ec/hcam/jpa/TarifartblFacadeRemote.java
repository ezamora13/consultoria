/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.hcam.jpa;

import ec.hcam.entities.Tarifartbl;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jochoa
 */
@Remote
public interface TarifartblFacadeRemote {

    void create(Tarifartbl tarifartbl);

    void edit(Tarifartbl tarifartbl);

    void remove(Tarifartbl tarifartbl);

    Tarifartbl find(Object id);

    List<Tarifartbl> findAll();

    List<Tarifartbl> findRange(int[] range);

    int count();
    
}

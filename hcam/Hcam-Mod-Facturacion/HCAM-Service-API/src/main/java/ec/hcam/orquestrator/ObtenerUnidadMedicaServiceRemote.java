/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.hcam.orquestrator;

import ec.hcam.data.CodigoUnidMedica;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author programacion
 */
@Remote
public interface ObtenerUnidadMedicaServiceRemote {
    List<CodigoUnidMedica> obtenerNivelTarifario();
}

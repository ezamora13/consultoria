/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
* Copyright 2016 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR
* Todos los derechos reservados
*/
package ec.gob.iessHcam.ws.gestionAs400;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>
 *   Incluir aqui la descripcion de la clase.
 * </b>
 *
 * @author Jorge Zúñiga
 * <p>[$Author: Jorge Zúñiga $, $Date: 09-jun-2016]</p>
 */
public class DisponibilidadRespuesta implements Serializable {

    private static final long serialVersionUID = -4002897314076938975L;

    @Getter
    @Setter
    private String codigo;
    
    @Getter
    @Setter
    private String cuerpo;
    
    @Getter
    @Setter
    private List<DisponibilidadSalida> cuerpoLista;
    @Getter
    @Setter
    private String mensaje;

    public DisponibilidadRespuesta() {
    }

}


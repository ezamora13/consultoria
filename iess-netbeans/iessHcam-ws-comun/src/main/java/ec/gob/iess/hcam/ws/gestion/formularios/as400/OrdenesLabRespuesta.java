/*
 * Copyright 2017 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR
 * Todos los derechos reservados
 */
package ec.gob.iess.hcam.ws.gestion.formularios.as400;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>clase respuesta con la lista de ordenesSalida.</b>
 *
 * @author jsaltosb@iess.gob.ec
 * <p>
 * [Author: Gabriel Saltos, Date: 11/05/2017]
 * </p>
 */
public class OrdenesLabRespuesta implements Serializable {

    private static final long serialVersionUID = 4434117246185725664L;

    @Getter
    @Setter
    private String codigo;

    @Getter
    @Setter
    private List<OrdenesLabSalida> cuerpoLista;

    @Getter
    @Setter
    private String mensaje;

    /**
     * <b>método constructor.</b>
     * <p>
     * [Autor: Gabriel Saltos, Date: 11/05/2017]
     * </p>
     *
     */
    public OrdenesLabRespuesta() {
        //vacio
    }

}

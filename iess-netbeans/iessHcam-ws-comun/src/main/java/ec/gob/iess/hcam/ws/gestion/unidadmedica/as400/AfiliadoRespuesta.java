/*
 * Copyright 2017 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR
 * Todos los derechos reservados
 */
package ec.gob.iess.hcam.ws.gestion.unidadmedica.as400;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>clase .</b>
 *
 * @author jsaltosbm@iess.gob.ec
 * <p>
 * [Author: Gabriel Saltos, Date: 28/06/2017]
 * </p>
 */
public class AfiliadoRespuesta implements Serializable {

    private static final long serialVersionUID = 397796815810014204L;
    @Getter
    @Setter
    private String codigo;

    @Getter
    @Setter
    private List<AfiliadoSalida> cuerpoLista;

    @Getter
    @Setter
    private String mensaje;

    /**
     * <b>método .</b>
     * <p>
     * [Autor: Gabriel Saltos, Date: 28/06/2017]
     * </p>
     *
     */
    public AfiliadoRespuesta() {
        //vacio
    }

}

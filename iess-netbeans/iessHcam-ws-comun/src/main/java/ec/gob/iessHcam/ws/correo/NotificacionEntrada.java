/*
* Copyright 2016 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR
* Todos los derechos reservados
 */
package ec.gob.iessHcam.ws.correo;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ezamora
 */
public class NotificacionEntrada implements Serializable {

    private static final long serialVersionUID = -1422461130529850817L;

    @Getter
    @Setter
    private String codigoNotificacion;
    @Getter
    @Setter
    private List<DatoCorreo> correoEntradas;

    /**
     *
     * <b>
     * Constructor
     * </b>
     * <p>
     * [$Author: ezamoram $, $Date: 14/09/2016]</p>
     */
    public NotificacionEntrada() {
    }

}
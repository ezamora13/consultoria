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
 * <b>Respuesta Datos del paciente de la orden.</b>
 *
 * @author jsaltosb@iess.gob.ec
 * <p>
 * [Author: Gabriel Saltos, Date: 15/03/2017]
 * </p>
 */
public class PacienteRespuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private String codigo;

    @Getter
    @Setter
    private List<PacienteSalida> cuerpoLista;

    @Getter
    @Setter
    private String mensaje;

    /**
     * <b>Método constructor.</b>
     * <p>
     * [Autor: Gabriel Saltos, Date: 15/03/2017]
     * </p>
     *
     */
    public PacienteRespuesta() {

    }

}
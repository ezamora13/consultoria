package ec.gob.iessHcam.ws.nutricion;

/*
 * Copyright 2016 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR
 * Todos los derechos reservados
 */
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>
 *   Este pojo define la respuesta del método getTransaccionesxBodega del ws.
 * </b>
 *
 * @author fsalazarn@iess.gob.ec
 * <p>[$Author: fsalazarn@iess.gob.ec $, $Date: 30/09/2016]</p>
 */

public class SaldosxBodegaRespuesta implements Serializable {

    private static final long serialVersionUID = 111518189642740343L;
    @Getter
    @Setter
    private String codigo;
    @Getter
    @Setter
    private List<SaldosxBodegaSalida> cuerpo;
    @Getter
    @Setter
    private String mensaje;
        
    public SaldosxBodegaRespuesta() {
    }

    public SaldosxBodegaRespuesta(String codigo, List<SaldosxBodegaSalida> cuerpo, String mensaje) {
        this.codigo = codigo;
        this.cuerpo = cuerpo;
        this.mensaje = mensaje;
    }
    
}
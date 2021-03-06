/*
 * Copyright 2016 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR
 * Todos los derechos reservados
 */
package ec.gob.iessHcam.componente.seguridad.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>
 * Incluir aqui la descripcion de la clase.
 * </b>
 *
 * @author ezamora
 * <p>
 * [$Author: ezamora $, $Date: 02/02/2016]</p>
 */
@Entity
@Table(name = "seg_rolxmenu_tbl", schema = "seguridad")
public class Rolxmenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "rm_id_rolxmenu")
    @SequenceGenerator(name = "seguridad.seg_rolxmenu_tbl_seq", schema = "seguridad", sequenceName = "seguridad.seg_rolxmenu_tbl_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seguridad.seg_rolxmenu_tbl_seq")
    @Getter
    @Setter
    private Integer rmIdRolxmenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "rm_estado")
    @Getter
    @Setter
    private String rmEstado;
    @JoinColumn(name = "me_id_opcion", referencedColumnName = "me_id_opcion")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Menu meIdOpcion;
    @JoinColumn(name = "ro_id_rol", referencedColumnName = "ro_id_rol")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Roles roIdRol;

    public Rolxmenu() {
    }

}

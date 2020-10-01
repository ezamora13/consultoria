/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.hcam.orquestrator;

import ec.hcam.data.Dependencia;
import ec.hcam.data.FarmacosPres;
import ec.hcam.entity.Cabplantbl;
import ec.hcam.entity.Detplantbl;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.BEAN;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 *
 * @author Programacion
 */
@Stateless
@TransactionManagement(BEAN)
public class FarmacosAplicadosService extends Basica implements FarmacosAplicadosServiceRemote {

    @Override
    @Asynchronous
    public Future obtenerRegistrosF(String iounme, Integer iofeor, Integer nivel,Integer unidpk,Integer ubicapk) {
        utx = ctx.getUserTransaction();
        if (bag != null) {
            bag.clear();
        }
        if (bagDependecia != null) {
            bagDependecia.clear();
        }
        if (listaDetalles != null) {
            listaDetalles.clear();
        }
        System.out.println("Hora Inicio FarmacosAplicados:" + imprimirHora().toString());
        cargarDependencias(iounme, iofeor);
        List<Dependencia> lstDependencias = (List<Dependencia>) bagDependecia.get("lstDependencias");        
        
        for (Dependencia depend : lstDependencias) {
            List<FarmacosPres> tasksf;
            String mesSt = String.valueOf(iofeor).substring(4, 6);
            String diaSt = String.valueOf(iofeor).substring(6, 8);
            String anioSt = String.valueOf(iofeor).substring(0, 4);

            Integer mesI = Integer.parseInt(mesSt);
            Integer diaI = Integer.parseInt(diaSt);
            Integer anioI = Integer.parseInt(anioSt);

            existenciaCabecera(mesI, diaI, anioI,unidpk,ubicapk);

            tasksf = obtenerTodosLosFarmacos(iounme, String.valueOf(iofeor), depend.getIR2DEP());
        }
        try {
            utx.begin();
            for (Detplantbl detplantbl : listaDetalles) {
                detalleService.createDetplantbl(detplantbl);
            }
            utx.commit();
        } catch (NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException ex) {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(FarmacosAplicadosService.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(FarmacosAplicadosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (bag != null) {
            bag.clear();
        }
        if (bagDependecia != null) {
            bagDependecia.clear();
        }
        if (listaDetalles != null) {
            listaDetalles.clear();
        }
        System.out.println("Hora Fin FarmacosAplicados:" + imprimirHora().toString());

        return new AsyncResult("OK");
    }

    @Override
    public List<FarmacosPres> obtenerTodosLosFarmacos(String iounme, String iofeor, Double depend) {
        
        String sqlString1 = "SELECT I1SBTS PLASSE, I1TISE PLATSE, I1UNME PLAUBME,I1HICL PLAHIC, I1COMA PLACOD, I1CANT PLACAN, b.isddep PALDEX, c.ffprec ValorUnitario , I1FEAC FECHA_ORDEN, TRIM(D.CODESC) ||' '|| TRIM (F.PSDESC) PLACOP,'' PLACOX,'FAR' PLATIP,'SECUENCIAL' PLASEC FROM ceddta.iomu10 a inner join ceddta.iser01 b on a.i1unme=b.isdunm and a.i1tise=b.isdtip and a.i1sbts=b.isdstp and a.i1cobo=b.isdbod left join invlib.invf35 c on a.i1unme=c.FFUNME and substr(i1coma, 1 , 10)=FFTIPO||FFGRUP||FFSUBG||FFORDI INNER JOIN INVLIB.INVF11 D ON a.i1unme = D.COCDUN AND i1coma = d.cotipo||D.COGRUP||D.COSUBG||D.COORDI||d.coespe JOIN INVLIB.INVF10 E ON D.COCDUN=E.GECDUN AND d.cotipo=E.GETIPO AND D.COGRUP=E.GEGRUP AND D.COSUBG=E.GESUBG AND D.COORDI=E.GEORDI JOIN INVLIB.INVF13 F ON E.GECDUN = F.PSCDUN AND E.GETIPR = F.PSTIPO AND E.GECOPR=F.PSCODI WHERE I1UNME =:iounme and I1FEAC=:iofeor and I1NUTR >0 and I1TIPM ='321' and b.isddep=:iodep";
        Map<String, Object> params = new HashMap<>();
        params.put("iounme", iounme);
        params.put("iofeor", iofeor);
        params.put("iodep", depend);
        List<FarmacosPres> farmacosList = queryExecutor.executeQuery(sqlString1, params, FarmacosPres.class);

        
        for (FarmacosPres farmaco : farmacosList) {
            registrarDetallePlanillaje(farmaco, iounme, iofeor);
            
        }
        return farmacosList;
    }

    @Override
    public void registrarDetallePlanillaje(FarmacosPres registro, String uniMed, String iofeor) {

        Detplantbl detalle = new Detplantbl();
        //Datos Cabecera        
        Cabplantbl cab = (Cabplantbl) bag.get("cabplantbl");
        // TODO Solicitar a bdd que cree la BDD SEQUENCE para poder autogenrer secuenciales

        //Empieza        
        Calendar c1 = Calendar.getInstance();

        detalle.setReidpk(2);
        detalle.setNumplad(cab.getNumplanilla());
        detalle.setCoditem(registro.getPLACOD());
        detalle.setHistoCli(String.valueOf(registro.getPLAHIC().intValue()));
        detalle.setFechaReg(c1.getTime());
        detalle.setDescItem(registro.getPLACOP()); //imp
        detalle.setDepRea(NombreDependenciaSQL(BigDecimal.valueOf(registro.getPALDEX()).toBigInteger()));
        detalle.setCoddepsol(BigDecimal.valueOf(registro.getPALDEX()).toBigInteger());
        detalle.setCoddeprea(BigDecimal.valueOf(registro.getPALDEX()).toBigInteger());
        detalle.setTipSer(registro.getPLATSE().intValue());  //ver este campo
        detalle.setSubtipser(registro.getPLASSE().intValue());
        detalle.setCantidad(registro.getPLACAN().intValue());
        detalle.setValorUnit(registro.getVALORUNITARIO()); //ver este campo
        detalle.setValorTotal(registro.getPLACAN().intValue() * registro.getVALORUNITARIO());
        detalle.setNumOrden(1); //ver este campo
        detalle.setTipoRubro("FAH"); //Tipo de servicio
        //DATOS DEL MEDICO
        //No contiene nombre de medico ni codigo
        detalle.setCodMedico(0);
        detalle.setNomMedico("");
        //En farmacos no se ingresan diagnosticos

        detalle.setTimeAntestesia(Short.parseShort("0"));  //no aplica anestencia en farmacos
        detalle.setCodDerivacion("CD"); //ver este campo
        detalle.setSecDerivacion(Short.parseShort("0")); //ver este campo
        detalle.setContingenCubre(0);
        //detalle = registrarDetallePaciente(detalle, uniMed, registro.getPLAHIC().toString());

        cab.setDetplantblList(null);
        detalle.setCpidpk(cab);
        //Termina

        

        // TODO Completar el objeto detalle para que pueda ser almacenado correctamente sin restriccionescab.setDetplantblList(null);
        cab.setDetplantblList(null);
        detalle.setCpidpk(cab);

        listaDetalles.add(detalle);
        registrarPorBloque(listaDetalles);
        
    }

    public void cargarDependencias(String ir2unm, Integer ir2cit) {
        String sqlString1 = "SELECT  DISTINCT b.isddep IR2DEP FROM ceddta.iomu10 a inner join ceddta.iser01 b on a.i1unme=b.isdunm and a.i1tise=b.isdtip and a.i1sbts=b.isdstp and a.i1cobo=b.isdbod left join invlib.invf35 c on a.i1unme=c.FFUNME and substr(i1coma, 1 , 10)=FFTIPO||FFGRUP||FFSUBG||FFORDI INNER JOIN INVLIB.INVF11 D ON a.i1unme = D.COCDUN AND i1coma = d.cotipo||D.COGRUP||D.COSUBG||D.COORDI||d.coespe WHERE I1UNME =:iounme and I1FEAC=:iofeor and I1NUTR >0 and I1TIPM ='321'";
        Map<String, Object> params = new HashMap<>();
        params.put("iounme", ir2unm);
        params.put("iofeor", ir2cit);
        List<Dependencia> informacionDependenciaList = queryExecutor.executeQuery(sqlString1, params, Dependencia.class);
        if (bagDependecia == null) {
            bagDependecia = new HashMap<>();
        }
        bagDependecia.clear();
        bagDependecia.put("lstDependencias", informacionDependenciaList);
    }
}

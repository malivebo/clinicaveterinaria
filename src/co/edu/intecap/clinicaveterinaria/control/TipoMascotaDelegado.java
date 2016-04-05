/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.TipoMascotaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.TipoMascotaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author capacitaciones
 */
public class TipoMascotaDelegado {
    //atributo de clase que son constantes
    // su valor como objeto no puede cambiar
    private final JPanel contenedor;
    private final TipoMascotaDao tipomascotaDao;

    public TipoMascotaDelegado(JPanel contenedor) {
        this.contenedor = contenedor;
        this.tipomascotaDao = new TipoMascotaDao();
    }
    
    public void insertarTipoMascota(TipoMascotaVo tipomascotaVo){
        try {
            this.tipomascotaDao.insertar(tipomascotaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insercion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarTipoMascota(TipoMascotaVo tipomascotaVo){
        try {
            this.tipomascotaDao.editar(tipomascotaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en edicion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<TipoMascotaVo> consultarTipoMascotas(TipoMascotaVo tipomascotaVo){
        List<TipoMascotaVo> listaTipoMascotas; 
        try {
            listaTipoMascotas = this.tipomascotaDao.consultar();
        } catch (Exception e) {
            listaTipoMascotas = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en consulta general", JOptionPane.ERROR_MESSAGE);
        }
        return listaTipoMascotas;
    }
    
    public TipoMascotaVo consultarTipoMascota(int id){
        TipoMascotaVo tipomascotaVo;
        try {
            tipomascotaVo = this.tipomascotaDao.consultar(id);
        } catch (Exception e) {
            tipomascotaVo = new TipoMascotaVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en consulta especifica", JOptionPane.ERROR_MESSAGE);
        }
        return tipomascotaVo;
    } 
    
}

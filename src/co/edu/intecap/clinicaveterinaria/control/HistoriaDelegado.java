/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.HistoriaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.HistoriaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author capacitaciones
 */
public class HistoriaDelegado {
    //atributo de clase que son constantes
    // su valor como objeto no puede cambiar
    private final JPanel contenedor;
    private final HistoriaDao historiaDao;

    public HistoriaDelegado(JPanel contenedor) {
        this.contenedor = contenedor;
        this.historiaDao = new HistoriaDao();
    }
    
    public void insertarHistoria(HistoriaVo historiaVo){
        try {
            this.historiaDao.insertar(historiaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insercion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarHistoria(HistoriaVo historiaVo){
        try {
            this.historiaDao.editar(historiaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en edicion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<HistoriaVo> consultarHistorias(HistoriaVo historiaVo){
        List<HistoriaVo> listaHistorias; 
        try {
            listaHistorias = this.historiaDao.consultar();
        } catch (Exception e) {
            listaHistorias = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en consulta general", JOptionPane.ERROR_MESSAGE);
        }
        return listaHistorias;
    }
    
    public HistoriaVo consultarHistoria(int id){
        HistoriaVo historiaVo;
        try {
            historiaVo = this.historiaDao.consultar(id);
        } catch (Exception e) {
            historiaVo = new HistoriaVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en consulta especifica", JOptionPane.ERROR_MESSAGE);
        }
        return historiaVo;
    } 
    
}

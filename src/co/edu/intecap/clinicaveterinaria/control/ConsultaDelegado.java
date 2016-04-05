/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.ConsultaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.ConsultaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author capacitaciones
 */
public class ConsultaDelegado {
    //atributo de clase que son constantes
    // su valor como objeto no puede cambiar
    private final JPanel contenedor;
    private final ConsultaDao consultaDao;

    public ConsultaDelegado(JPanel contenedor) {
        this.contenedor = contenedor;
        this.consultaDao = new ConsultaDao();
    }
    
    public void insertarConsulta(ConsultaVo consultaVo){
        try {
            this.consultaDao.insertar(consultaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insercion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarConsulta(ConsultaVo consultaVo){
        try {
            this.consultaDao.editar(consultaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en edicion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<ConsultaVo> consultarConsultas(ConsultaVo consultaVo){
        List<ConsultaVo> listaConsultas; 
        try {
            listaConsultas = this.consultaDao.consultar();
        } catch (Exception e) {
            listaConsultas = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en consulta general", JOptionPane.ERROR_MESSAGE);
        }
        return listaConsultas;
    }
    
    public ConsultaVo consultarConsulta(int id){
        ConsultaVo consultaVo;
        try {
            consultaVo = this.consultaDao.consultar(id);
        } catch (Exception e) {
            consultaVo = new ConsultaVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en consulta especifica", JOptionPane.ERROR_MESSAGE);
        }
        return consultaVo;
    } 
    
}

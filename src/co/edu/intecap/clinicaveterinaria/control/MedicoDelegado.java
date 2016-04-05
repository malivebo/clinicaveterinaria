/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.MedicoDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.MedicoVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author capacitaciones
 */
public class MedicoDelegado {
    //atributo de clase que son constantes
    // su valor como objeto no puede cambiar
    private final JPanel contenedor;
    private final MedicoDao medicoDao;

    public MedicoDelegado(JPanel contenedor) {
        this.contenedor = contenedor;
        this.medicoDao = new MedicoDao();
    }
    
    public void insertarMedico(MedicoVo medicoVo){
        try {
            this.medicoDao.insertar(medicoVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insercion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarMedico(MedicoVo medicoVo){
        try {
            this.medicoDao.editar(medicoVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en edicion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<MedicoVo> medicoMedicos(MedicoVo medicoVo){
        List<MedicoVo> listaMedicos; 
        try {
            listaMedicos = this.medicoDao.consultar();
        } catch (Exception e) {
            listaMedicos = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en medico general", JOptionPane.ERROR_MESSAGE);
        }
        return listaMedicos;
    }
    
    public MedicoVo medicorMedico(int id){
        MedicoVo medicoVo;
        try {
            medicoVo = this.medicoDao.consultar(id);
        } catch (Exception e) {
            medicoVo = new MedicoVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en medico especifica", JOptionPane.ERROR_MESSAGE);
        }
        return medicoVo;
    } 
    
}

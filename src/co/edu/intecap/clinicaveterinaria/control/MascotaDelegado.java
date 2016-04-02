/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.MascotaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.MascotaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author capacitaciones
 */
public class MascotaDelegado {
    //atributo de clase que son constantes
    // su valor como objeto no puede cambiar
    private final JFrame contenedor;
    private final MascotaDao mascotaDao;

    public MascotaDelegado(JFrame contenedor) {
        this.contenedor = contenedor;
        this.mascotaDao = new MascotaDao();
    }
    
    public void insertarMascota(MascotaVo mascotaVo){
        try {
            this.mascotaDao.insertar(mascotaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insercion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarMascota(MascotaVo mascotaVo){
        try {
            this.mascotaDao.editar(mascotaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insercion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<MascotaVo> consultarMascotas(MascotaVo mascotaVo){
        List<MascotaVo> listaMascotas; 
        try {
            listaMascotas = this.mascotaDao.consultar();
        } catch (Exception e) {
            listaMascotas = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insercion", JOptionPane.ERROR_MESSAGE);
        }
        return listaMascotas;
    }
    
    public MascotaVo consultarMascota(int id){
        MascotaVo mascotaVo;
        try {
            mascotaVo = this.mascotaDao.consultar(id);
        } catch (Exception e) {
            mascotaVo = new MascotaVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insercion", JOptionPane.ERROR_MESSAGE);
        }
        return mascotaVo;
    } 
    
}

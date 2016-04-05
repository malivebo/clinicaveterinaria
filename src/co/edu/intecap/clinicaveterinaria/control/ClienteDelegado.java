/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.ClienteDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.ClienteVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author capacitaciones
 */
public class ClienteDelegado {
    //atributo de clase que son constantes
    // su valor como objeto no puede cambiar
    private final JPanel contenedor;
    private final ClienteDao clienteDao;

    public ClienteDelegado(JPanel contenedor) {
        this.contenedor = contenedor;
        this.clienteDao = new ClienteDao();
    }
    
    public void insertarCliente(ClienteVo clienteVo){
        try {
            this.clienteDao.insertar(clienteVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insercion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarCliente(ClienteVo clienteVo){
        try {
            this.clienteDao.editar(clienteVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en edicion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<ClienteVo> consultarClientes(ClienteVo clienteVo){
        List<ClienteVo> listaClientes; 
        try {
            listaClientes = this.clienteDao.consultar();
        } catch (Exception e) {
            listaClientes = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en consulta general", JOptionPane.ERROR_MESSAGE);
        }
        return listaClientes;
    }
    
    public ClienteVo consultarCliente(int id){
        ClienteVo clienteVo;
        try {
            clienteVo = this.clienteDao.consultar(id);
        } catch (Exception e) {
            clienteVo = new ClienteVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en consulta especifica", JOptionPane.ERROR_MESSAGE);
        }
        return clienteVo;
    } 
    
}

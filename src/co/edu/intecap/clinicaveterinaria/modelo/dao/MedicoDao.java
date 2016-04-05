/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.conexion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.MedicoVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author capacitaciones
 */
public class MedicoDao extends Conexion implements GenericoDao<MedicoVo> {

    @Override
    public void insertar(MedicoVo object) {
        PreparedStatement sentencia; 
        try {
            conectar();
            String sql = "Insert into Medico (nombre, correo, documento, tarjeta_profesional, estado) values ( ?, ?, ?, ?, ?) ";
            sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            sentencia.setString(1, object.getNombre());
            sentencia.setString(2, object.getCorreo());
            sentencia.setString(3, object.getDocumento());
            sentencia.setString(4, object.getTarjetaProfesional());
            sentencia.setBoolean(5, object.isEstado());
            // ejecutar la insercion
            sentencia.executeUpdate();
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                object.setIdMedico(rs.getInt(1));
            }
            
        } catch (Exception e) {
        e.printStackTrace(System.err);
        }  
        finally {
            desconectar();
        }
    }

    @Override
    public void editar(MedicoVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
            // crear consulta de actualizacion
            String sql = "Update Medico set id_medico=?,nombre=?,correo=?,documento=?,tarjeta_profesional=?,estado=? where id_medico = ?"; 
            sentencia = cnn.prepareStatement(sql);
            //asignar parametros a la actualizacion
            sentencia.setInt(1, object.getIdMedico());
            sentencia.setString(2, object.getNombre());
            sentencia.setString(3, object.getCorreo());
            sentencia.setString(4, object.getDocumento());
            sentencia.setString(5, object.getTarjetaProfesional());
            sentencia.setBoolean(6, object.isEstado());
            sentencia.setInt(7, object.getIdMedico());
            // ejecutar la actualizacion
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }  
        finally {
            desconectar();
        }
        
    }

    @Override
    public List<MedicoVo> consultar() {
        PreparedStatement sentencia;
        List<MedicoVo> lista = new ArrayList<>();
        
        try {
            conectar();
            // crear sentencia de consulta 
            String sql = "Select * from medico"; 
            sentencia = cnn.prepareStatement(sql);
            // Obtener los registros de la tabla
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                MedicoVo medico= new MedicoVo();
                //obtener el id del medico del cursor
                // y asignarlo al atributo idMedico de 
                // un objeto de la clase MedicoVo
                medico.setIdMedico(rs.getInt("id_medico"));
                medico.setNombre(rs.getString("nombre"));
                medico.setCorreo(rs.getString("correo"));
                medico.setDocumento(rs.getString("documento"));
                medico.setTarjetaProfesional(rs.getString("tarjeta_profesional"));                
                medico.setEstado(rs.getBoolean("estado"));
                lista.add(medico);
            }
        } catch (Exception e) {
        e.printStackTrace(System.err);
        }  
        finally {
            desconectar();
        }
        return lista;
    }

    @Override
    public MedicoVo consultar(int id) {
        PreparedStatement sentencia;
        MedicoVo obj = new MedicoVo();
        try {
            conectar();
            // crear sentencia de consulta de un registro  
            String sql = "Select * from medico where id_medico = ?"; 
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            // Obtener los registros de la tabla
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                //obtener el id del Medico 
                // y asignarlo al atributo idMedico de 
                // un objeto de la clase MedicoVo
                obj.setIdMedico(rs.getInt("id_medico"));
                obj.setNombre(rs.getString("nombre"));
                obj.setCorreo(rs.getString("correo"));
                obj.setDocumento(rs.getString("documento"));
                obj.setTarjetaProfesional(rs.getString("tarjeta_profesional"));
                obj.setEstado(rs.getBoolean("estado"));
            }
        } catch (Exception e) {
        e.printStackTrace(System.err);
        }  
        finally {
            desconectar();
        }
        return obj;
    }
            
}

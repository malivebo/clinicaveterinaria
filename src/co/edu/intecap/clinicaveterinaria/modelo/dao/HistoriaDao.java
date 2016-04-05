/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.conexion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.HistoriaVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author capacitaciones
 */
public class HistoriaDao extends Conexion implements GenericoDao<HistoriaVo>  {

    @Override
    public void insertar(HistoriaVo object) {
        PreparedStatement sentencia;
        
        try {
            conectar();
            // crear consulta de insercion
            String sql = "insert into historia (fecha_apertura, fecha_cierre,  estado, id_mascota) values(?,?,?,?)"; 
            sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //asignar parametros a la insercion
            sentencia.setDate(1, object.getFechaApertura());
            sentencia.setDate(2, object.getFechaCierre());
            sentencia.setBoolean(3, object.isEstado());
            sentencia.setInt(4, object.getIdMascota());
            // ejecutar la insercion
            sentencia.executeUpdate();
            // obtener la llave de registro de la historia
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                object.setIdHistoria(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }  
        finally {
            desconectar();
        }
                
                
                
    }

    @Override
    public void editar(HistoriaVo object) {
        PreparedStatement sentencia; 
        
        try {
            conectar();
            // crear consulta de actualizacion
            String sql = "Update historia set id_historia=?,fecha_apertura=?,fecha_cierre=?,estado=?,id_mascota=? where id_historia = ?"; 
            sentencia = cnn.prepareStatement(sql);
            //asignar parametros a la actualizacion
            sentencia.setInt(1, object.getIdHistoria());
            sentencia.setDate(2, object.getFechaApertura());
            sentencia.setDate(3, object.getFechaCierre());
            sentencia.setBoolean(4, object.isEstado());
            sentencia.setInt(5, object.getIdMascota());
            sentencia.setInt(6, object.getIdHistoria());
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
    public List<HistoriaVo> consultar() {
       PreparedStatement sentencia;
       List<HistoriaVo> lista = new ArrayList<>();
        try {
            conectar();
            // crear sentencia de consulta 
            String sql = "Select * from historia"; 
            sentencia = cnn.prepareStatement(sql);
            // Obtener los registros de la tabla
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                HistoriaVo historia= new HistoriaVo();
                //obtener el id de la mascota del cursor
                // y asignarlo al atributo idHistoria de 
                // un objeto de la clase HistoriaVo
                historia.setIdHistoria(rs.getInt("id_historia"));
                historia.setFechaApertura(rs.getDate("fecha_apertura"));
                historia.setFechaCierre(rs.getDate("fecha_cierre"));
                historia.setEstado(rs.getBoolean("estado"));
                historia.setIdMascota(rs.getInt("id_mascota"));
                lista.add(historia);
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
    public HistoriaVo consultar(int id) {
       PreparedStatement sentencia;
       HistoriaVo obj = new HistoriaVo();
        try {
            conectar();
            // crear sentencia de consulta de un registro  
            String sql = "Select * from historia where id_historia = ?"; 
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            // Obtener los registros de la tabla
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                //obtener el id de la historia 
                // y asignarlo al atributo idHistoria de 
                // un objeto de la clase HistoriaVo
                obj.setIdMascota(rs.getInt("id_historia"));
                obj.setFechaApertura(rs.getDate("fecha_apertura"));
                obj.setFechaCierre(rs.getDate("fecha_cierre"));
                obj.setEstado(rs.getBoolean("estado"));
                obj.setIdMascota(rs.getInt("id_mascota"));
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.conexion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.TipoMascotaVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author capacitaciones
 */
public class TipoMascotaDao extends Conexion implements GenericoDao<TipoMascotaVo> {

    @Override
    public void insertar(TipoMascotaVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
            String sql = "Insert into tipo_mascota (nombre, estado) values ( ?, ?) ";
            sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            sentencia.setString(1, object.getNombre());
            sentencia.setBoolean(2, object.isEstado());
            // ejecutar la insercion
            sentencia.executeUpdate();
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                object.setIdTipoMascota(rs.getInt(1));
            }
            
        } catch (Exception e) {
        e.printStackTrace(System.err);
        }  
        finally {
            desconectar();
        }
        
        
    }

    @Override
    public void editar(TipoMascotaVo object) {
    PreparedStatement sentencia;
        try {
            conectar();
            // crear consulta de actualizacion
            String sql = "Update tipo_mascota set id_tipo_mascota=?,nombre=?,estado=? where id_tipo_mascota = ?"; 
            sentencia = cnn.prepareStatement(sql);
            //asignar parametros a la actualizacion
            sentencia.setInt(1, object.getIdTipoMascota());
            sentencia.setString(2, object.getNombre());
            sentencia.setBoolean(3, object.isEstado());
            sentencia.setInt(4, object.getIdTipoMascota());
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
    public List<TipoMascotaVo> consultar() {
        PreparedStatement sentencia;
        List<TipoMascotaVo> lista = new ArrayList<>();
        
        try {
            conectar();
            // crear sentencia de consulta 
            String sql = "Select * from tipo_mascota"; 
            sentencia = cnn.prepareStatement(sql);
            // Obtener los registros de la tabla
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                TipoMascotaVo tipoMascota= new TipoMascotaVo();
                //obtener el id del cliente del cursor
                // y asignarlo al atributo idCliente de 
                // un objeto de la clase TipoMascotaVo
                tipoMascota.setIdTipoMascota(rs.getInt("id_tipo_mascota"));
                tipoMascota.setNombre(rs.getString("nombre"));
                tipoMascota.setEstado(rs.getBoolean("estado"));
                lista.add(tipoMascota);
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
    public TipoMascotaVo consultar(int id) {
       PreparedStatement sentencia;
        TipoMascotaVo obj = new TipoMascotaVo();
        try {
            conectar();
            // crear sentencia de consulta de un registro  
            String sql = "Select * from tipo_mascota where id_tipo_mascota = ?"; 
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            // Obtener los registros de la tabla
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                //obtener el id del cliente 
                // y asignarlo al atributo idClinte de 
                // un objeto de la clase ClienteVo
                obj.setIdTipoMascota(rs.getInt("id_tipo_mascota"));
                obj.setNombre(rs.getString("nombre"));
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

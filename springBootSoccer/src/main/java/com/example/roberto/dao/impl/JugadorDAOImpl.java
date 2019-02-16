package com.example.roberto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.roberto.beans.Camiseta;
import com.example.roberto.beans.Equipo;
import com.example.roberto.beans.Jugador;
import com.example.roberto.beans.Marca;
import com.example.roberto.dao.JugadorDAO;
import com.example.roberto.dao.ModelDAO;

@Repository
public class JugadorDAOImpl implements ModelDAO<Jugador> {

	@Autowired
	private DataSource dataSource;
	
	
	@Override
	public void insert(Jugador entity) throws Exception {
		String sql = "INSERT INTO jugador(nombre, equipo_id, camiseta_id) VALUES (?,?,?)";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, entity.getNombre());
			ps.setInt(2, entity.getEquipo().getId());
			ps.setInt(3, entity.getCamiseta().getId());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				con = null;				
			}
		}	
	}
	
	@Override
	public List<Jugador> findAll() throws Exception {
		String sql = "SELECT j.id, " + 
						"j.nombre as jugador, " + 
						"j.equipo_id as equipo_id, " + 
						"eq.nombre as equipo, " + 
						"j.camiseta_id as camiseta_id, " +
						"ca.nombre as camiseta_marca, " +
						"ma.id as id_marca, " +
						"ma.nombre as marca_camiseta " + 
					"FROM jugador as j " + 
					"LEFT JOIN equipo as eq ON j.equipo_id=eq.id " + 
					"LEFT JOIN camiseta as ca ON j.camiseta_id=ca.id " + 
					"LEFT JOIN marca as ma ON ca.marca_id=ma.id";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Jugador> list = new ArrayList<>(); 
			while(rs.next()) {
				Equipo eq = new Equipo();
				eq.setId(rs.getInt(3));
				eq.setNombre(rs.getString(4));
				
				Camiseta ca = new Camiseta();
				ca.setId(rs.getInt(5));
				ca.setNombre(rs.getString(6));
				
				Marca ma = new Marca();
				ma.setId(rs.getInt(7));
				ca.setMarca(ma);
				ca.getMarca().setNombre(rs.getString(8));
				
				Jugador j = new Jugador();
				j.setId(rs.getInt(1)); 
				j.setNombre(rs.getString(2)); 
				j.setEquipo(eq); 
				j.setCamiseta(ca);
				
				list.add(j);
			}
			return list;
		} catch (Exception e) {
			throw e; 
		} finally {
			if (con != null) {
				con = null;
			}
		}		
	}

	@Override
	public void delete(int id) throws Exception {
		String sql = "DELETE FROM jugador WHERE id=?";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				con = null;				
			}
		}		
	}

	@Override
	public void update(Jugador entity) throws Exception {
		String sql = "UPDATE jugador SET nombre=?, equipo_id=?, camiseta_id=? WHERE id=?";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, entity.getNombre());
			ps.setInt(2, entity.getEquipo().getId());
			ps.setInt(3, entity.getCamiseta().getId());
			ps.setInt(4, entity.getId());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				con = null;				
			}
		}
		
	}
	
	@Override
	public Jugador findOne(int id) throws Exception {
		String sql = "SELECT j.id, " + 
						"j.nombre as jugador, " + 
						"j.equipo_id as equipo_id, " + 
						"eq.nombre as equipo, " + 
						"j.camiseta_id as camiseta_id, " +
						"ca.nombre as camiseta_marca, " +
						"ma.id as id_marca, " +
						"ma.nombre as marca_camiseta " + 
					"FROM jugador as j " + 
					"LEFT JOIN equipo as eq ON j.equipo_id=eq.id " + 
					"LEFT JOIN camiseta as ca ON j.camiseta_id=ca.id " + 
					"LEFT JOIN marca as ma ON ca.marca_id=ma.id " + 
					"WHERE j.id=" + id;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Jugador j = new Jugador(); 
			while(rs.next()) {
				Equipo eq = new Equipo();
				eq.setId(rs.getInt(3));
				eq.setNombre(rs.getString(4));
				
				Camiseta ca = new Camiseta();
				ca.setId(rs.getInt(5));
				ca.setNombre(rs.getString(6));
				
				Marca ma = new Marca();
				ma.setId(rs.getInt(7));
				ca.setMarca(ma);
				ca.getMarca().setNombre(rs.getString(8));
				
				j.setId(rs.getInt(1)); 
				j.setNombre(rs.getString(2)); 
				j.setEquipo(eq); 
				j.setCamiseta(ca);
			}
			return j;
		} catch (Exception e) {
			throw e; 
		} finally {
			if (con != null) {
				con = null;
			}
		}		
	}


	

}

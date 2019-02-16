package com.example.roberto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.roberto.beans.Equipo;
import com.example.roberto.dao.EquipoDAO;
import com.example.roberto.dao.ModelDAO;

@Repository
public class EquipoDAOImpl implements ModelDAO<Equipo> {

	@Autowired
	private DataSource dataSource;
	
	
	@Override
	public void insert(Equipo entity) throws Exception {
		String sql = "INSERT INTO equipo(nombre) VALUES (?)";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, entity.getNombre());
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
	public List<Equipo> findAll() throws Exception {
		List<Equipo> list = new ArrayList<>(); 
		String sql = "SELECT * FROM equipo ORDER BY id Asc";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Equipo e = new Equipo();
				e.setId(rs.getInt(1)); 
				e.setNombre(rs.getString(2)); 
				list.add(e);	
			}
			ps.close();
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
		String sql = "DELETE FROM equipo WHERE id=?";
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
	public void update(Equipo entity) throws Exception {
		String sql = "UPDATE equipo SET nombre=? WHERE id=?";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, entity.getNombre());
			ps.setInt(2, entity.getId());
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
	public Equipo findOne(int id) throws Exception {
		String sql = "SELECT * FROM equipo WHERE id = ?";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Equipo e = new Equipo();
			while(rs.next()) {
				e.setId(rs.getInt(1)); 
				e.setNombre(rs.getString(2)); 	
			}
			ps.close();
			return e;
		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				con = null;
			}
		}
	}
	
	

}

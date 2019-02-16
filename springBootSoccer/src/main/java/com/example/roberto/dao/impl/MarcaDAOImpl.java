package com.example.roberto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.roberto.beans.Marca;
import com.example.roberto.dao.MarcaDAO;
import com.example.roberto.dao.ModelDAO;

@Repository
public class MarcaDAOImpl implements ModelDAO<Marca> {

	@Autowired
	private DataSource dataSource;
	
	
	@Override
	public void insert(Marca entity) throws Exception {
		String sql = "INSERT INTO marca(nombre) VALUES (?)";
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
	public List<Marca> findAll() throws Exception {
		List<Marca> list = new ArrayList<>(); 
		String sql = "SELECT * FROM marca ORDER BY id Asc";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Marca m = new Marca();
				m.setId(rs.getInt(1)); 
				m.setNombre(rs.getString(2)); 
				list.add(m);	
			}
			ps.close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			if (con != null) {
				con = null;
			}
		}
	}
	
	@Override
	public void delete(int id) throws Exception {
		String sql = "DELETE FROM marca WHERE id=?";
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
	public void update(Marca entity) throws Exception {
		String sql = "UPDATE marca SET nombre=? WHERE id=?";
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
	public Marca findOne(int id) throws Exception {
		String sql = "SELECT * FROM marca WHERE id = ?";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Marca m = new Marca();
			while(rs.next()) {
				m.setId(rs.getInt(1)); 
				m.setNombre(rs.getString(2)); 	
			}
			ps.close();
			return m;
		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				con = null;
			}
		}
	}
	
	
	
}

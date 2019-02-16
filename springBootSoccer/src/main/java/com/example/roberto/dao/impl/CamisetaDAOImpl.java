package com.example.roberto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.roberto.beans.Camiseta;
import com.example.roberto.beans.Marca;
import com.example.roberto.dao.CamisetaDAO;
import com.example.roberto.dao.ModelDAO;

@Repository
public class CamisetaDAOImpl implements ModelDAO<Camiseta> {

	@Autowired
	private DataSource dataSource;
	
	
	@Override
	public void insert(Camiseta entity) throws Exception {
		String sql = "INSERT INTO camiseta(nombre, marca_id) VALUES (?,?)";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, entity.getNombre());
			ps.setInt(2, entity.getMarca().getId());
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
	public List<Camiseta> findAll() throws Exception {
		String sql = "SELECT c.id, " + 
							"c.nombre, " + 
							"c.marca_id as marca_id, " + 
							"m.nombre as nombre_marca " + 
					 "FROM camiseta as c " + 
					 "LEFT JOIN marca as m " + 
					 "ON c.marca_id=m.id";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Camiseta> list = new ArrayList<>(); 
			while(rs.next()) {
				Camiseta c = new Camiseta();
				c.setId(rs.getInt(1));
				c.setNombre(rs.getString(2));
				Marca m = new Marca();
				m.setId(rs.getInt(3));	
				m.setNombre(rs.getString(4));
				c.setMarca(m); 
				list.add(c);
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
		String sql = "DELETE FROM camiseta WHERE id=?";
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
	public void update(Camiseta entity) throws Exception {
		String sql = "UPDATE camiseta SET nombre=?, marca_id=? WHERE id=?";
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, entity.getNombre());
			ps.setInt(2, entity.getMarca().getId());
			ps.setInt(3, entity.getId());
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
	public Camiseta findOne(int id) throws Exception {
		String sql = "SELECT c.id, " + 
							"c.nombre, " + 
							"c.marca_id as marca_id, " + 
							"m.nombre as nombre_marca " + 
					 "FROM camiseta as c " + 
					 "LEFT JOIN marca as m " + 
					 "ON c.marca_id=m.id " +
					 "WHERE c.id=" + id;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Camiseta c = new Camiseta(); 
			while(rs.next()) {
				c.setId(rs.getInt(1));
				c.setNombre(rs.getString(2));
				
				Marca m = new Marca();
				m.setId(rs.getInt(3));	
				m.setNombre(rs.getString(4));
				
				c.setMarca(m); 
			}
			ps.close();
			return c;
		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				con = null;
			}
		}
	}
	
	
}

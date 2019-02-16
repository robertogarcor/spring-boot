package com.example.roberto.gui;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.roberto.beans.Jugador;
import com.example.roberto.controllers.JugadorController;
 
/**
 * 
 * @author Roberto
 * 
 * Frame Internal management Jugador Objects
 */
public class FrameGestionJugador extends JInternalFrame implements ActionListener {
	
	private JTable table;
	private DefaultTableModel tablemodel;
	
	private JButton btn_refresc;
	private JButton btn_delete;
	private JButton btn_update;
	private JButton btn_insert;
	
	
	public FrameGestionJugador() {
		initComponents();
		setSize(500, 275);
		setMinimumSize(new Dimension(500, 275));
		fillTable();
		setVisible(true);		
	}
	
	
	private void initComponents() {
		setTitle("Gestion Jugadores");
		
		GridBagLayout g = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(g);
        
        // Table constraints
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
		
		tablemodel = new DefaultTableModel();
		String[] columns = {"Id", "Jugador","Equipo", "Nombre Camiseta", "Marca Camiseta"};
		tablemodel.setColumnIdentifiers(columns);
		
		table = new JTable();
		JScrollPane scroll = new JScrollPane(table);
		add(scroll, gbc);
		
		// Buttons constraints
		
		btn_update = new JButton("Update");
		btn_update.addActionListener(this);
		
		btn_refresc = new JButton("Refresc");
		btn_refresc.addActionListener(this);
		
		btn_insert = new JButton("Insert");
		btn_insert.addActionListener(this);
		
		btn_delete = new JButton("Delete");
		btn_delete.addActionListener(this);
		
		gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
		add(btn_refresc, gbc);
		
		gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
		
        add(btn_insert, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        add(btn_update, gbc);
		
		gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
		
        add(btn_delete, gbc);
		
		pack();
					
	}
	
	
	private void fillTable() {
		tablemodel.setRowCount(0);
		for (Jugador jugador : getJugadores()) {
			tablemodel.addRow(new String[]{
					String.valueOf(jugador.getId()), 
					jugador.getNombre(),
					jugador.getEquipo().getNombre(),
					jugador.getCamiseta().getNombre(),
					jugador.getCamiseta().getMarca().getNombre(),
					});
		}
		table.setModel(tablemodel);	
	}
	
	
	private List<Jugador> getJugadores() {
		try {
			return (List<Jugador>) JugadorController.getInstance().getApplication().findAll();
		} catch (Exception e) {
			e.getMessage();
			JOptionPane.showMessageDialog(getRootPane(), "Fail llista jugadores.");
		}
		return null;	
	}
	
	private Jugador getJugador(int id) {
		try {
			return JugadorController.getInstance().getApplication().findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(getRootPane(), "Fail get jugador.");
		}
		return null;	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_delete) {
			try {
				int sel = table.getSelectedRow();
				if (sel != -1) {
					Integer idjugador = Integer.parseInt((String) tablemodel.getValueAt(sel, 0)); // row , col 0 is: id
					JugadorController.getInstance().getApplication().delete(idjugador);
					JOptionPane.showMessageDialog(getRootPane(), "delete jugador OK.");
					tablemodel.removeRow(sel);	
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(getRootPane(), "Fail delete jugador.");
			}
		} 
		
		else if (e.getSource() == btn_insert) {
			JDialog frame = new DialogFormJugador(null, true);
			frame.setVisible(true);	
		}
		
		else if (e.getSource() == btn_refresc) {
			fillTable();
		}
		
		else if (e.getSource() == btn_update) {
			try {
				int sel = table.getSelectedRow();
				if (sel != -1) {
					Integer idjugador = Integer.parseInt((String) tablemodel.getValueAt(sel, 0)); // row , col 0 is: id
					Jugador jugador = getJugador(idjugador);
					JDialog form = new DialogFormJugador(null, true, jugador);
					form.setVisible(true); 	
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				//JOptionPane.showMessageDialog(getRootPane(), "Fail update jugador.");
			}
		} 
		
	}
	
	
}



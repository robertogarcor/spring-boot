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

import com.example.roberto.beans.Marca;
import com.example.roberto.controllers.MarcaController;

/**
 * 
 * @author Roberto
 * 
 * Frame Internal management Marca Objects
 */
public class FrameGestionMarca extends JInternalFrame implements ActionListener {
	
	private JTable table;
	private DefaultTableModel tablemodel;
	
	private JButton btn_refresc;
	private JButton btn_delete;
	private JButton btn_update;
	private JButton btn_insert;
	
	public FrameGestionMarca() {
		initComponents();
		setSize(400, 275);
		setMinimumSize(new Dimension(400, 275));
		fillTable();
		setVisible(true);		
	}
	
	private void initComponents() {
		setTitle("Gestion Marcas");
		
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
		String[] columns = {"Id", "Marca"};
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
		for (Marca marca : getMarcas()) {
			tablemodel.addRow(new String[]{String.valueOf(marca.getId()), marca.getNombre()});
		}
		table.setModel(tablemodel);	
	}
	
	
	private List<Marca> getMarcas() {
		try {
			return (List<Marca>) MarcaController.getInstance().getApplication().findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(getRootPane(), "Fail llista marcas.");
		}
		return null;	
	}
	
	private Marca getMarca(int id) {
		try {
			return MarcaController.getInstance().getApplication().findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(getRootPane(), "Fail get marca.");
		}
		return null;	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_delete) {
			try {
				int sel = table.getSelectedRow();
				if (sel != -1) {
					Integer idmarca = Integer.parseInt((String) tablemodel.getValueAt(sel, 0)); // row , col 0 is: id
					MarcaController.getInstance().getApplication().delete(idmarca);
					JOptionPane.showMessageDialog(getRootPane(), "delete marca OK.");
					tablemodel.removeRow(sel);	
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(getRootPane(), "Fail delete marca.");
			}
		}
		
		else if (e.getSource() == btn_insert) {
			JDialog frame = new DialogFormMarca(null, true);
			frame.setVisible(true);	
		}
		
		else if (e.getSource() == btn_refresc) {
			fillTable();
		}
		
		else if (e.getSource() == btn_update) {
			try {
				int sel = table.getSelectedRow();
				if (sel != -1) {
					Integer idmarca = Integer.parseInt((String) tablemodel.getValueAt(sel, 0)); // row , col 0 is: id
					Marca marca = getMarca(idmarca);
					JDialog form = new DialogFormMarca(null, true, marca);
					form.setVisible(true); 
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				//JOptionPane.showMessageDialog(getRootPane(), "Fail update equipo.");
			}
		} 
	}
	
	
	
}



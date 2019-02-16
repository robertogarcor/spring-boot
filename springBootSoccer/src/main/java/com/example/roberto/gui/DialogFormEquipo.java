package com.example.roberto.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.example.roberto.beans.Equipo;
import com.example.roberto.controllers.EquipoController;


/**
 * 
 * @author Roberto
 *
 * Dialog form insert/Update entities Type Equipo
 */
public class DialogFormEquipo extends JDialog implements ActionListener {
	
	private JLabel l_nombre;
	private JTextField input_nombre;
	private JButton btn_aceptar;
	private JLabel separator;
	
	private Equipo equipo;
	

	public DialogFormEquipo(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		setTitle("Insertar Equipo");
		setSize(300, 200);
		this.setLocationRelativeTo(null);
	}
	
	public DialogFormEquipo(java.awt.Frame parent, boolean modal, Equipo equipo) {
		super(parent, modal);
		this.equipo = equipo;
		initComponents();
		setTitle("Update Equipo");
		setSize(300, 200);
		this.setLocationRelativeTo(null);
		
		input_nombre.setText(equipo.getNombre().toString());
		
	}
	
	

	private void initComponents() {
		
		setLayout(new GridLayout(0, 1));
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		l_nombre = new JLabel("Nombre Equipo");
		input_nombre = new JTextField();
		separator = new JLabel("");
		btn_aceptar = new JButton("Aceptar");
		btn_aceptar.addActionListener(this);
		
		add(l_nombre);
		add(input_nombre);
		add(separator);
		add(btn_aceptar);
		
		pack();
			
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btn_aceptar) {
				if (input_nombre.getText().length() > 0 ) {
				
					if (equipo != null ) {
						equipo.setNombre(input_nombre.getText());
						EquipoController.getInstance().getApplication().update(equipo);
						JOptionPane.showMessageDialog(this, "Update Equipo Ok.");
						
					} else {
						Equipo new_equipo = new Equipo();
						new_equipo.setNombre(input_nombre.getText());
						EquipoController.getInstance().getApplication().insert(new_equipo);
						JOptionPane.showMessageDialog(this, "Insert Equipo Ok.");
					}
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Value not valid.");
				}
			}	
		} catch (Exception ex) {
			ex.getMessage();
			JOptionPane.showMessageDialog(this, "Fail Insert/Update Equipo.");
		} finally {
			
		}	
		
	}
	
	
}

package com.example.roberto.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.example.roberto.beans.Marca;
import com.example.roberto.controllers.MarcaController;

/**
 * 
 * @author Roberto
 * 
 * Dialog form insert/Update entities Type Marca
 */
public class DialogFormMarca extends JDialog implements ActionListener {
	
	private JLabel l_nombre;
	private JTextField input_nombre;
	private JButton btn_aceptar;
	private JLabel separator;
	
	private Marca marca;
	

	public DialogFormMarca(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		setTitle("Insertar Marca");
		setSize(300, 200);
		this.setLocationRelativeTo(null);
		
	}
	
	public DialogFormMarca(java.awt.Frame parent, boolean modal, Marca marca) {
		super(parent, modal);
		this.marca = marca;
		initComponents();
		setTitle("Update Marca");
		setSize(300, 200);
		this.setLocationRelativeTo(null);
		
		input_nombre.setText(marca.getNombre().toString());
		
	}

	private void initComponents() {
		setLayout(new GridLayout(0, 1));
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		l_nombre = new JLabel("Nombre Marca");
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
				if (input_nombre.getText().length() > 0) {
				
					if (marca != null ) {
						marca.setNombre(input_nombre.getText());
						MarcaController.getInstance().getApplication().update(marca);
						JOptionPane.showMessageDialog(this, "Update Marca Ok.");
						
					} else {
					
						Marca new_marca = new Marca();
						new_marca.setNombre(input_nombre.getText());
						MarcaController.getInstance().getApplication().insert(new_marca);
						JOptionPane.showMessageDialog(this, "Insert Marca Ok.");
					}
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Value not valid.");
				}	
			}
		} catch (Exception ex) {
			ex.getMessage();
			JOptionPane.showMessageDialog(this, "Fail Insert/Update Marca.");
		} finally {
			
		}	
	}
		
	
}

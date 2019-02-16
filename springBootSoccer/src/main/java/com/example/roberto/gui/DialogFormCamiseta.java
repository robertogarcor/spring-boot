package com.example.roberto.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.example.roberto.beans.Camiseta;
import com.example.roberto.beans.Marca;
import com.example.roberto.controllers.CamisetaController;
import com.example.roberto.controllers.MarcaController;

/**
 * 
 * @author Roberto
 *
 * Dialog form insert/Update entities Type Camiseta
 */
public class DialogFormCamiseta extends JDialog implements ActionListener {
	
	private JLabel l_nombre;
	private JTextField input_nombre;
	private JLabel l_marca;
	private JComboBox<String> combo_marca;
	private JButton btn_aceptar;
	private JLabel separator;

	private Map<Integer, Marca> mapMarcas = new HashMap<>();
	private Camiseta camiseta;
	
	public DialogFormCamiseta(FrameGestionCamiseta frameGestionCamiseta, boolean modal) {
		super();
		initComponents();
		setTitle("Insertar Camiseta");
		setSize(300, 300);
		fillCombo();
		this.setLocationRelativeTo(null);	
	}
	
	public DialogFormCamiseta(java.awt.Frame parent, boolean modal, Camiseta camiseta) {
		super(parent, modal);
		this.camiseta = camiseta;
		initComponents();
		setTitle("Update Camiseta");
		setSize(300, 300);
		fillCombo();
		this.setLocationRelativeTo(null);

		input_nombre.setText(camiseta.getNombre().toString());
		combo_marca.setSelectedIndex(searchIndexMarca(camiseta));
	}

	private void initComponents() {
		setLayout(new GridLayout(0, 1));
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		l_nombre = new JLabel("Nombre Camiseta");
		input_nombre = new JTextField();
		l_marca = new JLabel("Identificador Marca");
		
		combo_marca = new JComboBox<>();
		
		separator = new JLabel("");
		btn_aceptar = new JButton("Aceptar");
		btn_aceptar.addActionListener(this);
		
		add(l_nombre);
		add(input_nombre);
		add(l_marca);
		add(combo_marca);
		add(separator);
		add(btn_aceptar);
		
		pack();
		
			
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btn_aceptar) {
				if (input_nombre.getText().length() > 0 & combo_marca.getSelectedIndex() > 0) {
				
					if (camiseta != null ) {
						camiseta.setNombre(input_nombre.getText());
						camiseta.getMarca().setId(mapMarcas.get(combo_marca.getSelectedIndex()).getId());
						CamisetaController.getInstance().getApplication().update(camiseta);
						JOptionPane.showMessageDialog(this, "Update Camiseta Ok.");
						
					} else {
						Camiseta camiseta = new Camiseta();
						camiseta.setNombre(input_nombre.getText());
						Marca marca = new Marca();
						marca.setId(mapMarcas.get(combo_marca.getSelectedIndex()).getId());
						camiseta.setMarca(marca);
						CamisetaController.getInstance().getApplication().insert(camiseta);
						JOptionPane.showMessageDialog(this, "Insert Camiseta Ok."); 
					}
					this.dispose();
					
				} else {
					JOptionPane.showMessageDialog(this, "Value not valid.");
				}
			}
		} catch (Exception ex) {
			ex.getMessage();
			JOptionPane.showMessageDialog(this, "Fail Insert/Update Camiseta.");
		} finally {
				
		}	
	}
	

	private List<Marca> getMarcas() {
		try {
			return (List<Marca>) MarcaController.getInstance().getApplication().findAll();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getRootPane(), "Fail llista marcas.");
		}
		return null;	
	}
	
	
	private void fillCombo() {
		Marca m_null = new Marca();
		m_null.setId(0);
		m_null.setNombre("--");
		mapMarcas.put(0, m_null);
		combo_marca.addItem(mapMarcas.get(0).getNombre());
		Integer index = 1;
		for (Marca marca : getMarcas()) {
			mapMarcas.put(index,marca);
			combo_marca.addItem(marca.getNombre());
			index++;
		}	
	}
	
	
	private Integer searchIndexMarca(Camiseta camiseta) {
		for (Entry<Integer, Marca> obj : mapMarcas.entrySet()) {
			if (obj.getValue().getNombre().equals(camiseta.getMarca().getNombre())) {
				return obj.getKey();
			}	
		}
		return 0;	
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	
	private Integer searchMarcaId(List<Marca> marcas, String marca) {
		for (Marca e : marcas) {
			if (e.getNombre().equals(marca)) {
				return e.getId();
			}	
		}
		return null;			
	}

	
			
}


	
	
	
	
	
	


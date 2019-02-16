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
import com.example.roberto.beans.Equipo;
import com.example.roberto.beans.Jugador;
import com.example.roberto.beans.Marca;
import com.example.roberto.controllers.CamisetaController;
import com.example.roberto.controllers.EquipoController;
import com.example.roberto.controllers.JugadorController;

/**
 * 
 * @author Roberto
 *
 * Dialog form insert/Update entities Type Jugador
 */
public class DialogFormJugador extends JDialog implements ActionListener {
	
	private JLabel l_nombre;
	private JTextField input_nombre;
	private JLabel l_equipo;
	private JComboBox<String> combo_equipo;
	private JLabel l_camiseta;
	private JComboBox<String> combo_camiseta;
	private JButton btn_aceptar;
	private JLabel separator;
	
	private Map<Integer, Equipo> mapEquipos = new HashMap<>();
	private Map<Integer, Camiseta> mapCamisetas = new HashMap<>();
	private Jugador jugador;
	
	
	public DialogFormJugador(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		setTitle("Insertar Jugador");
		fillComboCamisetas();
		fillComboEquipos();
		setSize(300, 300);
		this.setLocationRelativeTo(null);
	}
	
	public DialogFormJugador(java.awt.Frame parent, boolean modal, Jugador jugador) {
		super(parent, modal);
		this.jugador = jugador;
		initComponents();
		setTitle("Update Camiseta");
		fillComboCamisetas();
		fillComboEquipos();
		setSize(300, 300);
		this.setLocationRelativeTo(null);

		input_nombre.setText(jugador.getNombre().toString());
		combo_equipo.setSelectedIndex(searchIndexEquipo(jugador));
		combo_camiseta.setSelectedIndex(searchIndexCamiseta(jugador));
	}

	private void initComponents() {
		setLayout(new GridLayout(0, 1));
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		l_nombre = new JLabel("Nombre Jugador");
		input_nombre = new JTextField();
		l_equipo = new JLabel("Identificador Equipo");
		combo_equipo = new JComboBox<>();
		l_camiseta = new JLabel("Identificador Camiseta");
		combo_camiseta = new JComboBox<>();
		separator = new JLabel("");
		btn_aceptar = new JButton("Aceptar");
		btn_aceptar.addActionListener(this);
		
		add(l_nombre);
		add(input_nombre);
		add(l_equipo);
		add(combo_equipo);
		add(l_camiseta);
		add(combo_camiseta);
		add(separator);
		add(btn_aceptar);
		
		pack();
			
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btn_aceptar) {
				
				if (input_nombre.getText().length() > 0 &
					combo_equipo.getSelectedIndex() > 0 &
					combo_camiseta.getSelectedIndex() > 0) {
					
					if (jugador != null) {
						jugador.setNombre(input_nombre.getText());
						jugador.getEquipo().setId(mapEquipos.get(combo_equipo.getSelectedIndex()).getId());
						jugador.getCamiseta().setId(mapCamisetas.get(combo_camiseta.getSelectedIndex()).getId());
						JugadorController.getInstance().getApplication().update(jugador);
						JOptionPane.showMessageDialog(this, "Update Jugador Ok.");

					} else {
						Jugador jugador = new Jugador();
						jugador.setNombre(input_nombre.getText());
						
						Equipo equipo = new Equipo();
						equipo.setId(mapEquipos.get(combo_equipo.getSelectedIndex()).getId());
						
						Camiseta camiseta = new Camiseta();
						camiseta.setId(mapCamisetas.get(combo_camiseta.getSelectedIndex()).getId());
						
						jugador.setEquipo(equipo);
						jugador.setCamiseta(camiseta);
						
						JugadorController.getInstance().getApplication().insert(jugador);
						
						JOptionPane.showMessageDialog(this, "Insert jugador Ok.");	
					}	
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Value not valid.");
				}
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Fail Insert/Update Jugador.");
			
		} finally {
			
		}	
	}
	
	// Combo Equipo
	
	private void fillComboEquipos() {
		Equipo eq_null = new Equipo();
		eq_null.setId(0);
		eq_null.setNombre("--");
		mapEquipos.put(0, eq_null);
		combo_equipo.addItem(mapEquipos.get(0).getNombre());
		Integer index = 1;
		for (Equipo equipo : getEquipos()) {
			mapEquipos.put(index,equipo);
			combo_equipo.addItem(equipo.getNombre());
			index++;
		}		
	}
	
	
	private List<Equipo> getEquipos() {
		try {
			return (List<Equipo>) EquipoController.getInstance().getApplication().findAll();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(getRootPane(), "Fail llista equipos.");
		}
		return null;	
	}
	
	
	private Integer searchIndexEquipo(Jugador jugador) {
		for (Entry<Integer, Equipo> obj : mapEquipos.entrySet()) {
			if (obj.getValue().getNombre().equals(jugador.getEquipo().getNombre())) {
				return obj.getKey();
			}	
		}
		return 0;	
	}
	

	// Combo Camiseta 
	
	
	private void fillComboCamisetas() {
		Camiseta ca_null = new Camiseta();
		ca_null.setId(0);
		ca_null.setNombre("--");
		mapCamisetas.put(0, ca_null);
		combo_camiseta.addItem(mapCamisetas.get(0).getNombre());
		Integer index = 1;
		for (Camiseta camiseta : getCamisetas()) {
			mapCamisetas.put(index,camiseta);
			combo_camiseta.addItem(camiseta.getNombre());
			index++;
		}		
	}
		
		
	private List<Camiseta> getCamisetas() {
		try {
			return (List<Camiseta>) CamisetaController.getInstance().getApplication().findAll();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(getRootPane(), "Fail llista camisetas.");
		}
		return null;	
	}
	
	
	private Integer searchIndexCamiseta(Jugador jugador) {
		for (Entry<Integer, Camiseta> obj : mapCamisetas.entrySet()) {
			if (obj.getValue().getNombre().equals(jugador.getCamiseta().getNombre())) {
				return obj.getKey();
			}	
		}
		return 0;	
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	
	private Integer searchEquipoId(List<Equipo> equipos, String equipo) {
	for (Equipo e : equipos) {
		if (e.getNombre().equals(equipo)) {
			return e.getId();
		}	
	}
	return null;			
}
	
	private Integer searchCamisetaId(List<Camiseta> camisetas, String camiseta) {
		for (Camiseta e : camisetas) {
			if (e.getNombre().equals(camiseta)) {
				return e.getId();
			}	
		}
		return null;			
	}
		
}

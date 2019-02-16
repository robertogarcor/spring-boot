package com.example.roberto.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;

/**
 * 
 * @author Roberto
 * 
 * Form Main Panel Desktop
 */
public class FormMain extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu mnFile, mnEquipos, mnCamisetas, mnMarcas, mnJugadores;
	private JMenuItem mntmExit;
	private JMenuItem mntmPanel_Equipo, mntmPanel_Camiseta, mntmPanel_Marca, mntmPanel_Jugador;
	private JDesktopPane desktopPane;

	
	/**
	 * Create the frame.
	 */
	public FormMain() {
		initComponents();
		setSize(1024, 768);
		setMinimumSize(new Dimension(1024, 768));
		
	}
	
	
	private void initComponents() {		
		setTitle("Spring Soccer");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(this);
		
		// Equipo
		
		mnEquipos = new JMenu("Equipos");
		menuBar.add(mnEquipos);
		
		mntmPanel_Equipo = new JMenuItem("Panel Equipos");
		mnEquipos.add(mntmPanel_Equipo);
		mntmPanel_Equipo.addActionListener(this);
		
		// Camiseta
		
		mnCamisetas = new JMenu("Camisetas");
		menuBar.add(mnCamisetas);
		
		mntmPanel_Camiseta = new JMenuItem("Panel Camisetas");
		mnCamisetas.add(mntmPanel_Camiseta);
		mntmPanel_Camiseta.addActionListener(this);
		
		// Jugador
		
		mnJugadores = new JMenu("Jugadores");
		menuBar.add(mnJugadores);
		
		mntmPanel_Jugador = new JMenuItem("Panel Jugadores");
		mnJugadores.add(mntmPanel_Jugador);
		mntmPanel_Jugador.addActionListener(this);
		
		// Marca
		
		mnMarcas = new JMenu("Marcas");
		menuBar.add(mnMarcas);
		
		mntmPanel_Marca = new JMenuItem("Panel Marcas");
		mnMarcas.add(mntmPanel_Marca);
		mntmPanel_Marca.addActionListener(this);
		
		// Panel Desktop
		
		desktopPane = new JDesktopPane();
		setContentPane(desktopPane);
		
		pack();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Equipo
		
		if (e.getSource() == mntmPanel_Equipo) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					JInternalFrame frame = new FrameGestionEquipo();
					addFrame(frame);		
				}
			});
			t.start();
		}
		
		// Camiseta
		
		else if (e.getSource() == mntmPanel_Camiseta) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					JInternalFrame frame = new FrameGestionCamiseta();
					addFrame(frame);		
				}
			});
			t.start();
		}
		
		// Jugador
		
		else if (e.getSource() == mntmPanel_Jugador ) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					JInternalFrame frame = new FrameGestionJugador();
					addFrame(frame);	
				}
			});
			t.start();
			
		}
		
		// Marca
		
		else if (e.getSource() == mntmPanel_Marca ) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					JInternalFrame frame = new FrameGestionMarca();
					addFrame(frame);	
					
				}
			});
			t.start();
		} 
		
		// Exit
		
		else if (e.getSource() == mntmExit) {
			this.dispose();	
		}
	
	}
	
	
	private void addFrame(JInternalFrame frame){
        desktopPane.add(frame);
        frame.setClosable(true);
        frame.setMaximizable(true);
        frame.setResizable(true);
        try {            
            frame.setSelected(true);   
        } catch (PropertyVetoException ex) {}    
    }
	

}

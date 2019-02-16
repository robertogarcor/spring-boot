package com.example.roberto.springsoccer;

import java.awt.EventQueue;

import com.example.roberto.gui.FormMain;

/**
 * Class run aplication desktop
 * @author Roberto
 *
 */
public class AppDesktop {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMain frame = new FormMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
   	
}

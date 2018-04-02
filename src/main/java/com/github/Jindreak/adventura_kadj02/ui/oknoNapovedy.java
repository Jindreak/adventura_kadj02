package com.github.Jindreak.adventura_kadj02.ui;

import java.net.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Trida, ktera ma nacist napovedu z html souboru a zkonstruovat okno
 */
public class oknoNapovedy extends JDialog {
	private URL souborSNapovedou;
	private JEditorPane prohlizecHelpu;
		
	oknoNapovedy(String soubor){
	    setTitle("Napoveda ke hre raketa");
		prohlizecHelpu = new JEditorPane();
		prohlizecHelpu.setEditable(false);
		try{
			souborSNapovedou = this.getClass().getResource(soubor);
			prohlizecHelpu.setPage(souborSNapovedou);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,
                    		"Nelze nacist soubor" +e,
                    		"Chyba",
                    		JOptionPane.INFORMATION_MESSAGE);	
		}
			
		getContentPane().add(prohlizecHelpu);
	}
    
}

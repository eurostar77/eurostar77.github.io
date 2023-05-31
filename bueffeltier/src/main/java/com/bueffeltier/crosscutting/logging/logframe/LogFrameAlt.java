/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bueffeltier.crosscutting.logging.logframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 *
 * todo: methoden-namen einfügen. einzelne tracking pfade einfügen. todo:
 * grafische darstellung
 * 
 * es können betrachtungspfade markiert werden, die dann gespeichert werden
 * können. es können geschwindigkeitsmessungen vorgenomemn werden. todo:
 * Überschriften/Info bereich einfügen todo: kontrollstrukturen: wenn-dann,
 * do-while, try-catch
 * 
 * @author sveng
 */
public class LogFrameAlt extends javax.swing.JFrame
{

	private static final LogFrameAlt LOG_FRAME = new LogFrameAlt();
	private boolean useIt;
	private final ArrayList<LogNote> logNotes = new ArrayList<>();
	/**
	 * Variable verweist auf den zuletzt benutzten Klassen-Namen.
	 */
	private String lastClassType = "";
	private int raster = 50;

	private LogFrameAlt()
	{
		initComponents();
		this.setVisable(true);
		this.setAlwaysOnTop(false);
		jButton1.setEnabled(true);
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
		int x = (int) rect.getMaxX() - this.getWidth();
		int y = 0;
		setLocation(x, y);
		init();
	}

	private void init()
	{
		// Neu:
		jFrame1.setVisible(true);
		jFrame1.setSize(300, 750);

		JPanel groundPanel = new JPanel();
		groundPanel.setSize(300, 750);

		JPanel panel = new JPanel();
		panel.setSize(jFrame1.getWidth() - this.raster, this.raster);
		panel.setBackground(Color.green);

//        jFrame1.getContentPane().add(panel, java.awt.FlowLayout.LEFT); // todo: layout ändern.
//        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);
		jScrollPane2.setAutoscrolls(true);
		jScrollPane2.setPreferredSize(new java.awt.Dimension(300, 750));

		jScrollPane2.setViewportView(groundPanel); // todo: hier muss das
													// Gesamtpanel rein!

		jFrame1.getContentPane()
				.add(jScrollPane2, java.awt.BorderLayout.CENTER);

		groundPanel.add(panel);

		pack();

		// Alt:
		this.pack();
		jTextArea1.setLineWrap(true);
		jTextArea1.setWrapStyleWord(true);
		this.jTextField1.selectAll();
	}

	/**
	 * Es kann nur eine Instanz von LogFrame geben. Hier wird sie angefordert.
	 * 
	 * @return
	 */
	public static LogFrameAlt getInstance()
	{
		return LOG_FRAME;
	}

	/**
	 * LogFrame sichtbar machen.
	 * 
	 * @param visable
	 */
	private void setVisable(boolean visable)
	{
		if (useIt)
		{
			this.setVisible(visable);
		}
	}

	/**
	 * Aktiviert den LogFrame, sodass das Logging während der Programmausführung
	 * angezeigt wird.
	 * 
	 * @param useIt
	 */
	public void activate(boolean useIt)
	{
		this.useIt = useIt;
	}

	/**
	 * Fügt den "logNotes" eine neue hinzu.
	 * 
	 * @param origin
	 * @param loggingInfo
	 */
	public void add(Object origin, String loggingInfo)
	{
		String className = "Klasse: " + origin.getClass().getSimpleName();
		LogNote logNote = new LogNote(
				className, "", loggingInfo, logNotes.size() + 1
		);

		if (logNotes.isEmpty())
		{
			logNote.setShift(0);
		} else
		{
			if (!hasClassType(logNote.getClassName()))
			{
				int maxShift = getMaxShift();
				logNote.setShift(maxShift + 1);
			} else
			{
				logNote.setShift(getClassShift(className));
			}
		}

		this.logNotes.add(logNote);

		this.jTextArea1.append(logNote.writeAlt(className));
//        this.jTextArea1.append(origin.getClass().getSimpleName() + loggingInfo+"\n");
		// todo: klasse und methode übergeben und daraus eine visuelle
		// hierarchie mit ebenen erzeugen!

		// todo: nur visable, wenn die Klasse oder die Methode eingeschaltet
		// sind.
		this.setVisable(true);
//        jButton1.setEnabled(false);
		int length = jTextArea1.getDocument().getLength();
		jTextArea1.setCaretPosition(length);
		lastClassType = className;
	}

	/**
	 *
	 * @return
	 */
	public int getMaxShift()
	{
		int shift1 = 0;
		for (int i = 0; i < logNotes.size(); i++)
		{
			if (logNotes.get(i).getShift() > shift1)
			{
				shift1++;
			}
		}
		return shift1;
	}

	private int getClassShift(String className)
	{
		for (int i = 0; i < logNotes.size(); i++)
		{
			if (logNotes.get(i).getClassName().equals(className))
			{
				return logNotes.get(i).getShift();
			}
		}
		return 0;
	}

	/**
	 *
	 * @param classType
	 * @return
	 */
	private boolean hasClassType(String classType)
	{
		for (int i = 0; i < logNotes.size(); i++)
		{
			if (logNotes.get(i).getClassName().equals(classType))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents()
	{

		jFrame1 = new javax.swing.JFrame();
		jScrollPane2 = new javax.swing.JScrollPane();
		jPanel1 = new javax.swing.JPanel();
		jButton2 = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		jButton3 = new javax.swing.JButton();
		jTextField1 = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();

		jFrame1.getContentPane().setLayout(new java.awt.GridLayout(15, 1));
		jFrame1.getContentPane().add(jScrollPane2);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setMaximumSize(new java.awt.Dimension(32767, 64));
		jPanel1.setMinimumSize(new java.awt.Dimension(289, 64));
		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

		jButton2.setText("Ende");
		jPanel1.add(jButton2);

		jButton1.setText("Weiter");
		jButton1.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButton1ActionPerformed(evt);
			}
		});
		jPanel1.add(jButton1);

		jLabel3.setText("Klassenbaum");
		jPanel1.add(jLabel3);

		jButton3.setText("Hide");
		jButton3.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButton3ActionPerformed(evt);
			}
		});
		jPanel1.add(jButton3);

		jTextField1.setText("Breite");
		jTextField1.setMaximumSize(new java.awt.Dimension(2147483647, 40));
		jTextField1.setMinimumSize(new java.awt.Dimension(6, 40));
		jTextField1.setPreferredSize(new java.awt.Dimension(50, 22));
		jTextField1.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jTextField1ActionPerformed(evt);
			}
		});
		jPanel1.add(jTextField1);

		getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

		jScrollPane1.setAutoscrolls(true);
		jScrollPane1.setPreferredSize(new java.awt.Dimension(226, 800));

		jTextArea1.setColumns(20);
		jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_jButton1ActionPerformed

	}// GEN-LAST:event_jButton1ActionPerformed

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_jButton3ActionPerformed
		this.setVisable(false);
	}// GEN-LAST:event_jButton3ActionPerformed

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_jTextField1ActionPerformed
		int neueBreite = Integer.parseInt(evt.getActionCommand());
		Dimension dim = this.getSize();
		int alteBreite = this.getWidth();
		dim.width = neueBreite;
		this.setSize(dim);
		this.jTextField1.setText("Breite");
		this.jTextField1.selectAll();
		Point location = this.getLocation();
		location.x = location.x - neueBreite + alteBreite;
		this.setLocation(location);
		this.update(this.getGraphics());
		this.repaint();
	}// GEN-LAST:event_jTextField1ActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JFrame jFrame1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField1;
	// End of variables declaration//GEN-END:variables
}

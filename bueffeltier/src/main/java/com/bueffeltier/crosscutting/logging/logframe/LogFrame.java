/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bueffeltier.crosscutting.logging.logframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * todo: methoden-namen einfügen. einzelne tracking pfade einfügen. todo:
 * grafische darstellung todo: abstufung und farben nach gespeicherten klassen
 * darstellen. klassen können auf und absteigen! es können betrachtungspfade
 * markiert werden, die dann gespeichert werden können. es können
 * geschwindigkeitsmessungen vorgenomemn werden. todo: Überschriften/Info
 * bereich einfügen todo: kontrollstrukturen: wenn-dann, do-while, try-catch
 * todo: final long timeStart = System.currentTimeMillis();
 * 
 * @author sveng
 */
public class LogFrame extends javax.swing.JFrame
{

	private static final LogFrame LOG_FRAME = new LogFrame();
	private final ArrayList<LogNote> logNotes = new ArrayList<>();
	private boolean useIt;

	/**
	 * Variable verweist auf den zuletzt benutzten Klassen-Namen.
	 */
	private String lastClassName = "";
	private final int raster = 10;
//    private final List<Color> colors = this.getRainbow();
	private final List<Color> colors = this.getGradientColors();
	int colorShift = 0;

	private LogFrame()
	{

		initComponents();
		initComponents1();

//        addGraphicsEntry();
	}

	private void initComponents1()
	{

		// JFrame: todo: in initComponents() generieren lassen, was auto
		// generated ist.
		this.setVisable(true);
		this.setAlwaysOnTop(false);
		jButton1.setEnabled(true);
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
//        int x = (int) rect.getMaxX() - this.getWidth();
//        int y = 0;
//        setLocation(x, y);

		// todo: Kontroll-Leiste bearbeiten: setAlways on top etc.
		jScrollPane1.setAutoscrolls(true);
		jScrollPane1.setViewportView(this.logPanel);
		this.logPanel.setBackground(Color.LIGHT_GRAY);

		jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);

	}

	/**
	 * Zeichnet eine weitere LogNote in das LogPanel.
	 */
	private void addGraphicsEntry(LogNote logNote)
	{
		int shift = logNote.getShift() * raster * 5;

		// Dimensions:
		Dimension zeilenPanelDimension = new Dimension();
		zeilenPanelDimension.height = this.raster * 14;
		zeilenPanelDimension.width = 410 + shift;

		Dimension shiftPanelDimension = new Dimension();
		shiftPanelDimension.height = this.raster * 12;
		shiftPanelDimension.width = shift;

		Dimension notePanelDimension = new Dimension();
		notePanelDimension.height = this.raster * 12;
		notePanelDimension.width = 400;

		// Zeilen Panel:
		JPanel zeilenPanel = new JPanel();
		zeilenPanel.setMaximumSize(zeilenPanelDimension);
		zeilenPanel.setPreferredSize(zeilenPanelDimension);
		zeilenPanel.setMaximumSize(zeilenPanelDimension);
		zeilenPanel.setBackground(Color.LIGHT_GRAY);

		// Zeilen Panel Layout:
		zeilenPanel.setAlignmentX(LEFT_ALIGNMENT);
		zeilenPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		zeilenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// Shift Panel:
		JPanel shiftPanel = new JPanel();
		shiftPanel.setMaximumSize(shiftPanelDimension);
		shiftPanel.setPreferredSize(shiftPanelDimension);
		shiftPanel.setMaximumSize(shiftPanelDimension);

		// Shift Panel Layout:
		shiftPanel.setAlignmentX(LEFT_ALIGNMENT);
		shiftPanel.setBackground(Color.LIGHT_GRAY);

		// Note Panel:
		JPanel notePanel = new JPanel();
		notePanel.setMaximumSize(notePanelDimension);
		notePanel.setPreferredSize(notePanelDimension);
		notePanel.setMaximumSize(notePanelDimension);

		// Note Panel Design:
//        notePanel.setBackground(this.colors.get(colorShift));
		notePanel.setBorder(BorderFactory.createLineBorder(Color.black));

		// Note Panel Layout:
		notePanel.setAlignmentX(LEFT_ALIGNMENT);
		notePanel.setLayout(new BorderLayout());

		// Note Main Panel:
		JPanel noteMainPanel = new JPanel();
		noteMainPanel.setLayout(new BorderLayout());
		notePanel.add(noteMainPanel, BorderLayout.CENTER);

		// Text Area:
		JTextArea textArea = new JTextArea();
		Font textAreaFont = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
		textArea.setFont(textAreaFont);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JPanel textAreaPanel = new JPanel(new BorderLayout());
		textAreaPanel.add(textArea, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(textAreaPanel);
		scrollPane.setAutoscrolls(true);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		noteMainPanel.add(scrollPane, BorderLayout.CENTER);

//        noteMainPanel.add(textArea, BorderLayout.CENTER);
		// Method Panel:
		JPanel methodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		noteMainPanel.add(methodPanel, BorderLayout.NORTH);

		// Method Label
		JLabel methodLabel = new JLabel();
		methodPanel.add(methodLabel, FlowLayout.LEFT);
		methodLabel.setText(logNote.getClassName());
		Font methodLabelFont = new Font(Font.SANS_SERIF, Font.BOLD, 13);
		methodLabel.setFont(methodLabelFont);
		methodLabel.setText(logNote.getMethodName());

		// Note Head Panel:
		JPanel noteHeadPanel = new JPanel();
		notePanel.add(noteHeadPanel, BorderLayout.NORTH);
//        noteHeadPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
//        noteHeadPanel.setBackground(new Color(51, 204, 255));
//        noteHeadPanel.setBackground(this.colors.get(logNote.getShift()).darker());
		if (!logNote.isStart())
		{
			noteHeadPanel.setBackground(this.getColor(logNote.getShift()));
		} else
		{
			noteHeadPanel.setBackground(Color.red);
		}

		// Note Head Panel Layout:
		noteHeadPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        noteHeadPanel.setAlignmentX(LEFT_ALIGNMENT);
//        noteMainPanel.setComponentOrientation(
//                        ComponentOrientation.LEFT_TO_RIGHT);
//        noteMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// Class Label
		JLabel classLabel = new JLabel();
		noteHeadPanel.add(classLabel, FlowLayout.LEFT);
		classLabel.setText(logNote.getClassName());
		Font classLabelFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
		classLabel.setFont(classLabelFont);

		// Adding Panels
		zeilenPanel.add(shiftPanel);
		zeilenPanel.add(notePanel);
		logPanel.add(zeilenPanel);

		// Content laden
		StringBuilder stringBuilder = new StringBuilder();
		if (this.lastClassName.equals(logNote.getClassName()))
		{
			// Keine Klasse schreiben.
		} else
		{
//            stringBuilder.append("\n");
//            stringBuilder.append(this.writeShiftString(this.shift));
			stringBuilder.append(logNote.getClassName());
			stringBuilder.append("\n");
		}

		//
		stringBuilder.append(logNote.getLoggingText());
		stringBuilder.append("\n");
		textArea.setText(stringBuilder.toString());

		colorShift++;

		this.pack();

		// Alt:
//        this.pack();
//        jTextArea1.setLineWrap(true);
//        jTextArea1.setWrapStyleWord(true);
		this.jTextField1.selectAll();

		// Nach ganz unten scrollen.
		JScrollBar vertical = jScrollPane1.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());

	}

	private Color getColor(int shift)
	{
		return colors.get(shift % 7);
	}

	/**
	 * Es kann nur eine Instanz von LogFrame geben. Hier wird sie angefordert.
	 * 
	 * @return
	 */
	public static LogFrame getInstance()
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
	 * @param clazz
	 * @param methodName
	 * @param loggingInfo
	 * @param start
	 */
	public void add(
			Object clazz,
			String methodName,
			String loggingInfo,
			boolean start
	)
	{

		String className = clazz.getClass().getSimpleName();
		LogNote logNote = new LogNote(
				className, methodName, loggingInfo, logNotes.size() + 1
		);

		if (logNotes.isEmpty())
		{
			logNote.setShift(0);
		} else
		{
			if (!hasClassName(logNote.getClassName()))
			{
				int maxShift = getMaxShift();
				logNote.setShift(maxShift + 1);
			} else
			{
				logNote.setShift(getClassShift(className));
			}
		}

		if (start == true)
		{
			logNote.setStart();
		}

		this.logNotes.add(logNote);

		addGraphicsEntry(logNote);
//        this.jTextArea1.append(logNote.getLoggingText(className));

		// todo: nur visable, wenn die Klasse oder die Methode eingeschaltet
		// sind.
		this.setVisable(true);
//        jButton1.setEnabled(false);
//        int length = jTextArea1.getDocument().getLength();
//        jTextArea1.setCaretPosition(length);
		lastClassName = className;
	}

	/**
	 * Fügt den "logNotes" eine neue hinzu.
	 * 
	 * @param clazz
	 * @param methodName
	 * @param loggingInfo
	 */
	public void add(Object clazz, String methodName, String loggingInfo)
	{
		this.add(clazz, methodName, loggingInfo, false);
	}

	/**
	 * Fügt den "logNotes" eine neue hinzu.
	 * 
	 * @param clazz
	 * @param loggingInfo
	 */
	public void add(Object clazz, String loggingInfo)
	{
		this.add(clazz, "", loggingInfo, false);
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
	 * @param component
	 */
	public void printComponent(Component component)
	{
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setJobName(" Print Component ");

		pj.setPrintable(new Printable()
		{
			public int print(Graphics pg, PageFormat pf, int pageNum)
			{
				if (pageNum > 0)
				{
					return Printable.NO_SUCH_PAGE;
				}

				Graphics2D g2 = (Graphics2D) pg;
				g2.translate(pf.getImageableX(), pf.getImageableY());
				component.paint(g2);
				return Printable.PAGE_EXISTS;
			}
		});
		if (pj.printDialog() == false)
		{
			return;
		}

		try
		{
			pj.print();
		} catch (PrinterException ex)
		{
			// handle exception
		}
	}

	/**
	 *
	 * @param classType
	 * @return
	 */
	private boolean hasClassName(String classType)
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

	private List<Color> getGradientColors()
	{
		List<Color> generatedColors = new ArrayList<>();
		generatedColors.add(new Color(67, 85, 219));
		generatedColors.add(new Color(52, 187, 230));
		generatedColors.add(new Color(73, 218, 154));
		generatedColors.add(new Color(163, 224, 72));
		generatedColors.add(new Color(247, 208, 56));
		generatedColors.add(new Color(235, 117, 50));
		generatedColors.add(new Color(230, 38, 31));
		return generatedColors;
	}

	private List<Color> getRainbow()
	{
		int shift = 3;
		List<Color> generatedColors = new ArrayList<>();

		for (int r = 0; r < shift; r++)
		{
			generatedColors.add(new Color(r * 255 / shift, 255, 0));
		}

		for (int g = shift; g > 0; g--)
		{
			generatedColors.add(new Color(255, g * 255 / shift, 0));
		}

		for (int b = 0; b < shift; b++)
		{
			generatedColors.add(new Color(255, 0, b * 255 / shift));
		}

		for (int r = shift; r > 0; r--)
		{
			generatedColors.add(new Color(r * 255 / shift, 0, 255));
		}

		for (int g = 0; g < shift; g++)
		{
			generatedColors.add(new Color(0, g * 255 / shift, 255));
		}
		for (int b = shift; b > 0; b--)
		{
			generatedColors.add(new Color(0, 255, b * 255 / shift));
		}

		generatedColors.add(new Color(0, 255, 0));

		/*
		 * Alternative Rückgabe eines Arrays:
		 */
//        Color[] c = colors.toArray(new Color[colors.size()]);
//        return c;
		return generatedColors;
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

		testPanel = new javax.swing.JPanel();
		testLabel = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jButton2 = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		jButton3 = new javax.swing.JButton();
		jTextField1 = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		logPanel = new javax.swing.JPanel();

		testLabel.setText("jLabel1");
		testPanel.add(testLabel);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setLocation(new java.awt.Point(736, 0));

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

		jScrollPane1.setHorizontalScrollBarPolicy(
				javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
		);
		jScrollPane1.setVerticalScrollBarPolicy(
				javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
		);

		logPanel.setLayout(
				new javax.swing.BoxLayout(
						logPanel, javax.swing.BoxLayout.PAGE_AXIS
				)
		);
		jScrollPane1.setViewportView(logPanel);

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
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JPanel logPanel;
	private javax.swing.JLabel testLabel;
	private javax.swing.JPanel testPanel;
	// End of variables declaration//GEN-END:variables
}

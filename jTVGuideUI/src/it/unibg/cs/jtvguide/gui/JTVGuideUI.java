/*
 * jTVGuideUI.java
 *
 * Created on 12 agosto 2008, 16.11
 */

package it.unibg.cs.jtvguide.gui;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;

public class JTVGuideUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private SystemTray sysTray;
	private TrayIcon trayIcon;

	public JTVGuideUI(){
		it.unibg.cs.jtvguide.xmltv.UserPreferences.loadFromXMLFile();
		initComponents();
		sysTray = SystemTray.getSystemTray ();
		Image image = Toolkit.getDefaultToolkit().getImage("icons/tv.png");
		trayIcon = new TrayIcon (image.getScaledInstance(16, 16, 1),"jTVGuide v1.0");

		addWindowListener (new WindowAdapter ()
		{
			public void windowIconified (WindowEvent we)
			{
				setVisible (false);

				try {
					sysTray.add (trayIcon);
				} catch (Exception e) { }
			}
		});

		trayIcon.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				setVisible (true);
				setState (NORMAL);
				sysTray.remove (trayIcon);
			}
		});

	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		jMenu3 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenuItem3 = new javax.swing.JMenuItem();
		jMenuItem4 = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("jTVGuide v1.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage("icons/tv.png"));

		jMenu1.setText("File");

		jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem2.setText("Exit");
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem2ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem2);

		jMenuBar1.add(jMenu1);

		jMenu2.setText("Tools");

		jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem1.setText("Preferences...");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem1);

		jMenu3.setText("?");

		jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem3.setText("Help");
		jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jDialog2.setVisible(true);
			}
		});

		jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem4.setText("About");
		jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jDialog1.setVisible(true);
			}
		});

		jMenu3.add(jMenuItem3);
		jMenu3.add(jMenuItem4);
		jMenuBar1.add(jMenu2);
		jMenuBar1.add(jMenu3);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		{
			jTabbedPane1 = new JTabbedPane();
			{
				jPanel1 = new OnAirAndUpcoming();
				jTabbedPane1.addTab("OnAirAndUpcoming", null, jPanel1, null);
				jPanel1.setAutoscrolls(true);
			}
			{
				jPanel2 = new SearchForProgram();
				jTabbedPane1.addTab("SearchForProgram", null, jPanel2, null);
				jPanel2.setAutoscrolls(true);
			}
			{
				jPanel3 = new ChannelShow();
				jTabbedPane1.addTab("ChannelShow", null, jPanel3, null);
				jPanel3.setAutoscrolls(true);
			}
			{
				jPanel4 = new MultiChannelShow();
				jTabbedPane1.addTab("MultiChannelShow", null, jPanel4, null);
				jPanel4.setAutoscrolls(true);
			}
		}
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(jTabbedPane1, 0, 575, Short.MAX_VALUE));
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(jTabbedPane1, 0, 684, Short.MAX_VALUE));

		jDialog1 = getJDialog1();
		jDialog1.setLocationByPlatform(false);
		jDialog1.setLocation(new java.awt.Point(100, 100));
		jDialog1.setPreferredSize(new java.awt.Dimension(359, 149));
		jDialog1.setVisible(false);

		jDialog2 = getJDialog2();
		jDialog2.setLocationByPlatform(false);
		jDialog2.setLocation(new java.awt.Point(100, 100));
		jDialog2.setPreferredSize(new java.awt.Dimension(414, 253));
		{
			jTextArea1 = new JTextArea();
			jDialog2.getContentPane().add(jTextArea1);
			jTextArea1.setText("\nPer ottenere la programmazione desiderata seguire la seguente procedura:\n\nTools -> Preferences\n\nSelezionare le preferenze desiderate.\n\n-> Selezione canali\n\nSelezionare i canali di cui si vuole ottenere la programmazione.\n\n-> Ok\n\nIl programma effettuerà il download della programmazione dei canali desiderati.");
			jTextArea1.setBounds(0, 0, 406, 219);
			jTextArea1.setFont(new java.awt.Font("Tahoma",0,11));
			jTextArea1.setEditable(false);
		}
		jDialog2.setVisible(false);
		this.setResizable(false);


		pack();
	}

	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
		Preferences p = new Preferences(this);
		p.setVisible(true);
	}

	public static void main(String args[]) {
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new JTVGuideUI().setVisible(true);
			}
		});
	}

	private JDialog getJDialog1() {
		if(jDialog1 == null) {
			jDialog1 = new JDialog(this);
			jDialog1.setTitle("About");
			jDialog1.getContentPane().setLayout(null);
			{
				jLabel1 = new JLabel();
				jDialog1.getContentPane().add(jLabel1);
				jLabel1.setText("jTVGuide and jTVGuideUI have been brought to you by:");
				jLabel1.setBounds(10, 11, 321, 26);
				jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
				jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel2 = new JLabel();
				jDialog1.getContentPane().add(jLabel2);
				jLabel2.setText("Michele Bologna <michele.bologna@gmail.com>");
				jLabel2.setBounds(47, 48, 256, 14);
				jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
				jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jLabel4 = new JLabel();
				jDialog1.getContentPane().add(jLabel4);
				jLabel4.setText("Sebastiano Rota <sebastiano.rota@gmail.com>");
				jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
				jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
				jLabel4.setBounds(57, 68, 241, 14);
			}
			jDialog1.setSize(359, 149);
		}
		return jDialog1;
	}

	private JDialog getJDialog2() {
		if(jDialog2 == null) {
			jDialog2 = new JDialog(this);
			jDialog2.getContentPane().setLayout(null);
			jDialog2.setTitle("Help");
			jDialog2.setAlwaysOnTop(true);
			jDialog2.setSize(414, 253);
		}
		return jDialog2;
	}

	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenu jMenu3;
	private javax.swing.JMenuBar jMenuBar1;
	private JLabel jLabel2;
	private JLabel jLabel4;
	private JTextArea jTextArea1;
	private JDialog jDialog2;
	private JLabel jLabel1;
	private JDialog jDialog1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JMenuItem jMenuItem3;
	private javax.swing.JMenuItem jMenuItem4;
	private JPanel jPanel1;
	private JPanel jPanel4;
	private javax.swing.JTabbedPane jTabbedPane1;

}

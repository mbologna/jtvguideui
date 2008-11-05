/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Preferences.java
 *
 * Created on 1-set-2008, 14.07.00
 */
package it.unibg.cs.jtvguide.gui;

import it.unibg.cs.jtvguide.interfaces.XMLTVGrabbersByCountry;
import it.unibg.cs.jtvguide.xmltv.MD5Checksum;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;
import it.unibg.cs.jtvguide.xmltv.XMLTVScheduleInspector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;

public class Preferences extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JTVGuideUI reference;
	private javax.swing.JFrame jFrame = this;
	{
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

    public Preferences(JTVGuideUI guideUI) {
    	reference = guideUI;
        try {
			initComponents();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @SuppressWarnings("unchecked")
    private void initComponents() throws Exception {

    	if (!it.unibg.cs.jtvguide.xmltv.UserPreferences.loadFromXMLFile()
				|| !it.unibg.cs.jtvguide.xmltv.UserPreferences.getXmltvConfigFile().exists()
				|| it.unibg.cs.jtvguide.xmltv.UserPreferences.getXmltvConfigFile().length() == 0) {

    		it.unibg.cs.jtvguide.xmltv.UserPreferences.saveToXMLFile();
    		it.unibg.cs.jtvguide.xmltv.UserPreferences.loadFromXMLFile();
    	}

    	else
    		it.unibg.cs.jtvguide.xmltv.UserPreferences.loadFromXMLFile();


        GroupLayout layout = new GroupLayout((JComponent)getContentPane());
        getContentPane().setLayout(layout);
        jPanel1 = new javax.swing.JPanel();
        GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        GroupLayout jPanel2Layout = new GroupLayout((JComponent)jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jCheckBox1 = new javax.swing.JCheckBox();
        jSlider1 = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setTitle("Preferences");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("XMLTV Configuration"));

        jLabel1.setText("Select your country: ");
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
        	.addGap(8)
        	.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	    .addComponent(jComboBox1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        	    .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
        	.addContainerGap(20, 20));
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
        	.addContainerGap()
        	.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
        	.addGap(0, 186, Short.MAX_VALUE)
        	.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        	.addContainerGap());

        for (XMLTVGrabbersByCountry element : XMLTVGrabbersByCountry.values()) {
            jComboBox1.addItem(element);
            if(element.getCOMMAND().equals(it.unibg.cs.jtvguide.xmltv.UserPreferences.getXMLTVCommandByCountry()))
            	jComboBox1.setSelectedItem(element);
        }
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("XMLTV Preferences"));
        {
        	jButton2 = new JButton();
        	jButton2.setText("Selezione canali");
        	jButton2.addActionListener(new SelezionaCanali());
        }
        {
        	jButton1 = new JButton();
        	jButton1.setText("Ok");
        	jButton1.addActionListener(new AggiornaProgrammazione());
        	jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jFrame.dispose();
                }
            });
        }
        {
        	jButton5 = new JButton();
        	jButton5.setText("Cancel");
        	jButton5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton5ActionPerformed(evt);
                }
            });
        }
        layout.setVerticalGroup(layout.createSequentialGroup()
        	.addContainerGap()
        	.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
        	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        	.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
        	.addGap(23)
        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	    .addComponent(jButton1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	    .addComponent(jButton5, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	    .addComponent(jButton2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
        	.addContainerGap());
        layout.setHorizontalGroup(layout.createSequentialGroup()
        	.addContainerGap()
        	.addGroup(layout.createParallelGroup()
        	    .addComponent(jPanel1, GroupLayout.Alignment.LEADING, 0, 359, Short.MAX_VALUE)
        	    .addComponent(jPanel2, GroupLayout.Alignment.LEADING, 0, 359, Short.MAX_VALUE)
        	    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
        	        .addGap(0, 92, Short.MAX_VALUE)
        	        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
        	        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 1, GroupLayout.PREFERRED_SIZE)
        	        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
        	        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        	        .addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
        	        .addGap(11))));

        jCheckBox1.setText("quiet");
        jCheckBox1.setSelected(it.unibg.cs.jtvguide.xmltv.UserPreferences.isQuiet());
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jSlider1.setMaximum(7);
        jSlider1.setMinimum(1);
        jSlider1.setValue(it.unibg.cs.jtvguide.xmltv.UserPreferences.getDays());
        {
        	jCheckBox2 = new JCheckBox();
        	jCheckBox2.setText("with cache");
        	jCheckBox2.setSelected(it.unibg.cs.jtvguide.xmltv.UserPreferences.isWithCache());
        	jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox2ActionPerformed(evt);
                }
            });
        }
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel2.setText("Download data for:");
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createSequentialGroup()
        	.addComponent(jCheckBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        	.addComponent(jCheckBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addGap(0, 20, GroupLayout.PREFERRED_SIZE)
        	.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	    .addComponent(jLabel2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	    .addComponent(jLabel3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
        	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        	.addComponent(jSlider1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addContainerGap(19, 19));
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createSequentialGroup()
        	.addContainerGap()
        	.addGroup(jPanel2Layout.createParallelGroup()
        	    .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
        	        .addGroup(jPanel2Layout.createParallelGroup()
        	            .addComponent(jLabel2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	            .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
        	                .addComponent(jCheckBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	                .addGap(24)))
        	        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        	        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	        .addGap(157))
        	    .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
        	        .addComponent(jCheckBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	        .addGap(20)
        	        .addComponent(jSlider1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
        	.addContainerGap(61, 61));

        jLabel3.setText(Integer.toString(it.unibg.cs.jtvguide.xmltv.UserPreferences.getDays())+" days");

        pack();
        this.setSize(377, 322);
    }

    protected void jButton1ActionPerformed(ActionEvent evt) {
		this.dispose();
	}

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
    	this.dispose();
    }

    private class SelezionaCanali implements ActionListener, Runnable {

    	public void actionPerformed(ActionEvent e) {
    		Thread t = new Thread(this);
   	        t.start();
   	    }

    	public void run() {
    		XMLTVCommander xmltvc = new XMLTVCommander();
    		xmltvc.configureXMLTV();
    	}

   }

    private class AggiornaProgrammazione implements ActionListener, Runnable {

        public void actionPerformed(ActionEvent e) {
            Thread t = new Thread(this);
            t.start();
        }

        public void run() {
			it.unibg.cs.jtvguide.xmltv.UserPreferences.saveToXMLFile();
        	XMLTVCommander xmltvc = new XMLTVCommander();
        	XMLTVParserImpl xmltvParser = new XMLTVParserImpl();
        	Calendar c = Calendar.getInstance();
    		c.add(Calendar.DATE, it.unibg.cs.jtvguide.xmltv.UserPreferences.getDays());
    		int tries = 0;

    		boolean parsed = false;
    		while (parsed == false && tries <= 3) {
    			if (!new XMLTVScheduleInspector().isUpToDate()
    					|| !MD5Checksum.checkMD5(it.unibg.cs.jtvguide.xmltv.UserPreferences
    							.getXmltvConfigFile().toString(), MD5Checksum
    							.readMD5FromFile())) {
    				if(!it.unibg.cs.jtvguide.xmltv.UserPreferences.getXmltvConfigFile().exists()){
    					xmltvc.configureXMLTV();
    				}
    				try {
						it.unibg.cs.jtvguide.xmltv.UserPreferences.loadFromXMLFile();
					} catch (Exception e) {
						e.printStackTrace();
					}
    				xmltvc.downloadSchedule();
    			}

    			if(!new XMLTVScheduleInspector().isUpToDate(c.getTime()))
    			{
    				try {
						it.unibg.cs.jtvguide.xmltv.UserPreferences.loadFromXMLFile();
					} catch (Exception e) {
						e.printStackTrace();
					}
    				xmltvc.downloadSchedule();
    			}

    			if (tries >= 1) {
    				it.unibg.cs.jtvguide.xmltv.UserPreferences.getXmltvOutputFile().delete();
    				xmltvc.downloadSchedule();
    			}

    			parsed = xmltvParser.parse();
    			tries++;
    		}

    		if(parsed)
    		{
    			javax.swing.JPanel jPanelParent1 = new OnAirAndUpcoming();
    			javax.swing.JPanel jPanelParent2 = new SearchForProgram();
    			javax.swing.JPanel jPanelParent3 = new ChannelShow();
    			javax.swing.JPanel jPanelParent4 = new MultiChannelShow();
    			javax.swing.JPanel jPanel = (JPanel) ((javax.swing.JLayeredPane)((javax.swing.JRootPane)reference.getComponent(0)).getComponent(1)).getComponent(0);
    	    	javax.swing.JTabbedPane jTabbedPane = (JTabbedPane) jPanel.getComponent(0);
    	    	jTabbedPane.removeAll();
    	    	jTabbedPane.addTab("OnAirAndUpcoming", null, jPanelParent1, null);
    	    	jTabbedPane.addTab("SearchForProgram", null, jPanelParent2, null);
    	    	jTabbedPane.addTab("ChannelShow", null, jPanelParent3, null);
    	    	jTabbedPane.addTab("MultiChannelShow", null, jPanelParent4, null);
    		}

    		else
    		{
    			javax.swing.JPanel jPanel = (JPanel) ((javax.swing.JLayeredPane)((javax.swing.JRootPane)reference.getComponent(0)).getComponent(1)).getComponent(0);
    	    	javax.swing.JTabbedPane jTabbedPane = (JTabbedPane) jPanel.getComponent(0);
    	    	jTabbedPane.removeAll();
    		}

        }

    }

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
            it.unibg.cs.jtvguide.xmltv.UserPreferences.setCountry((XMLTVGrabbersByCountry) jComboBox1.getSelectedItem());
    }

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {
        jLabel3.setText(jSlider1.getValue() + " days");
        it.unibg.cs.jtvguide.xmltv.UserPreferences.setDays(jSlider1.getValue());
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    	if (jCheckBox1.isSelected())
    		it.unibg.cs.jtvguide.xmltv.UserPreferences.setQuiet(true);
    	else
    		it.unibg.cs.jtvguide.xmltv.UserPreferences.setQuiet(false);
    }

    private void jCheckBox2ActionPerformed(ActionEvent evt) {
    	if (jCheckBox2.isSelected())
    		it.unibg.cs.jtvguide.xmltv.UserPreferences.setWithCache(true);
    	else
    		it.unibg.cs.jtvguide.xmltv.UserPreferences.setWithCache(false);
    }

    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private JButton jButton5;
    private JButton jButton1;
    private JCheckBox jCheckBox2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider jSlider1;
}

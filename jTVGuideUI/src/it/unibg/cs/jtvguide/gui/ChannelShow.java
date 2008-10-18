package it.unibg.cs.jtvguide.gui;

import it.unibg.cs.jtvguide.model.Channel;
import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ChannelShow extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 4092293337693014545L;
	/**
	 *
	 */
	Schedule schedule;
	String selezionato = "";
	private javax.swing.JComboBox jComboBox1 = new javax.swing.JComboBox();
	private javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
	private javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
	final int visualized = 15;

	public ChannelShow(){
		XMLTVParserImpl xmltvParser = new XMLTVParserImpl();
		xmltvParser.parse();
		schedule = xmltvParser.getSchedule();
		initComponents();
	}

	private void initComponents() {
        this.setLayout(null);
        jLabel1.setText("Channel: ");
		jLabel1.setBounds(10,10,100,20);
		jComboBox1.setBounds(70,10,120,20);
		this.add(jLabel1);
		this.add(jComboBox1);

		Set<Channel> channels = new HashSet<Channel>();
		for(int i=0;i<schedule.getOnAirPrograms().size();i++)
			channels.add(schedule.getOnAirPrograms().get(i).getChannel());

		jComboBox1.addItem("Select your channel");

		Iterator<Channel> channelIterator = channels.iterator();
		while(channelIterator.hasNext())
			jComboBox1.addItem(channelIterator.next().getDisplayName());

		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
	}

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
		selezionato = (String) jComboBox1.getSelectedItem();
		paintShow();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ChannelShow jtv = new ChannelShow();

		jtv.setVisible(true);
	}

	private void paintShow()
	{
		jPanel1.removeAll();
		jPanel1.setLayout(new GridLayout());
		Rectangle dim = this.getBounds();
		jPanel1.setBounds(dim.x, dim.y+10, dim.width, dim.height-20);

		Calendar today = Calendar.getInstance();
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DATE, 1);

        List<Program> lk = new ArrayList<Program>();
        lk = schedule.getOnAirPrograms();
		List<Program> lp = new ArrayList<Program>();
		lp = schedule.getProgramsFromDateToDate(today.getTime(), tomorrow.getTime());

		List<Program> clk = new ArrayList<Program>();
		List<Program> clp = new ArrayList<Program>();

        for(int i=0;i<lk.size();i++)
		{
			if(selezionato.equals(((Channel)lk.get(i).getChannel()).getDisplayName()))
				clk.add(lk.get(i));
		}

		for(int i=0;i<lp.size();i++)
		{
			if(selezionato.equals(((Channel)lp.get(i).getChannel()).getDisplayName())
					&& clp.size()<visualized
					&& ((Date)lp.get(i).getStartDate()).after((Date)clk.get(0).getStartDate())){
				clp.add(lp.get(i));
			}
		}

		jPanel1.setLayout(new GridLayout(clk.size()+clp.size(),1));

		for (Program p : clk) {
			JLabel jl = new JLabel();
			if (p.getDesc() != null) {
				jl.setToolTipText(p.getDesc());
			}
			jPanel1.add(jl);
			jl.setText(p.toString());
			jl.setForeground(Color.RED);
		}
		for (Program p : clp) {
			JLabel jl1 = new JLabel(p.toString());
			if (p.getDesc() != null) {
				jl1.setToolTipText(p.getDesc());
			}
			jPanel1.add(jl1);
		}

		this.add(jPanel1);
		this.revalidate();
		this.repaint();
	}

}

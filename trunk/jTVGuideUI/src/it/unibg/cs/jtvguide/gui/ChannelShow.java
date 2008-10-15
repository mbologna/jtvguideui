package it.unibg.cs.jtvguide.gui;

import it.unibg.cs.jtvguide.UserPreferences;
import it.unibg.cs.jtvguide.model.Channel;
import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.model.XMLTVScheduleInspector;
import it.unibg.cs.jtvguide.util.FileUtils;
import it.unibg.cs.jtvguide.util.MD5Checksum;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class ChannelShow extends JPanel implements Runnable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4092293337693014545L;
	/**
	 *
	 */
	private static Thread thread;
	Random r = new Random();
	Schedule schedule;
	private String selezionato = "";
	private javax.swing.JComboBox jComboBox1 = new javax.swing.JComboBox();
	private javax.swing.JLabel jLabel1 = new javax.swing.JLabel();


	public ChannelShow(){
		XMLTVParserImpl xmltvParser = new XMLTVParserImpl();
		xmltvParser.parse();
		schedule = xmltvParser.getSchedule();
		initComponents();
		thread = new Thread(this);
		thread.start();
	}

	private void initComponents() {
		this.setLayout(null);
		jLabel1.setText("Channel: ");
		jLabel1.setBounds(10, 20, 150, 20);
		jComboBox1.setBounds(80, 20, 100, 20);
		this.add(jLabel1);
		this.add(jComboBox1);
		Set<Channel> channels = new HashSet<Channel>();

		for(int i=0;i<schedule.getOnAirPrograms().size();i++)
			channels.add(schedule.getOnAirPrograms().get(i).getChannel());

		Iterator<Channel> channelIterator = channels.iterator();
		while(channelIterator.hasNext())
			jComboBox1.addItem(channelIterator.next().getDisplayName());

		selezionato = (String) jComboBox1.getSelectedItem();

		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selezionato = (String) (jComboBox1.getSelectedItem());
            }
		});
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

	public void run() {
		while (true) {
			if (schedule != null){
				Calendar today = Calendar.getInstance();
				Calendar tomorrow = Calendar.getInstance();
				tomorrow.add(Calendar.DATE, 1);

				List<Program> lk = schedule.getOnAirPrograms();
				List<Program> lp = schedule.getProgramsFromDateToDate(today.getTime(), tomorrow.getTime());

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
							&& clp.size()<10
							&& ((Date)lp.get(i).getStartDate()).after((Date)clk.get(0).getStartDate())){
						clp.add(lp.get(i));
					}
				}

				javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
				Rectangle parentPanel = this.getBounds();
				jPanel1.setBounds(0, 50, parentPanel.width, parentPanel.height-50);
				this.add(jPanel1);
				jPanel1.setLayout(new GridLayout(clk.size() + clp.size(), 2));
				jPanel1.removeAll();

				for (Program p : clk) {
					JProgressBar jb = new JProgressBar();
					jb.setString(p.getInfo());
					jb.setStringPainted(true);
					jb.setValue(p.getCompletionPercentile());
					JLabel jl = new JLabel(p.toString());
					if (p.getDesc() != null) {
						jl.setToolTipText(p.getDesc());
						jb.setToolTipText(p.getDesc());
					}
					jPanel1.add(jl);
					jPanel1.add(jb);
				}
				for (Program p : clp) {
					JLabel jl1 = new JLabel(p.toString());
					JLabel jl2 = new JLabel(p.getInfo());
					if (p.getDesc() != null) {
						jl1.setToolTipText(p.getDesc());
						jl2.setToolTipText(p.getDesc());
					}
					jPanel1.add(jl1);
					jPanel1.add(jl2);
				}
				jPanel1.revalidate();
			}
			try {
				Thread.sleep(100 * (r.nextInt(5) + 5));
			} catch (InterruptedException e) {
			}

			}

		//}
	}

}

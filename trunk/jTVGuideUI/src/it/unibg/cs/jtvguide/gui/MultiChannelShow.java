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
import java.util.Random;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class MultiChannelShow extends JPanel implements Runnable{

	/**
	 *
	 */
	private static final long serialVersionUID = -9221049871768124350L;
	private static Thread thread;
	Random r = new Random();
	Schedule schedule;
	final int visualizedPerChannel = 10;

	public MultiChannelShow(){
		XMLTVParserImpl xmltvParser = new XMLTVParserImpl();
		xmltvParser.parse();
		schedule = xmltvParser.getSchedule();
		thread = new Thread(this);
		thread.start();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		MultiChannelShow jtv = new MultiChannelShow();

		jtv.setVisible(true);
	}

	public void run() {
		while (true) {
			if (schedule != null) {

				this.setLayout(null);

				Calendar today = Calendar.getInstance();
				Calendar tomorrow = Calendar.getInstance();
				tomorrow.add(Calendar.DATE, 1);

				List<Program> lk = new ArrayList<Program>();
		        lk = schedule.getOnAirPrograms();
				List<Program> lp = new ArrayList<Program>();
				lp = schedule.getProgramsFromDateToDate(today.getTime(), tomorrow.getTime());

				Set<Channel> channels = new HashSet<Channel>();
				for(int i=0;i<schedule.getOnAirPrograms().size();i++)
					channels.add(schedule.getOnAirPrograms().get(i).getChannel());


				JPanel jPanel1 = new JPanel();
				jPanel1.setLayout(new GridLayout(Math.round(channels.size()/2),2));

				Iterator<Channel> channelIterator = channels.iterator();
				while(channelIterator.hasNext())
				{
					List<Program> clk = new ArrayList<Program>();
					List<Program> clp = new ArrayList<Program>();
					String selezionato = channelIterator.next().getDisplayName();

					for(int i=0;i<lk.size();i++)
					{
						if(selezionato.equals(((Channel)lk.get(i).getChannel()).getDisplayName()))
							clk.add(lk.get(i));
					}

					for(int i=0;i<lp.size();i++)
					{
						if(selezionato.equals(((Channel)lp.get(i).getChannel()).getDisplayName())
								&& clp.size()<(visualizedPerChannel-1)
								&& ((Date)lp.get(i).getStartDate()).after((Date)clk.get(0).getStartDate())){
							clp.add(lp.get(i));
						}
					}

					JPanel jPanelChild = new JPanel();
					Border jPanelChildBorder = BorderFactory.createLineBorder(Color.BLACK);
					jPanelChild.setBorder(jPanelChildBorder);
					jPanelChild.setLayout(new GridLayout(visualizedPerChannel+1,1));
					JLabel jLabel2 = new JLabel(selezionato);
					jLabel2.setFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD,15));

					jPanelChild.add(jLabel2);

					for (Program p : clk) {
						JLabel jl = new JLabel();
						if (p.getDesc() != null) {
							jl.setToolTipText(p.getDesc());
						}
						jPanelChild.add(jl);
						jl.setText(p.toString());
						jl.setForeground(Color.RED);
					}
					for (Program p : clp) {
						JLabel jl1 = new JLabel(p.toString());
						if (p.getDesc() != null) {
							jl1.setToolTipText(p.getDesc());
						}
						jPanelChild.add(jl1);
					}

					jPanel1.add(jPanelChild);
				}

				JScrollPane jScrollPane1 = new JScrollPane(jPanel1);
				jScrollPane1.setBounds(0, 0, this.getBounds().width, this.getBounds().height);

				this.add(jScrollPane1);

				this.repaint();
				this.revalidate();
			}
			try {
				Thread.sleep(1000 * 300);
			} catch (InterruptedException e) {
			}
		}
	}
}
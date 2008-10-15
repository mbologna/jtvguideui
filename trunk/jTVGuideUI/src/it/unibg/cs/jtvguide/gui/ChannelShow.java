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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

	public ChannelShow(){
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
		ChannelShow jtv = new ChannelShow();

		jtv.setVisible(true);
	}

	public void run() {
		while (true) {
			if (schedule != null){
				this.removeAll();
				Calendar today = Calendar.getInstance();
				Calendar tomorrow = Calendar.getInstance();
				tomorrow.add(Calendar.DATE, 1);
				List<Program> lk = schedule.getOnAirPrograms();
				List<Program> lp = schedule.getProgramsFromDateToDate(today.getTime(), tomorrow.getTime());


				List<Program> clk = new ArrayList<Program>();
				List<Program> clp = new ArrayList<Program>();

				String selezionato = "Alice";

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
						System.out.println(((Channel)lp.get(i).getChannel()).getDisplayName());
						clp.add(lp.get(i));
					}
				}

				this.setLayout(new GridLayout(clk.size() + clp.size(), 2));
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
					this.add(jl);
					this.add(jb);
				}
				for (Program p : clp) {
					JLabel jl1 = new JLabel(p.toString());
					JLabel jl2 = new JLabel(p.getInfo());
					if (p.getDesc() != null) {
						jl1.setToolTipText(p.getDesc());
						jl2.setToolTipText(p.getDesc());
					}
					this.add(jl1);
				this.add(jl2);
				}
				this.revalidate();
			}
			try {
				Thread.sleep(1000 * (r.nextInt(5) + 5));
			} catch (InterruptedException e) {
			}

			}

		//}
	}
}

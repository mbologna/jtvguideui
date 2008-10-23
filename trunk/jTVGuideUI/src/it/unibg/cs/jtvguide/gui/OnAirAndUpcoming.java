package it.unibg.cs.jtvguide.gui;

import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.util.FileUtils;
import it.unibg.cs.jtvguide.xmltv.MD5Checksum;
import it.unibg.cs.jtvguide.xmltv.UserPreferences;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;
import it.unibg.cs.jtvguide.xmltv.XMLTVScheduleInspector;

import java.awt.GridLayout;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class OnAirAndUpcoming extends JPanel implements Runnable {

	/**
	 *
	 */
	private static final long serialVersionUID = -9221049871768124350L;
	private static Thread thread;
	Random r = new Random();
	Schedule schedule;

	public OnAirAndUpcoming(){
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
		OnAirAndUpcoming jtv = new OnAirAndUpcoming();

		jtv.setVisible(true);
	}

	public void run() {
		while (true) {
			if (schedule != null) {
				this.removeAll();
				List<Program> lk = schedule.getOnAirPrograms();
				List<Program> lp = schedule.getUpcomingPrograms();

				this.setLayout(new GridLayout(lk.size() + lp.size(), 2));
				for (Program p : lk) {
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
				for (Program p : lp) {
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
	}
}

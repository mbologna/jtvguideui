package it.unibg.cs.jtvguide.gui;

import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.xmltv.MD5Checksum;
import it.unibg.cs.jtvguide.xmltv.UserPreferences;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;
import it.unibg.cs.jtvguide.xmltv.XMLTVScheduleInspector;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchForProgram extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 2219149016130289380L;
	/**
	 *
	 */

	//JPanel jp = new JPanel();
	JTextField jt = new JTextField();
	JTextArea ja = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(ja);
	Schedule schedule;

	public SearchForProgram(){
		setLayout(new BorderLayout());
		jt.getDocument().addDocumentListener(new MyListener());
		this.add(BorderLayout.NORTH, jt);
		this.add(BorderLayout.CENTER, scrollPane);
		XMLTVParserImpl xmltvParser = new XMLTVParserImpl();
		xmltvParser.parse();
		schedule = xmltvParser.getSchedule();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SearchForProgram jtv = new SearchForProgram();
		jtv.setVisible(true);
	}

	class MyListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			if (jt.getText().length() >= 2)
				search(jt.getText());
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			if (jt.getText().length() >= 2)
				search(jt.getText());
			else
				ja.setText("");
		}

		public void search(String pattern) {
			List<Program> s = schedule.getProgramsByName(pattern);
			if (s.size() > 0) {
				ja.setText("");
				for (Program program : s) {
					if (ja.getText().equals(""))
						ja.setText(program.toString() + "   "
								+ program.getInfo());
					else
						ja.setText(ja.getText() + '\n' + program.toString()
								+ "   " + program.getInfo());
				}
			}
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

	}
}

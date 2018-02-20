package gan.dircopy;

import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class DialogVer {
	private Preferences prefs;
	private JFrame frmDircopyuiver;
	private JTextField SrcDirText;
	private JTextField TarDirText;
	boolean threadFlag = false;
	private JFrame frm = new JFrame();
	private JFileChooser fileChooser = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DialogVer window = new DialogVer();
					window.frmDircopyuiver.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DialogVer() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		prefs = Preferences.userNodeForPackage(DialogVer.class);
		String LastSrcDir = prefs.get("SrcDir", "이전 경로가 없습니다. 경로를 추가해주세요.");
		String LastTarDir = prefs.get("TarDir", "이전 경로가 없습니다. 경로를 추가해주세요.");

		frmDircopyuiver = new JFrame();
		frmDircopyuiver.setTitle("DirCopyUIver");
		frmDircopyuiver.setBounds(100, 100, 450, 310);
		frmDircopyuiver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDircopyuiver.getContentPane().setLayout(null);

		JButton SrcDirButton = new JButton("\uC18C\uC2A4\uD3F4\uB354");
		SrcDirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setCurrentDirectory(new File("D:/"));
				fileChooser.showOpenDialog(frm);
				File dir = fileChooser.getSelectedFile();
				SrcDirText.setText(dir.getPath());
				prefs.put("SrcDir", dir.getPath());
			}
		});
		SrcDirButton.setBounds(281, 21, 97, 23);
		frmDircopyuiver.getContentPane().add(SrcDirButton);

		JButton TarDirButton = new JButton("\uD0C0\uCF13\uD3F4\uB354");
		TarDirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setCurrentDirectory(new File("D:/"));
				fileChooser.showOpenDialog(frm);
				File dir = fileChooser.getSelectedFile();
				TarDirText.setText(dir.getPath());
				prefs.put("TarDir", dir.getPath());
			}
		});
		TarDirButton.setBounds(281, 52, 97, 23);
		frmDircopyuiver.getContentPane().add(TarDirButton);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStart.setEnabled(false);

				String[] SrcAndTarDir = { SrcDirText.getText(), TarDirText.getText() };
				
				JTextArea textArea = new JTextArea();
				textArea.setBounds(34, 261, 344, 107);
				textArea.setMargin(new Insets(5,5,5,5));
				frmDircopyuiver.getContentPane().add(textArea);

				JEditorPane editorPane = new JEditorPane();
				editorPane.setBounds(34, 204, 344, 21);
				frmDircopyuiver.getContentPane().add(editorPane);

				JProgressBar progressBar = new JProgressBar();
				progressBar.setStringPainted(true);
				progressBar.setBounds(86, 110, 160, 23);
				frmDircopyuiver.getContentPane().add(progressBar);

				JProgressBar progressBar_all = new JProgressBar();
				progressBar_all.setStringPainted(true);
				progressBar_all.setBounds(86, 143, 160, 23);
				frmDircopyuiver.getContentPane().add(progressBar_all);

				JButton btnStop = new JButton("Stop");
				btnStop.setBounds(281, 118, 97, 23);
				frmDircopyuiver.getContentPane().add(btnStop);

				publishTask2 thread2 = new publishTask2(SrcAndTarDir, progressBar, progressBar_all, editorPane, btnStart, btnStop, textArea);
				thread2.start();

				btnStop.addActionListener(new ActionListener() {

					@SuppressWarnings("deprecation")
					@Override
					public void actionPerformed(ActionEvent e) {

						if (!threadFlag) {
							thread2.suspend();
							threadFlag = true;
						} else if (threadFlag) {
							thread2.resume();
							threadFlag = false;
						}

					}
				});

			}
		});
		btnStart.setBounds(281, 85, 97, 23);
		frmDircopyuiver.getContentPane().add(btnStart);

		SrcDirText = new JTextField();
		SrcDirText.setBounds(34, 22, 177, 21);
		frmDircopyuiver.getContentPane().add(SrcDirText);
		SrcDirText.setColumns(10);
		SrcDirText.setText(LastSrcDir);

		TarDirText = new JTextField();
		TarDirText.setColumns(10);
		TarDirText.setBounds(34, 53, 177, 21);
		frmDircopyuiver.getContentPane().add(TarDirText);
		TarDirText.setText(LastTarDir);

		JLabel label = new JLabel("\uC804\uCCB4 \uC9C4\uD589\uB3C4");
		label.setBounds(12, 134, 72, 32);
		frmDircopyuiver.getContentPane().add(label);

		JLabel label_1 = new JLabel("\uC9C4\uD589\uB3C4");
		label_1.setBounds(12, 101, 72, 32);
		frmDircopyuiver.getContentPane().add(label_1);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(34, 175, 344, 76);
		frmDircopyuiver.getContentPane().add(editorPane);

//		JTextArea textArea = new JTextArea();
//		textArea.setBounds(34, 261, 344, 107);
//		textArea.setEditable(false);
//		textArea.append("text");
//		frmDircopyuiver.getContentPane().add(textArea);
		

	}
}

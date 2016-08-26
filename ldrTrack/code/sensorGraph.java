package solar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import com.fazecast.jSerialComm.SerialPort;

public class SensorGraph {
	
	static SerialPort chosenPort;
	static float x = 0,t=0,y=0;
	static float z=0;
	static int line;
	static int key=4,h=0,keyx=4,keyy=4,keyl=1,keyh=1;
	static boolean bool=false;
	 	
	public static void main(String[] args) {
		
		// create and configure the window
		JFrame window = new JFrame();
		JFrame warning = new JFrame();
		warning.setBounds(500, 500, 350, 500);
		window.setTitle("Sensor Graph GUI");
		window.setSize(600, 400);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextField field=new JTextField();
		JTextField field1=new JTextField();
		JTextField field2=new JTextField();
		JButton warn = new JButton("WARNING");
		warning.add(warn);
		// create a drop-down box and connect button, then place them at the top of the window
		JComboBox<String> portList = new JComboBox<String>();
		JPanel topPanel = new JPanel();
		JPanel pane = new JPanel();
		JButton connectButton = new JButton("Start");
		JLabel label=new JLabel("Power");
		JLabel label1=new JLabel("Set Limits");
		window.add(field);
		window.add(field1);
		window.add(field2);
		window.add(label);
		window.add(label1);
		field.setBounds(150, 5, 35, 27);
		field1.setBounds(440, 5, 35, 27);
		field2.setBounds(100, 5, 35, 27);
		label.setBounds(395, 5, 100, 27);
		label1.setBounds(35, 5, 100, 27);
		topPanel.add(connectButton);
		window.add(topPanel, BorderLayout.NORTH);
		JButton connectButton2 = new JButton("Stop");
		topPanel.add(connectButton2);
		// populate the drop-down box
		SerialPort[] portNames = SerialPort.getCommPorts();
		
		for(int i = 0; i < portNames.length; i++)
			portList.addItem(portNames[i].getSystemPortName());
		
		// create the line graph
		XYSeries series = new XYSeries("Light Sensor Readings");
		XYSeriesCollection dataset = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory.createXYLineChart("Light Sensor Readings", "Time (seconds)", "LDR Reading", dataset);
		window.add(new ChartPanel(chart), BorderLayout.CENTER);
		
	
	// configure the connect button and use another thread to listen for data

	
		
		// attempt to connect to the serial port
		chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
		chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		
		if(chosenPort.openPort()) {
			
			portList.setEnabled(false);
		}
		
		PrintWriter output=new PrintWriter(chosenPort.getOutputStream());
		// create a new thread that listens for incoming text and populates the graph
		Thread thread = new Thread(){
			@Override public void run() {
			Scanner scanner = new Scanner(chosenPort.getInputStream());
			while(scanner.hasNextLine()) {
			try {
				
				if(bool==true)
				{
					warn.setBackground(new java.awt.Color(255, 51, 51));
					bool=false;
				}
				else if(bool==false)
				{
					warn.setBackground(null);
					bool=true;
				}
				 line =1023- scanner.nextInt();
				 int k=line;
				String a="";
				a=a+k;
				field1.setText(a);
				//int number = Integer.parseInt(line);
				series.add(x++, line);
				window.repaint();
				chart.clearSubtitles();
				if(Integer.parseInt(field.getText())>Integer.parseInt(field2.getText()))
				{
					t=Integer.parseInt(field.getText());
					y=Integer.parseInt(field2.getText());
				}
				else
				{
					y=Integer.parseInt(field.getText());
					t=Integer.parseInt(field2.getText());
				}
				if(line<t)
				{	
					keyh=1;
				}
				if(line>y)
				{
					keyl=1;
				}
				if(key==1 && line>t || key==1 &&  line<y)
				{
					output.print("1");
					output.flush();
				}
				else 
				{
					try {
						output.print("0");
						output.flush();		
					} catch (Exception e) {
						// TODO: handle exception
					}
				}		
				if(keyh==1 && keyl==1 && keyx==1 && line>t || keyh==1 && keyl==1 &&  keyx==1 &&  line<y)
				{
					keyx=0;
					
					output.print("3");
					output.flush();
					
					
					try {
						
					warn.setVisible(true);
							
						Calendar cal = Calendar.getInstance();
						Date currentTime = cal.getTime();
						warning.setVisible(true);
						if(line>t)
						{
							JLabel labelx=new JLabel(""+currentTime+" + High Light");
							warning.add(pane);
							pane.add(labelx);
							keyh=0;
							
						}
						else if(line<y)
						{
							JLabel labelx=new JLabel(""+currentTime+" + Low Light");
							warning.add(pane);
							pane.add(labelx);
							keyl=0;
						
						}
						warning.repaint();
							
					} catch (Exception e) {
						// TODO: handle exception
					}
						
					}	
						
					} catch(Exception e) {}
				}
				canner.close();
			}
		};
		thread.start();
		connectButton.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent arg0) {
				key=1;
				keyx=1;
				connectButton.setBackground(new java.awt.Color(51, 255, 51));
				connectButton2.setBackground(null);
			}
			
		});
				
		connectButton2.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent arg0) {
				
				key=0;
				keyx=0;
				connectButton2.setBackground(new java.awt.Color(255, 51, 51));
				connectButton.setBackground(null);	
				
			}
		});
		warn.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent arg0) {
				warn.setVisible(false);	
				keyx=1;
				output.print("2");
				output.flush();
	
			}
		});
				
		// show the window
		window.setVisible(true);
	}

}
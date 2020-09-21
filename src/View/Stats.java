package View;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;

public class Stats extends JFrame {
	private JPanel contentPane;
	private static final long serialVersionUID = 6294689542092367723L;
	private static Stats statsMember;
	static {
		statsMember = new Stats();
	}
	public static Stats getInstance(){
		return statsMember;
	}
	//PieChart DataSet
	private PieDataset createDataset() {
			    DefaultPieDataset dataset=new DefaultPieDataset();
	    dataset.setValue("국어", 50);
	    dataset.setValue("사회", 90);
	    dataset.setValue("행정법", 20);
	    dataset.setValue("한국사", 70);
	    dataset.setValue("영어", 65);
	    return dataset;
	 }
	
	public Stats() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1038, 729);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		Font font = new Font("돋움", Font.BOLD, 15);
		JLabel lbldn = new JLabel("5월 2주차");
		lbldn.setBounds(232, 123, 80, 15);
		lbldn.setFont(font);
		contentPane.add(lbldn);
		
		JButton button = new JButton("<");
		button.setBounds(123, 131, 73, 31);
		button.setFont(font);
		contentPane.add(button);
		
		JButton button_1 = new JButton(">");
		button_1.setBounds(330, 131, 73, 31);
		button_1.setFont(font);
		contentPane.add(button_1);
		
		JLabel label = new JLabel("05.06 - 05.12");
		label.setBounds(208, 148, 120, 15);
		label.setFont(font);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\uCE74\uD14C\uACE0\uB9AC \uBCC4");
		label_1.setBounds(706, 131, 96, 15);
		label_1.setFont(font);
		contentPane.add(label_1);
		
		JButton button_2 = new JButton("<");
		button_2.setFont(new Font("돋움", Font.BOLD, 15));
		button_2.setBounds(621, 123, 73, 31);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton(">");
		button_3.setFont(new Font("돋움", Font.BOLD, 15));
		button_3.setBounds(803, 123, 73, 31);
		contentPane.add(button_3);

        // 원형그래프
        PieChart pieChart = new PieChart();
	    ChartPanel pieChartPanel = pieChart.getChartPanel();
	    pieChartPanel.setBounds(510, 190, 500, 270);
	    pieChartPanel.setVisible(true);
		contentPane.add(pieChartPanel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 189, 500, 270);
		contentPane.add(panel);
		CardLayout cardLayout=new CardLayout(0,0);
		panel.setLayout(cardLayout);
		
				// 막대그래프
				HistogramChart histogramPanel = new HistogramChart();
				panel.add(histogramPanel, "막대그래프");
				histogramPanel.setVisible(true);
				
				LineChart lineChart = new LineChart();
				ChartPanel lineChartPanel = lineChart.getLineChartPanel();
				
				panel.add(lineChartPanel, "선형그래프");
				
				setContentPane(contentPane);	
				
				histogramPanel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
					 //cardLayout.last(panel);
						cardLayout.show(panel, "선형그래프");
						panel.repaint();
					}
				});
			
				lineChartPanel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
					//cardLayout.first(panel);
						cardLayout.show(panel, "막대그래프");
					panel.repaint();	
					}
				});
	}
	
	/*public String[] statsLoad(){
		Object[] simpleMemoList = simpleMemoInfoDao.getSimpleMemoInfoList();
		
	}*/
	public JPanel getContentPane() {
		return this.contentPane;
	}
	
	public void chartDataInput(List<String> chartDataList){
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		
		public void run() {
			try {
				Stats frame = new Stats();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});
	}
}


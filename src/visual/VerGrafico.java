package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import logico.Altice;
import logico.Factura;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerGrafico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JPanel panelGrafico = new JPanel();

	public VerGrafico() {
		setTitle("Ganancias Por Mes");
		createBarChart();
		setBounds(100, 100, 666, 479);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			
			contentPanel.add(panelGrafico, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		
	}
	public static void createBarChart() {
		float [] cantActmeses = new float[12];
		getCantMonth(cantActmeses);
		DefaultCategoryDataset dataLine = new DefaultCategoryDataset();
		String[] mes = {"Ene","Feb", "Mar", "Abr","May", "Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
		for(int i= 0; i < 12; i ++) {
			dataLine.addValue(cantActmeses[i], "Eventos", mes[i]);
		}
		JFreeChart lineChart = ChartFactory.createBarChart3D("Ganancias Facturas Por Mes", "Meses","Ganancias",dataLine, 
				PlotOrientation.VERTICAL, true, true, false);
		panelGrafico.setLayout(new BorderLayout(0, 0));
		ChartPanel linePanel = new ChartPanel(lineChart);
		lineChart.getPlot().setBackgroundPaint(Color.DARK_GRAY);
		lineChart.setBackgroundPaint(Color.lightGray);
		panelGrafico.add(linePanel);
		linePanel.setLayout(new BorderLayout(0, 0));
	}
	private static void getCantMonth(float[] mes) {
		Calendar date = Calendar.getInstance();
		for(int i = 0; i < 12 ; i ++) {
			for(Factura factura : Altice.getInstance().getMisFacturas()) {
				if(factura.isPagada()) {
					date.setTime(factura.getFecha_facturacion());
					if(date.get(Calendar.MONTH) == i) {
						mes[i] += factura.getMonto();
					}
				}
			}
		}
		
	}

}

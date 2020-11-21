package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Altice;
import logico.Plan;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.SpinnerNumberModel;

public class RegPlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Plan miPlan;
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JTextField txtId = new JTextField();
	private JSpinner spnMensualidad = new JSpinner();
	private JSpinner spnCanales = new JSpinner();
	private JSpinner spnInternet = new JSpinner();
	private JSpinner spnVoz = new JSpinner();
	private JSpinner spnPrecio = new JSpinner();
	public RegPlan(Plan plan) {
		
		if(plan == null) {
			setTitle("Crear Plan");
			if(Altice.getInstance().getMisPlanes().size() == 0) {
				txtId.setText(Integer.toString(0));
			}else {
				int cant_planes = Altice.getInstance().getMisPlanes().size();
				String id_ultimo = Altice.getInstance().getMisPlanes().get(cant_planes - 1).getId();
				txtId.setText(Integer.toString((Integer.parseInt(id_ultimo) + 1)));
			}
			
		}else {
			setTitle("Modificar Plan");
			
		}
		
		miPlan = plan;
		setBounds(100, 100, 512, 241);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Caracteristicas del plan", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblId = new JLabel("ID:");
			lblId.setBounds(10, 26, 46, 14);
			panel.add(lblId);
			
			txtId.setEditable(false);
			txtId.setBounds(66, 23, 86, 20);
			panel.add(txtId);
			txtId.setColumns(10);
			
			JLabel lblPrecio = new JLabel("Precio Apertura: ");
			lblPrecio.setBounds(10, 51, 110, 14);
			panel.add(lblPrecio);
			
			spnPrecio.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			spnPrecio.setBounds(130, 48, 86, 20);
			panel.add(spnPrecio);
			
			JLabel lblMensualidad = new JLabel("Mensualidad: ");
			lblMensualidad.setBounds(10, 76, 86, 14);
			panel.add(lblMensualidad);
			
			spnMensualidad.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			spnMensualidad.setBounds(130, 73, 86, 20);
			panel.add(spnMensualidad);
			
			JLabel lblMinutosVoz = new JLabel("Minutos Voz:");
			lblMinutosVoz.setBounds(10, 101, 86, 14);
			panel.add(lblMinutosVoz);

			spnVoz.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnVoz.setBounds(130, 98, 86, 20);
			panel.add(spnVoz);
			
			JLabel lblVelocidadDeInternet = new JLabel("Velocidad De Internet: ");
			lblVelocidadDeInternet.setBounds(226, 76, 124, 14);
			panel.add(lblVelocidadDeInternet);

			spnInternet.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnInternet.setBounds(360, 73, 101, 20);
			panel.add(spnInternet);
			
			JLabel lblCantidadCanales = new JLabel("Cantidad Canales:");
			lblCantidadCanales.setBounds(229, 101, 110, 14);
			panel.add(lblCantidadCanales);
			
			spnCanales.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnCanales.setBounds(360, 98, 101, 20);
			panel.add(spnCanales);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if((int)spnVoz.getValue() == 0 && (int)spnInternet.getValue() == 0 && (int)spnCanales.getValue() == 0) {
							JOptionPane.showMessageDialog(null, "Al menos una de las caracteristicas del plan debe ser mayor que 0", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							if(plan == null) {
								String id = txtId.getText();
								int voz = (int)spnVoz.getValue();
								int internet = (int)spnInternet.getValue();
								int canales = (int)spnCanales.getValue();
								float apertura = (float) spnPrecio.getValue();
								float mensualidad = (float)spnMensualidad.getValue();
								Plan nuevoPlan = new Plan(id,apertura,voz,internet,canales,mensualidad);
								Altice.getInstance().agregarPlan(nuevoPlan);
								JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Info", JOptionPane.INFORMATION_MESSAGE);
								clean();
								
							}else {
								plan.setCanales((int)spnCanales.getValue());
								plan.setMensualidad((float)spnMensualidad.getValue());
								plan.setMinutos((int)spnVoz.getValue());
								plan.setPrecio_apertura((float) spnPrecio.getValue());
								plan.setVelocidad_internet((int)spnInternet.getValue());
								Altice.getInstance().modificarPlan(plan);
								ListPlanes.loadPlanes();
								dispose();
							}

						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		if(miPlan != null) {
			loadPlan();
		}
	}
	private void clean() {
		spnVoz.setValue((int) 0);
		spnInternet.setValue((int) 0);
		spnCanales.setValue((int) 0);
		spnPrecio.setValue((float) 0);
		spnMensualidad.setValue((float) 0);
		
		int cant_planes = Altice.getInstance().getMisPlanes().size();
		String id_ultimo = Altice.getInstance().getMisPlanes().get(cant_planes - 1).getId();
		txtId.setText(Integer.toString((Integer.parseInt(id_ultimo) + 1)));
		
	}
	private void loadPlan() {
		spnVoz.setValue(miPlan.getMinutos());
		spnInternet.setValue(miPlan.getVelocidad_internet());
		spnCanales.setValue(miPlan.getCanales());
		spnPrecio.setValue(miPlan.getPrecio_apertura());
		spnMensualidad.setValue(miPlan.getMensualidad());
		txtId.setText(miPlan.getId());
	}
}

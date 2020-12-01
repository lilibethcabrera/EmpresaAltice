package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Factura;
import logico.Plan;
import visual.RegCliente;
import logico.Altice;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class ListFacturas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private static Object[] fila;
	private JTable table;
	private JButton btnCancelar;
	private JButton btnPagar;
	private String selecte = "";
	private static ArrayList<Factura> facturas;
	private JButton btnImprimirFactura;

	public ListFacturas(ArrayList<Factura> misFacturas, Cliente miCliente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListFacturas.class.getResource("/Imagenes/bill(1).png")));
		getContentPane().setBackground(SystemColor.activeCaptionBorder);
		facturas = misFacturas;
		setTitle("Facturas Actuales");
		setBounds(100, 100, 557, 357);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaptionBorder);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						if(index >= 0) {
							selecte = table.getValueAt(index, 0).toString();
							if(miCliente.buscarFacturaPorId(selecte).isPagada()) {
								btnPagar.setEnabled(false);
							}else {
								btnPagar.setEnabled(true);
							}
							btnImprimirFactura.setEnabled(true);
						}
					}
				});
				model = new DefaultTableModel();
				String[] columnNames = {"Id","Id de Plan", "Fecha de Facturacion","Monto", "Estado"};
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);
				scrollPane.setViewportView(table);
				loadFacturas();
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.activeCaptionBorder);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnPagar = new JButton("Pagar");
				btnPagar.setEnabled(false);
				btnPagar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Factura factu = Altice.getInstance().buscarFacturaPorId(selecte);
						int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea pagar la factura con un monto de: " + factu.getMonto(),"Información",JOptionPane.WARNING_MESSAGE);
						if(option == JOptionPane.OK_OPTION) {
							miCliente.pagarFactura(selecte);
							btnPagar.setEnabled(false);
							selecte="";
							JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
							loadFacturas();
							ListCliente.loadClientes();
							PlanesCliente.loadPlanes();
						}
						
					}
				});
				{
					btnImprimirFactura = new JButton("Imprimir Factura");
					btnImprimirFactura.setEnabled(false);
					btnImprimirFactura.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Factura factu = Altice.getInstance().buscarFacturaPorId(selecte);
							try {
								Altice.getInstance().imprimirFactura(factu);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
							btnImprimirFactura.setEnabled(false);
						}
					});
					buttonPane.add(btnImprimirFactura);
				}
				buttonPane.add(btnPagar);
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
		loadFacturas();
		ListCliente.loadClientes();
		PlanesCliente.loadPlanes();
	}
	 //{"Id","Fecha de Facturacion","Monto","Id de Plan", "Estado"};
	public static void loadFacturas() {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for(Factura factura : facturas) {
			fila[0] = factura.getId();
			fila[1] = factura.getPlan().getId();
			fila[2] = format.format(factura.getFecha_facturacion());
			fila[3] = factura.getMonto();
			fila[4] = factura.isPagada() ? "Pagada" : "No pagada";
			model.addRow(fila);
		}
			
		
	}
	

}

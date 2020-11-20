package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width,(dim.height-50));
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		JMenuItem mntmRegistrarCliente = new JMenuItem("Registrar Cliente");
		mntmRegistrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCliente registrarCliente = new RegCliente(null);
				registrarCliente.setModal(true);
				registrarCliente.setVisible(true);
			}
		});
		mnCliente.add(mntmRegistrarCliente);
		
		JMenuItem mntmListarClientes = new JMenuItem("Listar Clientes");
		mntmListarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListCliente listarCliente = new ListCliente();
				listarCliente.setModal(true);
				listarCliente.setVisible(true);
			}
		});
		mnCliente.add(mntmListarClientes);
		
		JMenu mnEmpleado = new JMenu("Empleado");
		menuBar.add(mnEmpleado);
		
		JMenuItem mntmRegistrarEmpleado = new JMenuItem("Registrar Empleado");
		mntmRegistrarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEmpleado registrarEmpleado = new RegEmpleado(null);
				registrarEmpleado.setModal(true);
				registrarEmpleado.setVisible(true);
			}
		});
		mnEmpleado.add(mntmRegistrarEmpleado);
		
		JMenuItem mntmListarEmpleado = new JMenuItem("Listar Empleado");
		mntmListarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListEmpleado listarEmpleado = new ListEmpleado();
				listarEmpleado.setModal(true);
				listarEmpleado.setVisible(true);
			}
		});
		mnEmpleado.add(mntmListarEmpleado);
		
		JMenu mnFactura = new JMenu("Factura");
		menuBar.add(mnFactura);
		
		JMenuItem mntmGenerarFactura = new JMenuItem("Generar Factura");
		mnFactura.add(mntmGenerarFactura);
		
		JMenuItem mntmListarFacturas = new JMenuItem("Listar Facturas");
		mnFactura.add(mntmListarFacturas);
		
		JMenu mnPlanes = new JMenu("Planes");
		menuBar.add(mnPlanes);
		
		JMenuItem mntmCrearPlan = new JMenuItem("Crear Plan");
		mnPlanes.add(mntmCrearPlan);
		
		JMenuItem mntmListarPlanes = new JMenuItem("Listar Planes");
		mnPlanes.add(mntmListarPlanes);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}

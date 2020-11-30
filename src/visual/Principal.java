package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Imagenes.ImagenFondoPrincipal;
import logico.Altice;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImagenFondoPrincipal p = new ImagenFondoPrincipal("/Imagenes/Fondo.jpg");
					Principal frame = new Principal();
					frame.setVisible(true);
					frame.getContentPane().add(p);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		File datos = new File("Altice.dat");
		if(datos.exists()) {
			FileInputStream entradaFichero;
			 try {
	                entradaFichero = new FileInputStream(datos);
	                ObjectInputStream entrada = new ObjectInputStream(entradaFichero);
	                Altice.setInstance((Altice)entrada.readObject());
	                entradaFichero.close();
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
			
		}
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                FileOutputStream f;
                try {
                    f = new FileOutputStream("Altice.dat");
                    ObjectOutputStream guardar = new ObjectOutputStream(f);
                    guardar.writeObject(Altice.getInstance());
                    f.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
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
		
		JMenuItem mntmSuscribirEnPlan = new JMenuItem("Suscribir en Plan");
		mntmSuscribirEnPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AplicarPlanCliente aplicar = new AplicarPlanCliente();
				aplicar.setModal(true);
				aplicar.setVisible(true);
			}
		});
		mnCliente.add(mntmSuscribirEnPlan);
		
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
		
		JMenuItem mntmGenerarReporte = new JMenuItem("Generar Reporte");
		mntmGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Reporte generado con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnFactura.add(mntmGenerarReporte);
		
		JMenu mnPlanes = new JMenu("Planes");
		menuBar.add(mnPlanes);
		
		JMenuItem mntmCrearPlan = new JMenuItem("Crear Plan");
		mntmCrearPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegPlan registrarPlan = new RegPlan(null);
				registrarPlan.setModal(true);
				registrarPlan.setVisible(true);
			}
		});
		mnPlanes.add(mntmCrearPlan);
		
		JMenuItem mntmListarPlanes = new JMenuItem("Listar Planes");
		mntmListarPlanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListPlanes listarPlanes = new ListPlanes();
				listarPlanes.setModal(true);
				listarPlanes.setVisible(true);
			}
		});
		mnPlanes.add(mntmListarPlanes);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}

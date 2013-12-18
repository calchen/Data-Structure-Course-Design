package com.cky.gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import com.cky.service.MainService;

/**
 * 主界面 <br />
 * 
 * @version 1.0 <br />
 * @author 陈恺垣 chenkaiyuan1993@gmail.com
 */
public class MainFrame extends JFrame {
	/**
	 * 通过该成员变量操作数据
	 */
	private MainService service;
	/**
	 * 窗体本身
	 */
	private MainFrame mainFrame;
	/**
	 * 
	 */
	private JPanel contentPane;
	/**
	 * 右上表格
	 */
	private JTable rightTable;
	/**
	 * 左上表格
	 */
	private JTable leftTable;
	/**
	 * 左下表格
	 */
	private JTable bottomTable;
	/**
	 * 出发地下拉框
	 */
	private JComboBox<String> beginComboBox;
	/**
	 * 目的地下拉框
	 */
	private JComboBox<String> endComboBox;

	/**
	 * main函数，程序起点。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 构造函数 <br />
	 * 采用与系统相同的风格构造界面。<br />
	 * private类型实现单态模式保证程序安全。<br />
	 * 窗口大小为800×600，默认出现在屏幕中间。
	 */
	private MainFrame() {
		this.mainFrame = this;
		this.service = new MainService();

		// 设置默认风格
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setTitle("交通咨询系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置窗口大小并居中显示
		setSize(800, 600);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int) (toolkit.getScreenSize().getWidth() - getWidth()) / 2;
		int y = (int) (toolkit.getScreenSize().getHeight() - getHeight()) / 2;
		setLocation(x, y);

		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("文件");
		menuBar.add(fileMenu);

		JMenuItem exitMenuItem = new JMenuItem("退出");
		fileMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(new ActionListener() {

			// 退出关闭程序
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		JMenu helpMenu = new JMenu("帮助");
		menuBar.add(helpMenu);

		JMenuItem aboutMenuItem2 = new JMenuItem("关于作者");
		helpMenu.add(aboutMenuItem2);
		aboutMenuItem2.addActionListener(new ActionListener() {
			// 关于操作
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"姓名：陈恺垣  学号：20112308039 专业：计算机科学与技术", "关于",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// 主层
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane leftScrollPane = new JScrollPane();
		leftScrollPane.setBounds(36, 38, 238, 220);
		contentPane.add(leftScrollPane);

		leftTable = new JTable();
		leftTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		leftTable.getTableHeader().setReorderingAllowed(false);
		setLeftTableModel();
		leftScrollPane.setViewportView(leftTable);
		leftTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						// TODO Auto-generated method stub
						if (leftTable.getSelectedRow() >= 0) {
							setRightTableModel((String) leftTable.getValueAt(
									leftTable.getSelectedRow(), 0));
						}
					}
				});

		JScrollPane rightScrollPane = new JScrollPane();
		rightScrollPane.setBounds(310, 38, 436, 220);
		contentPane.add(rightScrollPane);
		rightTable = new JTable();
		rightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rightTable.getTableHeader().setReorderingAllowed(false);
		setRightTableModel(null);
		rightScrollPane.setViewportView(rightTable);

		JScrollPane bottomScrollPane = new JScrollPane();
		bottomScrollPane.setBounds(36, 293, 307, 220);
		contentPane.add(bottomScrollPane);

		bottomTable = new JTable();
		bottomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bottomTable.getTableHeader().setReorderingAllowed(false);
		setBottomTableModel(null);
		bottomScrollPane.setViewportView(bottomTable);

		JPanel searchPanel = new JPanel();
		searchPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		searchPanel.setBounds(378, 293, 209, 220);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);

		JLabel beginLabel = new JLabel("出发地");
		beginLabel.setBounds(12, 20, 36, 15);
		searchPanel.add(beginLabel);

		JLabel endLabel = new JLabel("目的地");
		endLabel.setBounds(12, 55, 36, 15);
		searchPanel.add(endLabel);

		beginComboBox = new JComboBox();
		beginComboBox.setBounds(60, 17, 135, 21);
		beginComboBox.setSelectedIndex(-1);
		searchPanel.add(beginComboBox);

		endComboBox = new JComboBox();
		endComboBox.setBounds(60, 52, 135, 21);
		endComboBox.setSelectedIndex(-1);
		searchPanel.add(endComboBox);

		JButton query1Button = new JButton("Dijkstra查询");
		query1Button.setBounds(52, 90, 105, 23);
		searchPanel.add(query1Button);
		query1Button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				String begin = beginComboBox.getSelectedItem().toString();
				String end = endComboBox.getSelectedItem().toString();

				setBottomTableModel(service.getPathByDijkstea(begin, end));
			}

		});

		JButton query2Button = new JButton("Floyd查询");
		query2Button.setBounds(52, 133, 105, 23);
		searchPanel.add(query2Button);
		query2Button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				String begin = beginComboBox.getSelectedItem().toString();
				String end = endComboBox.getSelectedItem().toString();

				setBottomTableModel(service.getPathByFloyd(begin, end));

			}

		});

		JButton restButton = new JButton("重置");
		restButton.setBounds(52, 176, 105, 23);
		searchPanel.add(restButton);
		restButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				beginComboBox.setSelectedIndex(0);
				endComboBox.setSelectedIndex(0);

			}

		});

		JPanel loadPanel = new JPanel();
		loadPanel.setBounds(617, 293, 142, 220);
		contentPane.add(loadPanel);
		loadPanel.setLayout(null);

		JButton loadButton = new JButton("加载数据");
		loadButton.setBounds(24, 58, 93, 23);
		loadPanel.add(loadButton);
		loadButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				// 加载数据前先卸载数据
				service.unloadData();

				setLeftTableModel();
				setRightTableModel(null);
				setBottomTableModel(null);
				setComboBox();

				// 弹窗选择数据文件
				JFileChooser jfChooser = new JFileChooser();
				jfChooser.setDialogTitle("选择数据文件");
				jfChooser.setFileFilter(new FileFilter() {
					@Override
					public boolean accept(File f) {
						if (f.getName().endsWith("txt") || f.isDirectory())
							return true;
						return false;
					}

					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return "(*.txt)";
					}
				});
				int result = jfChooser.showOpenDialog(mainFrame);
				// 确认打开
				if (result == JFileChooser.APPROVE_OPTION) {
					File fileIn = jfChooser.getSelectedFile();

					if (fileIn.exists()) {
						JOptionPane.showMessageDialog(mainFrame, "数据已加载");

						service.loadData(fileIn.getPath());
						setLeftTableModel();
						setComboBox();
					}
				}
			}

		});

		JButton restDataButton = new JButton("重置数据");
		restDataButton.setBounds(24, 139, 93, 23);
		loadPanel.add(restDataButton);
		restDataButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				service.unloadData();
				setLeftTableModel();
				setRightTableModel(null);
				setBottomTableModel(null);
				setComboBox();
			}

		});

		JLabel label_1 = new JLabel("请选择");
		label_1.setBounds(20, 13, 54, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("查询结果");
		label_2.setBounds(20, 265, 54, 15);
		contentPane.add(label_2);

	}

	// 继承DefaultTableModel，重写构造函数以及isCellEditable方法
	class TableModel extends DefaultTableModel {
		boolean[] columnEditables = null;

		public TableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
			columnEditables = new boolean[columnNames.length];
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			// TODO Auto-generated method stub
			return columnEditables[column];
		}
	}

	/**
	 * 设置左上表格的Model
	 */
	private void setLeftTableModel() {
		if (service.dataHasLoad()) {
			String[] nodes = service.getNodeList();
			Object[][] data = new String[nodes.length][1];
			for (int i = 0; i < nodes.length; i++) {
				data[i][0] = nodes[i];
			}

			leftTable.setModel(new TableModel(data, new String[] { service
					.getNodeTitle()[0] }));
		} else {
			leftTable.setModel(new TableModel(new String[][] { {} },
					new String[] { " " }));
		}
	}

	/**
	 * 根据节点名设置右上表格的Model
	 * 
	 * @param node
	 *            节点名称
	 */
	private void setRightTableModel(String node) {
		if (service.dataHasLoad()) {
			String[] nodes = service.getNodeConnect(node);

			if (nodes != null) {
				String[][] data = new String[nodes.length][2];
				for (int i = 0; i < nodes.length; i++) {
					data[i][0] = nodes[i];
					data[i][1] = String.valueOf(service.getNodeValue(node,
							nodes[i]));
				}
				rightTable
						.setModel(new TableModel(data, service.getNodeTitle()));
			} else {
				rightTable.setModel(new TableModel(new String[][] { {} },
						service.getNodeTitle()));
			}
		} else {
			rightTable.setModel(new TableModel(new String[][] { {} },
					new String[] { " ", " " }));
		}
	}

	/**
	 * 根据输入的节点数组设置左下表格的Model
	 * 
	 * @param path
	 *            两个节点间最短路径上所有节点，包括这两个节点。如果为null则设置Model为默认形式
	 */
	private void setBottomTableModel(String[] path) {
		if (service.dataHasLoad()) {
			if (path != null && path.length > 0) {
				String[][] data = new String[path.length][3];
				int sum = 0;
				for (int i = 0; i < path.length - 1; i++) {
					data[i][0] = path[i];
					data[i][1] = path[i + 1];
					int value = service.getNodeValue(path[i], path[i + 1]);
					sum += value;
					data[i][2] = String.valueOf(value);
				}
				data[path.length-1][1] = "总和";
				data[path.length-1][2] = String.valueOf(sum); 

				bottomTable.setModel(new TableModel(data, new String[] { "出发地",
						"目的地", service.getNodeTitle()[1] }));
			} else {
				bottomTable
						.setModel(new TableModel(new String[][] { {"","","无法到达"} },
								new String[] { "出发地", "目的地",
										service.getNodeTitle()[1] }));
			}

		} else {
			bottomTable.setModel(new TableModel(new String[][] { {} },
					new String[] { "出发地", "目的地", "" }));
		}
	}

	/**
	 * 设置comboBox的Model
	 */
	private void setComboBox() {
		if (service.dataHasLoad()) {
			String[] data = service.getNodeList();
			beginComboBox.setModel(new DefaultComboBoxModel<String>(data));
			beginComboBox.setSelectedIndex(0);
			endComboBox.setModel(new DefaultComboBoxModel<String>(data));
			endComboBox.setSelectedIndex(0);
		} else {
			String[] data = new String[1];
			beginComboBox.setModel(new DefaultComboBoxModel<String>(data));
			beginComboBox.setSelectedIndex(0);
			endComboBox.setModel(new DefaultComboBoxModel<String>(data));
			endComboBox.setSelectedIndex(0);
		}
	}

}

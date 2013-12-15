package com.cky.gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.cky.model.Graphic;

import java.util.ArrayList;


/**
 * 文件名：MainFrame.java
 * 作   者： 陈恺垣 E-mail:chenkaiyuan1993@gmail.com
 * 创建时间：2013年12月14日 下午3:53:21
 * 类说明 ：主界面
 */
public class MainFrame extends JFrame{
	private JPanel contentPane;
	private JTable rightTable;
	private JTable leftTable;
	private JTable bottomTable;
	
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
	
	private MainFrame() {
		// 设置默认风格
		try {
			// Windows风格
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

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

		JMenu aboutMenu = new JMenu("关于");
		menuBar.add(aboutMenu);

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
		DefaultTableModel leftTableModel = new DefaultTableModel(
				new Object[][] { { "A" }, { "B" }, { "C" }, { "D" }, { "E" },
						{ "F" }, { "G" }, { "H" }, { "I" }, { "J" }, { "K" },
						{ "L" }, { "M" }, { "N" }, },
				new String[] { "\u57CE\u5E02" }) {
			boolean[] columnEditables = new boolean[] { false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		leftTable.setModel(leftTableModel);
		leftTable.getTableHeader().setReorderingAllowed(false);
		leftTable.getColumnModel().getColumn(0).setResizable(false);
		leftScrollPane.setViewportView(leftTable);

		JScrollPane rightScrollPane = new JScrollPane();
		rightScrollPane.setBounds(310, 38, 436, 220);
		contentPane.add(rightScrollPane);
		rightTable = new JTable();
		rightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		rightTable.setModel(new DefaultTableModel(new Object[][] {
				{ "A", "1" }, { "B", "1" }, { "C", "1" }, { "D", "1" },
				{ "E", "1" }, { "F", "1" }, { "G", "1" }, { "H", "1" },
				{ "I", "1" }, { "J", "1" }, { "K", "1" }, { "L", "1" },
				{ "M`", "1" }, { "N", "1" }, { "O", "1" }, { "P", "1" },
				{ "Q", "1" }, { "R", "1" }, { "S", "1" }, }, new String[] {
				"\u57CE\u5E02", "\u7968\u4EF7" }) {
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		rightTable.getTableHeader().setReorderingAllowed(false);
		rightScrollPane.setViewportView(rightTable);

		JScrollPane bottomScrollPane = new JScrollPane();
		bottomScrollPane.setBounds(36, 293, 307, 220);
		contentPane.add(bottomScrollPane);

		bottomTable = new JTable();
		bottomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bottomTable.setModel(new DefaultTableModel(new Object[][] {
				{ "A", "B", null }, { "B", "C", null }, { "C", "D", null },
				{ "D", "E", null }, { "E", "F", null }, { "F", "G", null },
				{ "G", "H", null }, { "H", "I", null }, { "I", "J", null },
				{ "J", "K", null }, { "K", "L", null }, { "L", "M", null },
				{ "L", "M", null }, { "L", "M", null }, { "L", "M", null },
				{ "L", "M", null }, { "L", "M", null }, }, new String[] {
				"\u51FA\u53D1\u5730", "\u76EE\u7684\u5730", "\u7968\u4EF7" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bottomTable.getTableHeader().setReorderingAllowed(false);
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

		final JComboBox beginComboBox = new JComboBox();
		beginComboBox.setModel(new DefaultComboBoxModel(new String[] { "A",
				"B", "C", "D", "E", "F", "G" }));
		beginComboBox.setBounds(60, 17, 135, 21);
		beginComboBox.setSelectedIndex(-1);
		searchPanel.add(beginComboBox);

		final JComboBox endComboBox = new JComboBox();
		endComboBox.setModel(new DefaultComboBoxModel(new String[] { "A", "B",
				"C", "D", "E", "F", "G", "H" }));
		endComboBox.setBounds(60, 52, 135, 21);
		endComboBox.setSelectedIndex(-1);
		searchPanel.add(endComboBox);

		JButton query1Button = new JButton("Dijkstra查询");
		query1Button.setBounds(52, 90, 105, 23);
		searchPanel.add(query1Button);

		JButton query2Button = new JButton("Floyd查询");
		query2Button.setBounds(52, 133, 105, 23);
		searchPanel.add(query2Button);

		JButton restButton = new JButton("重置");
		restButton.setBounds(52, 176, 105, 23);
		searchPanel.add(restButton);
		restButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				beginComboBox.setSelectedIndex(-1);
				endComboBox.setSelectedIndex(-1);
			}
		});

		JPanel loadPanel = new JPanel();
		loadPanel.setBounds(617, 293, 142, 220);
		contentPane.add(loadPanel);
		loadPanel.setLayout(null);

		JButton loadButton = new JButton("加载数据");
		loadButton.setBounds(24, 58, 93, 23);
		loadPanel.add(loadButton);

		JButton restDataButton = new JButton("重置数据");
		restDataButton.setBounds(24, 139, 93, 23);
		loadPanel.add(restDataButton);

		JLabel label_1 = new JLabel("请选择");
		label_1.setBounds(20, 13, 54, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("查询结果");
		label_2.setBounds(20, 271, 54, 15);
		contentPane.add(label_2);

	}
	
	
	class TableModel extends DefaultTableModel {
		boolean[] columnEditables = null;

		public TableModel(ArrayList<Graphic> list) {
			
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			// TODO Auto-generated method stub
			return columnEditables[column];
		}

	}
	

}

package view.gui_utility;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.MasterProjectImpl;
import control.UnitImpl;
import view.LoaderImpl;
import view.MainGuiImpl;



public class LoaderManager extends JPanel {
	
	private static final long serialVersionUID = 5231397320694535637L;
	private final MasterProjectImpl project;
	public LoaderManager(MasterProjectImpl pr){
		this.project=pr;
	}
	
public class LoadUnit extends JPanel{
	
	private static final long serialVersionUID = -153428810527607474L;
		private final JFrame frame=new JFrame();
		private final JPanel panelCenter=new JPanel(new GridLayout(0, 1));
		private final JPanel panelBottom=new JPanel();
		private final JButton load=new JButton("Load");
		private String selected="";
		List<JButton> list=new ArrayList<>();
		public LoadUnit(){
			load.setEnabled(false);
			this.setLayout(new BorderLayout());
			try {
				if(!project.getListOfUnit().isEmpty()){
					this.add(new JLabel("Scegli quale reparto caricare"), BorderLayout.NORTH);
					for(String i: project.getListOfUnit()){
						JButton tmp=new JButton(i);
						tmp.addActionListener(e->{
							load.setEnabled(true);
							selected=tmp.getText();
							for(JButton t : list){
								t.setEnabled(false);
							}
						});
						list.add(tmp);
						panelCenter.add(tmp);					
					}
					load.addActionListener(e->{
						try {
							MyJFrameSingletonImpl.getInstance(project.loadUnit(selected));
						} catch (Exception k){
							k.printStackTrace();
						}
						new MainGuiImpl();
						this.frame.dispose();
						
					});

					ImageIcon img = new ImageIcon("res/back-icon-small.png");
					JButton t=new JButton();
					t.addActionListener(e->{
						new LoaderImpl();
						this.frame.dispose();
					});
					t.setIcon(img);
					panelBottom.add(t, BorderLayout.LINE_START);
					panelBottom.add(load, BorderLayout.LINE_END);
					this.add(panelCenter, BorderLayout.NORTH);
					this.add(panelBottom, BorderLayout.SOUTH);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			this.frame.add(this);
			this.frame.pack();
			this.frame.setLocationRelativeTo(null);
			this.frame.setVisible(true);
			
		}
	}


	public class CreateUnit extends JPanel{


		private static final long serialVersionUID = 6411454791261548663L;
		private final JFrame frame=new JFrame();
		public CreateUnit(){
			
			this.setLayout(new GridLayout(2,2));
			JLabel a = new JLabel("Nome nuovo reparto: ");
			TextField b = new TextField();
			this.add(a);
			this.add(b);
			JButton save=new JButton("Save");
			save.addActionListener(e->{
				try {
					project.save(new UnitImpl(b.getText()));
					MyJFrameSingletonImpl.getInstance(project.loadUnit(b.getText()));
					new MainGuiImpl();
				} catch (Exception k){
					k.printStackTrace();
				}
			});
			
			ImageIcon img = new ImageIcon("res/back-icon-small.png");
			JButton t=new JButton();
			t.addActionListener(e->{
				new LoaderImpl();
				this.frame.dispose();
			});
			t.setIcon(img);
			this.add(t);
			this.add(save);
			this.frame.add(this);
			this.frame.pack();
			this.frame.setLocationRelativeTo(null);
			this.frame.setVisible(true);
			
		}
	
	}
}

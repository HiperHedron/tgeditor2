package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;

import db.DBTools;
import db.entity.Interaction;
import db.entity.Item;
import db.entity.ReferenceDictionary;
import db.entity.Storyline;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class TgeWindow {

	
	private String dbFilePath = "Choose a valid *.db file.";
	//private DBTools dbt;
	private JFrame frame;
	private JTextField txtInteractionsReferenceDictionary_Reference;
	private JTextField txtInteractionsName;
	private JScrollPane scrollSysout;
	protected JTextArea systemOutput = new JTextArea();
	private JTabbedPane tabbedPane;
	private JPanel InteractionsPanel;
	private JButton btnInsertInteraction;
	private JTextField txtInteractions_Storyline_PageNo;
	private JTextField txtMapNo;
	private JTextField txtEmpathyValue;
	private JTextField txtSanityValue;
	private JTextField txtDescription;
	private JTextField txtOptionalJournalEntry;
	private JLabel lblStorylinepageno, lblMapno, lblEmpathyvalue, lblSanityvalue, lblDescription, lblOptionaljournalentry, lblReferenceDictionary_Reference, lblInteractionName;
	private JTextField dbPathField;
	private JLabel lblItemid;
	private JLabel lblNameItems;
	private JLabel lblDescriptionItems;
	private JTextField txtItemID;
	private JTextField txtNameItem;
	private JTextField txtDescriptionItem;
	private JButton btnInsertItem;
	private JLabel lblReference;
	private JLabel lblNameReference;
	private JLabel lblDescriptionReference;
	private JTextField txtDescriptionReference;
	private JTextField txtNameReference;
	private JTextField txtReferenceReference;
	private JButton btnInsertReferenceDictionary;
	private JTextField txtStorylinePageNo;
	private JTextField txtStorylineTargetWord;
	
	private String backup;
	private JButton btnRedoReference;
	private JEditorPane editorPane_Milestone;
	private JLabel EmpValWarning;
	private JLabel SanityValWarning;
	private JButton btnRefreshReferenceList;
	private List<String> rfls = new ArrayList<>();
	private JButton buttonItemsClear;
	private JButton buttonReferenceDictionaryClear;
	private JButton btnDeleteReference;
	private JButton btnDeleteItem;
	private JButton btnDeleteInteraction;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TgeWindow window = new TgeWindow();
					
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//dbt = new DBTools();
	}

	/**
	 * Create the application.
	 */
	public TgeWindow() {
		
		initialize();
		//dbt = new DBTools();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 663, 658);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		dbPathField = new JTextField();
		dbPathField.setBounds(227, 12, 410, 20);
		frame.getContentPane().add(dbPathField);
		dbPathField.setColumns(10);
		
		JLabel lblSelectedDb = new JLabel("Selected DB >>");
		lblSelectedDb.setBounds(121, 15, 96, 14);
		frame.getContentPane().add(lblSelectedDb);
		
		addTextArea();
		
		addBtnSelectDB();
		
		addTabbedPane();
		
		
		
		
		
		
		
	}

	private void addBtnInserInteractionToThePanel() {
		btnInsertInteraction = new JButton("Insert");
		btnInsertInteraction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Interaction i = new Interaction();
				Interaction i = new Interaction(
						txtInteractionsReferenceDictionary_Reference.getText(), 
						txtInteractionsName.getText(), 
						txtInteractions_Storyline_PageNo.getText(), 
						txtMapNo.getText(), 
						Integer.parseInt(txtEmpathyValue.getText()), 
						Integer.parseInt(txtSanityValue.getText()), 
						txtDescription.getText(), 
						txtOptionalJournalEntry.getText());
				try {
					DBTools.connect(dbPathField.getText());
					DBTools.insertInteraction(i);
					systemOutput.append("\n" + i.toString());
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		btnInsertInteraction.setBounds(10, 243, 89, 23);
		InteractionsPanel.add(btnInsertInteraction);
		
		EmpValWarning = new JLabel("Integer");
		EmpValWarning.setBounds(511, 114, 101, 14);
		InteractionsPanel.add(EmpValWarning);
		
		SanityValWarning = new JLabel("Integer");
		SanityValWarning.setBounds(511, 139, 101, 14);
		InteractionsPanel.add(SanityValWarning);
		
		JButton btnInteractionsClear = new JButton("Clear");
		btnInteractionsClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtInteractionsReferenceDictionary_Reference.setText("");
				txtInteractionsName.setText("");
				txtInteractions_Storyline_PageNo.setText("");
				txtMapNo.setText(""); 
				txtEmpathyValue.setText(""); 
				txtSanityValue.setText(""); 
				txtDescription.setText(""); 
				txtOptionalJournalEntry.setText("");
			}
		});
		btnInteractionsClear.setBounds(103, 243, 89, 23);
		InteractionsPanel.add(btnInteractionsClear);
		
		btnDeleteInteraction = new JButton("Delete Interaction");
		btnDeleteInteraction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBTools.connect(dbPathField.getText());
					systemOutput.append("\n" + DBTools.deleteInteraction(txtInteractionsReferenceDictionary_Reference.getText(), txtInteractionsName.getText()));
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		btnDeleteInteraction.setBounds(10, 277, 142, 23);
		InteractionsPanel.add(btnDeleteInteraction);
	}
	
	public void addJPanelStoryline() {
		
		
		Border blackline;
		blackline = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder tb = BorderFactory.createTitledBorder(blackline, "PageNo");
		tb.setTitleJustification(TitledBorder.LEFT);
		tb.setTitlePosition(TitledBorder.ABOVE_TOP);
		
		JPanel storylinePanel = new JPanel();
		tabbedPane.addTab("Storyline", null, storylinePanel, null);
		storylinePanel.setLayout(null);
		
		JComboBox<Object> jComboReferenceList = new JComboBox<>(rfls.toArray());
		jComboReferenceList.setBounds(10, 123, 142, 20);
		storylinePanel.add(jComboReferenceList);
		
		txtStorylinePageNo = new JTextField();
		txtStorylinePageNo.setBounds(75, 11, 77, 20);
		storylinePanel.add(txtStorylinePageNo);
		txtStorylinePageNo.setColumns(10);
		//txtStorylinePageNo.setBorder(tb);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setForeground(Color.WHITE);
		editorPane.setBackground(Color.BLACK);
		editorPane.setBounds(162, 11, 450, 305);
		storylinePanel.add(editorPane);
		
		JLabel lblStorylineTarget = new JLabel("Target word");
		lblStorylineTarget.setBounds(10, 42, 142, 14);
		storylinePanel.add(lblStorylineTarget);
		
		txtStorylineTargetWord = new JTextField();
		txtStorylineTargetWord.setColumns(10);
		txtStorylineTargetWord.setBounds(10, 67, 142, 20);
		storylinePanel.add(txtStorylineTargetWord);
		
		JLabel lblStorylineReference = new JLabel("Reference");
		lblStorylineReference.setBounds(10, 98, 142, 14);
		storylinePanel.add(lblStorylineReference);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 314, 142, 2);
		storylinePanel.add(separator);
		
		JButton btnAddReference = new JButton("Add Reference");
		btnAddReference.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backup = editorPane.getText();
				String storyText = backup;
				String changedText = Storyline.addReference(
						storyText, 
						txtStorylineTargetWord.getText(), 
						/*txtStorylineReferenceForAWord.getText()*/jComboReferenceList.getSelectedItem().toString());
				editorPane.setText(changedText);
			}
		});
		btnAddReference.setBounds(10, 280, 142, 23);
		storylinePanel.add(btnAddReference);
		
		JButton btnAddPage = new JButton("Save Page");
		btnAddPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Storyline i = new Storyline(
						txtStorylinePageNo.getText(), editorPane.getText(), editorPane_Milestone.getText() );
				try {
					DBTools.connect(dbPathField.getText());
					DBTools.insertStoryline(i);
					systemOutput.append("\n" + i.toString());
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		btnAddPage.setBounds(10, 361, 142, 23);
		storylinePanel.add(btnAddPage);
		
		JButton btnDeletePage = new JButton("Delete Page");
		btnDeletePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DBTools.connect(dbPathField.getText());
					systemOutput.append("\n" + DBTools.deleteStoryline(txtStorylinePageNo.getText()));
					systemOutput.append("\n" + txtStorylinePageNo.getText());
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		btnDeletePage.setBounds(10, 327, 142, 23);
		storylinePanel.add(btnDeletePage);
		
		btnRedoReference = new JButton("Redo");
		btnRedoReference.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(backup != null) {
					String current = editorPane.getText();
					editorPane.setText(backup);
					backup = current;
				}
			}
		});
		btnRedoReference.setBounds(10, 246, 142, 23);
		storylinePanel.add(btnRedoReference);
		
		editorPane_Milestone = new JEditorPane();
		editorPane_Milestone.setForeground(Color.WHITE);
		editorPane_Milestone.setBackground(Color.BLACK);
		editorPane_Milestone.setBounds(162, 327, 450, 57);
		storylinePanel.add(editorPane_Milestone);
		
		//String[] referencesFromDb = new String[] {"a", "b", "c"};
		
		
		
		
		btnRefreshReferenceList = new JButton("Refresh Reference List");
		btnRefreshReferenceList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				try {
					DBTools.connect(dbPathField.getText());
					List<ReferenceDictionary> rfl = DBTools.selectReferenceDictionary();
					/*rfls = new ArrayList<String>();
					for(ReferenceDictionary d : rfl) {
						rfls.add(d.getName());
					}*/
					systemOutput.append("\n" + "Reference list refreshed");
					
					jComboReferenceList.removeAllItems();
					
					for(ReferenceDictionary d : rfl) {
						jComboReferenceList.addItem(d.getReference());
					}
					
					
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}

				
			}
		});
		btnRefreshReferenceList.setBounds(10, 154, 142, 23);
		storylinePanel.add(btnRefreshReferenceList);
		
		JLabel lblPageno = new JLabel("PageNo");
		lblPageno.setBounds(10, 14, 46, 14);
		storylinePanel.add(lblPageno);
	}
	
	public void addJPanelReferenceDescriptions() {
		JPanel referenceDictionaryPanel = new JPanel();
		tabbedPane.addTab("ReferenceDictionary", null, referenceDictionaryPanel, null);
		referenceDictionaryPanel.setLayout(null);
		
		lblReference = new JLabel("Reference");
		lblReference.setBounds(10, 14, 182, 14);
		referenceDictionaryPanel.add(lblReference);
		
		lblNameReference = new JLabel("Name");
		lblNameReference.setBounds(10, 39, 182, 14);
		referenceDictionaryPanel.add(lblNameReference);
		
		lblDescriptionReference = new JLabel("Description");
		lblDescriptionReference.setBounds(10, 64, 182, 14);
		referenceDictionaryPanel.add(lblDescriptionReference);
		
		txtDescriptionReference = new JTextField();
		txtDescriptionReference.setColumns(10);
		txtDescriptionReference.setBounds(202, 61, 299, 20);
		referenceDictionaryPanel.add(txtDescriptionReference);
		
		txtNameReference = new JTextField();
		txtNameReference.setColumns(10);
		txtNameReference.setBounds(202, 36, 299, 20);
		referenceDictionaryPanel.add(txtNameReference);
		
		txtReferenceReference = new JTextField();
		txtReferenceReference.setColumns(10);
		txtReferenceReference.setBounds(202, 11, 299, 20);
		referenceDictionaryPanel.add(txtReferenceReference);
		
		btnInsertReferenceDictionary = new JButton("Insert");
		btnInsertReferenceDictionary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReferenceDictionary i = new ReferenceDictionary(
						txtReferenceReference.getText(), txtNameReference.getText(), txtDescriptionReference.getText() );
				try {
					DBTools.connect(dbPathField.getText());
					DBTools.insertReferenceDictionary(i);
					systemOutput.append("\n" + i.toString());
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		btnInsertReferenceDictionary.setBounds(10, 244, 89, 23);
		referenceDictionaryPanel.add(btnInsertReferenceDictionary);
		
		buttonReferenceDictionaryClear = new JButton("Clear");
		buttonReferenceDictionaryClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtReferenceReference.setText("");
				txtNameReference.setText("");
				txtDescriptionReference.setText("");
			}
		});
		buttonReferenceDictionaryClear.setBounds(103, 244, 89, 23);
		referenceDictionaryPanel.add(buttonReferenceDictionaryClear);
		
		btnDeleteReference = new JButton("Delete Reference");
		btnDeleteReference.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBTools.connect(dbPathField.getText());
					systemOutput.append("\n" + DBTools.deleteReferenceDictionary(txtReferenceReference.getText()));
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		btnDeleteReference.setBounds(10, 278, 142, 23);
		referenceDictionaryPanel.add(btnDeleteReference);
	}
	
	public void addJPanelItems() {
		JPanel ItemsPanel = new JPanel();
		tabbedPane.addTab("Items", null, ItemsPanel, null);
		ItemsPanel.setLayout(null);
		
		lblItemid = new JLabel("ItemID");
		lblItemid.setBounds(10, 14, 182, 14);
		ItemsPanel.add(lblItemid);
		
		lblNameItems = new JLabel("Name");
		lblNameItems.setBounds(10, 39, 182, 14);
		ItemsPanel.add(lblNameItems);
		
		lblDescriptionItems = new JLabel("Description");
		lblDescriptionItems.setBounds(10, 64, 182, 14);
		ItemsPanel.add(lblDescriptionItems);
		
		txtItemID = new JTextField();
		txtItemID.setColumns(10);
		txtItemID.setBounds(202, 11, 299, 20);
		ItemsPanel.add(txtItemID);
		
		txtNameItem = new JTextField();
		txtNameItem.setColumns(10);
		txtNameItem.setBounds(202, 36, 299, 20);
		ItemsPanel.add(txtNameItem);
		
		txtDescriptionItem = new JTextField();
		txtDescriptionItem.setColumns(10);
		txtDescriptionItem.setBounds(202, 61, 299, 20);
		ItemsPanel.add(txtDescriptionItem);
		
		btnInsertItem = new JButton("Insert");
		btnInsertItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item i = new Item(
						txtItemID.getText(), 
						txtNameItem.getText(), 
						txtDescriptionItem.getText()
						);
				
				System.out.println(i);
				try {
					DBTools.connect(dbPathField.getText());
					DBTools.insertItem(i);
					systemOutput.append("\n" + i.toString());
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
				
			}
		});
		btnInsertItem.setBounds(10, 244, 89, 23);
		ItemsPanel.add(btnInsertItem);
		
		buttonItemsClear = new JButton("Clear");
		buttonItemsClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtItemID.setText("");
				txtNameItem.setText("");
				txtDescriptionItem.setText("");
			}
		});
		buttonItemsClear.setBounds(103, 244, 89, 23);
		ItemsPanel.add(buttonItemsClear);
		
		btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBTools.connect(dbPathField.getText());
					systemOutput.append("\n" +DBTools.deleteItem(txtItemID.getText()));
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		btnDeleteItem.setBounds(10, 278, 142, 23);
		ItemsPanel.add(btnDeleteItem);
	}

	private void addJPanelInteractions() {
		InteractionsPanel = new JPanel();
		tabbedPane.addTab("Interactions", null, InteractionsPanel, null);
		InteractionsPanel.setLayout(null);
		
		lblStorylinepageno = new JLabel("Storyline_PageNo");
		lblStorylinepageno.setBounds(10, 64, 182, 14);
		InteractionsPanel.add(lblStorylinepageno);
		
		lblMapno = new JLabel("MapNo");
		lblMapno.setBounds(10, 89, 182, 14);
		InteractionsPanel.add(lblMapno);
		
		lblEmpathyvalue = new JLabel("EmpathyValue");
		lblEmpathyvalue.setBounds(10, 114, 182, 14);
		InteractionsPanel.add(lblEmpathyvalue);
		
		lblSanityvalue = new JLabel("SanityValue");
		lblSanityvalue.setBounds(10, 139, 182, 14);
		InteractionsPanel.add(lblSanityvalue);
		
		lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 165, 182, 14);
		InteractionsPanel.add(lblDescription);
		
		lblOptionaljournalentry = new JLabel("OptionalJournalEntry");
		lblOptionaljournalentry.setBounds(10, 190, 182, 14);
		InteractionsPanel.add(lblOptionaljournalentry);
		
		
		txtInteractionsReferenceDictionary_Reference = new JTextField();
		txtInteractionsReferenceDictionary_Reference.setToolTipText("Interaction Reference");
		txtInteractionsReferenceDictionary_Reference.setBounds(202, 11, 299, 20);
		InteractionsPanel.add(txtInteractionsReferenceDictionary_Reference);
		
		lblReferenceDictionary_Reference = new JLabel("ReferenceDictionary_Reference");
		lblReferenceDictionary_Reference.setBounds(10, 14, 182, 14);
		InteractionsPanel.add(lblReferenceDictionary_Reference);
		
		lblInteractionName = new JLabel("Name");
		lblInteractionName.setBounds(10, 39, 182, 14);
		InteractionsPanel.add(lblInteractionName);
		
		txtInteractionsName = new JTextField();
		txtInteractionsName.setBounds(202, 36, 299, 20);
		InteractionsPanel.add(txtInteractionsName);
		
		txtInteractions_Storyline_PageNo = new JTextField();
		txtInteractions_Storyline_PageNo.setColumns(10);
		txtInteractions_Storyline_PageNo.setBounds(202, 61, 299, 20);
		InteractionsPanel.add(txtInteractions_Storyline_PageNo);
		
		txtMapNo = new JTextField();
		txtMapNo.setColumns(10);
		txtMapNo.setBounds(202, 86, 299, 20);
		InteractionsPanel.add(txtMapNo);
		
		txtEmpathyValue = new JTextField();
		txtEmpathyValue.setColumns(10);
		txtEmpathyValue.setBounds(202, 111, 299, 20);
		InteractionsPanel.add(txtEmpathyValue);
		
		txtSanityValue = new JTextField();
		txtSanityValue.setColumns(10);
		txtSanityValue.setBounds(202, 136, 299, 20);
		InteractionsPanel.add(txtSanityValue);
		
		txtDescription = new JTextField();
		lblDescription.setLabelFor(txtDescription);
		txtDescription.setColumns(10);
		txtDescription.setBounds(202, 162, 299, 20);
		InteractionsPanel.add(txtDescription);
		
		txtOptionalJournalEntry = new JTextField();
		lblOptionaljournalentry.setLabelFor(txtOptionalJournalEntry);
		txtOptionalJournalEntry.setColumns(10);
		txtOptionalJournalEntry.setBounds(202, 187, 299, 20);
		InteractionsPanel.add(txtOptionalJournalEntry);
		
		addBtnInserInteractionToThePanel();
	}

	private void addTabbedPane() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 45, 627, 423);
		frame.getContentPane().add(tabbedPane);
		
		addJPanelInteractions();
		addJPanelItems();
		addJPanelReferenceDescriptions();
		addJPanelStoryline();
		
	}
	
	public void addTextArea() {
		systemOutput.setText(">> Select SQLite DB file first.");
		systemOutput.setWrapStyleWord(true);
		systemOutput.setLineWrap(true);
		systemOutput.setForeground(Color.WHITE);
		systemOutput.setBackground(Color.BLACK);
		systemOutput.setBounds(121, 362, 516, 118);
		scrollSysout = new JScrollPane(systemOutput);
		scrollSysout.setLocation(10, 481);
		scrollSysout.setSize(627, 127);
		scrollSysout.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollSysout);
	}
	
	public void addBtnSelectDB() {
		JButton btnSelectDb = new JButton("Select DB");
		btnSelectDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = jfc.showOpenDialog(null);
				// int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					dbFilePath = selectedFile.getAbsolutePath();
					System.out.println(dbFilePath);
					dbPathField.setText(dbFilePath);
					systemOutput.append("\n" + "Database path has been successfuly set to : " + dbFilePath);
				}
			}
		});
		btnSelectDb.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnSelectDb);
	}
}

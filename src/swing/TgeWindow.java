package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;

import db.DBTools;
import db.entity.Interaction;
import db.entity.InventoryItemUseRefMatch;
import db.entity.InventoryItemsMatch;
import db.entity.Item;
import db.entity.ReferenceDictionary;
import db.entity.StorySpecification;
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
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TgeWindow {

	public JTextArea systemOutput = new JTextArea();
	private String dbFilePath = "Choose a valid *.db file.";
	private JFrame frame;
	private JTextField dbPathField;
	private JScrollPane scrollSysout;
	private JTabbedPane tabbedPane;
	private JPanel interactionsPanel;
	private String backup;
	private List<String> rfls = new ArrayList<>();


	
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
	}

	
	public TgeWindow() {
		initialize();
	}

	private void initialize() {
		createJFrame();
		addDBPathTextArea();
		addBtnSelectDB();
		addTabContainer();
	}

	private void createJFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 663, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	private void addJPanel_Storyline() {
		Border blackline;
		blackline = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder tb = BorderFactory.createTitledBorder(blackline, "PageNo");
		tb.setTitleJustification(TitledBorder.LEFT);
		tb.setTitlePosition(TitledBorder.ABOVE_TOP);
		
		JPanel storylinePanel = new JPanel();
		tabbedPane.addTab("Storyline", null, storylinePanel, null);
		storylinePanel.setLayout(null);
		
		JEditorPane editorPane_Milestone = new JEditorPane();
		editorPane_Milestone.setForeground(Color.BLACK);
		editorPane_Milestone.setBackground(Color.WHITE);
		editorPane_Milestone.setBounds(162, 400, 450, 57);
		storylinePanel.add(editorPane_Milestone);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setForeground(Color.BLACK);
		editorPane.setBackground(Color.WHITE);
		editorPane.setBounds(162, 31, 450, 340);
		storylinePanel.add(editorPane);
		
		JTextField txtStorylineTargetWord = new JTextField();
		txtStorylineTargetWord.setColumns(10);
		txtStorylineTargetWord.setBounds(10, 67, 142, 20);
		storylinePanel.add(txtStorylineTargetWord);
		
		JTextField txtStorylinePageNo = new JTextField();
		txtStorylinePageNo.setBounds(75, 11, 77, 20);
		storylinePanel.add(txtStorylinePageNo);
		txtStorylinePageNo.setColumns(10);
		//txtStorylinePageNo.setBorder(tb);
		
		JComboBox<Object> jComboReferenceList = new JComboBox<>(rfls.toArray());
		jComboReferenceList.setBounds(10, 170, 142, 20);
		storylinePanel.add(jComboReferenceList);
		
		JLabel lblPageno = new JLabel("PageNo");
		lblPageno.setBounds(10, 14, 46, 14);
		storylinePanel.add(lblPageno);
		
		JLabel storylineEditorLabel = new JLabel("Page content");
		storylineEditorLabel.setBounds(162, 11, 96, 14);
		storylinePanel.add(storylineEditorLabel);
		
		JLabel OptionalMilestoneContentLabel = new JLabel("(Optional) Milestone journal content");
		OptionalMilestoneContentLabel.setBounds(162, 382, 187, 14);
		storylinePanel.add(OptionalMilestoneContentLabel);
		
		JLabel lblStorylineTarget = new JLabel("Target word");
		lblStorylineTarget.setBounds(10, 42, 142, 14);
		storylinePanel.add(lblStorylineTarget);
		
		JLabel lblStorylineReference = new JLabel("Reference");
		lblStorylineReference.setBounds(10, 111, 142, 14);
		storylinePanel.add(lblStorylineReference);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 314, 142, 2);
		storylinePanel.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 98, 142, 2);
		storylinePanel.add(separator_2);
		
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
		btnAddReference.setBounds(10, 201, 142, 23);
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
		btnAddPage.setBounds(10, 434, 142, 23);
		storylinePanel.add(btnAddPage);
		
		JButton btnDeletePage = new JButton("Delete Page");
		btnDeletePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DBTools.connect(dbPathField.getText());
					DBTools.deleteStoryline(txtStorylinePageNo.getText());
					systemOutput.append("\n" + "Story page removed");
					systemOutput.append("\n" + txtStorylinePageNo.getText());
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		btnDeletePage.setBounds(10, 400, 142, 23);
		storylinePanel.add(btnDeletePage);
		
		JButton btnRedoReference = new JButton("Redo");
		btnRedoReference.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(backup != null) {
					String current = editorPane.getText();
					editorPane.setText(backup);
					backup = current;
				}
			}
		});
		btnRedoReference.setBounds(10, 280, 142, 23);
		storylinePanel.add(btnRedoReference);
		
		
		
		//String[] referencesFromDb = new String[] {"a", "b", "c"};
		
		
		
		
		JButton btnRefreshReferenceList = new JButton("Refresh Reference List");
		btnRefreshReferenceList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBTools.connect(dbPathField.getText());
					List<ReferenceDictionary> rfl = DBTools.selectReferenceDictionary();
					
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
		btnRefreshReferenceList.setBounds(10, 136, 142, 23);
		storylinePanel.add(btnRefreshReferenceList);
		
		
		
		
	}
	
	private void addJPanel_InventoryItemUseRefMatchPanel() {
		JPanel panel = new JPanel();
		tabbedPane.addTab("InvItemUseRefMatch", null, panel, null);
		panel.setLayout(null);
		
		JTextArea txtOptJournEntry = new JTextArea();
		txtOptJournEntry.setWrapStyleWord(true);
		txtOptJournEntry.setRows(8);
		txtOptJournEntry.setLineWrap(true);
		txtOptJournEntry.setBounds(202, 132, 299, 45);
		panel.add(txtOptJournEntry);
		
		JTextField txtSanity = new JTextField();
		txtSanity.setColumns(10);
		txtSanity.setBounds(70, 42, 50, 20);
		panel.add(txtSanity);
		
		JTextField txtEmpathy = new JTextField();
		txtEmpathy.setColumns(10);
		txtEmpathy.setBounds(202, 42, 50, 20);
		panel.add(txtEmpathy);
		
		JTextField txtSanityTreshold = new JTextField();
		txtSanityTreshold.setColumns(10);
		txtSanityTreshold.setBounds(381, 42, 50, 20);
		panel.add(txtSanityTreshold);
		
		JTextField txtEmpathyTreshold = new JTextField();
		txtEmpathyTreshold.setColumns(10);
		txtEmpathyTreshold.setBounds(562, 42, 50, 20);
		panel.add(txtEmpathyTreshold);
		
		JTextField txtPagesLocked = new JTextField();
		txtPagesLocked.setColumns(10);
		txtPagesLocked.setBounds(100, 365, 401, 20);
		panel.add(txtPagesLocked);
		
		JTextField txtMapNo = new JTextField();
		txtMapNo.setColumns(10);
		txtMapNo.setBounds(70, 104, 182, 20);
		panel.add(txtMapNo);
		
		JTextArea txtEffectDescription = new JTextArea();
		txtEffectDescription.setWrapStyleWord(true);
		txtEffectDescription.setRows(8);
		txtEffectDescription.setLineWrap(true);
		txtEffectDescription.setBounds(202, 188, 299, 166);
		panel.add(txtEffectDescription);
		
		JComboBox<Object> cmbPageNo = new JComboBox<Object>(new Object[]{});
		cmbPageNo.setBounds(70, 73, 182, 20);
		panel.add(cmbPageNo);
		
		JComboBox<Object> cmbItemID = new JComboBox<Object>(new Object[]{});
		cmbItemID.setBounds(490, 8, 122, 20);
		panel.add(cmbItemID);
		
		JTextField txtRefName = new JTextField();
		txtRefName.setColumns(10);
		txtRefName.setBounds(100, 11, 152, 20);
		panel.add(txtRefName);
		
		JCheckBox checkRemoveFromInv = new JCheckBox("Remove From Inventory");
		checkRemoveFromInv.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println(checkRemoveFromInv.isSelected());
			}
		});
		checkRemoveFromInv.setBounds(282, 72, 219, 23);
		panel.add(checkRemoveFromInv);
		
		JLabel lblRefname = new JLabel("RefName");
		lblRefname.setBounds(10, 14, 80, 14);
		panel.add(lblRefname);
		
		JLabel lblEmpathytreshold = new JLabel("EmpathyTreshold");
		lblEmpathytreshold.setBounds(441, 45, 111, 14);
		panel.add(lblEmpathytreshold);
		
		JLabel lblPagesLocked = new JLabel("PagesLocked");
		lblPagesLocked.setBounds(10, 368, 80, 14);
		panel.add(lblPagesLocked);
		
		JLabel lblItemid_1 = new JLabel("ItemID");
		lblItemid_1.setBounds(430, 11, 50, 14);
		panel.add(lblItemid_1);
		
		JLabel lblSanity = new JLabel("Sanity");
		lblSanity.setBounds(10, 45, 50, 14);
		panel.add(lblSanity);
		
		JLabel lblEmpathy = new JLabel("Empathy");
		lblEmpathy.setBounds(130, 45, 50, 14);
		panel.add(lblEmpathy);
		
		JLabel lblSanitytreshold_1 = new JLabel("SanityTreshold");
		lblSanitytreshold_1.setBounds(272, 45, 99, 14);
		panel.add(lblSanitytreshold_1);
		
		JLabel lblMapno_1 = new JLabel("MapNo");
		lblMapno_1.setBounds(10, 107, 50, 14);
		panel.add(lblMapno_1);
		
		JLabel lblEffectdescription = new JLabel("EffectDescription");
		lblEffectdescription.setBounds(10, 188, 182, 14);
		panel.add(lblEffectdescription);

		JLabel label = new JLabel("PageNo");
		label.setBounds(10, 76, 50, 14);
		panel.add(label);
		
		JLabel lblOptionaljournalentry_1 = new JLabel("OptionalJournalEntry");
		lblOptionaljournalentry_1.setBounds(10, 132, 182, 14);
		panel.add(lblOptionaljournalentry_1);
		
		JButton button_1 = new JButton("Delete Items Match");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String refName = txtRefName.getText();
					String itemID = cmbItemID.getSelectedItem().toString();
					
					DBTools.connect(dbPathField.getText());
					DBTools.deleteInventoryItemUseRefMatch(refName, itemID);
					systemOutput.append("\n" + ">> InventoryItemUseRefMatch by RefName and ItemID : [ " + refName + " / "+itemID+" ] has been successfully removed.");
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		button_1.setBounds(318, 396, 183, 23);
		panel.add(button_1);
		
		JButton button_3 = new JButton("Clear");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtEffectDescription.setText("");
				txtEmpathy.setText("");
				txtEmpathyTreshold.setText("");
				txtMapNo.setText("");
				txtOptJournEntry.setText("");
				txtPagesLocked.setText("");
				txtRefName.setText("");
				txtSanity.setText("");
				txtSanityTreshold.setText("");
				
			}
		});
		button_3.setBounds(523, 296, 89, 23);
		panel.add(button_3);
		
		JButton button = new JButton("Refresh All Lists");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBTools.connect(dbPathField.getText());
					List<Item> rfl = DBTools.selectItems();
					List<Storyline> stl = DBTools.selectStoryline();
					
					systemOutput.append("\n" + "Item lists refreshed");
					
					cmbItemID.removeAllItems();
					cmbPageNo.removeAllItems();
					
					for(Storyline s : stl) {
						cmbPageNo.addItem(s.getPageNo());
					}
					
					for(Item d : rfl) {
						cmbItemID.addItem(d.getName());
					}
					
					
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		button.setBounds(10, 396, 170, 23);
		panel.add(button);
		
		JButton button_2 = new JButton("Insert");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tf = "False";
				InventoryItemUseRefMatch i = new InventoryItemUseRefMatch();
				i.setRefName(txtRefName.getText());
				i.setItemID(cmbItemID.getSelectedItem().toString());
				i.setEffectDescript(txtEffectDescription.getText());
				i.setSanity(Integer.parseInt(txtSanity.getText()));
				i.setSanityTreshold(Integer.parseInt(txtSanityTreshold.getText()));
				i.setEmpathy(Integer.parseInt(txtEmpathy.getText()));
				i.setEmpathyTreshold(Integer.parseInt(txtEmpathyTreshold.getText()));
				i.setPageNo(cmbPageNo.getSelectedItem().toString());
				i.setMapNo(txtMapNo.getText());
				i.setOptionalJOurnalEntry(txtOptJournEntry.getText());
				i.setPagesLocked(txtPagesLocked.getText());
					if(checkRemoveFromInv.isSelected()) tf = "True";
				i.setRemoveFromInventoryFlag(tf);
				System.out.println(">" +checkRemoveFromInv.isSelected());
				
				try {
					DBTools.connect(dbPathField.getText());
					DBTools.insertInventoryItemUseRefMatch(i);
					systemOutput.append("\n" + i.toString());
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		button_2.setBounds(522, 329, 90, 90);
		panel.add(button_2);
		
		
	}
	
	private void addJPanel_StorySpecification() {
		JPanel storySpecsPanel = new JPanel();
		tabbedPane.addTab("Story specs.", null, storySpecsPanel, null);
		storySpecsPanel.setLayout(null);
		
		JTextArea txtStoryDescription_Spec = new JTextArea();
		txtStoryDescription_Spec.setWrapStyleWord(true);
		txtStoryDescription_Spec.setRows(8);
		txtStoryDescription_Spec.setLineWrap(true);
		txtStoryDescription_Spec.setBounds(202, 120, 299, 337);
		storySpecsPanel.add(txtStoryDescription_Spec);
		
		JTextField txtStartingEmpathy_Spec = new JTextField();
		txtStartingEmpathy_Spec.setColumns(10);
		txtStartingEmpathy_Spec.setBounds(202, 64, 148, 20);
		storySpecsPanel.add(txtStartingEmpathy_Spec);
		
		JTextField txtStartingSanity_Spec = new JTextField();
		txtStartingSanity_Spec.setColumns(10);
		txtStartingSanity_Spec.setBounds(202, 92, 148, 20);
		storySpecsPanel.add(txtStartingSanity_Spec);
		
		JTextField txtStartingPageNo_Spec = new JTextField();
		txtStartingPageNo_Spec.setColumns(10);
		txtStartingPageNo_Spec.setBounds(202, 36, 148, 20);
		storySpecsPanel.add(txtStartingPageNo_Spec);
		
		JTextField txtStoryName_Spec = new JTextField();
		txtStoryName_Spec.setColumns(10);
		txtStoryName_Spec.setBounds(202, 11, 299, 20);
		storySpecsPanel.add(txtStoryName_Spec);
		
		JButton btnInsertStorySpec = new JButton("Insert");
		btnInsertStorySpec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StorySpecification i = new StorySpecification();
				i.setStoryName(txtStoryName_Spec.getText());
				i.setStoryDescription(txtStoryDescription_Spec.getText());
				i.setStartingPageNo(txtStartingPageNo_Spec.getText());
				i.setStartingEmpathy(Integer.parseInt(txtStartingEmpathy_Spec.getText()));
				i.setStartingSanity(Integer.parseInt(txtStartingSanity_Spec.getText()));
				
				try {
					DBTools.connect(dbPathField.getText());
					DBTools.insertStorySpecification(i);
					systemOutput.append("\n" + i.toString());
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		btnInsertStorySpec.setBounds(10, 400, 89, 23);
		storySpecsPanel.add(btnInsertStorySpec);
		
		JButton btnClearStorySpec = new JButton("Clear");
		btnClearStorySpec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStartingEmpathy_Spec.setText("");
				txtStartingPageNo_Spec.setText("");
				txtStartingSanity_Spec.setText("");
				txtStoryDescription_Spec.setText("");
				txtStoryName_Spec.setText("");
			}
		});
		btnClearStorySpec.setBounds(103, 400, 89, 23);
		storySpecsPanel.add(btnClearStorySpec);
		
		JButton btnInsertStorySpecs = new JButton("Delete Story Spec");
		btnInsertStorySpecs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String storyName = txtStoryName_Spec.getText();
					
					DBTools.connect(dbPathField.getText());
					DBTools.deleteStorySpecification(storyName);
					systemOutput.append("\n" + ">> StorySpecification by StoryName : [ " + storyName + " ] has been successfully removed.");
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
				
			}
		});
		btnInsertStorySpecs.setBounds(10, 434, 142, 23);
		storySpecsPanel.add(btnInsertStorySpecs);
		
		JLabel lblStartingempathy = new JLabel("StartingEmpathy");
		lblStartingempathy.setBounds(10, 67, 182, 14);
		storySpecsPanel.add(lblStartingempathy);
		
		JLabel lblStoryName_Specication = new JLabel("StoryName");
		lblStoryName_Specication.setBounds(10, 14, 182, 14);
		storySpecsPanel.add(lblStoryName_Specication);
		
		JLabel lblStartingPageNo = new JLabel("StartingPageNo");
		lblStartingPageNo.setBounds(10, 39, 182, 14);
		storySpecsPanel.add(lblStartingPageNo);
		
		JLabel lblStoryDescription_StorySpec = new JLabel("StoryDescription");
		lblStoryDescription_StorySpec.setBounds(10, 120, 182, 14);
		storySpecsPanel.add(lblStoryDescription_StorySpec);

		JLabel lblStartingsanity = new JLabel("StartingSanity");
		lblStartingsanity.setBounds(10, 95, 182, 14);
		storySpecsPanel.add(lblStartingsanity);
		
		
	}
	
	private void addJPanel_InventoryItemsMatch() {
		JPanel inventoryItemsMatch = new JPanel();
		tabbedPane.addTab("Combining Items", null, inventoryItemsMatch, null);
		inventoryItemsMatch.setLayout(null);
		
		JTextArea txtEffectDescription_Combine = new JTextArea();
		txtEffectDescription_Combine.setWrapStyleWord(true);
		txtEffectDescription_Combine.setRows(8);
		txtEffectDescription_Combine.setLineWrap(true);
		txtEffectDescription_Combine.setBounds(10, 97, 300, 154);
		inventoryItemsMatch.add(txtEffectDescription_Combine);
		
		JTextArea txtPagePreview_Combine = new JTextArea();
		txtPagePreview_Combine.setEditable(false);
		txtPagePreview_Combine.setWrapStyleWord(true);
		txtPagePreview_Combine.setRows(8);
		txtPagePreview_Combine.setLineWrap(true);
		txtPagePreview_Combine.setBounds(317, 285, 295, 172);
		inventoryItemsMatch.add(txtPagePreview_Combine);
		
		JTextArea txtOptionalJEntry_Combine = new JTextArea();
		txtOptionalJEntry_Combine.setWrapStyleWord(true);
		txtOptionalJEntry_Combine.setRows(8);
		txtOptionalJEntry_Combine.setLineWrap(true);
		txtOptionalJEntry_Combine.setBounds(317, 97, 295, 100);
		inventoryItemsMatch.add(txtOptionalJEntry_Combine);
		
		JTextField txtMapNo_Combine = new JTextField();
		txtMapNo_Combine.setColumns(10);
		txtMapNo_Combine.setBounds(246, 287, 64, 20);
		inventoryItemsMatch.add(txtMapNo_Combine);
		
		JTextField txtSanity_Combine = new JTextField();
		txtSanity_Combine.setColumns(10);
		txtSanity_Combine.setBounds(10, 287, 64, 20);
		inventoryItemsMatch.add(txtSanity_Combine);
		
		JTextField txtSanityTreshold_Combine = new JTextField();
		txtSanityTreshold_Combine.setColumns(10);
		txtSanityTreshold_Combine.setBounds(84, 287, 64, 20);
		inventoryItemsMatch.add(txtSanityTreshold_Combine);
		
		JTextField txtEmpathy_Combine = new JTextField();
		txtEmpathy_Combine.setColumns(10);
		txtEmpathy_Combine.setBounds(10, 343, 64, 20);
		inventoryItemsMatch.add(txtEmpathy_Combine);
		
		JTextField txtEmpathyTreshold_Combine = new JTextField();
		txtEmpathyTreshold_Combine.setColumns(10);
		txtEmpathyTreshold_Combine.setBounds(84, 343, 64, 20);
		inventoryItemsMatch.add(txtEmpathyTreshold_Combine);
		
		JTextField txtPagesLocked_Combine = new JTextField();
		txtPagesLocked_Combine.setColumns(10);
		txtPagesLocked_Combine.setBounds(320, 230, 295, 20);
		inventoryItemsMatch.add(txtPagesLocked_Combine);
		
		JTextField txtPageNo_Combine = new JTextField();
		txtPageNo_Combine.setColumns(10);
		txtPageNo_Combine.setBounds(317, 324, 64, 20);
		//inventoryItemsMatch.add(txtPageNo_Combine);
		
		JComboBox<Object> cmbItemID_1 = new JComboBox<Object>(new Object[]{});
		cmbItemID_1.setBounds(10, 36, 182, 20);
		inventoryItemsMatch.add(cmbItemID_1);
		
		JComboBox<Object> cmbItemID_2 = new JComboBox<Object>(new Object[]{});
		cmbItemID_2.setBounds(224, 36, 182, 20);
		inventoryItemsMatch.add(cmbItemID_2);
		
		JComboBox<Object> cmbCreatedObjectID_Combine;
		cmbCreatedObjectID_Combine = new JComboBox<Object>(new Object[]{});
		cmbCreatedObjectID_Combine.setBounds(430, 36, 182, 20);
		inventoryItemsMatch.add(cmbCreatedObjectID_Combine);
		
		JComboBox<Object> cmbPageNo_Combine;
		cmbPageNo_Combine = new JComboBox<Object>(new Object[]{});
		cmbPageNo_Combine.setBounds(172, 287, 64, 20);
		inventoryItemsMatch.add(cmbPageNo_Combine);
		
		JComboBox<Object> cmbMapNo_Combine;
		cmbMapNo_Combine = new JComboBox<Object>(new Object[]{});
		cmbMapNo_Combine.setBounds(391, 355, 64, 20);
		//inventoryItemsMatch.add(cmbMapNo_Combine);
		
		
		JLabel labelPLUS = new JLabel("+");
		labelPLUS.setBounds(202, 31, 30, 30);
		inventoryItemsMatch.add(labelPLUS);
		
		JLabel labelEQUALS = new JLabel("=");
		labelEQUALS.setBounds(411, 31, 30, 30);
		inventoryItemsMatch.add(labelEQUALS);
		
		JLabel lblPagenocombine = new JLabel("PageNo");
		lblPagenocombine.setBounds(172, 262, 50, 14);
		inventoryItemsMatch.add(lblPagenocombine);
		
		JLabel lblMapNo_Combine = new JLabel("Map No");
		lblMapNo_Combine.setBounds(246, 262, 50, 14);
		inventoryItemsMatch.add(lblMapNo_Combine);
		
		JLabel lblEffectDescription_Combine = new JLabel("Effect Description");
		lblEffectDescription_Combine.setBounds(11, 72, 100, 14);
		inventoryItemsMatch.add(lblEffectDescription_Combine);
		
		JLabel lblOptionalJournalEntry_Combine = new JLabel("(Optional) Journal Entry");
		lblOptionalJournalEntry_Combine.setBounds(317, 72, 132, 14);
		inventoryItemsMatch.add(lblOptionalJournalEntry_Combine);
		
		JLabel lblSanity_Combine = new JLabel("Sanity");
		lblSanity_Combine.setBounds(10, 262, 50, 14);
		inventoryItemsMatch.add(lblSanity_Combine);
		
		JLabel lblSanityTreshold_Combine = new JLabel("Sanity Treshold");
		lblSanityTreshold_Combine.setBounds(84, 262, 79, 14);
		inventoryItemsMatch.add(lblSanityTreshold_Combine);
		
		JLabel lblEmpathy_Combine = new JLabel("Empathy");
		lblEmpathy_Combine.setBounds(10, 318, 50, 14);
		inventoryItemsMatch.add(lblEmpathy_Combine);
		
		JLabel lblEmpathyTreshold_Combine = new JLabel("Empathy Treshold");
		lblEmpathyTreshold_Combine.setBounds(84, 318, 94, 14);
		inventoryItemsMatch.add(lblEmpathyTreshold_Combine);
		
		JLabel lblPagesLocked_Combined = new JLabel("PagesLocked");
		lblPagesLocked_Combined.setBounds(320, 208, 79, 14);
		inventoryItemsMatch.add(lblPagesLocked_Combined);
		
		JLabel lblItemID_1 = new JLabel("ItemID_1");
		lblItemID_1.setBounds(10, 11, 50, 14);
		inventoryItemsMatch.add(lblItemID_1);
		
		JLabel lblItemID_2 = new JLabel("ItemID_2");
		lblItemID_2.setBounds(224, 11, 50, 14);
		inventoryItemsMatch.add(lblItemID_2);
		
		JLabel lblCreatedObjectID = new JLabel("Created Object ID");
		lblCreatedObjectID.setBounds(432, 11, 100, 14);
		inventoryItemsMatch.add(lblCreatedObjectID);
		
		JLabel lblPagePreview = new JLabel("PagePreview");
		lblPagePreview.setBounds(317, 262, 86, 14);
		inventoryItemsMatch.add(lblPagePreview);
		
		
		JButton btnClearInventoryItemsMatch = new JButton("Clear");
		btnClearInventoryItemsMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEffectDescription_Combine.setText("");
				txtEmpathy_Combine.setText("");
				txtEmpathyTreshold_Combine.setText("");
				txtMapNo_Combine.setText("");
				txtOptionalJEntry_Combine.setText("");
				txtPageNo_Combine.setText("");
				txtPagePreview_Combine.setText("");
				txtPagesLocked_Combine.setText("");
				txtSanity_Combine.setText("");
				txtSanityTreshold_Combine.setText("");
			}
		});
		btnClearInventoryItemsMatch.setBounds(221, 434, 89, 23);
		inventoryItemsMatch.add(btnClearInventoryItemsMatch);
		
		JButton btnDeleteInventoryItemsMatch = new JButton("Delete Items Match");
		btnDeleteInventoryItemsMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String item1 = cmbItemID_1.getSelectedItem().toString();
					String item2 = cmbItemID_2.getSelectedItem().toString();
					String createdItem = cmbCreatedObjectID_Combine.getSelectedItem().toString();
					DBTools.connect(dbPathField.getText());
					DBTools.deleteInventoryItemsMatch(item1, item2, createdItem);
					systemOutput.append("\n" + ">> InventoryItemsMatch by ItemID_1 AND ItemID_2 AND CreatedObjectID : [ " + item1 + " / " + item2 + " / " + createdItem + " ] has been successfully removed.");
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
			}
		});
		btnDeleteInventoryItemsMatch.setBounds(10, 434, 142, 23);
		inventoryItemsMatch.add(btnDeleteInventoryItemsMatch);
		
		JButton btnRefreshItemsList_Combine = new JButton("Refresh All Lists");
		btnRefreshItemsList_Combine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBTools.connect(dbPathField.getText());
					List<Item> rfl = DBTools.selectItems();
					List<Storyline> stl = DBTools.selectStoryline();
					
					systemOutput.append("\n" + "Item lists refreshed");
					
					cmbItemID_1.removeAllItems();
					cmbItemID_2.removeAllItems();
					cmbCreatedObjectID_Combine.removeAllItems();
					cmbPageNo_Combine.removeAllItems();
					cmbMapNo_Combine.removeAllItems();
					
					for(Storyline s : stl) {
						cmbPageNo_Combine.addItem(s.getPageNo());
					}
					
					
					for(Item d : rfl) {
						cmbItemID_1.addItem(d.getName());
						cmbItemID_2.addItem(d.getName());
						cmbCreatedObjectID_Combine.addItem(d.getName());
					}
					
					
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
				
			}
		});
		btnRefreshItemsList_Combine.setBounds(10, 374, 142, 23);
		inventoryItemsMatch.add(btnRefreshItemsList_Combine);
		
		JButton btnInsertInventoryItemsMatch = new JButton("Insert");
		btnInsertInventoryItemsMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InventoryItemsMatch i = new InventoryItemsMatch();
				i.setItemID_1(cmbItemID_1.getSelectedItem().toString());
				i.setItemID_2(cmbItemID_2.getSelectedItem().toString());
				i.setCreatedObjectID(cmbCreatedObjectID_Combine.getSelectedItem().toString());
				i.setEfectDesc(txtEffectDescription_Combine.getText());
				i.setSanity(Integer.parseInt(txtSanity_Combine.getText()));
				i.setEmpathy(Integer.parseInt(txtEmpathy_Combine.getText()));
				i.setSanityTreshold(Integer.parseInt(txtSanityTreshold_Combine.getText()));
				i.setEmpathyTreshold(Integer.parseInt(txtEmpathyTreshold_Combine.getText()));
				i.setPageNo(/*txtPageNo_Combine.getText()*/cmbPageNo_Combine.getSelectedItem().toString());
				i.setMapNo(txtMapNo_Combine.getText());
				i.setOptionalJournalEntry(txtOptionalJEntry_Combine.getText());
				i.setPagesLocked(txtPagesLocked_Combine.getText());
				
				try {
					DBTools.connect(dbPathField.getText());
					DBTools.insertInventoryItemsMatch(i);
					systemOutput.append("\n" + i.toString());
				} catch (Exception e1) {
					systemOutput.append("\n" + e1.getMessage());
				} finally{
					DBTools.closeConnection();
				}
				
			}
		});
		btnInsertInventoryItemsMatch.setBounds(221, 333, 90, 90);
		inventoryItemsMatch.add(btnInsertInventoryItemsMatch);
		
		JButton btnLoadPreview_Combine = new JButton("Load Preview");
		btnLoadPreview_Combine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						String page = cmbPageNo_Combine.getSelectedItem().toString();
						DBTools.connect(dbPathField.getText());
						Storyline s1 = DBTools.selectStoryline(page).get(0);
						System.out.println(s1);
						txtPagePreview_Combine.setText(s1.getPageText());
					} catch (Exception e1) {
						systemOutput.append("\n" + e1.getMessage());
					} finally{
						DBTools.closeConnection();
					}
			}
		});
		btnLoadPreview_Combine.setBounds(480, 258, 132, 23);
		inventoryItemsMatch.add(btnLoadPreview_Combine);
		
	}
	
	private void addJPanel_ReferenceDescriptions() {
		
		JPanel referenceDictionaryPanel = new JPanel();
		tabbedPane.addTab("ReferenceDictionary", null, referenceDictionaryPanel, null);
		referenceDictionaryPanel.setLayout(null);
		
		JTextArea txtDescriptionReference = new JTextArea();
		txtDescriptionReference.setWrapStyleWord(true);
		txtDescriptionReference.setRows(8);
		txtDescriptionReference.setLineWrap(true);
		txtDescriptionReference.setBounds(202, 64, 299, 365);
		referenceDictionaryPanel.add(txtDescriptionReference);
		
		JTextField txtNameReference = new JTextField();
		txtNameReference.setColumns(10);
		txtNameReference.setBounds(202, 36, 299, 20);
		referenceDictionaryPanel.add(txtNameReference);
		
		JTextField txtReferenceReference = new JTextField();
		txtReferenceReference.setColumns(10);
		txtReferenceReference.setBounds(202, 11, 299, 20);
		referenceDictionaryPanel.add(txtReferenceReference);
		
		JLabel lblReference = new JLabel("Reference");
		lblReference.setBounds(10, 14, 182, 14);
		referenceDictionaryPanel.add(lblReference);
		
		JLabel lblNameReference = new JLabel("Name");
		lblNameReference.setBounds(10, 39, 182, 14);
		referenceDictionaryPanel.add(lblNameReference);
		
		JLabel lblDescriptionReference = new JLabel("Description");
		lblDescriptionReference.setBounds(10, 64, 182, 14);
		referenceDictionaryPanel.add(lblDescriptionReference);
		
		JButton btnInsertReferenceDictionary = new JButton("Insert");
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
		btnInsertReferenceDictionary.setBounds(10, 400, 89, 23);
		referenceDictionaryPanel.add(btnInsertReferenceDictionary);
		
		JButton buttonReferenceDictionaryClear = new JButton("Clear");
		buttonReferenceDictionaryClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtReferenceReference.setText("");
				txtNameReference.setText("");
				txtDescriptionReference.setText("");
			}
		});
		buttonReferenceDictionaryClear.setBounds(103, 400, 89, 23);
		referenceDictionaryPanel.add(buttonReferenceDictionaryClear);
		
		JButton btnDeleteReference = new JButton("Delete Reference");
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
		btnDeleteReference.setBounds(10, 434, 142, 23);
		referenceDictionaryPanel.add(btnDeleteReference);
		
		
	}
	
	private void addJPanel_Items() {
		JPanel ItemsPanel = new JPanel();
		tabbedPane.addTab("Items", null, ItemsPanel, null);
		ItemsPanel.setLayout(null);
		
		JTextArea txtDescriptionItem = new JTextArea();
		txtDescriptionItem.setWrapStyleWord(true);
		txtDescriptionItem.setRows(8);
		txtDescriptionItem.setLineWrap(true);
		txtDescriptionItem.setBounds(202, 64, 299, 365);
		ItemsPanel.add(txtDescriptionItem);
		
		JTextField txtItemID = new JTextField();
		txtItemID.setColumns(10);
		txtItemID.setBounds(202, 11, 299, 20);
		ItemsPanel.add(txtItemID);
		
		JTextField txtNameItem = new JTextField();
		txtNameItem.setColumns(10);
		txtNameItem.setBounds(202, 36, 299, 20);
		ItemsPanel.add(txtNameItem);
		
		JLabel lblItemid = new JLabel("ItemID");
		lblItemid.setBounds(10, 14, 182, 14);
		ItemsPanel.add(lblItemid);
		
		JLabel lblNameItems = new JLabel("Name");
		lblNameItems.setBounds(10, 39, 182, 14);
		ItemsPanel.add(lblNameItems);
		
		JLabel lblDescriptionItems = new JLabel("Description");
		lblDescriptionItems.setBounds(10, 64, 182, 14);
		ItemsPanel.add(lblDescriptionItems);
		
		JButton btnInsertItem = new JButton("Insert");
		btnInsertItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item i = new Item(
						txtItemID.getText(), 
						txtNameItem.getText(), 
						txtDescriptionItem.getText());
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
		btnInsertItem.setBounds(10, 400, 89, 23);
		ItemsPanel.add(btnInsertItem);
		
		JButton buttonItemsClear = new JButton("Clear");
		buttonItemsClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtItemID.setText("");
				txtNameItem.setText("");
				txtDescriptionItem.setText("");
			}
		});
		buttonItemsClear.setBounds(103, 400, 89, 23);
		ItemsPanel.add(buttonItemsClear);
		
		JButton btnDeleteItem = new JButton("Delete Item");
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
		btnDeleteItem.setBounds(10, 434, 142, 23);
		ItemsPanel.add(btnDeleteItem);
	}

	private void addJPanel_Interactions() {
		interactionsPanel = new JPanel();
		tabbedPane.addTab("Interactions", null, interactionsPanel, null);
		interactionsPanel.setLayout(null);
		
		JTextArea txtOptionalJournalEntry = new JTextArea();
		txtOptionalJournalEntry.setWrapStyleWord(true);
		txtOptionalJournalEntry.setRows(8);
		txtOptionalJournalEntry.setLineWrap(true);
		txtOptionalJournalEntry.setBounds(202, 279, 299, 57);
		interactionsPanel.add(txtOptionalJournalEntry);
		
		JTextArea txtDescription = new JTextArea();
		txtDescription.setWrapStyleWord(true);
		txtDescription.setRows(8);
		txtDescription.setLineWrap(true);
		txtDescription.setBounds(202, 350, 299, 102);
		interactionsPanel.add(txtDescription);
		
		JTextField txtSanityTreshold = new JTextField();
		txtSanityTreshold.setColumns(10);
		txtSanityTreshold.setBounds(202, 192, 142, 20);
		interactionsPanel.add(txtSanityTreshold);
		
		JTextField txtPagesLocked = new JTextField();
		txtPagesLocked.setColumns(10);
		txtPagesLocked.setBounds(202, 220, 299, 20);
		interactionsPanel.add(txtPagesLocked);
		
		JTextField txtTakeItemID = new JTextField();
		txtTakeItemID.setColumns(10);
		txtTakeItemID.setBounds(202, 248, 299, 20);
		interactionsPanel.add(txtTakeItemID);
		
		JTextField txtInteractionsName = new JTextField();
		txtInteractionsName.setBounds(202, 36, 299, 20);
		interactionsPanel.add(txtInteractionsName);
		
		JTextField txtInteractions_Storyline_PageNo = new JTextField();
		txtInteractions_Storyline_PageNo.setColumns(10);
		txtInteractions_Storyline_PageNo.setBounds(202, 61, 299, 20);
		interactionsPanel.add(txtInteractions_Storyline_PageNo);
		
		JTextField txtMapNo = new JTextField();
		txtMapNo.setColumns(10);
		txtMapNo.setBounds(202, 86, 299, 20);
		interactionsPanel.add(txtMapNo);
		
		JTextField txtEmpathyValue = new JTextField();
		txtEmpathyValue.setColumns(10);
		txtEmpathyValue.setBounds(202, 111, 142, 20);
		interactionsPanel.add(txtEmpathyValue);
		
		JTextField txtSanityValue = new JTextField();
		txtSanityValue.setColumns(10);
		txtSanityValue.setBounds(202, 136, 142, 20);
		interactionsPanel.add(txtSanityValue);
		
		JTextField txtEmpathyTreshold = new JTextField();
		//lblEmpatyTreshold.setLabelFor(txtEmpathyTreshold);
		txtEmpathyTreshold.setColumns(10);
		txtEmpathyTreshold.setBounds(202, 164, 142, 20);
		interactionsPanel.add(txtEmpathyTreshold);
		
		JTextField txtInteractionsReferenceDictionary_Reference = new JTextField();
		txtInteractionsReferenceDictionary_Reference.setToolTipText("Interaction Reference");
		txtInteractionsReferenceDictionary_Reference.setBounds(202, 11, 299, 20);
		interactionsPanel.add(txtInteractionsReferenceDictionary_Reference);
		
		
		JLabel empValWarning = new JLabel("Integer");
		empValWarning.setBounds(354, 114, 101, 14);
		interactionsPanel.add(empValWarning);
		
		JLabel sanityValWarning = new JLabel("Integer");
		sanityValWarning.setBounds(354, 139, 101, 14);
		interactionsPanel.add(sanityValWarning);
		
		JLabel lblOptionaljournalentry = new JLabel("OptionalJournalEntry");
		lblOptionaljournalentry.setBounds(10, 279, 182, 14);
		interactionsPanel.add(lblOptionaljournalentry);
		
		JLabel label = new JLabel("Integer");
		label.setBounds(354, 167, 101, 14);
		interactionsPanel.add(label);
		
		JLabel label_1 = new JLabel("Integer");
		label_1.setBounds(354, 195, 101, 14);
		interactionsPanel.add(label_1);
		
		JLabel lblSanitytreshold = new JLabel("SanityTreshold");
		lblSanitytreshold.setBounds(10, 195, 182, 14);
		interactionsPanel.add(lblSanitytreshold);
		
		JLabel lblPageslocked = new JLabel("PagesLocked");
		lblPageslocked.setBounds(10, 223, 182, 14);
		interactionsPanel.add(lblPageslocked);
		
		JLabel lblTakeItemID = new JLabel("TakeItemID");
		lblTakeItemID.setBounds(10, 251, 182, 14);
		interactionsPanel.add(lblTakeItemID);
		
		JLabel lblStorylinepageno = new JLabel("Storyline_PageNo");
		lblStorylinepageno.setBounds(10, 64, 182, 14);
		interactionsPanel.add(lblStorylinepageno);
		
		JLabel lblMapno = new JLabel("MapNo");
		lblMapno.setBounds(10, 89, 182, 14);
		interactionsPanel.add(lblMapno);
		
		JLabel lblEmpathyvalue = new JLabel("EmpathyValue");
		lblEmpathyvalue.setBounds(10, 114, 182, 14);
		interactionsPanel.add(lblEmpathyvalue);
		
		JLabel lblSanityvalue = new JLabel("SanityValue");
		lblSanityvalue.setBounds(10, 139, 182, 14);
		interactionsPanel.add(lblSanityvalue);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 350, 182, 14);
		interactionsPanel.add(lblDescription);
		
		JLabel lblEmpatyTreshold = new JLabel("EmpathyTreshold");
		lblEmpatyTreshold.setBounds(10, 167, 182, 14);
		interactionsPanel.add(lblEmpatyTreshold);
		
		JLabel lblReferenceDictionary_Reference = new JLabel("ReferenceDictionary_Reference");
		lblReferenceDictionary_Reference.setBounds(10, 14, 182, 14);
		interactionsPanel.add(lblReferenceDictionary_Reference);
		
		JLabel lblInteractionName = new JLabel("Name");
		lblInteractionName.setBounds(10, 39, 182, 14);
		interactionsPanel.add(lblInteractionName);
		
		JButton btnInsertInteraction = new JButton("Insert");
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
						txtOptionalJournalEntry.getText(),
						Integer.parseInt(txtEmpathyTreshold.getText()),
						Integer.parseInt(txtSanityTreshold.getText()),
						txtPagesLocked.getText(),
						txtTakeItemID.getText()
						);
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
		btnInsertInteraction.setBounds(523, 367, 90, 90);
		interactionsPanel.add(btnInsertInteraction);
		
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
				txtEmpathyTreshold.setText("");
				txtSanityTreshold.setText("");
				txtPagesLocked.setText("");
				txtTakeItemID.setText("");
			}
		});
		btnInteractionsClear.setBounds(523, 341, 89, 23);
		interactionsPanel.add(btnInteractionsClear);
		
		JButton btnDeleteInteraction = new JButton("Delete Interaction");
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
		
		btnDeleteInteraction.setBounds(10, 434, 142, 23);
		interactionsPanel.add(btnDeleteInteraction);
	}

	private void addTabContainer() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 45, 627, 537);
		frame.getContentPane().add(tabbedPane);
		
		addJPanel_Interactions();
		addJPanel_InventoryItemsMatch();
		addJPanel_StorySpecification();
		addJPanel_Items();
		addJPanel_ReferenceDescriptions();
		addJPanel_Storyline();
		addJPanel_InventoryItemUseRefMatchPanel();
		
	}
	
	private void addDBPathTextArea() {
		
		dbPathField = new JTextField();
		dbPathField.setBounds(227, 12, 410, 20);
		frame.getContentPane().add(dbPathField);
		dbPathField.setColumns(10);
		
		JLabel lblSelectedDb = new JLabel("Selected DB >>");
		lblSelectedDb.setBounds(121, 15, 96, 14);
		frame.getContentPane().add(lblSelectedDb);
		
		systemOutput.setText(">> Select SQLite DB file first.");
		systemOutput.setWrapStyleWord(true);
		systemOutput.setLineWrap(true);
		systemOutput.setForeground(Color.WHITE);
		systemOutput.setBackground(Color.BLACK);
		systemOutput.setBounds(121, 362, 516, 118);
		scrollSysout = new JScrollPane(systemOutput);
		scrollSysout.setLocation(10, 593);
		scrollSysout.setSize(627, 127);
		scrollSysout.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollSysout);
	}
	
	private void addBtnSelectDB() {
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

package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.entity.Interaction;
import db.entity.InventoryItemsMatch;
import db.entity.Item;
import db.entity.ReferenceDictionary;
import db.entity.Storyline;

public class DBTools{
	
	public static Connection conn;
	public String out;
	
	public DBTools() {
	}


	public static void connect(String path) throws Exception {
        //Connection conn = null;
        //try {
            // db parameters
            String url = "jdbc:sqlite:"+path;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            //textArea.append("Connection to SQLite has been established.");
            
        //} catch (SQLException e) {
        	//textArea.append(e.getMessage());
		/*} finally {
            try {
                if (this.conn != null) {
                    this.conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }*/
    }
	
	public static void closeConnection() {
		try {
            if (conn != null) {
                conn.close();
                //textArea.append("Connection to SQLite has been closed.");
            }
        } catch (SQLException ex) {
            //textArea.append(ex.getMessage());
        }
	}
	
	
	public static void insertInteraction(Interaction i) throws SQLException {
		String sql = "INSERT INTO Interactions(ReferenceDictionary_Reference, Name, Storyline_PageNo, MapNo, EmpathyValue, "
					+ "SanityValue, Description, OptionalJournalEntry, EmpathyTreshold, SanityTreshold, PagesLocked, TakeItemID ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, i.getReference());
            pstmt.setString(2, i.getName());
            pstmt.setString(3, i.getPage());
            pstmt.setString(4, i.getMap());
            pstmt.setInt(5, i.getEmpathy());
            pstmt.setInt(6, i.getSanity());
            pstmt.setString(7, i.getDesc());
            pstmt.setString(8, i.getJournal());
            pstmt.setInt(9, i.getEmpatyTreshold());
            pstmt.setInt(10, i.getSanityTreshold());
            pstmt.setString(11, i.getPagesLocked());
            pstmt.setString(12, i.getTakeItemID());
            pstmt.executeUpdate();
        
		
	}


	public static void insertItem(Item i) throws SQLException {
		String sql = "INSERT INTO Items(ItemID, Name, Description ) VALUES(?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, i.getItemId());
            pstmt.setString(2, i.getName());
            pstmt.setString(3, i.getDesc());

            pstmt.executeUpdate();
        
		
	}


	public static void insertReferenceDictionary(ReferenceDictionary i) throws Exception {
		String sql = "INSERT INTO ReferenceDictionary(Reference, Name, Description ) VALUES(?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, i.getReference());
            pstmt.setString(2, i.getName());
            pstmt.setString(3, i.getDesc());

            pstmt.executeUpdate();
        
	}


	public static void insertStoryline(Storyline i) throws SQLException {
		String sql = "INSERT INTO Storyline(PageNo, PageText, MilestoneJournalEntry ) VALUES(?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, i.getPageNo());
            pstmt.setString(2, i.getPageText());
            pstmt.setString(3, i.getMilestoneJournalEntry());
            pstmt.executeUpdate();
	}
	
	public static void insertInventoryItemsMatch(InventoryItemsMatch i) throws SQLException {
		String sql = "INSERT INTO InventoryItemsMatch(ItemID_1, ItemID_2, CreatedObjectID, EffectDescription, Sanity, Empathy, SanityTreshold, EmpathyTreshold, PageNo, MapNo, OptionalJournalEntry, PagesLocked ) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, i.getItemID_1());
            pstmt.setString(2, i.getItemID_2());
            pstmt.setString(3, i.getCreatedObjectID());
            pstmt.setString(4, i.getEfectDesc());
            pstmt.setInt(5, i.getSanity());
            pstmt.setInt(6, i.getEmpathy());
            pstmt.setInt(7, i.getSanityTreshold());
            pstmt.setInt(8, i.getEmpathyTreshold());
            pstmt.setString(9, i.getPageNo());
            pstmt.setString(10, i.getMapNo());
            pstmt.setString(11, i.getOptionalJournalEntry());
            pstmt.setString(12, i.getPagesLocked());
            pstmt.executeUpdate();
	}
	
	public static List<ReferenceDictionary> selectReferenceDictionary() throws SQLException{
		String sql = "SELECT * FROM ReferenceDictionary";
		List<ReferenceDictionary> rfl = new ArrayList<ReferenceDictionary>();
		Statement stmt  = conn.createStatement();
	    ResultSet rs    = stmt.executeQuery(sql);
			
			while(rs.next()) {
				rfl.add(new ReferenceDictionary(
						rs.getString("Reference"), 
						rs.getString("Name"), 
						rs.getString("Description")));
			}
			
		return rfl;
	}
	
	public static List<Item> selectItems() throws SQLException{
		String sql = "SELECT * FROM Items";
		List<Item> itemList = new ArrayList<Item>();
		Statement stmt  = conn.createStatement();
	    ResultSet rs    = stmt.executeQuery(sql);
			
			while(rs.next()) {
				itemList.add(new Item(
						rs.getString("ItemID"), 
						rs.getString("Name"), 
						rs.getString("Description")));
			}
		return itemList;
	}
	
	public static List<Storyline> selectStoryline(String string) throws Exception {
		String sql = "SELECT * FROM Storyline where PageNo = '"+string+"'";
		System.out.println(sql);
		List<Storyline> storylist = new ArrayList<Storyline>();
		Statement stmt  = conn.createStatement();
	    ResultSet rs    = stmt.executeQuery(sql);
			
			while(rs.next()) {
				storylist.add(new Storyline(
						rs.getString("PageNo"), 
						rs.getString("PageText"), 
						rs.getString("MilestoneJournalEntry")));
			}
		return storylist;
	}
	
	public static List<Storyline> selectStoryline() throws SQLException {
		String sql = "SELECT * FROM Storyline";
		List<Storyline> storylist = new ArrayList<Storyline>();
		Statement stmt  = conn.createStatement();
	    ResultSet rs    = stmt.executeQuery(sql);
			
			while(rs.next()) {
				storylist.add(new Storyline(
						rs.getString("PageNo"), 
						rs.getString("PageText"), 
						rs.getString("MilestoneJournalEntry")));
			}
		return storylist;
	}


	public static void deleteStoryline(String pageNo) throws SQLException {
		String sql = "DELETE FROM Storyline WHERE PageNo = ?";
		//String out;
		PreparedStatement pstmt = conn.prepareStatement(sql);
	            // set the corresponding param
	            pstmt.setString(1, pageNo);
	            // execute the delete statement
	            pstmt.executeUpdate();
	            //out = ">> PageNo: " + pageNo + "has been successfully removed.";
		 //return out;
	}


	public static String deleteReferenceDictionary(String reference) {
		String sql = "DELETE FROM ReferenceDictionary WHERE Reference = ?";
		String out;
		 try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            // set the corresponding param
	            pstmt.setString(1, reference);
	            // execute the delete statement
	            pstmt.executeUpdate();
	            out = ">> Reference: " + reference + " has been successfully removed.";
	        } catch (SQLException e) {
	            out = e.getMessage();
	        }
		 
		 return out;
	}


	public static String deleteItem(String itemId) {
		String sql = "DELETE FROM Items WHERE ItemID = ?";
		String out;
		 try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            // set the corresponding param
	            pstmt.setString(1, itemId);
	            // execute the delete statement
	            pstmt.executeUpdate();
	            out = ">> Item by ItemID : " + itemId + " has been successfully removed.";
	        } catch (SQLException e) {
	            out = e.getMessage();
	        }
		 return out;
	}
	
	public static String deleteInteraction(String refDic_ref, String name) {
		String sql = "DELETE FROM Interactions WHERE ReferenceDictionary_Reference = ? AND Name = ?";
		String out;
		 try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            // set the corresponding param
	            pstmt.setString(1, refDic_ref);
	            pstmt.setString(2, name);
	            // execute the delete statement
	            pstmt.executeUpdate();
	            out = ">> Interaction by ReferenceDictionary_Reference AND Name : [" + refDic_ref + " / " + name + "] has been successfully removed.";
	        } catch (SQLException e) {
	            out = e.getMessage();
	        }
		 return out;
	}


	


	


	

}

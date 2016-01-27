package uk.ac.york.viewer;

public class TableEntry {

	private String GSNElementID;
	private String role;
	private String roleBinding;
	
	public static TableEntry[] example()
	{
		TableEntry[] entries = new TableEntry[12];
		entries[0] = new TableEntry("G1","r1","bin1");
		entries[1] = new TableEntry("G2","r2","bin2");
		entries[2] = new TableEntry("G3","r3","bin3");
		return entries;
	}
	
	public TableEntry(String GSNElementID, String role, String roleBinding)
	{
		this.GSNElementID = GSNElementID;
		this.role = role;
		this.roleBinding = roleBinding;
	}
	
	public String getID()
	{
		return GSNElementID;
	}
	
	public String getRole()
	{
		return role;
	}
	
	public String getRoleBinding()
	{
		return roleBinding;
	}
}

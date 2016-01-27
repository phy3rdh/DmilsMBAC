package uk.ac.york.viewer;

import uk.ac.york.parser.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import java.net.URL;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;

public class TableViewerShell extends Shell {
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			TableViewerShell shell = new TableViewerShell(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public TableViewerShell(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(2, false));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		TableViewer tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		tableViewer.setContentProvider(new ArrayContentProvider());
		
	    String[] columnNames = { "GSN Element ID", "Role Name", "Role Binding"};
	    int[] columnAlignments = new int[] {SWT.LEFT, SWT.LEFT, SWT.LEFT};
	    int[] columnWidths = new int[] {400, 250, SWT.MAX};
	    
	    for (int i = 0; i < columnNames.length; i++) {
	    	TableColumn tableColumn = new TableColumn(table, columnAlignments[i]);
	    	tableColumn.setText(columnNames[i]);
	    	tableColumn.setWidth(columnWidths[i]);
	    } 
	    tableViewer.setLabelProvider( new TableLabelProvider());
	  
	    try
	    {
	    	Parser parser = new Parser();
	    	parser.parse("table.xml");
	    	// tableViewer.setInput(TableEntry.example());
	    	tableViewer.setInput(parser.getElements().toArray());
	    }
	    catch(Exception ex){}
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Instantiation Table Viewer");
		//setSize(547, 379);
		setSize(1094, 500);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}

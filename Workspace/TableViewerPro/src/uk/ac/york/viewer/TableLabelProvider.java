package uk.ac.york.viewer;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableLabelProvider extends LabelProvider implements ITableLabelProvider { 

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		TableEntry tableEntry = (TableEntry) element;
		switch (columnIndex) {
			case 0 :
				return tableEntry.getID();
			case 1 :
				return tableEntry.getRole();
			case 2 :
				return tableEntry.getRoleBinding();
			case 3 :
			default :
				break;
		}
		return "";
	}
}
/*
 * [The "BSD license"]
 * Copyright (c) 2011, abego Software GmbH, Germany (http://www.abego.org)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of the abego Software GmbH nor the names of its 
 *    contributors may be used to endorse or promote products derived from this 
 *    software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
//package org.abego.treelayout.demo.swing;
package gsnSwingTree;

import java.awt.Container;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.xml.parsers.ParserConfigurationException;

import org.abego.treelayout.TreeForTreeLayout;
import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.demo.GSNTreeFactory;
import org.abego.treelayout.demo.SampleTreeFactory;
import org.abego.treelayout.demo.TextInBox;
import org.abego.treelayout.demo.TextInBoxNodeExtentProvider;
import org.abego.treelayout.util.DefaultConfiguration;
import org.xml.sax.SAXException;

/**
 * Creates a GSN diagram representation of a GSNML model file.
 * The GSNML file must have been pre-formatted as an XML file called "formattedGSNML.xml" of the following structure"
 * 
 * <?xml version="1.0"?>
	<argumentTree>
		<node id="x" parentID="y"></node>
	</argumentTree>
 * 
 * These XML files can be created from the GSNML file using the program gsnmlFormatForTree.eol
 * 
 * @author Richard Hawkins (richard.hawkins@york.ac.uk)
 */
public class GSNSwingTree {

	private static void showInDialog(JComponent panel) {
		JDialog dialog = new JDialog();
		Container contentPane = dialog.getContentPane();
		
		//new
		 // JPanel container = new JPanel();
	        JScrollPane scrPane = new JScrollPane(panel);
	        contentPane.add(scrPane);
	        //endNew
	        
		((JComponent) contentPane).setBorder(BorderFactory.createEmptyBorder(
				10, 10, 10, 10));
		//contentPane.add(panel);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	private static TreeForTreeLayout<TextInBox> getSampleTree(String treeName) throws SAXException, IOException, ParserConfigurationException {
		TreeForTreeLayout<TextInBox> tree;
		if (treeName.equals("2")) {
			tree = SampleTreeFactory.createSampleTree2();
		} else if (treeName.equals("")) {
			tree = GSNTreeFactory.createGSNTree();
		} else {
			throw new RuntimeException(String.format("Invalid tree name: '%s'",
					treeName));
		}
		return tree;
	}

	/**
	 * Shows a dialog with a tree in a layout created by {@link TreeLayout},
	 * using the Swing component {@link TextInBoxTreePane}.
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// get the sample tree
		String treeName = (args.length > 0) ? args[0] : "";
		TreeForTreeLayout<TextInBox> tree = getSampleTree(treeName);
				
		// setup the tree layout configuration
		double gapBetweenLevels = 50;
		double gapBetweenNodes = 10;
		DefaultConfiguration<TextInBox> configuration = new DefaultConfiguration<TextInBox>(
				gapBetweenLevels, gapBetweenNodes);


		// create the NodeExtentProvider for TextInBox nodes
		TextInBoxNodeExtentProvider nodeExtentProvider = new TextInBoxNodeExtentProvider();

		// create the layout
		TreeLayout<TextInBox> treeLayout = new TreeLayout<TextInBox>(tree,
				nodeExtentProvider, configuration);

		// Create a panel that draws the nodes and edges and show the panel
		TextInBoxTreePane panel = new TextInBoxTreePane(treeLayout);
		showInDialog(panel);
	}
}

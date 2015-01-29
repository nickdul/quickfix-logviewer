/****************************************************************************
** Copyright (c) quickfixengine.org  All rights reserved.
**
** This file is part of the QuickFIX FIX Engine
**
** This file may be distributed under the terms of the quickfixengine.org
** license as defined by quickfixengine.org and appearing in the file
** LICENSE included in the packaging of this file.
**
** This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING THE
** WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
**
** See http://www.quickfixengine.org/LICENSE for licensing information.
**
** Contact ask@quickfixengine.org if any conditions of this licensing are
** not clear to you.
**
****************************************************************************/

package quickfix.logviewer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class Frame extends JFrame {
	
	public Frame( DefaultDataDictionaryAccess dataDictionary ) throws HeadlessException {
		super();		
		
		ProgressBarPanel progressBarPanel = new ProgressBarPanel( new ProgressBar() );
		MenuBar menuBar = new MenuBar();
		setJMenuBar( menuBar );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		SplitPane pane = new SplitPane( 
				this, 
				progressBarPanel, 
				new MessageTable(), new MessageTree(), 
				new JTabbedPane(), new JTabbedPane(), 
				dataDictionary );
		
		pane.setDividerLocation(300);
		
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        addButtons(toolbar, pane);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolbar, BorderLayout.NORTH);
		getContentPane().add( pane, BorderLayout.CENTER );
        getContentPane().add(progressBarPanel, BorderLayout.SOUTH);

		setTitle( null );
		setSize( 800, 600 );
        fileLoaded( false );
        menuBar.addActionListener(pane);
	}

    private void addButtons(JToolBar toolbar, ActionListener l) {
        toolbar.add(makeButton("open.gif", "Open", "Open FIX log file", l));
        toolbar.add(makeButton("delete_obj.gif", "Close", "Close current tab", l));
        toolbar.addSeparator(new Dimension(20, 10));
        toolbar.add(makeButton("sync.png", "Reload", "Reload active tab", l));
    }

    private JButton makeButton(String imageName, String actionCommand, String toolTipText, ActionListener l)
    {
        URL imageURL = Thread.currentThread().getContextClassLoader().getResource("quickfix/logviewer/images/" + imageName);
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.addActionListener(l);
        button.setText(actionCommand);
        button.setToolTipText(toolTipText);
        button.setIcon(new ImageIcon(imageURL));
        button.setFocusable(false);
        return button;
    }

	public void fileLoaded(boolean value) {
		if( !value )
			super.setTitle( "QuickFIX Log Viewer (no file loaded)" );
		else
			super.setTitle("QuickFIX Log Viewer");
	}
}

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

import quickfix.DataDictionary;

import javax.swing.*;
import java.awt.*;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class Main {

	static {
		TimeZone.setDefault( new SimpleTimeZone(SimpleTimeZone.UTC_TIME,"GMT") );
	}
	
	public static void main( String[] args ) throws Exception {
		String version = null;
		for (int i = 0; i < args.length; i++) {
			if ("-v".equals(args[i])) {
				version = args[(i + 1)];
			}
		}
		String resource = version != null ? version + ".xml" : "FIX44.xml";

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		DataDictionary dataDictionary = new DataDictionary(loader.getResourceAsStream(resource));
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		}
		Frame frame = new Frame(new DefaultDataDictionaryAccess(dataDictionary));
		frame.setSize(new Dimension(1000, 900));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
 	}
}

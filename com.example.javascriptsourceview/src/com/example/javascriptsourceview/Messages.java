
package com.example.javascriptsourceview;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.example.javascriptsourceview.messages";//$NON-NLS-1$

	public static String Script_Snippet;
	
	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}

package com.example.javascriptsourceview;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;
import org.eclipse.wst.jsdt.core.JavaScriptCore;
import org.eclipse.wst.jsdt.l.ui.text.PreferencesAdapter;
//import org.eclipse.wst.jsdt.internal.ui.text.PreferencesAdapter;
import org.eclipse.wst.jsdt.ui.PreferenceConstants;
import org.eclipse.wst.jsdt.ui.javaeditor.JavaSourceViewer;
import org.eclipse.wst.jsdt.ui.text.IJavaScriptPartitions;
import org.eclipse.wst.jsdt.ui.text.JavaScriptSourceViewerConfiguration;
import org.eclipse.wst.jsdt.ui.text.JavaScriptTextTools;

public class JavaScriptSourceView extends ViewPart {

	private IDocument document;

	
	@Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new FillLayout());
        ISourceViewer viewer;
        int styles = SWT.H_SCROLL | SWT.V_SCROLL;
        document = new Document();
        document.set(Messages.Script_Snippet);
//        IPreferenceStore store = JavaScriptPlugin.getDefault().getCombinedPreferenceStore();
        PreferenceConstants.initializeDefaultValues(Activator.getDefault().getPreferenceStore());
        IPreferenceStore generalTextStore= EditorsUI.getPreferenceStore(); 
        ChainedPreferenceStore store= new ChainedPreferenceStore(new IPreferenceStore[] { new PreferencesAdapter(JavaScriptCore.getPlugin().getPluginPreferences()), Activator.getDefault().getPreferenceStore(), generalTextStore });
		
        
		
        viewer = new JavaSourceViewer(parent, null, null, false, styles, store);
        viewer.setDocument(document);
//        JavaScriptTextTools tools = JavaScriptPlugin.getDefault().getJavaTextTools();
        JavaScriptTextTools tools = new JavaScriptTextTools(store);
        tools.setupJavaDocumentPartitioner(viewer.getDocument(), IJavaScriptPartitions.JAVA_PARTITIONING);
//        JavaScriptSourceViewerConfiguration config = new JavaScriptSourceViewerConfiguration(tools.getColorManager(), store,
//                null, IJavaScriptPartitions.JAVA_PARTITIONING, true);
        JavaScriptSourceViewerConfiguration configure = new JavaScriptSourceViewerConfiguration(tools.getColorManager(), store, null,
        		IJavaScriptPartitions.JAVA_PARTITIONING);
        viewer.configure(configure);
        viewer.getTextWidget().setFont(JFaceResources.getFont(PreferenceConstants.EDITOR_TEXT_FONT));
        document = viewer.getDocument();
        viewer.setEditable(true);
//        getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
//        createMenu();
    }

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}

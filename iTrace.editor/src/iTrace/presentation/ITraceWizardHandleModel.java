package iTrace.presentation;



import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;

import iTrace.presentation.ITraceModelWizard.ITraceModelWizardModelsCreationPage;

public class ITraceWizardHandleModel extends Dialog {
	
	final int TYPE_METAMODEL = 0;
	final int TYPE_MODEL = 1;
	protected int resourceType = TYPE_METAMODEL;
	
	private Text modelNameText;
	private Text pathText;
	private Text metamodelText;
	private Button firstBrowseButton; 
	private Button secondBrowseButton;
	private Button thirdBrowseButton;
	private Label firstFileLabel;
	private Label secondFileLabel; 	
	private Button button1;
	private Button button2;
	private ArrayList<ArtefactData> artefacts;
	private ITraceModelWizardModelsCreationPage page;
	private int index_selected=-1;
	private String project;
	
	final int CREATING = 0;
	final int EDITING = 1;
	private int action;
	private Label labelBoth;

	protected ITraceWizardHandleModel(Shell parentShell, ArrayList<ArtefactData> artefacts, ITraceModelWizardModelsCreationPage page, String project) {
		super(parentShell);
		this.artefacts = artefacts;
		this.page = page;
		this.project = project;
	}
	
	protected ITraceWizardHandleModel(Shell parentShell,
			ArrayList<ArtefactData> artefacts,
			ITraceModelWizardModelsCreationPage page, int index_modelData, String project) {
		super(parentShell);
		this.artefacts = artefacts;
		this.page = page;
		this.index_selected=index_modelData;
		this.project = project;
	}

	public Control createDialogArea(Composite parent){
		Composite container = new Composite(parent, SWT.NULL);
		{
			GridLayout layout = new GridLayout();
			layout.numColumns = 3;
			container.setLayout(layout);
		}	
		createDialogComponents(container);
		initControls();
		return parent;
	}
	
	private void initControls() {
		if(index_selected<0){
			action = CREATING;
			button1.setSelection(true);
			modelNameText.setText("");
			secondFileLabel.setVisible(false);
			metamodelText.setVisible(false);
			secondBrowseButton.setVisible(false);
			thirdBrowseButton.setVisible(false);
			firstFileLabel.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_pathMetamodel"));
			pathText.setText("");
			metamodelText.setText("");
			labelBoth.setVisible(false);
			resourceType = TYPE_METAMODEL;
		}else{
			action = EDITING;
			ArtefactData elementSelected = artefacts.get(index_selected);
			if(elementSelected.isMetamodel()){
				resourceType = TYPE_METAMODEL;
				button1.setSelection(true);
				modelNameText.setText(elementSelected.getName());
				secondFileLabel.setVisible(false);
				thirdBrowseButton.setVisible(false);
				metamodelText.setVisible(false);
				secondBrowseButton.setVisible(false);
				firstFileLabel.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_pathMetamodel"));
				pathText.setText(elementSelected.getPath());
				labelBoth.setVisible(false);
				metamodelText.setText("");
			}else{
				resourceType=TYPE_MODEL;
				button2.setSelection(true);
				modelNameText.setText(elementSelected.getName());
				firstFileLabel.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_pathModel"));
				secondFileLabel.setVisible(true);
				thirdBrowseButton.setVisible(true);
				labelBoth.setVisible(true);
				metamodelText.setVisible(true);
				secondBrowseButton.setVisible(true);
				pathText.setText(elementSelected.getPath());
				metamodelText.setText(elementSelected.getMetamodel());
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	public void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_Title"));
	}

	private void createDialogComponents(Composite parent) {
		Composite composite = new Composite(parent,SWT.BEGINNING);
		{
			GridData gdata = new GridData(GridData.FILL_HORIZONTAL);
			gdata.horizontalSpan=3;
			composite.setLayoutData(gdata);
		}
		{
			GridLayout layout = new GridLayout();
			composite.setLayout(layout);
			layout.numColumns = 4;
			layout.marginWidth = 4;
		}
		
		Label label = new Label(composite, SWT.BEGINNING);
		label.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_labelName"));
		modelNameText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		{
			GridData gd = new GridData(GridData.GRAB_HORIZONTAL);
			gd.widthHint = 200;
			modelNameText.setLayoutData(gd);
		}
		
		modelNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				//dialogChanged();
			}
		});
		
		label = new Label(composite, SWT.BEGINNING);
		label = new Label(composite, SWT.BEGINNING);
		
		label = new Label(composite,SWT.BEGINNING);
		label.setText("Is &a");
		
		Composite radios = new Composite(composite,SWT.BEGINNING);
		{
			GridData gdata = new GridData(GridData.GRAB_HORIZONTAL);
			gdata.widthHint = 200;
			radios.setLayoutData(gdata);
			
			GridLayout layout = new GridLayout();
			radios.setLayout(layout);
			layout.numColumns = 4;
			layout.marginWidth = 4;
		}
		button1 = new Button(radios, SWT.RADIO);
		button1.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_labelButton1"));
		button1.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e) {
				// Work with metamodel or with model-metamodel
				resourceType = TYPE_METAMODEL;
				secondFileLabel.setVisible(false);
				metamodelText.setVisible(false);
				firstBrowseButton.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_BrowseMetamodelLabel"));
				secondBrowseButton.setVisible(false);
				thirdBrowseButton.setVisible(false);
				firstFileLabel.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_pathMetamodel"));
				pathText.setText("");
				metamodelText.setText("");
				labelBoth.setVisible(false);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}});
		
		
		label = new Label(radios, SWT.BEGINNING);
		label.setText("or a");
		
		button2 = new Button(radios, SWT.RADIO);
		button2.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_labelButton2"));
		button2.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e) {
				resourceType=TYPE_MODEL;
				firstFileLabel.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_pathModel"));
				secondFileLabel.setVisible(true);
				metamodelText.setVisible(true);
				firstBrowseButton.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_BrowseModelLabel"));
				secondBrowseButton.setVisible(true);
				thirdBrowseButton.setVisible(true);
				pathText.setText("");
				metamodelText.setText("");
				labelBoth.setVisible(true);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}});
		label = new Label(radios, SWT.BEGINNING);
		label.setText(" ?");
		
		// Third Line : metamodel
		firstFileLabel = new Label(parent, SWT.BEGINNING);
		firstFileLabel.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_pathMetamodel"));
		
		pathText = new Text(parent, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 300;
		pathText.setLayoutData(gd);
		
		pathText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
			}
		});

		firstBrowseButton = new Button(parent, SWT.PUSH);
		firstBrowseButton.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_BrowseMetamodelLabel"));
		firstBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleWorkspaceFileBrowse(pathText, project, resourceType);
			}
		});	
		
		// Fourth line : model
		secondFileLabel = new Label(parent, SWT.BEGINNING);
		secondFileLabel.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_pathMetamodelRegistry"));
		
		metamodelText = new Text(parent, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		metamodelText.setLayoutData(gd);
		metamodelText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
			}
		});
		
		secondBrowseButton = new Button(parent, SWT.PUSH);
		secondBrowseButton.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_BrowseMetamodelLabel"));
		secondBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleWorkspaceFileBrowse(metamodelText,project,TYPE_METAMODEL);
			}
		});	
			
		
		label = new Label(parent, SWT.BEGINNING);
		labelBoth = new Label(parent, SWT.BEGINNING);
		labelBoth.setText("To select both resources (first the model and later the metamodel):");
		thirdBrowseButton = new Button(parent, SWT.PUSH);
		thirdBrowseButton.setText(ITraceEditorPlugin.INSTANCE.getString("_UI_WizardHandleModelDialog_BrowseModelandMetamodelLabel"));
		thirdBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleWorkspaceFileBrowse(pathText,metamodelText,project);
			}
		});	
	}
	
	public void okPressed(){
		// Verify they are all not null		
		if (this.verifyData() == false){
			MessageDialog.openWarning(getShell(), 
					"Warning","Some informations are empty");
			return;
		}
		if (action==CREATING){
			ArtefactData artefact = new ArtefactData();
			artefact.setName(modelNameText.getText());
			artefact.setPath(pathText.getText());
			if (resourceType == TYPE_METAMODEL){
				artefact.setIsMetamodel(true);
			}else{ //TYPE_MODEL
				artefact.setIsMetamodel(false);
				artefact.setMetamodel(metamodelText.getText());
			}
			artefacts.add(artefact);
		}else{
			ArtefactData elementSelected = artefacts.get(index_selected);
			elementSelected.setName(modelNameText.getText());
			elementSelected.setPath(pathText.getText());
			if (resourceType == TYPE_METAMODEL){
				elementSelected.setIsMetamodel(true);
			}else{ //TYPE_MODEL
				elementSelected.setIsMetamodel(false);
				elementSelected.setMetamodel(metamodelText.getText());
			}
		}
		page.rewriteTable();		
		
		// Close dialog
		this.close();
	}
	
	/**
	 * Tests the controls
	 * @return
	 */
	private boolean verifyData() {
		boolean result = true;
		if(resourceType==TYPE_MODEL)
			result = result && (metamodelText.getText() != "");
		result = result && (pathText.getText() != "");
		result = result && (modelNameText.getText() != "");
		return result;
	}
	
	/*
	 * Shows a dialog to select a file. File path is set on the fileText
	 */
	protected void handleWorkspaceFileBrowse(Text text, String project,int resourceType) {
		ResourceSelectionDialog resourceSelectionDialog;
		if(resourceType==TYPE_METAMODEL){
			resourceSelectionDialog = new ResourceSelectionDialog(
					getShell(),
					ResourcesPlugin.getWorkspace().getRoot()
							.getProject(project),
							ITraceEditorPlugin.INSTANCE
							.getString("_UI_WizardHandleModelDialog_SelectMetaModel"));
		}else{
			 resourceSelectionDialog = new ResourceSelectionDialog(
					getShell(),
					ResourcesPlugin.getWorkspace().getRoot()
							.getProject(project),
							ITraceEditorPlugin.INSTANCE
							.getString("_UI_WizardHandleModelDialog_SelectModel"));
		}

		String path = text.getText();
		
		if (path != "") {
			ArrayList<IResource> selectedElements = new ArrayList<IResource>();
			selectedElements.add(ResourcesPlugin.getWorkspace().getRoot()
					.findMember(path));
			resourceSelectionDialog
					.setInitialElementSelections(selectedElements);
		}
		resourceSelectionDialog.open();

		if (resourceSelectionDialog.getReturnCode() == ResourceSelectionDialog.OK) {
			Object[] result = resourceSelectionDialog.getResult();
			if (result != null) {
				text.setText(((IFile) result[0]).getFullPath().toString());
			}
		}
	}
	
	/*
	 * Shows a dialog to select a file. File path is set on the fileText
	 */
	protected void handleWorkspaceFileBrowse(Text pathText, Text metamodelText, String project) {
		handleWorkspaceFileBrowse(pathText,project,TYPE_MODEL);
		handleWorkspaceFileBrowse(metamodelText,project,TYPE_METAMODEL);
	}

}

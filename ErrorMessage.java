package edu.byu.isys414.jmcmurdi.IntexII;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;

public class ErrorMessage extends Dialog {

	protected Object result;
	protected Shell shlError;
	public String error_message;
	private Text text_1;
	private Text text;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ErrorMessage(Shell parent, String error_message, int style) {
		super(parent, style);
		setText("SWT Dialog");
		this.error_message = error_message;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlError.open();
		shlError.layout();
		Display display = getParent().getDisplay();
		while (!shlError.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlError = new Shell(getParent(),SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlError.setSize(387, 248);
		shlError.setText("Error");
		shlError.setLayout(new GridLayout(1, false));
		new Label(shlError, SWT.NONE);
		new Label(shlError, SWT.NONE);
		new Label(shlError, SWT.NONE);
		new Label(shlError, SWT.NONE);
		
		text = new Text(shlError, SWT.READ_ONLY | SWT.WRAP | SWT.CENTER);
		text.setText(error_message);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		
		
	

	}
}

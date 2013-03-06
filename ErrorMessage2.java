package edu.byu.isys414.jmcmurdi.IntexII;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import swing2swt.layout.BorderLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ErrorMessage2 extends Dialog {

	protected Object result;
	protected Shell shell;
	public String errormessage;
	private Text text;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ErrorMessage2(Shell parent, String errormessage, int style) {
		super(parent, style);
		setText("SWT Dialog");
		this.errormessage = errormessage;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(),SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(387, 248);
		shell.setText("Error");
		shell.setLayout(new BorderLayout(0, 0));
		
		text = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.CENTER | SWT.MULTI);
		text.setEnabled(false);
		text.setEditable(false);
		text.setFont(SWTResourceManager.getFont("Ubuntu", 20, SWT.NORMAL));
		text.setText(errormessage);
		text.setLayoutData(BorderLayout.CENTER);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		
		Button Okay = new Button(composite, SWT.NONE);
		Okay.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		Okay.setText("Okay");
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		

	}
}

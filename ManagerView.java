package edu.byu.isys414.jmcmurdi.IntexII;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;

public class ManagerView {

	protected Shell ManagerView;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ManagerView window = new ManagerView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		ManagerView.open();
		ManagerView.layout();
		while (!ManagerView.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		ManagerView = new Shell();
		ManagerView.setSize(986, 698);
		ManagerView.setText("Manager View");

	}
}

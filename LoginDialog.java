package edu.byu.isys414.jmcmurdi.IntexII;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BoxLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Listener;

public class LoginDialog extends Dialog {

	protected Object result;
	protected Shell shlLogin;
	private Text password;
	private Text username;
	private boolean successful = false;
	private String username2;
	private Button btnLogin;

	
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public LoginDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlLogin.open();
		shlLogin.layout();
		Display display = getParent().getDisplay();
		while (!shlLogin.isDisposed()) {
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
		shlLogin = new Shell(getParent(), SWT.APPLICATION_MODAL);
		
		shlLogin.setSize(450, 300);
		shlLogin.setText("Login");
		shlLogin.setLayout(new BorderLayout(0, 0));

		shlLogin.setDefaultButton(btnLogin);

		Composite composite = new Composite(shlLogin, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		
		Composite composite_1 = new Composite(shlLogin, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.WEST);
		
		Composite composite_2 = new Composite(shlLogin, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.SOUTH);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite_6 = new Composite(composite_2, SWT.NONE);
		
		Button btnQuit = new Button(composite_2, SWT.NONE);
		btnQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//System.out.println("test");
				shlLogin.dispose();
//				System.exit(0);
				//shlPosView.close();
			}
		});
		btnQuit.setFont(SWTResourceManager.getFont("Ubuntu", 15, SWT.NORMAL));
		btnQuit.setText("Quit");
		
		Composite composite_5 = new Composite(composite_2, SWT.NONE);
		
		this.btnLogin = new Button(composite_2, SWT.NONE);
		btnLogin.setSelection(true);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loginbuttonclicked();
			}
		});
		btnLogin.setFont(SWTResourceManager.getFont("Ubuntu", 15, SWT.NORMAL));
		btnLogin.setText("Login");
		
		Composite composite_7 = new Composite(composite_2, SWT.NONE);
		
		Composite composite_3 = new Composite(shlLogin, SWT.NONE);
		composite_3.setLayoutData(BorderLayout.EAST);
		
		Composite composite_4 = new Composite(shlLogin, SWT.NONE);
		composite_4.setLayoutData(BorderLayout.CENTER);
		composite_4.setLayout(new GridLayout(3, false));
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		
		Label lblUsername = new Label(composite_4, SWT.NONE);
		lblUsername.setFont(SWTResourceManager.getFont("Ubuntu", 15, SWT.NORMAL));
		lblUsername.setText("Username");
		new Label(composite_4, SWT.NONE);
		
		username = new Text(composite_4, SWT.BORDER);
		username.setFont(SWTResourceManager.getFont("Lucida Grande", 16, SWT.NORMAL));
		GridData gd_username = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_username.heightHint = 33;
		username.setLayoutData(gd_username);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		username.setFocus();
		//sername.setCapture(true);
		
		Label lblPassword = new Label(composite_4, SWT.NONE);
		lblPassword.setFont(SWTResourceManager.getFont("Ubuntu", 15, SWT.NORMAL));
		lblPassword.setText("Password");
		new Label(composite_4, SWT.NONE);
		
		password = new Text(composite_4, SWT.BORDER | SWT.PASSWORD);
		password.setFont(SWTResourceManager.getFont("Lucida Grande", 16, SWT.NORMAL));
		password.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent event) {
				if (event.detail == SWT.TRAVERSE_RETURN){
					//System.out.println("The user pressed Enter");
					loginbuttonclicked();
					
				}
			}
		});
		GridData gd_password = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_password.heightHint = 32;
		password.setLayoutData(gd_password);

	}
	  public boolean authenticate(String NetID, String Password) {
	        try{
	            Hashtable env = new Hashtable();
	            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	            env.put(Context.PROVIDER_URL, "ldaps://ldap.byu.edu/");
	            env.put(Context.SECURITY_AUTHENTICATION, "simple");
	            env.put(Context.SECURITY_PRINCIPAL, "uid=" + NetID + ", ou=People, o=byu.edu");
	            env.put(Context.SECURITY_CREDENTIALS, Password);
	            DirContext ctx = new InitialDirContext(env);
	            return true;
	        }catch (NamingException e) {
	            return false;
	        }
	    }

	/**
	 * @return the successful
	 */
	public boolean isSuccessful() {
		return successful;
	}

	/**
	 * @param successful the successful to set
	 */
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	private void errormessage(String error) {
		String error_message = error;
		ErrorMessage2 em = new ErrorMessage2(shlLogin, error_message, 0);
		em.open();
	    }

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username2;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username2 = username;
	}
	public void loginbuttonclicked(){
		String usernametext = username.getText();
		username2 = usernametext;
		String passwordtext = password.getText();
		boolean tempbool = authenticate(usernametext, passwordtext);
		if(tempbool){
			//System.out.println("Party like a rockstar");
			setSuccessful(true);
			shlLogin.dispose();
		}
		else{
			//System.out.println("Failure");
			setSuccessful(false);
			errormessage("Incorrect UserName or password\nPlease contact administrator for help");
		}
	}
	  
	    
}

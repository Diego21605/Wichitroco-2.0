
package Controlador;

import vista.Login;
import vista.JfrSplash;

/**
 *
 * @author Diego
 */
public class CtrlSplash extends javax.swing.JFrame implements Runnable{
    
    private JfrSplash JfrSplash;
    private Thread tiempo=null;
    
    public CtrlSplash(JfrSplash JfrSplash) {
        this.JfrSplash = JfrSplash;
        this.tiempo=new Thread(this);
        this.tiempo.start();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfrSplash().setVisible(true);
            }
        });
    }
    
    @Override
    public void run() {

        try {

             Thread.sleep(5000);
             JfrSplash.dispose();
             Login Login = new Login();
             Login.setVisible(true);
             CtrlLogin log= new CtrlLogin(Login);
             Login.txtUser.requestFocus();

         } catch (InterruptedException ex) {

         }
    }
    
    
    
}

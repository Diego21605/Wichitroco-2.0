
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.Login;
import Modelo.Conexion;
import vista.JfrAdmin;
import vista.JfrJefeBodega;
import vista.JfrJefeVentas;

/**
 *
 * @author Diego
 */
public class CtrlLogin implements ActionListener{
    
    private Login Login;
    
    public CtrlLogin(Login Login) {
        this.Login = Login;
        this.Login.txtUser.addActionListener(this);
        this.Login.txtPass.addActionListener(this);
        this.Login.btnEntrar.addActionListener(this);
        this.Login.btnCerrar.addActionListener(this);
        }
    
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Login.btnEntrar){
            
            if(Login.txtUser.getText() .isEmpty() && Login.txtPass.getText().isEmpty()){
            
                JOptionPane.showMessageDialog(null, "HAY CAMPOS VACIOS ","ALERTA ",1);
        
                Login.txtUser.requestFocus();
                
        }else {
    
            try {
                Conexion con = new Conexion();
                ResultSet resul;
                
                String usuario= Login.txtUser.getText();
                String contraseña= Login.txtPass.getText();
                
                String Sql="select cNombreUsuario,cTipoUsuario from tusuario where cUsuario='"+usuario+"' and cPass='"+contraseña+"'";
                
                resul=con.GetConsultar(Sql);
                
                if(resul.next()==true){
                   
                    String nombre = "";
                    
                  if (resul.getString("cTipoUsuario").equals("A"))  {
                      
                      
                      nombre =resul.getString("cNombreUsuario");
                      
                      JOptionPane.showMessageDialog(null, "Bienveni@ "+nombre);
                     
                      con.Setcerrar();
                      Login.dispose();
                      JfrAdmin JfrAdmin = new JfrAdmin();
                      JfrAdmin.setVisible(true);
                      CtrlAdmin CtrlAdmin = new CtrlAdmin(JfrAdmin);
                      
                  }else if (resul.getString("cTipoUsuario").equals("B")) {
                      
                      nombre =resul.getString("cNombreUsuario");
                      
                       JOptionPane.showMessageDialog(null, "Bienveni@ "+nombre);
                       
                       con.Setcerrar();
                       Login.dispose();
                       JfrJefeBodega JfrJefeBodega = new JfrJefeBodega();
                       JfrJefeBodega.setVisible(true);
                       CtrlJefeBodega CtrlJefeBodega = new CtrlJefeBodega(JfrJefeBodega);
                      
                  }else if (resul.getString("cTipoUsuario").equals("C")){
                      
                      nombre =resul.getString("cNombreUsuario");
                      
                      JOptionPane.showMessageDialog(null, "Bienveni@ "+nombre);
                      
                      con.Setcerrar();
                      Login.dispose();
                      JfrJefeVentas JfrJefeVentas = new JfrJefeVentas();
                      JfrJefeVentas.setVisible(true);
                      CtrlJefeVentas CtrlJefeVentas = new CtrlJefeVentas(JfrJefeVentas);
                  }
                 
                }else {
                    
                  JOptionPane.showMessageDialog(null, "Datos erroneos ");  
                  this.Login.txtUser.setText("");
                  this.Login.txtPass.setText("");
                  this.Login.txtUser.requestFocus();
                    
                }
              
            } catch (Exception ex) {
                Logger.getLogger(CtrlLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }else if (e.getSource() == Login.btnCerrar){
        
        this.Login.dispose();
    }
    }
    
}


package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.JfrJefeBodega;
import vista.JfrProductoBodega;
import vista.JfrProveedorBodega;
import vista.Login;

/**
 *
 * @author Diego
 */
public class CtrlJefeBodega implements ActionListener{

    private JfrJefeBodega JfrJefeBodega;

    public CtrlJefeBodega(JfrJefeBodega JfrJefeBodega) {
        this.JfrJefeBodega = JfrJefeBodega;
        this.JfrJefeBodega.btnProducto.addActionListener(this);
        this.JfrJefeBodega.btnProveedor.addActionListener(this);
        this.JfrJefeBodega.btnSalir.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == JfrJefeBodega.btnSalir){
            
            JfrJefeBodega.dispose();
            Login Login = new Login();
            Login.setVisible(true);
            CtrlLogin CtrlLogin = new CtrlLogin(Login);
            
        }else if(e.getSource() == JfrJefeBodega.btnProducto){
            
            JfrJefeBodega.dispose();
            JfrProductoBodega JfrProductoBodega = new JfrProductoBodega();
            JfrProductoBodega.setVisible(true);
            CtrlProductoBodega CtrlProductoBodega = new CtrlProductoBodega(JfrProductoBodega);
            
        }else if(e.getSource() == JfrJefeBodega.btnProveedor){
            
            JfrJefeBodega.dispose();
            JfrProveedorBodega JfrProveedorBodega = new JfrProveedorBodega();
            JfrProveedorBodega.setVisible(true);
            CtrlProveedorBodega CtrlProveedorBodega = new CtrlProveedorBodega(JfrProveedorBodega);
            
        }

    }
    
}


package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.JfrClientesVentas;
import vista.JfrFacturaVentas;
import vista.JfrJefeVentas;
import vista.Login;

/**
 *
 * @author Diego
 */
public class CtrlJefeVentas implements ActionListener{

    private JfrJefeVentas JfrJefeVentas;

    public CtrlJefeVentas(JfrJefeVentas JfrJefeVentas) {
        this.JfrJefeVentas = JfrJefeVentas;
        this.JfrJefeVentas.btnCliente.addActionListener(this);
        this.JfrJefeVentas.btnFactura.addActionListener(this);
        this.JfrJefeVentas.btnSalir.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == JfrJefeVentas.btnSalir){
            
            JfrJefeVentas.dispose();
            Login Login = new Login();
            Login.setVisible(true);
            CtrlLogin CtrlLogin = new CtrlLogin(Login);
            
        }else if(e.getSource() == JfrJefeVentas.btnCliente){
            
            JfrJefeVentas.dispose();
            JfrClientesVentas JfrClientesVentas = new JfrClientesVentas();
            JfrClientesVentas.setVisible(true);
            CtrlClientesVentas CtrlClientesVentas = new CtrlClientesVentas(JfrClientesVentas);
            
        }else if(e.getSource() == JfrJefeVentas.btnFactura){
            
            JfrJefeVentas.dispose();
            JfrFacturaVentas JfrFacturaVentas = new JfrFacturaVentas();
            JfrFacturaVentas.setVisible(true);
            CtrlFacturaVentas CtrlFacturaVentas = new CtrlFacturaVentas(JfrFacturaVentas);
            
        }
        
    }
    
}

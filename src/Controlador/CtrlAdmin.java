package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.JfrAdmin;
import vista.JfrClientes;
import vista.JfrFactura;
import vista.JfrProducto;
import vista.JfrProveedor;
import vista.JfrUsuario;
import vista.Login;

/**
 *
 * @author Diego
 */
public class CtrlAdmin implements ActionListener{

    private JfrAdmin JfrAdmin;

    public CtrlAdmin(JfrAdmin JfrAdmin) {
        this.JfrAdmin = JfrAdmin;
        this.JfrAdmin.btnClientes.addActionListener(this);
        this.JfrAdmin.btnProductos.addActionListener(this);
        this.JfrAdmin.btnProveedor.addActionListener(this);
        this.JfrAdmin.btnSalir.addActionListener(this);
        this.JfrAdmin.btnUsuario.addActionListener(this);
        this.JfrAdmin.btnVentas.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == JfrAdmin.btnSalir){
            
            JfrAdmin.dispose();
            Login Login = new Login(); 
            Login.setVisible(true);
            CtrlLogin log= new CtrlLogin(Login);
            Login.txtUser.requestFocus();
            
        }else if (e.getSource() == JfrAdmin.btnClientes){
            
            JfrAdmin.dispose();
            JfrClientes JfrClientes = new JfrClientes();
            JfrClientes.setVisible(true);
            CtrlClientes CtrlClientes = new CtrlClientes(JfrClientes);
            
        }else if (e.getSource() == JfrAdmin.btnProductos){
            
            JfrAdmin.dispose();
            JfrProducto JfrProducto = new JfrProducto();
            JfrProducto.setVisible(true);
            CtrlProducto CtrlProducto = new CtrlProducto(JfrProducto);
            
        }else if(e.getSource() == JfrAdmin.btnProveedor){
            
            JfrAdmin.dispose();
            JfrProveedor JfrProveedor = new JfrProveedor();
            JfrProveedor.setVisible(true);
            CtrlProveedor CtrlProveedor = new CtrlProveedor(JfrProveedor);
            
        }else if(e.getSource() == JfrAdmin.btnUsuario){
            
            JfrAdmin.dispose();
            JfrUsuario JfrUsuario = new JfrUsuario();
            JfrUsuario.setVisible(true);
            CtrlUsuario CtrlUsuario = new CtrlUsuario(JfrUsuario);
            
        }else if(e.getSource() == JfrAdmin.btnVentas){
            
            JfrAdmin.dispose();
            JfrFactura JfrFactura = new JfrFactura();
            JfrFactura.setVisible(true);
            CtrlFactura CtrlFactura = new CtrlFactura(JfrFactura);
            
        }
        
    }
    
    
    
}

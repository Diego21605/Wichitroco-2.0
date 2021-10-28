
package Controlador;

import Modelo.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JfrAdmin;
import vista.JfrClientes;
import vista.JfrJefeVentas;

/**
 *
 * @author Diego
 */
public class CtrlClientes implements ActionListener{

    private JfrClientes JfrClientes;

    public CtrlClientes(JfrClientes JfrClientes) {
        this.JfrClientes = JfrClientes;
        this.JfrClientes.btnCancelar.addActionListener(this);
        this.JfrClientes.btnLimpiar.addActionListener(this);
        this.JfrClientes.btnRegistrar.addActionListener(this);
        this.JfrClientes.btnConsultar.addActionListener(this);
        this.JfrClientes.btnMostrar.addActionListener(this);
        this.JfrClientes.btnEditar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == JfrClientes.btnCancelar){
            
            JfrClientes.dispose();
            JfrAdmin JfrAdmin = new JfrAdmin();
            JfrAdmin.setVisible(true);
            CtrlAdmin CtrlAdmin = new CtrlAdmin(JfrAdmin);
            
        }else if(e.getSource() == JfrClientes.btnLimpiar){
            
            JfrClientes.txtNombre.setText("");
            JfrClientes.txtDirección.setText("");
            JfrClientes.txtTelefono.setText("");
            JfrClientes.txtRut.setText("");
            JfrClientes.txtRut.requestFocus();
            
        }else if (e.getSource() == JfrClientes.btnRegistrar){
            
            String nombre="";
            String direccion="";
            String telefono="";
            String rut ="";
            
            
            if(JfrClientes.txtRut.getText().length()==0
                ||JfrClientes.txtNombre.getText().length()==0
                ||JfrClientes.txtDirección.getText().length()==0
                ||JfrClientes.txtTelefono.getText().length()==0){
            
                JOptionPane.showMessageDialog(null, "HAY CAMPOS VACIOS ","ALERTA ",1);
        
               JfrClientes.txtRut.requestFocus();
                
        }else {{
                
                    try {
                        nombre=JfrClientes.txtNombre.getText();
                        direccion=JfrClientes.txtDirección.getText();
                        telefono=JfrClientes.txtTelefono.getText();
                        rut=JfrClientes.txtRut.getText();
                                
                        Conexion con;
                 
                        con = new Conexion();
                        String insert;
                        insert = "insert into tcliente (`nRutc`, `cNombre`, `cDireccion`, `nTelefono`) values ('"+rut+"','"+nombre+"','"+direccion+"','"+telefono+"')";
                 
                        con.dml(insert);
                 
                        JOptionPane.showMessageDialog(null, "Conexion exitosa");
                 
       
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
         
            
                JfrClientes.txtNombre.setText("");
                JfrClientes.txtDirección.setText("");
                JfrClientes.txtTelefono.setText("");
                JfrClientes.txtRut.setText("");
                JfrClientes.txtRut.requestFocus();
                
        }
            
        }
        
    }else if(e.getSource() == JfrClientes.btnConsultar){
        
        try {
                Conexion con = new Conexion ();
                ResultSet res;
                String id=JfrClientes.txtRut.getText();
                String Sql="select * from tcliente where nRutc = '"+id+"';";
                res=con.GetConsultar(Sql);
                if(res.next()==true){
                    
                    JfrClientes.txtNombre.setText(res.getString("cNombre"));
                    JfrClientes.txtDirección.setText(res.getString("cDireccion"));
                    JfrClientes.txtTelefono.setText(res.getString("nTelefono"));
                                     
                    con.Setcerrar();
                 }else{
                    JOptionPane.showMessageDialog(null, "Datos erroneos");
                 }
            
        } catch (Exception ex) {
                System.out.println(ex);
        }
        
    }else if(e.getSource() == JfrClientes.btnEditar){
        
        try {

                Conexion con = new Conexion();
                
                String identificacion = JfrClientes.txtRut.getText();
                String nombre = JfrClientes.txtNombre.getText();
                String direccion = JfrClientes.txtDirección.getText();
                String telefono = JfrClientes.txtTelefono.getText();
                
                String Sql="update tcliente set cNombre='"+nombre+"',cDireccion='"+direccion+"',nTelefono='"+telefono+"' where nRutc='"+identificacion+"';";
                con.dml(Sql);
                JOptionPane.showMessageDialog(null, "Actualizacion con exito " , "FELICIDADES " , JOptionPane.OK_OPTION);
                con.Setcerrar();

                JfrClientes.txtNombre.setText("");
                JfrClientes.txtDirección.setText("");
                JfrClientes.txtTelefono.setText("");
                JfrClientes.txtRut.setText("");
                JfrClientes.txtRut.requestFocus();
                
        } catch (Exception ex) {
                System.out.println(ex);
        }
        
    }else if(e.getSource() == JfrClientes.btnMostrar){
        
        try {
                Conexion con = new Conexion ();
                ResultSet res;
                String Sql = "SELECT * FROM tcliente";
                res=con.GetConsultar(Sql);
                
                if(res.next()){
                
                    DefaultTableModel model =new DefaultTableModel();
                
                        model.addColumn("Nombre");
                        model.addColumn("ID");
                        model.addColumn("Direccion");
                        model.addColumn("Telefono");
            
                        this.JfrClientes.jTableEmpleados.setModel(model);
                        
                        String [] info=new String[8];
                        
                        do{
                     
                            info[0]=res.getString("cNombre");
                            info[1]=res.getString("nRutc");
                            info[2]=res.getString("cDireccion");
                            info[3]=res.getString("nTelefono");
                            model.addRow(info);
                            
                    } while(res.next());
                 
                }else{
                
                    con.Setcerrar();
                    
                    JOptionPane.showMessageDialog(null, "Datos erroneos");
                    
                }
            
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error","ERROR",JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(null, ex);
            }
        
    }
    
    }
}

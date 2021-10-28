
package Controlador;

import Modelo.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JfrClientesVentas;
import vista.JfrJefeVentas;

/**
 *
 * @author Diego
 */
public class CtrlClientesVentas implements ActionListener{

    private JfrClientesVentas JfrClientesVentas;

    CtrlClientesVentas(JfrClientesVentas JfrClientesVentas) {

        this.JfrClientesVentas = JfrClientesVentas;
        this.JfrClientesVentas.btnCancelar.addActionListener(this);
        this.JfrClientesVentas.btnLimpiar.addActionListener(this);
        this.JfrClientesVentas.btnRegistrar.addActionListener(this);
        this.JfrClientesVentas.btnConsultar.addActionListener(this);
        this.JfrClientesVentas.btnMostrar.addActionListener(this);
        this.JfrClientesVentas.btnEditar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == JfrClientesVentas.btnCancelar){
            
            JfrClientesVentas.dispose();
            JfrJefeVentas JfrJefeVentas = new JfrJefeVentas();
            JfrJefeVentas.setVisible(true);
            CtrlJefeVentas CtrlJefeVentas = new CtrlJefeVentas(JfrJefeVentas);
            
        }else if(e.getSource() == JfrClientesVentas.btnLimpiar){
            
            JfrClientesVentas.txtNombre.setText("");
            JfrClientesVentas.txtDirección.setText("");
            JfrClientesVentas.txtTelefono.setText("");
            JfrClientesVentas.txtRut.setText("");
            JfrClientesVentas.txtRut.requestFocus();
            
        }else if (e.getSource() == JfrClientesVentas.btnRegistrar){
            
            String nombre="";
            String direccion="";
            String telefono="";
            String rut ="";
            
            
            if(JfrClientesVentas.txtRut.getText().length()==0
                ||JfrClientesVentas.txtNombre.getText().length()==0
                ||JfrClientesVentas.txtDirección.getText().length()==0
                ||JfrClientesVentas.txtTelefono.getText().length()==0){
            
                JOptionPane.showMessageDialog(null, "HAY CAMPOS VACIOS ","ALERTA ",1);
        
               JfrClientesVentas.txtRut.requestFocus();
                
        }else {{
                
                    try {
                        nombre=JfrClientesVentas.txtNombre.getText();
                        direccion=JfrClientesVentas.txtDirección.getText();
                        telefono=JfrClientesVentas.txtTelefono.getText();
                        rut=JfrClientesVentas.txtRut.getText();
                                
                        Conexion con;
                 
                        con = new Conexion();
                        String insert;
                        insert = "insert into tcliente (`nRutc`, `cNombre`, `cDireccion`, `nTelefono`) values ('"+rut+"','"+nombre+"','"+direccion+"','"+telefono+"')";
                 
                        con.dml(insert);
                 
                        JOptionPane.showMessageDialog(null, "Conexion exitosa");
                 
       
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
         
            
                JfrClientesVentas.txtNombre.setText("");
                JfrClientesVentas.txtDirección.setText("");
                JfrClientesVentas.txtTelefono.setText("");
                JfrClientesVentas.txtRut.setText("");
                JfrClientesVentas.txtRut.requestFocus();
                
        }
            
        }
        
    }else if(e.getSource() == JfrClientesVentas.btnConsultar){
        
        try {
                Conexion con = new Conexion ();
                ResultSet res;
                String id=JfrClientesVentas.txtRut.getText();
                String Sql="select * from tcliente where nRutc = '"+id+"';";
                res=con.GetConsultar(Sql);
                if(res.next()==true){
                    
                    JfrClientesVentas.txtNombre.setText(res.getString("cNombre"));
                    JfrClientesVentas.txtDirección.setText(res.getString("cDireccion"));
                    JfrClientesVentas.txtTelefono.setText(res.getString("nTelefono"));
                                     
                    con.Setcerrar();
                 }else{
                    JOptionPane.showMessageDialog(null, "Datos erroneos");
                 }
            
        } catch (Exception ex) {
                System.out.println(ex);
        }
        
    }else if(e.getSource() == JfrClientesVentas.btnEditar){
        
        try {

                Conexion con = new Conexion();
                
                String identificacion = JfrClientesVentas.txtRut.getText();
                String nombre = JfrClientesVentas.txtNombre.getText();
                String direccion = JfrClientesVentas.txtDirección.getText();
                String telefono = JfrClientesVentas.txtTelefono.getText();
                
                String Sql="update tcliente set cNombre='"+nombre+"',cDireccion='"+direccion+"',nTelefono='"+telefono+"' where nRutc='"+identificacion+"';";
                con.dml(Sql);
                JOptionPane.showMessageDialog(null, "Actualizacion con exito " , "FELICIDADES " , JOptionPane.OK_OPTION);
                con.Setcerrar();

                JfrClientesVentas.txtNombre.setText("");
                JfrClientesVentas.txtDirección.setText("");
                JfrClientesVentas.txtTelefono.setText("");
                JfrClientesVentas.txtRut.setText("");
                JfrClientesVentas.txtRut.requestFocus();
                
        } catch (Exception ex) {
                System.out.println(ex);
        }
        
    }else if(e.getSource() == JfrClientesVentas.btnMostrar){
        
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
            
                        this.JfrClientesVentas.jTableEmpleados.setModel(model);
                        
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


package Controlador;

import Modelo.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JfrAdmin;
import vista.JfrUsuario;

/**
 *
 * @author Diego
 */
public class CtrlUsuario implements ActionListener{

    private JfrUsuario JfrUsuario;

    public CtrlUsuario(JfrUsuario JfrUsuario) {
        this.JfrUsuario = JfrUsuario;
        this.JfrUsuario.btnRegresar.addActionListener(this);
        this.JfrUsuario.btnAyuda.addActionListener(this);
        this.JfrUsuario.btnLimpiar.addActionListener(this);
        this.JfrUsuario.btnRegistrar.addActionListener(this);
        this.JfrUsuario.btnConsultar.addActionListener(this);
        this.JfrUsuario.btnEditar.addActionListener(this);
        this.JfrUsuario.btnMostrar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == JfrUsuario.btnRegresar){
            
            JfrUsuario.dispose();
            JfrAdmin JfrAdmin = new JfrAdmin();
            JfrAdmin.setVisible(true);
            CtrlAdmin CtrlAdmin = new CtrlAdmin(JfrAdmin);
            
        }else if(e.getSource() == JfrUsuario.btnAyuda){
            
            JOptionPane.showMessageDialog(null, "Para llenar el campo 'Tipo de Usuario' debes colocar  \n"
                    + "A ---> Para los usuarios de tipo 'Administrador' \n"
                    + "B ---> Para los usuarios de tipo 'Jefe de Bodega' \n"
                    + "C ---> Para los usuarios de tipo 'Personal de Ventas' \n"
                    + "Se debe colocar en mayusculas.");
            
        }else if(e.getSource() == JfrUsuario.btnLimpiar){
            
            JfrUsuario.txtNombre.setText("");
            JfrUsuario.txtIdentificaion.setText("");
            JfrUsuario.txtCorreo.setText("");
            JfrUsuario.txtDireccion.setText("");
            JfrUsuario.txtUsuario.setText("");
            JfrUsuario.txtContraseña.setText("");
            JfrUsuario.txtTipoUsuario.setText("");
            JfrUsuario.txtNombre.requestFocus();
            
        }else if(e.getSource() == JfrUsuario.btnRegistrar){
            
            String nombre="";
            String id="";
            String correo="";
            String direccion="";
            String tipoUsuario="";
            String usuario="";
            String contraseña="";
            
            if(JfrUsuario.txtNombre.getText().length()==0
                ||JfrUsuario.txtIdentificaion.getText().length()==0
                ||JfrUsuario.txtDireccion.getText().length()==0
                ||JfrUsuario.txtCorreo.getText().length()==0
                ||JfrUsuario.txtUsuario.getText().length()==0
                ||JfrUsuario.txtContraseña.getText().length()==0
                ||JfrUsuario.txtTipoUsuario.getText().length()==0){
            
                JOptionPane.showMessageDialog(null, "HAY CAMPOS VACIOS ","ALERTA ",1);
        
               JfrUsuario.txtNombre.requestFocus();
                
        }else {
            int A = 1;
            int B = 2;
            int C = 2;
                if(JfrUsuario.txtTipoUsuario.getText().length()== A
                         ||JfrUsuario.txtTipoUsuario.getText().length() == B
                         ||JfrUsuario.txtTipoUsuario.getText().length() == C){
                
                    System.out.println("entra");
            
                    try {
                        nombre=JfrUsuario.txtNombre.getText();
                        id=JfrUsuario.txtIdentificaion.getText();
                        correo=JfrUsuario.txtCorreo.getText();
                        direccion=JfrUsuario.txtDireccion.getText();
                        usuario=JfrUsuario.txtUsuario.getText();
                        contraseña=JfrUsuario.txtContraseña.getText(); 
                        tipoUsuario=JfrUsuario.txtTipoUsuario.getText();
                              
                        Conexion con;
                 
                        con = new Conexion();
                        String insert;
                        insert = "insert into tusuario (`cNombreUsuario`, `nIdentificacion`, `cDireccion`, `cCorreo`, `cUsuario`, `cPass`, `cTipoUsuario`) values ('"+nombre+"','"+id+"','"+direccion+"','"+correo+"','"+usuario+"','"+contraseña+"','"+tipoUsuario+"')";
                 
                        con.dml(insert);
                 
                        JOptionPane.showMessageDialog(null, "Conexion exitosa");
                 
       
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
         
        }else {
                    JOptionPane.showMessageDialog(null, "Para llenar el campo 'Tipo de Usuario' debes colocar  \n"
                    + "A ---> Para los usuarios de tipo 'Administrador' \n"
                    + "B ---> Para los usuarios de tipo 'Jefe de Bodega' \n"
                    + "C ---> Para los usuarios de tipo 'Personal de Ventas' \n"
                    + "Se debe colocar en mayusculas.");
                }
            
                JfrUsuario.txtNombre.setText("");
                JfrUsuario.txtIdentificaion.setText("");
                JfrUsuario.txtCorreo.setText("");
                JfrUsuario.txtDireccion.setText("");
                JfrUsuario.txtUsuario.setText("");
                JfrUsuario.txtContraseña.setText("");
                JfrUsuario.txtTipoUsuario.setText("");
                JfrUsuario.txtNombre.requestFocus();
            
        }
        
    }else if(e.getSource() == JfrUsuario.btnConsultar){
        
        try {
                Conexion con = new Conexion ();
                ResultSet res;
                String id=JfrUsuario.txtIdentificaion.getText();
                String Sql="select * from tusuario where nIdentificacion = '"+id+"';";
                res=con.GetConsultar(Sql);
                if(res.next()==true){
                    
                    JfrUsuario.txtNombre.setText(res.getString("cNombreUsuario"));
                    JfrUsuario.txtDireccion.setText(res.getString("cDireccion"));
                    JfrUsuario.txtCorreo.setText(res.getString("cCorreo"));
                    JfrUsuario.txtUsuario.setText(res.getString("cUsuario"));
                    JfrUsuario.txtContraseña.setText(res.getString("cPass"));
                    JfrUsuario.txtTipoUsuario.setText(res.getString("cTipoUsuario"));
                                     
                    con.Setcerrar();
                 }else{
                    JOptionPane.showMessageDialog(null, "Datos erroneos");
                 }
            
        } catch (Exception ex) {
                System.out.println(ex);
        }
        
    }else if(e.getSource() == JfrUsuario.btnEditar){
        
        try {

                Conexion con = new Conexion();
                
                String identificacion = JfrUsuario.txtIdentificaion.getText();
                String nombre = JfrUsuario.txtNombre.getText();
                String direccion = JfrUsuario.txtDireccion.getText();
                String correo = JfrUsuario.txtCorreo.getText();
                String usuario = JfrUsuario.txtUsuario.getText();
                String contraseña = JfrUsuario.txtContraseña.getText();
                String tipo = JfrUsuario.txtTipoUsuario.getText();
                
                String Sql="update tusuario set cNombreUsuario='"+nombre+"',cDireccion='"+direccion+"',cCorreo='"+correo+"',cUsuario='"+usuario+"',cPass='"+contraseña+"',cTipoUsuario='"+tipo+"' where nIdentificacion='"+identificacion+"';";
                con.dml(Sql);
                JOptionPane.showMessageDialog(null, "Actualizacion con exito " , "FELICIDADES " , JOptionPane.OK_OPTION);
                con.Setcerrar();

                JfrUsuario.txtNombre.setText("");
                JfrUsuario.txtIdentificaion.setText("");
                JfrUsuario.txtCorreo.setText("");
                JfrUsuario.txtDireccion.setText("");
                JfrUsuario.txtUsuario.setText("");
                JfrUsuario.txtContraseña.setText("");
                JfrUsuario.txtTipoUsuario.setText("");
                JfrUsuario.txtNombre.requestFocus();
                
        } catch (Exception ex) {
                System.out.println(ex);
        }
        
    }else if(e.getSource() == JfrUsuario.btnMostrar){
        
        try {
                Conexion con = new Conexion ();
                ResultSet res;
                String Sql = "SELECT * FROM tusuario";
                res=con.GetConsultar(Sql);
                
                if(res.next()){
                
                    DefaultTableModel model =new DefaultTableModel();
                
                        model.addColumn("Nombre");
                        model.addColumn("ID");
                        model.addColumn("Direccion");
                        model.addColumn("Correo");
                        model.addColumn("Usuario");
                        model.addColumn("Tipo de Usuarios");
            
                        this.JfrUsuario.jTableEmpleados.setModel(model);
                        
                        String [] info=new String[8];
                        
                        do{
                     
                            info[0]=res.getString("cNombreUsuario");
                            info[1]=res.getString("nIdentificacion");
                            info[2]=res.getString("cDireccion");
                            info[3]=res.getString("cCorreo");
                            info[4]=res.getString("cUsuario");
                            info[5]=res.getString("cTipoUsuario");
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

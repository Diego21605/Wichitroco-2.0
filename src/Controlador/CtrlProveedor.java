
package Controlador;
import Modelo.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JfrAdmin;
import vista.JfrProveedor;
/**
 *
 * @author Javier
 */
public class CtrlProveedor implements ActionListener{
    private JfrProveedor JfrProveedor;

    public CtrlProveedor(JfrProveedor JfrProveedor) {
        this.JfrProveedor = JfrProveedor;
        this.JfrProveedor.btnRegresar.addActionListener(this);
        this.JfrProveedor.btnRegistrar.addActionListener(this);
        this.JfrProveedor.btnConsultar.addActionListener(this);
        this.JfrProveedor.btnEditar.addActionListener(this);
        this.JfrProveedor.txtPaginaWeb.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
     if(e.getSource() == JfrProveedor.btnRegresar){//-------REGRESAR-----------
            
         JfrProveedor.dispose();
         JfrAdmin JfrAdmin = new JfrAdmin();
         JfrAdmin.setVisible(true);
         CtrlAdmin CtrlAdmin = new CtrlAdmin(JfrAdmin);
            
        }else if (e.getSource() == JfrProveedor.btnRegistrar){
            
            String rut="";
            String nombreProveedor="";
            String direccion="";
            String telefono="";
            String paginaWeb="";
            
            if(JfrProveedor.txtRut.getText().length()==0
                ||JfrProveedor.txtNombreProveedor.getText().length()==0
                ||JfrProveedor.txtDireccion.getText().length()==0
                ||JfrProveedor.txtTelefono.getText().length()==0
                ||JfrProveedor.txtPaginaWeb.getText().length()==0)
            {
                JOptionPane.showMessageDialog(null, "HAY CAMPOS VACIOS ","ALERTA ",1);
                JfrProveedor.txtRut.requestFocus();
                
            }else {

                        try {
                            rut=JfrProveedor.txtRut.getText();
                            nombreProveedor=JfrProveedor.txtNombreProveedor.getText();
                            direccion=JfrProveedor.txtDireccion.getText();
                            telefono=JfrProveedor.txtTelefono.getText();
                            paginaWeb=JfrProveedor.txtPaginaWeb.getText();

                            Conexion con;

                            con = new Conexion();
                            String insert;
                            insert = "insert into tproveedores (`nRutp`, `cNombreProveedor`, `cDireccion`, `nTelefono`, `cPaginaWeb`) values ('"+rut+"','"+nombreProveedor+"','"+direccion+"','"+telefono+"','"+paginaWeb+"')";

                            con.dml(insert);

                            JOptionPane.showMessageDialog(null, "Conexion exitosa");

                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                        JfrProveedor.txtRut.setText("");
                        JfrProveedor.txtNombreProveedor.setText("");
                        JfrProveedor.txtDireccion.setText("");
                        JfrProveedor.txtTelefono.setText("");
                        JfrProveedor.txtPaginaWeb.setText("");
                        JfrProveedor.txtRut.requestFocus();
                }
      
            }else if(e.getSource() == JfrProveedor.btnConsultar){//----------CONSULTA----------
        
                try {
                Conexion con = new Conexion ();
                ResultSet res;
                String Sql = "SELECT * FROM tproveedores";
                res=con.GetConsultar(Sql);
                
                if(res.next()){
                
                    DefaultTableModel model =new DefaultTableModel();
                
                        model.addColumn("Rut");
                        model.addColumn("Nombre Proveedor");
                        model.addColumn("Direccion");
                        model.addColumn("Telefono");
                        model.addColumn("Pagina Web");
            
                        this.JfrProveedor.jTableProveedore.setModel(model);
                        
                        String [] info=new String[10];
                        
                        do{
                     
                            info[0]=res.getString("nRutp");
                            info[1]=res.getString("cNombreProveedor");
                            info[2]=res.getString("cDireccion");
                            info[3]=res.getString("nTelefono");
                            info[4]=res.getString("cPaginaWeb");
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
        
            }else if(e.getSource() == JfrProveedor.btnEditar){//----------EDITAR-----------
        
                try {

                    Conexion con = new Conexion();

                    String rut=JfrProveedor.txtRut.getText();
                    String nombreProveedor=JfrProveedor.txtNombreProveedor.getText();
                    String direccion=JfrProveedor.txtDireccion.getText();
                    String telefono=JfrProveedor.txtTelefono.getText();
                    String paginaWeb=JfrProveedor.txtPaginaWeb.getText();


                    String Sql="update tproveedores set cNombreProveedor='"+nombreProveedor+"',cDireccion='"+direccion+"',nTelefono='"+telefono+"',cPaginaWeb='"+paginaWeb+"' where nRutp='"+rut+"';";
                    con.dml(Sql);
                    JOptionPane.showMessageDialog(null, "Actualizacion con exito " , "FELICIDADES " , JOptionPane.OK_OPTION);
                    con.Setcerrar();

                    JfrProveedor.txtRut.setText("");
                    JfrProveedor.txtNombreProveedor.setText("");
                    JfrProveedor.txtDireccion.setText("");
                    JfrProveedor.txtTelefono.setText("");
                    JfrProveedor.txtPaginaWeb.setText("");
                    JfrProveedor.txtRut.requestFocus();

                    } catch (Exception ex) {
                    System.out.println(ex);
                    }
        
    }
        
    }

}


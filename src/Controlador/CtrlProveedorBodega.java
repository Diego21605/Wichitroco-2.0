
package Controlador;
import Modelo.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JfrAdmin;
import vista.JfrJefeBodega;
import vista.JfrProveedorBodega;
/**
 *
 * @author Javier
 */
public class CtrlProveedorBodega implements ActionListener{
    private JfrProveedorBodega JfrProveedorBodega;

    public CtrlProveedorBodega(JfrProveedorBodega JfrProveedorBodega) {
        this.JfrProveedorBodega = JfrProveedorBodega;
        this.JfrProveedorBodega.btnRegresar.addActionListener(this);
        this.JfrProveedorBodega.btnRegistrar.addActionListener(this);
        this.JfrProveedorBodega.btnConsultar.addActionListener(this);
        this.JfrProveedorBodega.btnEditar.addActionListener(this);
        this.JfrProveedorBodega.txtPaginaWeb.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
     if(e.getSource() == JfrProveedorBodega.btnRegresar){//-------REGRESAR-----------
            
         JfrProveedorBodega.dispose();
         JfrJefeBodega JfrJefeBodega = new JfrJefeBodega();
         JfrJefeBodega.setVisible(true);
         CtrlJefeBodega CtrlJefeBodega = new CtrlJefeBodega(JfrJefeBodega);
            
        }else if (e.getSource() == JfrProveedorBodega.btnRegistrar){
            
            String rut="";
            String nombreProveedor="";
            String direccion="";
            String telefono="";
            String paginaWeb="";
            
            if(JfrProveedorBodega.txtRut.getText().length()==0
                ||JfrProveedorBodega.txtNombreProveedor.getText().length()==0
                ||JfrProveedorBodega.txtDireccion.getText().length()==0
                ||JfrProveedorBodega.txtTelefono.getText().length()==0
                ||JfrProveedorBodega.txtPaginaWeb.getText().length()==0)
            {
                JOptionPane.showMessageDialog(null, "HAY CAMPOS VACIOS ","ALERTA ",1);
                JfrProveedorBodega.txtRut.requestFocus();
                
            }else {

                        try {
                            rut=JfrProveedorBodega.txtRut.getText();
                            nombreProveedor=JfrProveedorBodega.txtNombreProveedor.getText();
                            direccion=JfrProveedorBodega.txtDireccion.getText();
                            telefono=JfrProveedorBodega.txtTelefono.getText();
                            paginaWeb=JfrProveedorBodega.txtPaginaWeb.getText();

                            Conexion con;

                            con = new Conexion();
                            String insert;
                            insert = "insert into tproveedores (`nRutp`, `cNombreProveedor`, `cDireccion`, `nTelefono`, `cPaginaWeb`) values ('"+rut+"','"+nombreProveedor+"','"+direccion+"','"+telefono+"','"+paginaWeb+"')";

                            con.dml(insert);

                            JOptionPane.showMessageDialog(null, "Conexion exitosa");

                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                        JfrProveedorBodega.txtRut.setText("");
                        JfrProveedorBodega.txtNombreProveedor.setText("");
                        JfrProveedorBodega.txtDireccion.setText("");
                        JfrProveedorBodega.txtTelefono.setText("");
                        JfrProveedorBodega.txtPaginaWeb.setText("");
                        JfrProveedorBodega.txtRut.requestFocus();
                }
      
            }else if(e.getSource() == JfrProveedorBodega.btnConsultar){//----------CONSULTA----------
        
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
            
                        this.JfrProveedorBodega.jTableProveedore.setModel(model);
                        
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
        
            }else if(e.getSource() == JfrProveedorBodega.btnEditar){//----------EDITAR-----------
        
                try {

                    Conexion con = new Conexion();

                    String rut=JfrProveedorBodega.txtRut.getText();
                    String nombreProveedor=JfrProveedorBodega.txtNombreProveedor.getText();
                    String direccion=JfrProveedorBodega.txtDireccion.getText();
                    String telefono=JfrProveedorBodega.txtTelefono.getText();
                    String paginaWeb=JfrProveedorBodega.txtPaginaWeb.getText();


                    String Sql="update tproveedores set cNombreProveedor='"+nombreProveedor+"',cDireccion='"+direccion+"',nTelefono='"+telefono+"',cPaginaWeb='"+paginaWeb+"' where nRutp='"+rut+"';";
                    con.dml(Sql);
                    JOptionPane.showMessageDialog(null, "Actualizacion con exito " , "FELICIDADES " , JOptionPane.OK_OPTION);
                    con.Setcerrar();

                    JfrProveedorBodega.txtRut.setText("");
                    JfrProveedorBodega.txtNombreProveedor.setText("");
                    JfrProveedorBodega.txtDireccion.setText("");
                    JfrProveedorBodega.txtTelefono.setText("");
                    JfrProveedorBodega.txtPaginaWeb.setText("");
                    JfrProveedorBodega.txtRut.requestFocus();

                    } catch (Exception ex) {
                    System.out.println(ex);
                    }
        
    }
        
    }

}



package Controlador;

import Modelo.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JfrAdmin;
import vista.JfrProducto;
/**
 *
 * @author Javier
 */
public class CtrlProducto implements ActionListener{
    
     private JfrProducto JfrProducto;

    public CtrlProducto(JfrProducto JfrProducto) {
        this.JfrProducto = JfrProducto;
        this.JfrProducto.btnConsultar.addActionListener(this);
        this.JfrProducto.btnEditar.addActionListener(this);
        this.JfrProducto.btnRegistrar.addActionListener(this);
        this.JfrProducto.btnRegresar.addActionListener(this);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == JfrProducto.btnRegresar){//-------REGRESAR-----------
            
            JfrProducto.dispose();
            JfrAdmin JfrAdmin = new JfrAdmin();
            JfrAdmin.setVisible(true);
            CtrlAdmin CtrlAdmin = new CtrlAdmin(JfrAdmin);
            
        }else if (e.getSource() == JfrProducto.btnRegistrar){
            
            String nombreProducto="";
            String precioActual="";
            String nombreProveedor="";
            
            
            if(JfrProducto.txtNombreProducto.getText().length()==0
                ||JfrProducto.txtPrecioActual.getText().length()==0
                ||JfrProducto.txtNombreProveedor.getText().length()==0)
            {
                JOptionPane.showMessageDialog(null, "HAY CAMPOS VACIOS ","ALERTA ",1);
                JfrProducto.txtNombreProducto.requestFocus();
                
            }else {

                        try {
                            nombreProducto=JfrProducto.txtNombreProducto.getText();
                            precioActual=JfrProducto.txtPrecioActual.getText();
                            nombreProveedor=JfrProducto.txtNombreProveedor.getText();


                            Conexion con;

                            con = new Conexion();
                            String insert;
                            insert = "insert into tproducto (`cNombreProducto`, `nPrecioActual`, `cNombreProveedor`) values ('"+nombreProducto+"','"+precioActual+"','"+nombreProveedor+"')";

                            con.dml(insert);

                            JOptionPane.showMessageDialog(null, "Conexion exitosa");


                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                        JfrProducto.txtNombreProducto.setText("");
                        JfrProducto.txtPrecioActual.setText("");
                        JfrProducto.txtNombreProveedor.setText("");
                        JfrProducto.txtNombreProducto.requestFocus();
                }
      
    }else if(e.getSource() == JfrProducto.btnConsultar){//----------CONSULTA----------
        
        try {
                Conexion con = new Conexion ();
                ResultSet res;
                String Sql = "SELECT * FROM tproducto";
                res=con.GetConsultar(Sql);
                
                if(res.next()){
                
                    DefaultTableModel model =new DefaultTableModel();
                
                        model.addColumn("Producto");
                        model.addColumn("Percio");
                        model.addColumn("Proveedor");
            
                        this.JfrProducto.jTableProductos.setModel(model);
                        
                        String [] info=new String[6];
                        
                        do{
                     
                            info[0]=res.getString("cNombreProducto");
                            info[1]=res.getString("nPrecioActual");
                            info[2]=res.getString("cNombreProveedor");
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
        
    }else if(e.getSource() == JfrProducto.btnEditar){//----------EDITAR-----------
        
        try {

                Conexion con = new Conexion();
                
                String nombreProducto = JfrProducto.txtNombreProducto.getText();
                String precioActual = JfrProducto.txtPrecioActual.getText();
                String nombreProveedor = JfrProducto.txtNombreProveedor.getText();
                
                
                String Sql="update tproducto set cNombreProducto='"+nombreProducto+"',nPrecioActual='"+precioActual+"' where cNombreProveedor='"+nombreProveedor+"';";
                con.dml(Sql);
                JOptionPane.showMessageDialog(null, "Actualizacion con exito " , "FELICIDADES " , JOptionPane.OK_OPTION);
                con.Setcerrar();

                JfrProducto.txtNombreProducto.setText("");
                JfrProducto.txtPrecioActual.setText("");
                JfrProducto.txtNombreProveedor.setText("");
                JfrProducto.txtNombreProducto.requestFocus();
                
        } catch (Exception ex) {
                System.out.println(ex);
        }
        
    }
    
    }
    
}

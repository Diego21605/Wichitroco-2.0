
package Controlador;

import Modelo.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JfrJefeBodega;
import vista.JfrProductoBodega;
/**
 *
 * @author Javier
 */
public class CtrlProductoBodega implements ActionListener{
    
     private JfrProductoBodega JfrProductoBodega;

    public CtrlProductoBodega(JfrProductoBodega JfrProductoBodega) {
        this.JfrProductoBodega = JfrProductoBodega;
        this.JfrProductoBodega.btnRegresar.addActionListener(this);
        this.JfrProductoBodega.btnRegistrar.addActionListener(this);
        this.JfrProductoBodega.btnConsultar.addActionListener(this);
        this.JfrProductoBodega.btnEditar.addActionListener(this);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == JfrProductoBodega.btnRegresar){//-------REGRESAR-----------
            
            JfrProductoBodega.dispose();
            JfrJefeBodega JfrJefeBodega = new JfrJefeBodega();
            JfrJefeBodega.setVisible(true);
            CtrlJefeBodega CtrlJefeBodega = new CtrlJefeBodega(JfrJefeBodega);
            
        }else if (e.getSource() == JfrProductoBodega.btnRegistrar){
            
            String nombreProducto="";
            String precioActual="";
            String nombreProveedor="";
            
            
            if(JfrProductoBodega.txtNombreProducto.getText().length()==0
                ||JfrProductoBodega.txtPrecioActual.getText().length()==0
                ||JfrProductoBodega.txtNombreProveedor.getText().length()==0)
            {
                JOptionPane.showMessageDialog(null, "HAY CAMPOS VACIOS ","ALERTA ",1);
                JfrProductoBodega.txtNombreProducto.requestFocus();
                
            }else {

                        try {
                            nombreProducto=JfrProductoBodega.txtNombreProducto.getText();
                            precioActual=JfrProductoBodega.txtPrecioActual.getText();
                            nombreProveedor=JfrProductoBodega.txtNombreProveedor.getText();


                            Conexion con;

                            con = new Conexion();
                            String insert;
                            insert = "insert into tproducto (`cNombreProducto`, `nPrecioActual`, `cNombreProveedor`) values ('"+nombreProducto+"','"+precioActual+"','"+nombreProveedor+"')";

                            con.dml(insert);

                            JOptionPane.showMessageDialog(null, "Conexion exitosa");


                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                        JfrProductoBodega.txtNombreProducto.setText("");
                        JfrProductoBodega.txtPrecioActual.setText("");
                        JfrProductoBodega.txtNombreProveedor.setText("");
                        JfrProductoBodega.txtNombreProducto.requestFocus();
                }
      
    }else if(e.getSource() == JfrProductoBodega.btnConsultar){//----------CONSULTA----------
        
        try {
                Conexion con = new Conexion ();
                ResultSet res;
                String Sql = "SELECT * FROM tproducto";
                res=con.GetConsultar(Sql);
                
                if(res.next()){
                
                    DefaultTableModel model =new DefaultTableModel();
                
                        model.addColumn("Nombre Producto");
                        model.addColumn("Percio Actual");
                        model.addColumn("Nombre Proveedor");
            
                        this.JfrProductoBodega.jTableProductos.setModel(model);
                        
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
        
    }else if(e.getSource() == JfrProductoBodega.btnEditar){//----------EDITAR-----------
        
        try {

                Conexion con = new Conexion();
                
                String nombreProducto = JfrProductoBodega.txtNombreProducto.getText();
                String precioActual = JfrProductoBodega.txtPrecioActual.getText();
                String nombreProveedor = JfrProductoBodega.txtNombreProveedor.getText();
                
                
                String Sql="update tproducto set cNombreProducto='"+nombreProducto+"',nPrecioActual='"+precioActual+"' where cNombreProveedor='"+nombreProveedor+"';";
                con.dml(Sql);
                JOptionPane.showMessageDialog(null, "Actualizacion con exito " , "FELICIDADES " , JOptionPane.OK_OPTION);
                con.Setcerrar();

                JfrProductoBodega.txtNombreProducto.setText("");
                JfrProductoBodega.txtPrecioActual.setText("");
                JfrProductoBodega.txtNombreProveedor.setText("");
                JfrProductoBodega.txtNombreProducto.requestFocus();
                
        } catch (Exception ex) {
                System.out.println(ex);
        }
        
    }
    
    }
    
}

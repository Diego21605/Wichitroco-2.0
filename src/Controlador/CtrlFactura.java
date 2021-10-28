package Controlador;

import Modelo.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JfrAdmin;
import vista.JfrFactura;

/**
 *
 * @author Diego
 */
public class CtrlFactura implements ActionListener{

    private JfrFactura JfrFactura;

    public CtrlFactura(JfrFactura JfrFactura) {
        this.JfrFactura = JfrFactura;
        this.JfrFactura.btnAgregar.addActionListener(this);
        this.JfrFactura.btnCancelar.addActionListener(this);
        this.JfrFactura.btnConsultarCliente.addActionListener(this);
        this.JfrFactura.btnConsultarProducto.addActionListener(this);
        this.JfrFactura.btnImprimir.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == JfrFactura.btnCancelar){
            
            JfrFactura.dispose();
            JfrAdmin JfrAdmin = new JfrAdmin();
            JfrAdmin.setVisible(true);
            CtrlAdmin CtrlAdmin = new CtrlAdmin(JfrAdmin);
            
        }else if(e.getSource() == JfrFactura.btnConsultarCliente){
            
            try {
                Conexion con = new Conexion ();
                ResultSet res;
                String id=JfrFactura.txtIdCliente.getText();
                String Sql="select * from tcliente where nRutc = '"+id+"';";
                res=con.GetConsultar(Sql);
                if(res.next()==true){
                    
                    JfrFactura.txtNombreCliente.setText(res.getString("cNombre"));
                                     
                    con.Setcerrar();
                 }else{
                    JOptionPane.showMessageDialog(null, "Datos erroneos o Cliente no registrado");
                 }
            
            } catch (Exception ex) {
                    System.out.println(ex);
            }
            
        }else if(e.getSource() == JfrFactura.btnConsultarProducto){
            
            try {
                Conexion con = new Conexion ();
                ResultSet res;
                String id=JfrFactura.txtIdProducto.getText();
                String Sql="select * from tcliente where nRutc = '"+id+"';";
                res=con.GetConsultar(Sql);
                if(res.next()==true){
                    
                    JfrFactura.txtNombreProducto.setText(res.getString("cNombreProducto"));
                    JfrFactura.txtPrecio.setText(res.getString("nPrecioActual"));
                                     
                    con.Setcerrar();
                 }else{
                    JOptionPane.showMessageDialog(null, "Datos erroneos o Producto no registrado");
                 }
            
        } catch (Exception ex) {
                System.out.println(ex);
        }
            
        }else if(e.getSource() == JfrFactura.btnAgregar){
            
        //--------------DESCUENTO----------------------//
            if("".equals(JfrFactura.txtDescuento.getText())){
            
                JOptionPane.showMessageDialog(null, "ERROR, NO HA DIGITADO EL DESCUENTO ","ALERTA",1);   
        
            }else{
                double descuento_1 = 0, monto_desc = 0, mont_total = 0, monto_final = 0;
                mont_total=Double.parseDouble(JfrFactura.txtPrecio.getText());
                descuento_1=Double.parseDouble(JfrFactura.txtDescuento.getText());

                monto_desc=(mont_total*descuento_1/100);

                monto_final=mont_total-monto_desc;

            }
            
            //---------Mostrar en la Tabla-----------
            DefaultTableModel model =new DefaultTableModel();
                
            model.addColumn("Id Producto");
            model.addColumn("Nombre");
            model.addColumn("Cantidad");
            model.addColumn("Precio");
            model.addColumn("Descuento");
            model.addColumn("Total");
            
            String id_producto="", nombre="", cantidad="", precio="", descuento="", total="";
            
            id_producto = JfrFactura.txtIdProducto.getText();
            nombre = JfrFactura.txtNombreProducto.getText();
            cantidad = JfrFactura.txtCantidad.getText();
            precio = JfrFactura.txtPrecio.getText();
            descuento = JfrFactura.txtDescuento.getText();
            
            
        }else if(e.getSource() == JfrFactura.btnImprimir){
            
            try{
                PrinterJob gap = PrinterJob.getPrinterJob();
                gap.setPrintable((Printable) this.JfrFactura);
                boolean top = gap.printDialog();
                
                if(top){
                    gap.print();
                }
            
            }catch(PrinterException pex){
        
                JOptionPane.showMessageDialog(null, "Error de programa", "Error\n"+ pex, JOptionPane.INFORMATION_MESSAGE);
        
            }
            
        }
        
        
    }
   
    
    
}

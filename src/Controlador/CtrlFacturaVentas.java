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
import vista.JfrFacturaVentas;
import vista.JfrJefeVentas;

/**
 *
 * @author Diego
 */
public class CtrlFacturaVentas implements ActionListener{

    private JfrFacturaVentas JfrFacturaVentas;

    public CtrlFacturaVentas(JfrFacturaVentas JfrFacturaVentas) {
        this.JfrFacturaVentas = JfrFacturaVentas;
        this.JfrFacturaVentas.btnAgregar.addActionListener(this);
        this.JfrFacturaVentas.btnCancelar.addActionListener(this);
        this.JfrFacturaVentas.btnConsultarCliente.addActionListener(this);
        this.JfrFacturaVentas.btnConsultarProducto.addActionListener(this);
        this.JfrFacturaVentas.btnImprimir.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == JfrFacturaVentas.btnCancelar){
            
            JfrFacturaVentas.dispose();
            JfrJefeVentas JfrJefeVentas = new JfrJefeVentas();
            JfrJefeVentas.setVisible(true);
            CtrlJefeVentas CtrlJefeVentas = new CtrlJefeVentas(JfrJefeVentas);
            
        }else if(e.getSource() == JfrFacturaVentas.btnConsultarCliente){
            
            try {
                Conexion con = new Conexion ();
                ResultSet res;
                String id=JfrFacturaVentas.txtIdCliente.getText();
                String Sql="select * from tcliente where nRutc = '"+id+"';";
                res=con.GetConsultar(Sql);
                if(res.next()==true){
                    
                    JfrFacturaVentas.txtNombreCliente.setText(res.getString("cNombre"));
                                     
                    con.Setcerrar();
                 }else{
                    JOptionPane.showMessageDialog(null, "Datos erroneos o Cliente no registrado");
                 }
            
            } catch (Exception ex) {
                    System.out.println(ex);
            }
            
        }else if(e.getSource() == JfrFacturaVentas.btnConsultarProducto){
            
            try {
                Conexion con = new Conexion ();
                ResultSet res;
                String id=JfrFacturaVentas.txtIdProducto.getText();
                String Sql="select * from tcliente where nRutc = '"+id+"';";
                res=con.GetConsultar(Sql);
                if(res.next()==true){
                    
                    JfrFacturaVentas.txtNombreProducto.setText(res.getString("cNombreProducto"));
                    JfrFacturaVentas.txtPrecio.setText(res.getString("nPrecioActual"));
                                     
                    con.Setcerrar();
                 }else{
                    JOptionPane.showMessageDialog(null, "Datos erroneos o Producto no registrado");
                 }
            
        } catch (Exception ex) {
                System.out.println(ex);
        }
            
        }else if(e.getSource() == JfrFacturaVentas.btnAgregar){
            
        //--------------DESCUENTO----------------------//
            if("".equals(JfrFacturaVentas.txtDescuento.getText())){
            
                JOptionPane.showMessageDialog(null, "ERROR, NO HA DIGITADO EL DESCUENTO ","ALERTA",1);   
        
            }else{
                double descuento_1 = 0, monto_desc = 0, mont_total = 0, monto_final = 0;
                mont_total=Double.parseDouble(JfrFacturaVentas.txtPrecio.getText());
                descuento_1=Double.parseDouble(JfrFacturaVentas.txtDescuento.getText());

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
            
            id_producto = JfrFacturaVentas.txtIdProducto.getText();
            nombre = JfrFacturaVentas.txtNombreProducto.getText();
            cantidad = JfrFacturaVentas.txtCantidad.getText();
            precio = JfrFacturaVentas.txtPrecio.getText();
            descuento = JfrFacturaVentas.txtDescuento.getText();
            
            
        }else if(e.getSource() == JfrFacturaVentas.btnImprimir){
            
            try{
                PrinterJob gap = PrinterJob.getPrinterJob();
                gap.setPrintable((Printable) this.JfrFacturaVentas);
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

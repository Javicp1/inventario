import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Producto {
    String nombre;
    int cantidad;

    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public void agregarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }
}

public class Inventario extends JFrame implements ActionListener {
    private List<Producto> inventario = new ArrayList<>();
    private JTextArea textArea;

    public Inventario() {
        setTitle("Inventario de Papelería");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton addButton = new JButton("Agregar Producto");
        addButton.addActionListener(this);
        panel.add(addButton, BorderLayout.SOUTH);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Agregar Producto")) {
            String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del producto:");
            if (nombre != null && !nombre.isEmpty()) {
                String cantidadStr = JOptionPane.showInputDialog(this, "Ingrese la cantidad del producto:");
                if (cantidadStr != null && !cantidadStr.isEmpty()) {
                    int cantidad = Integer.parseInt(cantidadStr);
                    actualizarInventario(nombre, cantidad);
                    actualizarTextArea();
                }
            }
        }
    }

    private void actualizarInventario(String nombre, int cantidad) {
        boolean encontrado = false;
        for (Producto producto : inventario) {
            if (producto.nombre.equals(nombre)) {
                producto.agregarCantidad(cantidad);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            inventario.add(new Producto(nombre, cantidad));
        }
    }

    private void actualizarTextArea() {
        textArea.setText("");
        for (Producto producto : inventario) {
            textArea.append(producto.nombre + ": " + producto.cantidad + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inventario inventarioApp = new Inventario();
            inventarioApp.setVisible(true);
        });
    }
}

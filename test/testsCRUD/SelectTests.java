package testsCRUD;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;
import CRUD.Select;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *Clase para comprobar operaciones tipo Select
 * 
 * @author Antonio Company Rodriguez
 */

public class SelectTests {
    private Select select;
    private StringWriter stringWriter;
    private BufferedWriter bufferedWriter;
    private JTextArea textArea;
    private Socket socket;
    
    @Test
    public void testOperacionesConSelect() throws IOException, ClassNotFoundException {
        Select.operacionesConSelect("dni", "123", "operacion", "0", bufferedWriter, "usuario", socket, textArea);

        // Verifica que el resultado esperado se encuentra en el textArea
        assertTrue(textArea.getText().contains("Dni: 123"));
    }
    
    @Test
    public void testMostrarTablaEmpleados1Filtro() throws IOException, ClassNotFoundException {
        Select.mostrarTablaEmpleados1Filtro("0", "dni", "usuario", "123", "operacion", bufferedWriter, textArea, null, socket);

        // Verificamos que el resultado esperado se encuentra en el textArea
        assertTrue(textArea.getText().contains("Dni: 123"));
    }

}

package LoginLogoutTest;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

import vistas.*;
import logs.*;

/**
 *Clase para comprobar tests jUnit del método logout
 * 
 * @author Antonio Company Rodriguez
 */

public class LogoutTests {
    String user = "admin";
    String pass = "admin";
      
    @Test
    public void testLogoutConSocketConectado() {
        try {
            // Configuramos un socket "conectado"
            MainForm.setSocket(new Socket("localhost", 8888));

            FormVentanasUsuario formUsuario = new FormVentanasUsuario(user,pass);
            
            final boolean[] disposeMethodCalled = {false};
            formUsuario = new FormVentanasUsuario(user,pass) {
                @Override
                public void dispose() {
                    super.dispose();
                    disposeMethodCalled[0] = true;
                }
            };
            
            Logout.logout(formUsuario);
            
            // Aseguramos que los métodos correspondientes se llamen
            assertTrue(disposeMethodCalled[0]);
            
            assertNull(MainForm.getSocket());
        } catch (IOException ex) {
            Logger.getLogger(LogoutTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testLogoutSocketNulo() {
        
        // Configuramos un socket "desconectado"
        MainForm.setSocket(null);

        FormVentanasUsuario formUsuario = new FormVentanasUsuario(user,pass);

        final boolean[] disposeMethodCalled = {false};
        formUsuario = new FormVentanasUsuario(user,pass) {
            @Override
            public void dispose() {
                super.dispose();
                disposeMethodCalled[0] = true;
            }
        };
        Logout.logout(formUsuario);

        // Aseguramos que no haya interacciones con formUsuario
        assertFalse(disposeMethodCalled[0]);      
    }
}

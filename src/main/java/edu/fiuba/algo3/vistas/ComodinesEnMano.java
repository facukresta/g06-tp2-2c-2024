package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ComodinApretarEventHandler;
import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.comodin.Comodinera;
import edu.fiuba.algo3.modelo.comodin.Modificador;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class ComodinesEnMano implements Observador {
    private VBox contenedor;
    private Pane contenedorPrincipal;
    private CreadorDeBotones creadorDeBotones = new CreadorDeBotones();

    public ComodinesEnMano(VBox contenedor, Pane contenedorPrincipal) {
        this.contenedor = contenedor;
        this.contenedorPrincipal = contenedorPrincipal;
    }

    @Override
    public void actualizar(Object notificador) {
        Comodinera comodinera = (Comodinera) notificador;
        contenedor.getChildren().clear();
        CambiadorDeComodines cambiadorDeComodines = new CambiadorDeComodines(comodinera, contenedor, contenedorPrincipal);
        for (Modificador comodin : comodinera.obtenerComodines()) {
            Button comodinBoton = creadorDeBotones.crearBoton("comodines/"+comodin.obtenerNombre(), (null), contenedorPrincipal, 0.05, 0.13);
            comodinBoton.setOnAction(new ComodinApretarEventHandler(comodin, comodinBoton, cambiadorDeComodines));
            contenedor.getChildren().add(comodinBoton);
            VBox.setMargin(comodinBoton, new Insets(10,0,0,20));
        }
    }
}

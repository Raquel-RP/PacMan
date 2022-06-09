package modelo;

/**
 * 
 * @author Raquel Romero
 * @author Raquel Pulido
 */

public class TareaAnimarPersonajes implements Runnable {

    private Modelo modelo_;
    private int delay_;     //para controlar la rapidez de movimiento de la hebras
    private boolean continuar_; //true mientras sigue iterando
    private boolean suspendFlag_; //para detener la hebra momentaneamente

    public TareaAnimarPersonajes(Modelo modelo) {
        modelo_ = modelo;
        delay_ = 200; //valor deseado
        continuar_ = true;
        suspendFlag_ = false; //todo true 
    }

    public void run() {
        try {
            while (continuar_) {
                synchronized (this) {
                    while (suspendFlag_) {
                        wait();
                    }
                }
                modelo_.getComecocos().mover(modelo_);
                // Mover también los fantasmas
                Thread.sleep(delay_);
            } // end while
        } catch (InterruptedException e) { //excepción comprobada que pueden lanzarla sleep y wait
        }
    }
}

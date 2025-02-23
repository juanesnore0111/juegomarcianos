package juegomarcianos;

/**
 * Representa una nave en el juego de marcianos.
 * Esta clase maneja la posición y la salud de la nave.
 *
 * @author Tu Nombre
 * @version 1.0
 * @since 2025-02-23
 */
public class nave {
    private int posicion;
    private int salud;

    /**
     * Constructor para crear una nueva nave.
     *
     * @param posicionInicial La posición inicial de la nave en el eje X.
     */
    public nave(int posicionInicial) {
        this.posicion = posicionInicial;
        this.salud = 100;
    }

    /**
     * Mueve la nave a la izquierda.
     *
     * @param distancia La distancia que se moverá la nave.
     */
    public void moverIzquierda(int distancia) {
        this.posicion = Math.max(0, this.posicion - distancia);
    }

    /**
     * Mueve la nave a la derecha.
     *
     * @param distancia La distancia que se moverá la nave.
     * @param limiteTablero El límite derecho del tablero.
     */
    public void moverDerecha(int distancia, int limiteTablero) {
        this.posicion = Math.min(limiteTablero, this.posicion + distancia);
    }

    /**
     * Reduce la salud de la nave cuando es golpeada.
     *
     * @param daño La cantidad de daño recibido.
     * @return true si la nave sigue viva, false si ha sido destruida.
     */
    public boolean recibirDaño(int daño) {
        this.salud -= daño;
        return this.salud > 0;
    }

    /**
     * Obtiene la posición actual de la nave.
     *
     * @return La posición actual de la nave en el eje X.
     */
    public int getPosicion() {
        return this.posicion;
    }

    /**
     * Obtiene la salud actual de la nave.
     *
     * @return La salud actual de la nave.
     */
    public int getSalud() {
        return this.salud;
    }

    /**
     * @deprecated Este método será reemplazado en futuras versiones.
     * @see #recibirDaño(int)
     */
    @Deprecated
    public void destruir() {
        this.salud = 0;
    }
}
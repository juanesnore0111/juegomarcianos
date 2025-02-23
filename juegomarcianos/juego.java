package juegomarcianos;

import java.util.Scanner;
import java.util.Random;

/**
 * Clase principal que maneja la lógica del juego de marcianos.
 *
 * @author Tu Nombre
 * @version 1.0
 * @since 2025-02-23
 */
public class juego {
    private nave jugador;
    private int puntuacion;
    private static final int ANCHO_TABLERO = 10;

    /**
     * Constructor para iniciar un nuevo juego.
     */
    public juego() {
        this.jugador = new nave(ANCHO_TABLERO / 2);
        this.puntuacion = 0;
    }

    /**
     * Inicia el bucle principal del juego.
     */
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            dibujarTablero();
            System.out.println("Mueve la nave (A: izquierda, D: derecha, Q: salir):");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("Q")) {
                break;
            } else if (input.equals("A")) {
                jugador.moverIzquierda(1);
            } else if (input.equals("D")) {
                jugador.moverDerecha(1, ANCHO_TABLERO - 1);
            }

            if (random.nextDouble() < 0.3) {
                int posicionMarciano = random.nextInt(ANCHO_TABLERO);
                if (posicionMarciano == jugador.getPosicion()) {
                    if (!jugador.recibirDaño(20)) {
                        System.out.println("¡Tu nave ha sido destruida! Fin del juego.");
                        break;
                    }
                } else {
                    puntuacion += 10;
                    System.out.println("¡Has esquivado un marciano! +10 puntos");
                }
            }
        }

        System.out.println("Juego terminado. Puntuación final: " + puntuacion);
        scanner.close();
    }

    /**
     * Dibuja el estado actual del tablero de juego en la consola.
     */
    private void dibujarTablero() {
        System.out.println("Puntuación: " + puntuacion + " | Salud: " + jugador.getSalud());
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            if (i == jugador.getPosicion()) {
                System.out.print("N");
            } else {
                System.out.print(".");
            }
        }
        System.out.println();
    }
}


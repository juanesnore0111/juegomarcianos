package juegomarcianos;

import java.util.Scanner;
import java.util.Random;

/**
 * Clase principal que maneja la lógica del juego de marcianos.
 *
 * @author Juan Esteban Noreña
 * @version 1.4
 * @since 2025-02-23
 */
public class juego {
    private nave jugador;
    private int puntuacion;
    private int nivel;
    private static final int ANCHO_TABLERO = 10;
    private static final int PUNTOS_POR_NIVEL = 100;
    private boolean escudoActivo;
    private int turnosEscudo;

    /**
     * Constructor para iniciar un nuevo juego.
     */
    public juego() {
        this.jugador = new nave(ANCHO_TABLERO / 2);
        this.puntuacion = 0;
        this.nivel = 1;
        this.escudoActivo = false;
        this.turnosEscudo = 0;
    }

    /**
     * Inicia el bucle principal del juego.
     */
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("¡Bienvenido al juego de marcianos!");
        System.out.println("Presiona 'I' para ver las instrucciones o cualquier otra tecla para comenzar.");
        String input = scanner.nextLine().toUpperCase();

        if (input.equals("I")) {
            mostrarInstrucciones();
        }

        while (true) {
            dibujarTablero();
            System.out.println("Mueve la nave (A: izquierda, D: derecha, Q: salir):");
            input = scanner.nextLine().toUpperCase();

            if (input.equals("Q")) {
                break;
            } else if (input.equals("A")) {
                jugador.moverIzquierda(1);
            } else if (input.equals("D")) {
                jugador.moverDerecha(1, ANCHO_TABLERO - 1);
            }

            // Lógica de power-ups
            if (random.nextDouble() < 0.1) {
                activarPowerUp();
            }

            if (escudoActivo) {
                turnosEscudo--;
                if (turnosEscudo <= 0) {
                    escudoActivo = false;
                    System.out.println("El escudo se ha desactivado.");
                }
            }

            if (random.nextDouble() < 0.3 * nivel) {
                int posicionMarciano = random.nextInt(ANCHO_TABLERO);
                if (posicionMarciano == jugador.getPosicion()) {
                    if (!escudoActivo && !jugador.recibirDaño(20 * nivel)) {
                        System.out.println("¡Tu nave ha sido destruida! Fin del juego.");
                        break;
                    } else if (escudoActivo) {
                        System.out.println("¡El escudo te ha protegido del ataque!");
                    }
                } else {
                    puntuacion += 10;
                    System.out.println("¡Has esquivado un marciano! +10 puntos");
                    if (puntuacion >= PUNTOS_POR_NIVEL * nivel) {
                        subirNivel();
                    }
                }
            }
        }

        System.out.println("Juego terminado. Puntuación final: " + puntuacion + " | Nivel alcanzado: " + nivel);
        scanner.close();
    }

    /**
     * Activa un power-up aleatorio.
     */
    private void activarPowerUp() {
        Random random = new Random();
        int powerUp = random.nextInt(2);
        switch (powerUp) {
            case 0:
                escudoActivo = true;
                turnosEscudo = 3;
                System.out.println("¡Has obtenido un escudo! Duración: 3 turnos.");
                break;
            case 1:
                jugador.repararNave(50);
                System.out.println("¡Has obtenido una reparación! +50 de salud.");
                break;
        }
    }

    /**
     * Sube el nivel del juego, aumentando la dificultad.
     */
    private void subirNivel() {
        nivel++;
        System.out.println("¡Has subido al nivel " + nivel + "!");
        System.out.println("La dificultad ha aumentado.");
    }

    /**
     * Dibuja el estado actual del tablero de juego en la consola.
     */
    private void dibujarTablero() {
        System.out.println("Nivel: " + nivel + " | Puntuación: " + puntuacion + " | Salud: " + jugador.getSalud() +
                (escudoActivo ? " | Escudo activo: " + turnosEscudo + " turnos" : ""));
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            if (i == jugador.getPosicion()) {
                System.out.print(escudoActivo ? "S" : "N");
            } else {
                System.out.print(".");
            }
        }
        System.out.println();
    }

    /**
     * Muestra las instrucciones del juego.
     */
    private void mostrarInstrucciones() {
        System.out.println("Instrucciones:");
        System.out.println("  - Usa 'A' para mover la nave a la izquierda.");
        System.out.println("  - Usa 'D' para mover la nave a la derecha.");
        System.out.println("  - Presiona 'Q' para salir del juego.");
        System.out.println("  - Evita a los marcianos para ganar puntos.");
        System.out.println("  - Si un marciano te golpea, pierdes salud.");
        System.out.println("  - Cada 100 puntos, subirás de nivel y aumentará la dificultad.");
        System.out.println("  - Pueden aparecer power-ups que te den ventajas temporales.");
        System.out.println("  - El juego termina cuando tu nave es destruida o decides salir.");
    }
}
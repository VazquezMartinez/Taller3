
package org.example.taller3.controlador;

import org.example.taller3.modelo.Sudoku;
import org.example.taller3.vista.SudokuVista;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class SudokuControlador {

    private Sudoku modelo;
    private SudokuVista vista;
    private boolean cargando;
    private boolean gano;

    public SudokuControlador(Sudoku modelo, SudokuVista vista) {

        this.modelo = modelo;
        this.vista = vista;

        cargando = false;
        gano = false;

        prepararTablero();
        prepararEventos();
    }

    public void prepararTablero() {

        cargando = true;

        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {

                int numero = modelo.obtenerNumero(fila, columna);
                boolean fija = modelo.esFija(fila, columna);

                vista.actualizarCasilla(
                        fila, columna, numero, fija
                );
            }
        }

        cargando = false;
    }

    public void prepararEventos() {

        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {

                final int f = fila;
                final int c = columna;

                TextField casilla = vista.getCasilla(f, c);

                casilla.textProperty().addListener(
                        (observable, anterior, nuevo) -> {

                            if (cargando || gano) {
                                return;
                            }

                            if (nuevo.isEmpty()) {
                                modelo.ponerNumero(f, c, 0);
                                vista.limpiarColor(f, c);
                                return;
                            }

                            int numero = Integer.parseInt(nuevo);

                            boolean valido =
                                    modelo.validarNum(f, c, numero);

                            modelo.ponerNumero(f, c, numero);

                            vista.marcarColor(f, c, valido);

                            comprobarVictoria();
                        }
                );

                casilla.focusedProperty().addListener(
                        (observable, anterior, tieneFoco) -> {

                            if (!tieneFoco) {
                                vista.limpiarColor(f, c);
                            }
                        }
                );
            }
        }

        vista.getSelectorDificultades().setOnAction(event -> {

            String dificultad =
                    vista.getSelectorDificultades().getValue();

            int plantilla = 1;

            if (dificultad.equals("Normal")) {
                plantilla = 2;
            } else if (dificultad.equals("Dificil")) {
                plantilla = 3;
            }

            gano = false;

            modelo.cargarTablero(plantilla);

            prepararTablero();
        });
    }

    public void comprobarVictoria() {

        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {

                int numero =
                        modelo.obtenerNumero(fila, columna);

                if (numero == 0) {
                    return;
                }

                if (modelo.validarNum(
                        fila, columna, numero) == false) {
                    return;
                }
            }
        }

        gano = true;

        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setTitle("Sudoku completado");
        mensaje.setHeaderText("GGEZ!!!");
        mensaje.setContentText(
                "Completaste de forma correcta el sudoku"
        );

        mensaje.showAndWait();
    }
}

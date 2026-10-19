package org.example.taller3.vista;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SudokuVista {

    private TextField[][] casillas;
    private String[][] estilos;
    private ComboBox<String> selectorDificultades;
    private BorderPane raiz;

    public SudokuVista() {

        casillas = new TextField[9][9];
        estilos = new String[9][9];

        raiz = new BorderPane();
        raiz.setStyle("-fx-background-color: white;");

        Label titulo = new Label("SUDOKU");
        titulo.setFont(new Font(25));

        selectorDificultades = new ComboBox<>();
        selectorDificultades.getItems().addAll("Facil", "Normal", "Dificil");
        selectorDificultades.setValue("Facil");

        Label texto = new Label("Difucultad:");

        HBox superior = new HBox(15);
        superior.setAlignment(Pos.CENTER);
        superior.getChildren().addAll(titulo, texto, selectorDificultades);

        GridPane tablero = new GridPane();
        tablero.setAlignment(Pos.CENTER);

        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {

                TextField casilla = new TextField();

                casilla.setPrefSize(48, 48);
                casilla.setAlignment(Pos.CENTER);
                casilla.setFont(new Font(20));

                casilla.setTextFormatter(new javafx.scene.control.TextFormatter<String>(
                        cambio -> {
                            String textoNuevo = cambio.getControlNewText();

                            if (textoNuevo.isEmpty()
                                    || textoNuevo.matches("[1-9]")) {
                                return cambio;
                            }

                            return null;
                        }
                ));

                String arriba = fila % 3 == 0 ? "3" : "1";
                String izquierda = columna % 3 == 0 ? "3" : "1";
                String abajo = (fila + 1) % 3 == 0 ? "3" : "1";
                String derecha = (columna + 1) % 3 == 0 ? "3" : "1";

                estilos[fila][columna] =
                        "-fx-border-color: black;" +
                                "-fx-border-width: " + arriba + " " + derecha
                                + " " + abajo + " " + izquierda + ";" +
                                "-fx-background-color: white;" +
                                "-fx-text-fill: black;";

                casilla.setStyle(estilos[fila][columna]);

                casillas[fila][columna] = casilla;
                tablero.add(casilla, columna, fila);
            }
        }

        VBox contenido = new VBox(20);
        contenido.setAlignment(Pos.CENTER);
        contenido.getChildren().addAll(superior, tablero);

        raiz.setCenter(contenido);
    }

    public Parent getRaiz() {
        return raiz;
    }

    public TextField getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    public ComboBox<String> getSelectorDificultades() {
        return selectorDificultades;
    }

    public void actualizarCasilla(int fila, int columna,
                                  int numero, boolean fija) {

        TextField casilla = casillas[fila][columna];

        if (numero == 0) {
            casilla.setText("");
        } else {
            casilla.setText(String.valueOf(numero));
        }

        casilla.setEditable(!fija);

        if (fija) {
            estilos[fila][columna] =
                    estilos[fila][columna].replace(
                            "-fx-background-color: white;",
                            "-fx-background-color: #E0E0E0;"
                    );
        } else {
            estilos[fila][columna] =
                    estilos[fila][columna].replace(
                            "-fx-background-color: #E0E0E0;",
                            "-fx-background-color: white;"
                    );
        }

        casilla.setStyle(estilos[fila][columna]);
    }

    public void marcarColor(int fila, int columna, boolean valido) {

        String color;

        if (valido) {
            color = "green";
        } else {
            color = "red";
        }

        casillas[fila][columna].setStyle(
                estilos[fila][columna] + "-fx-text-fill: " + color + ";"
        );
    }

    public void limpiarColor(int fila, int columna) {
        casillas[fila][columna].setStyle(estilos[fila][columna]);
    }
}

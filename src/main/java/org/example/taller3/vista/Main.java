package org.example.taller3.vista;

import org.example.taller3.controlador.SudokuControlador;
import org.example.taller3.modelo.Sudoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage ventana) {

        Sudoku modelo = new Sudoku();

        SudokuVista vista = new SudokuVista();

        SudokuControlador controlador =
                new SudokuControlador(modelo, vista);

        Scene escena = new Scene(vista.getRaiz());

        ventana.setTitle("Mi Sudoku");
        ventana.setScene(escena);
        ventana.setWidth(600);
        ventana.setHeight(650);
        ventana.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

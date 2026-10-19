package org.example.taller3.modelo;

public class Sudoku {

    private int[][] tablero;
    private boolean[][] casillasFijas;

    public Sudoku() {
        tablero = new int[9][9];
        casillasFijas = new boolean[9][9];

        cargarTablero(1);
    }

    public void cargarTablero(int plantilla) {

        String[][] plantillas = {
                {
                        "000000060",
                        "800001403",
                        "240806090",
                        "010702039",
                        "097500004",
                        "000000081",
                        "000207900",
                        "001659800",
                        "960000307"
                },
                {
                        "000000700",
                        "704691058",
                        "050008260",
                        "000802000",
                        "080060030",
                        "430000026",
                        "008006004",
                        "007040583",
                        "300000090"
                },
                {
                        "000802003",
                        "400000000",
                        "059007600",
                        "020000069",
                        "940000300",
                        "080709205",
                        "004001098",
                        "500004000",
                        "008205470"
                }
        };

        if (plantilla < 1 || plantilla > 3) {
            plantilla = 1;
        }

        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {

                int numero = plantillas[plantilla - 1][fila]
                        .charAt(columna) - '0';

                tablero[fila][columna] = numero;

                if (numero != 0) {
                    casillasFijas[fila][columna] = true;
                } else {
                    casillasFijas[fila][columna] = false;
                }
            }
        }
    }

    public boolean validarNum(int fila, int columna, int numero) {

        if (numero < 1 || numero > 9) {
            return false;
        }

        if (validarFila(fila, columna, numero) == false) {
            return false;
        }

        if (validarColumna(fila, columna, numero) == false) {
            return false;
        }

        if (validarCuadricula(fila, columna, numero) == false) {
            return false;
        }

        return true;
    }

    public boolean validarFila(int fila, int columna, int numero) {

        for (int c = 0; c < 9; c++) {
            if (c != columna && tablero[fila][c] == numero) {
                return false;
            }
        }

        return true;
    }

    public boolean validarColumna(int fila, int columna, int numero) {

        for (int f = 0; f < 9; f++) {
            if (f != fila && tablero[f][columna] == numero) {
                return false;
            }
        }

        return true;
    }

    public boolean validarCuadricula(int fila, int columna, int numero) {

        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 3) * 3;

        for (int f = inicioFila; f < inicioFila + 3; f++) {
            for (int c = inicioColumna; c < inicioColumna + 3; c++) {

                if ((f != fila || c != columna)
                        && tablero[f][c] == numero) {
                    return false;
                }
            }
        }

        return true;
    }

    public void ponerNumero(int fila, int columna, int numero) {
        if (casillasFijas[fila][columna] == false) {
            tablero[fila][columna] = numero;
        }
    }

    public int obtenerNumero(int fila, int columna) {
        return tablero[fila][columna];
    }

    public boolean esFija(int fila, int columna) {
        return casillasFijas[fila][columna];
    }
}


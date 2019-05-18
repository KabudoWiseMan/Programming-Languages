//
//  main.cpp
//  main
//
//  Created by Vsevolod Molchanov on 25.04.17.
//  Copyright © 2017 Vsevolod Molchanov. All rights reserved.
//

#include <iostream>
#include "matrix.hpp"
#include "rational.hpp"

using namespace std;

int main(int argc, const char * argv[]) {
    
    int m, n;
    cin >> m;
    cin >> n;
    
    Matrix *matrix = new Matrix(m, n); // создание матрицы в динамической памяти
    for(int i = 0; i <= m - 1; i++) {
        for(int j = 0; j <= n - 1; j++) {
            Rational elem;
            cin >> elem;
            (*matrix)[i][j] = elem;
        }
    }
    
    Rational a(1, 2); // умножение строки на число типа Rational
    (*matrix)[0] * a; //
    
    (*matrix)[1] + (*matrix)[0]; // прибавление одной строки к другой
    
    Matrix *matrix2 = matrix;
    
    cout << endl;
    cout << "lines: " << matrix2->getLines() << endl; // получение количества строк
    cout << "columns: " << matrix2->getColumns() << endl; // получение количества столбцов
    cout << (*matrix2) << endl; // вывод матрицы
    
    delete matrix; // удаление матрицы
    
    return 0;
}

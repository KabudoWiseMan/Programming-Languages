//
//  matrix.hpp
//  main
//
//  Created by Vsevolod Molchanov on 25.04.17.
//  Copyright © 2017 Vsevolod Molchanov. All rights reserved.
//

#ifndef matrix_hpp
#define matrix_hpp

#include <stdio.h>
#include "rational.hpp"

using namespace std;

class Matrix {
    class Row {
    private:
        Matrix *matrix;
        int i;
    public:
        Row(Matrix*, int);
        Rational &operator[] (int);
        void operator* (const Rational&) const; // умножение строки на число типа Rational
        void operator+ (const Row&) const; // прибавление одной строки к другой
    };
    
private:
    int n, m;
    Rational **matrix;
    friend ostream &operator<< (ostream&, const Matrix&); // перегруженный оператор вывода
    
public:
    Matrix(int, int); // конструктор
    Matrix(const Matrix &matrixToCopy); // конструктор копий
    virtual ~Matrix(); // десструктор класса Matrix
    
    int getLines() const; // возвратить количество строк
    int getColumns() const; // возвратить количество столбцов
    
    Row operator[] (int);
    Matrix &operator= (const Matrix&); // оператор присваивания
};


#endif /* matrix_hpp */

//
//  matrix.cpp
//  main
//
//  Created by Vsevolod Molchanov on 25.04.17.
//  Copyright © 2017 Vsevolod Molchanov. All rights reserved.
//

#include <iostream>
#include "matrix.hpp"
#include "rational.hpp"

using namespace std;

Matrix::Matrix(int m, int n) { // конструктор
    matrix = new Rational* [m];
    for(int i = 0; i <= m - 1; i++) {
        matrix[i] = new Rational[n];
    }
    
    for(int i = 0; i <= m - 1; i++) {
        for(int j = 0; j <= n - 1; j++) {
            matrix[i][j] = 0;
        }
    }
    this->m = m;
    this->n = n;
}

Matrix::Matrix(const Matrix &matrixToCopy): m(matrixToCopy.m), n(matrixToCopy.n) { // конструктор копий
    matrix = new Rational* [m];
    for(int i = 0; i <= m - 1; i++) {
        matrix[i] = new Rational[n];
    }
    
    for(int i = 0; i <= m - 1; i++) {
        for(int j = 0; j <= n - 1; j++) {
            matrix[i][j] = matrixToCopy.matrix[i][j];
        }
    }
    this->m = matrixToCopy.m;
    this->n = matrixToCopy.n;
}

Matrix::~Matrix() { // десструктор класса Matrix
    for(int i = 0; i <= m - 1; i++) {
        delete [] matrix[i];
    }
    delete [] matrix;
}

Matrix::Row::Row(Matrix *matrix, int i) {
    this->matrix = matrix;
    this->i = i;
}

Matrix::Row Matrix::operator[](int i) {
    return Row(this, i);
}

Rational& Matrix::Row::operator[](int j) {
    return matrix->matrix[i][j];
}

void Matrix::Row::operator* (const Rational &rat) const { // умножение строки на число типа Rational
    for(int j = i; j <= matrix->n - 1; j++) {
        Rational elem = matrix->matrix[i][j] * rat;
        matrix->matrix[i][j] = elem;
    }
}

void Matrix::Row::operator+ (const Row &r) const { // прибавление одной строки к другой
    if(matrix == r.matrix) {
        for(int j = 0; j <= matrix->n - 1; j++) {
            Rational elem = matrix->matrix[r.i][j] + matrix->matrix[i][j];
            matrix->matrix[i][j] = elem;
        }
    } else {
        exit(1);
    }
}

int Matrix::getLines() const { // возвратить количество строк
    return m;
}

int Matrix::getColumns() const { // возвратить количество столбцов
    return n;
}

Matrix& Matrix::operator= (const Matrix &obj) { //оператор присваивания
    if (this != &obj) {
        if(this->m != obj.m || this->m != obj.m) {
            Rational **almostMatrix = new Rational* [obj.m];
            for(int i = 0; i <= obj.m - 1; i++) {
                almostMatrix[i] = new Rational[obj.n];
            }
            
            copy(&this->matrix, &this->matrix + m * n, &almostMatrix);
            
            for(int i = 0; i <= m - 1; i++) {
                delete [] this->matrix[i];
            }
            delete [] this->matrix;
            
            this->matrix = almostMatrix;
            
            this->m = obj.m;
            this->n = obj.n;
        }
        
        for(int i = 0; i <= m - 1; i++) {
            for(int j = 0; j <= n - 1; j++) {
                matrix[i][j] = obj.matrix[i][j];
            }
        }
    }
    return *this;
}

ostream &operator<< (ostream &os, const Matrix &obj) { // вывод матрицы
    for (int i = 0; i <= obj.m - 1; i++) {
        for (int j = 0; j <= obj.n - 1; j++) {
            os << obj.matrix[i][j] << " ";
        }
        os << endl;
    }
    
    return os;
}

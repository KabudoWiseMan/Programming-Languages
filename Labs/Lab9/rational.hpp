//
//  rational.hpp
//  main
//
//  Created by Vsevolod Molchanov on 25.04.17.
//  Copyright © 2017 Vsevolod Molchanov. All rights reserved.
//

#ifndef rational_hpp
#define rational_hpp

#include <stdio.h>
#include <iostream>

using namespace std;

class Rational {
private:
    int num; // числитель
    int denom; // знаменатель
    int nod(int, int);  // наибольший общий делитель
    friend istream& operator>> (istream&, Rational&); // ввод
    friend ostream& operator<< (ostream&, const Rational&); //вывод
public:
    Rational(int = 0, int = 1); // конструктор
    void norm(); // функция для нормализации числа
    Rational& operator= (const Rational&); // присваивание рациональных (дробных) чисел
    Rational operator* (const Rational&) const; // умножение рациональных (дробных) чисел
    Rational operator+ (const Rational&) const; // прибавление  рациональных (дробных) чисел
    Rational operator- (const Rational&) const; // вычитание рациональных (дробных) чисел
    Rational operator/ (const Rational&) const; // деление рациональных (дробных) чисел
};

#endif /* rational_hpp */

//
//  textstats.cpp
//  textstats
//
//  Created by Vsevolod Molchanov on 08.06.17.
//  Copyright © 2017 Vsevolod Molchanov. All rights reserved.
//

#include "textstats.hpp"
#include <string>
#include <unordered_set>
#include <vector>
#include <map>
#include <set>

void get_tokens(
                // [ВХОД] Текстовая строка.
                const string &s,
                // [ВХОД] Множество символов-разделителей.
                const unordered_set<char> &delimiters,
                // [ВЫХОД] Последовательность слов.
                vector<string> &tokens
                ) {
    string xs = "";
    for(int i = 0; i <= s.size() - 1; i++) {
        if(delimiters.find(s[i]) == delimiters.end()) {
            xs += tolower(s[i]);
        } else {
            if(xs.size() != 0) {
                tokens.push_back(xs);
            }
            xs = "";
        }
    }
    if(xs.size() != 0) {
        tokens.push_back(xs);
    }
}

void get_type_freq(
                   // [ВХОД] Последовательность слов.
                   const vector<string> &tokens,
                   // [ВЫХОД] Частотный словарь
                   // (ключи -- слова, значения -- количества вхождений).
                   map<string, int> &freqdi
                   ) {
    for(auto t : tokens) {
        freqdi[t]++;
    }
}

void get_types(
               // [ВХОД] Последовательность слов.
               const vector<string> &tokens,
               // [ВЫХОД] Список уникальных слов.
               vector<string> &wtypes
               ) {
    set<string> dict(tokens.begin(), tokens.end());
    for(auto d : dict) {
        wtypes.push_back(d);
    }
}

void get_x_length_words(
                        // [ВХОД] Список уникальных слов.
                        const vector<string> &wtypes,
                        // [ВХОД] Минимальная длина слова.
                        int x,
                        // [ВЫХОД] Список слов, длина которых не меньше x.
                        vector<string> &words
                        ) {
    for (auto w : wtypes) {
        if(w.size() >= x) {
            words.push_back(w);
        }
    }
}

void get_x_freq_words(
                      // [ВХОД] Частотный словарь
                      const map<string, int> &freqdi,
                      // [ВХОД] Минимальное количество вхождений.
                      int x,
                      // [ВЫХОД] Список слов, встречающихся не меньше x раз.
                      vector<string> &words
                      ) {
    for(auto f : freqdi) {
        if(f.second >= x) {
            words.push_back(f.first);
        }
    }
}

void get_words_by_length_dict(
                              // [ВХОД] Список уникальных слов.
                              const vector<string> &wtypes,
                              // [ВЫХОД] Словарь распределения слов по длинам.
                              map<int, vector<string> > &lengthdi
                              ) {
    for(auto w : wtypes) {
        lengthdi[w.size()].push_back(w);
    }
}

# CalculadoraSpark:
Projeto para disciplina de Sistemas Distribuidos
Facape
8º Período
By: Luanderson Santos

# Etapas:

O projeto é composto por etapas onde existe interação entre o cliente e dois servidores:
Main, ServidorA e ServidorB.

# Cliente:
O Cliente faz a checagem para saber com qual Servidor precisará se comunicar e o seu endereço definido
Ele envia uma String, com as informações da  operação deseja para sua calculadora.
As operações disponíveis são atribuidas por: operando1 / operador / operando2
Ao qual você pode substituir pelo valor e operação desejada, exemplo:
Adição: \a\+\b\
Subtração: \a\-\b\
Multiplicação: \a\*\b\
Divisão: \a\'/'\b\
Potenciação: \a\^\b\
Porcentagem: \a\"p"\b\
o operador de porcentagem será o caractere "p" pois não funcionou com o caractere "%"
Raiz quadrada: \a\ "r" \b\
Raiz quadrada foi utilizado o caractere "r"

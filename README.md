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

# Servidor A
O Servidor conecta pela port 8082 com o cliente, e recebe informações via socket.
Realiza as operações básicas e retorna o resultado em String para o cliente.

# Servidor B:
O Servidor conecta pela port 8083 com o cliente, e recebe informações via socket.
Realiza as operações de porcentagem, raiz quadrada e potenciação, retornando o resultado em String para o cliente.


# DIFICULDADES ENCONTRADAS:
Ao longo do projeto obtive dificuldades relacionadas a extração de váriavéis, como à um certo tempo já não programo em java. Não notei algumas otimizações necessárias para executar e JavaSpark, como por exemplo a extração de váriaveis para conversão e armazenamento. Devem ser declaradas antes para melhor conversão, após a melhoria do código tive problemas com caracteres como o de porcentagem e raiz, então usei caracteres simples como "p" e "r". Após tudo isso, foi executado normalmente!!

# INFORMAÇÕES ADQUIRIDAS:
https://www.devmedia.com.br/introducao-ao-apache-spark/34178

Com auxilio de:

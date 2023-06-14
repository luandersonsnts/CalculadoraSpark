/*public class ServerA {
    public double executarOperacao(double op1, char operador, double op2) {
        double resultado = 0.0;
        switch (operador) {
            case '+':
                resultado = op1 + op2;
                break;
            case '-':
                resultado = op1 - op2;
                break;
            case '*':
                resultado = op1 * op2;
                break;
            case '/':
                resultado = op1 / op2;
                break;
            default:
                System.out.println("OPERADOR INVALIDO");
        }
        return resultado;
    }
}*/


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerA {
    public static void main(String[] args) {
        int port = 8082; // PORTA ESCOLHIDA

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor A iniciado na porta " + port);

            while (true) {
                // AGUARDA UMA CONEXÃO DO CLIENTE
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // CRIA OS STREAMS DE ENTRADA E SAIDA DO CLIENTE
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                try {
                    // RECEBENDO A OPERAÇÃO PARA CALCULO
                    double op1 = in.readDouble();
                    char operador = in.readChar();
                    double op2 = in.readDouble();

                    // REALIZANDO ELA
                    double resultado = executarOperacao(op1, operador, op2);

                    // ENVIA O RESULTADO DE VOLTA PARA O CLIENTE
                    out.writeDouble(resultado);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // FECHA AS CONEXÕES COM O CLIENTE
                    in.close();
                    out.close();
                    clientSocket.close();
                    System.out.println("Cliente desconectado: " + clientSocket.getInetAddress());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double executarOperacao(double op1, char operador, double op2) {
        // REALIZA A OPERAÇÃO E RETORNA O RESULTADO
        double resultado = 0.0;
        switch (operador) {
            case '+':
                resultado = op1 + op2;
                break;
            case '-':
                resultado = op1 - op2;
                break;
            case '*':
                resultado = op1 * op2;
                break;
            case '/':
                resultado = op1 / op2;
                break;
            default:
                System.out.println("OPERADOR INVALIDO - SABE USAR CALC?");
        }
        return resultado;
    }
}

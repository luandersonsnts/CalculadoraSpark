import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerB {
    public static void main(String[] args) {
        int port = 8083; // PORTA ESCOLHIDA

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor B iniciado na porta " + port);

            while (true) {
                // AGUARDA UMA CONEXÃO DO CLIENTE
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // CRIA OS STREAMS DE ENTRADA E SAÍDA DO CLIENTE
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                try {
                    // RECEBENDO A OPERAÇÃO PARA CÁLCULO
                    double op1 = in.readDouble();
                    char operador = in.readChar();
                    double op2 = in.readDouble();

                    // REALIZANDO A OPERAÇÃO
                    double resultado;
                    switch (operador) {
                        case 'p':
                            resultado = calcularPorcentagem(op1, op2);
                            break;
                        case 'r':
                            resultado = calcularRaizQuadrada(op1);
                            break;
                        case '^':
                            resultado = calcularPotenciacao(op1, op2);
                            break;
                        default:
                            throw new IllegalArgumentException("Operação inválida");
                    }

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

    public static double calcularPorcentagem(double valor, double porcentagem) {
        return valor * (porcentagem / 100);
    }

    public static double calcularRaizQuadrada(double valor) {
        return Math.sqrt(valor);
    }

    public static double calcularPotenciacao(double base, double expoente) {
        return Math.pow(base, expoente);
    }
}

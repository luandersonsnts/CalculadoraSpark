import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        exception(Exception.class, (e, req, res) -> e.printStackTrace()); // print all exceptions
        staticFiles.location("/public");
        port(8081);

        get("/", (req, res) -> renderTodos(req));

        get("/hello", (req, res) -> "Hello World");
        get("/executarOperacao/:op1/:operador/:op2", (req, res) -> { // DEFINE OS PARAMETROS PARA EXECUTAR O CALC NO SERVER
            double op1 = Double.parseDouble(req.params(":op1"));
            char operador = req.params(":operador").charAt(0);
            double op2 = Double.parseDouble(req.params(":op2"));

            double resultado = 0.0;

            try {
                Socket serverSocket = conectarServidor(operador);

                // Cria os streams de entrada e saída para comunicação com o servidor
                ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());

                // Envia a operação e os valores para o servidor
                out.writeDouble(op1);
                out.writeChar(operador);
                out.writeDouble(op2);
                out.flush();

                // Recebe o resultado do servidor
                resultado = in.readDouble();

                // Fecha as conexões com o servidor
                out.close();
                in.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "Resultado: " + resultado;
        });
    }

    private static Socket conectarServidor(char operador) throws IOException {
        int portaServidor = obterPortaServidor(operador);
        return new Socket("localhost", portaServidor);
    }

    private static int obterPortaServidor(char operador) {
        // Mapear os operadores para as portas dos servidores correspondentes
        switch (operador) {
            case '+':
            case '-':
            case '*':
            case 'd':
                return 8082; // Porta do servidor ServerA
            case 'p':
            case 'r':
            case '^':
                return 8083; // Porta do servidor ServerB
            // Adicione casos para novos servidores aqui
            default:
                throw new IllegalArgumentException("Operador inválido");
        }
    }

    private static String renderTodos(Request req) {
        Map<String, Object> model = new HashMap<>();

        Integer a = 10;
        Integer b = 30;
        Integer resultado = a + b;

        model.put("nome", resultado);

        return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/index.vm"));
    }
}

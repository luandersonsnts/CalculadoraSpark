import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

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
        get("/executarOperacao/:op1/:operador/:op2", (req, res) -> {
            double op1 = Double.parseDouble(req.params(":op1"));
            char operador = req.params(":operador").charAt(0);
            double op2 = Double.parseDouble(req.params(":op2"));


            double resultado;
                if (operador == '+' || operador == '-' || operador == '*' || operador == '/') {
                ServerA serverA = new ServerA();
                resultado = serverA.executarOperacao(op1, operador, op2);
                } else {
                ServerB serverB = new ServerB();
                switch (operador) {
                    case 'p':
                        resultado = serverB.calcularPorcentagem(op1, op2);
                        break;
                    case 'r':
                        resultado = serverB.calcularRaizQuadrada(op1);
                        break;
                    case '^':
                        resultado = serverB.calcularPotenciacao(op1, op2);
                        break;
                    default:
                        throw new IllegalArgumentException("Operador inválido");
                }
            }

            return "Resultado: " + resultado;
        });
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

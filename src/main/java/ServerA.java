public class ServerA {
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
}

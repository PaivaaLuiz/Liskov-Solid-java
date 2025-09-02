package aula.solid;

interface Shape {
    int getArea();
}

class Rectangle implements Shape {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getArea() {
        return width * height;
    }
}

class Square implements Shape {
    private final int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public int getArea() {
        return side * side;
    }
}

interface Printer {
    void print(String content);
}

interface Scanner {
    void scan(String document);
}

interface Fax {
    void fax(String number, String content);
}

class BasicPrinter implements Printer {
    @Override
    public void print(String content) {
        System.out.println("[PRINT] " + content);
    }
}

class OfficeAllInOne implements Printer, Scanner, Fax {
    @Override
    public void print(String content) {
        System.out.println("[PRINT] " + content);
    }

    @Override
    public void scan(String document) {
        System.out.println("[SCAN] " + document);
    }

    @Override
    public void fax(String number, String content) {
        System.out.println("[FAX] Enviando para " + number + ": " + content);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Refatoração LSP (Princípio de Substituição de Liskov) ---");
        Shape r1 = new Rectangle(5, 10);
        System.out.println("Área (retângulo): " + r1.getArea()); 

        Shape r2 = new Square(5);
        System.out.println("Área (quadrado como forma): " + r2.getArea());
        System.out.println("-----------------------------------------------------------\n");

        System.out.println("--- Refatoração ISP (Princípio de Segregação de Interface) ---");
        Printer impressoraSimples = new BasicPrinter();
        impressoraSimples.print("Contrato 123"); 

        System.out.println("A impressora básica agora não tem mais métodos de scan/fax, eliminando a UnsupportedOperationException.");

        OfficeAllInOne multifuncional = new OfficeAllInOne();
        multifuncional.print("Relatório anual");
        multifuncional.scan("Foto 3x4");
        multifuncional.fax("555-1234", "Urgente");
        System.out.println("-----------------------------------------------------------\n");
    }
}

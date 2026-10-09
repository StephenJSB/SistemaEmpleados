package sistemaempleados;

public class SistemaEmpleados {

    public static void main(String[] args) {
        System.out.println("=== LOS 4 PILARES DE LA POO EN UN PROGRAMA ===\n");

        // ===== POLIMORFISMO: un arreglo de tipo Empleado =====
        // Puede guardar Vendedor, Gerente o Pasante, porque todos SON empleados
        Empleado[] empleados = new Empleado[3];
        empleados[0] = new Vendedor("Ana", 500, 10);
        empleados[1] = new Gerente("Carlos", 3000, 1500);
        empleados[2] = new Pasante("Lucía", 200);

        // POLIMORFISMO: mismo método, distintos resultados según el objeto
        for (int i = 0; i < empleados.length; i++) {
            Empleado emp = empleados[i];
            System.out.println("Empleado: " + emp.getNombre());
            emp.mostrarRol();
            System.out.println("Salario: $" + emp.calcularSalario());
            System.out.println();
        }

        // ===== ENCAPSULACIÓN en acción =====
        System.out.println("=== PRUEBA DE ENCAPSULACIÓN ===");
        Vendedor v = new Vendedor("Pedro", 500, 5);
        System.out.println("Ventas iniciales: " + v.getVentas());
        System.out.println("Intentando asignar ventas = -50 (inválido)...");
        v.setVentas(-50);  // el setter lo rechaza
        System.out.println("Ventas después del intento: " + v.getVentas());
    }
}

// ============ ABSTRACCIÓN ============
// Clase abstracta: define el "qué" (todo empleado tiene nombre, salario y rol)
// pero no el "cómo" (cada tipo de empleado calcula su salario a su manera)
abstract class Empleado {

    // ============ ENCAPSULACIÓN ============
    // Atributos privados: nadie desde fuera puede tocarlos directamente
    private String nombre;
    private double salarioBase;

    // Constructor con validación (parte de la encapsulación)
    public Empleado(String nombre, double salarioBase) {
        this.nombre = nombre;
        if (salarioBase >= 0) {
            this.salarioBase = salarioBase;
        } else {
            this.salarioBase = 0;
        }
    }

    // Getters y setters: puertas controladas de acceso
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre;
        }
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase >= 0) {
            this.salarioBase = salarioBase;
        }
    }

    // ABSTRACCIÓN: método abstracto, sin cuerpo.
    // Obliga a cada subclase a decir CÓMO calcula su salario.
    public abstract double calcularSalario();

    // Método concreto: común a todos, se puede sobrescribir o no.
    public void mostrarRol() {
        System.out.println("Rol: Empleado general");
    }
}

// ============ HERENCIA ============
// Vendedor hereda de Empleado: obtiene nombre, salarioBase, getters, setters...
class Vendedor extends Empleado {

    private int ventas;

    public Vendedor(String nombre, double salarioBase, int ventas) {
        super(nombre, salarioBase);   // llama al constructor del padre
        this.ventas = ventas;
    }

    public int getVentas() {
        return ventas;
    }

    // Setter con validación (encapsulación)
    public void setVentas(int ventas) {
        if (ventas >= 0) {
            this.ventas = ventas;
        } else {
            System.out.println(">> Ventas no pueden ser negativas. Valor rechazado.");
        }
    }

    // ============ POLIMORFISMO ============
    // Sobrescribimos el método abstracto para darle un comportamiento propio
    @Override
    public double calcularSalario() {
        return getSalarioBase() + (ventas * 10);  // base + comisión
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Vendedor con " + ventas + " ventas");
    }
}

// ============ HERENCIA ============
class Gerente extends Empleado {

    private double bono;

    public Gerente(String nombre, double salarioBase, double bono) {
        super(nombre, salarioBase);
        this.bono = bono;
    }

    public double getBono() {
        return bono;
    }

    public void setBono(double bono) {
        if (bono >= 0) {
            this.bono = bono;
        }
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + bono;
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Gerente con bono de $" + bono);
    }
}

// ============ HERENCIA ============
class Pasante extends Empleado {

    public Pasante(String nombre, double salarioBase) {
        super(nombre, salarioBase);
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase();  // sin extras
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Pasante");
    }
}
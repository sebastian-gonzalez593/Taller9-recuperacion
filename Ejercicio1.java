import java.util.ArrayList;
import java.util.List;

public class SistemaFIAVL2025 {
    public static void main(String[] args) {
        List<Presentacion> presentaciones = new ArrayList<>();

        RecursosTecnicos recursos1 = new RecursosTecnicos(3000, 2000, 5000);
        RecursosTecnicos recursos2 = new RecursosTecnicos(1500, 1200, 2500);

        Presentacion musica1 = new PresentacionMusical("Festival Jazz Nocturno", 100, 500, recursos1);
        Presentacion teatro1 = new PresentacionTeatral("Comedia Urbana Contemporánea", 75, 250, recursos2);

        presentaciones.add(musica1);
        presentaciones.add(teatro1);

        System.out.println("REPORTE FINAL DE PRESENTACIONES");
        System.out.println("==================================");

        for (Presentacion p : presentaciones) {
            System.out.println(p);
        }
    }
}

abstract class Presentacion {
    protected String titulo;
    protected int duracionMinutos;
    protected int aforoPermitido;
    protected RecursosTecnicos recursosTecnicos;

    public Presentacion(String titulo, int duracionMinutos, int aforoPermitido, RecursosTecnicos recursosTecnicos) {
        this.titulo = titulo;
        this.duracionMinutos = duracionMinutos;
        this.aforoPermitido = aforoPermitido;
        this.recursosTecnicos = recursosTecnicos;
    }

    public double calcularCostoBase() {
        double totalRecursos = recursosTecnicos.getTotalRecursos();
        return (totalRecursos == 0) ? 0 : aforoPermitido / totalRecursos;
    }

    public abstract double calcularCostoFinal();

    @Override
    public String toString() {
        return "Título: " + titulo +
               "\nDuración: " + duracionMinutos + " minutos" +
               "\nAforo permitido: " + aforoPermitido +
               "\nCosto base: $" + String.format("%.2f", calcularCostoBase()) +
               "\nCosto final: $" + String.format("%.2f", calcularCostoFinal()) +
               "\n" + recursosTecnicos.toString() +
               "\n-----------------------------------";
    }
}

class PresentacionMusical extends Presentacion {
    public PresentacionMusical(String titulo, int duracion, int aforoPermitido, RecursosTecnicos recursosTecnicos) {
        super(titulo, duracion, aforoPermitido, recursosTecnicos);
    }

    @Override
    public double calcularCostoFinal() {
        double base = calcularCostoBase();
        double indiceAforo = (aforoPermitido >= 500) ? 0.05 : 0.01;
        return base + (duracionMinutos * 0.08) + (aforoPermitido * indiceAforo);
    }
}

class PresentacionTeatral extends Presentacion {
    public PresentacionTeatral(String titulo, int duracion, int aforoPermitido, RecursosTecnicos recursosTecnicos) {
        super(titulo, duracion, aforoPermitido, recursosTecnicos);
    }

    @Override
    public double calcularCostoFinal() {
        double base = calcularCostoBase();
        return (base * 1.1) + (duracionMinutos * 0.05);
    }
}

class RecursosTecnicos {
    private double sonido;
    private double iluminacion;
    private double escenografia;

    public RecursosTecnicos(double sonido, double iluminacion, double escenografia) {
        this.sonido = sonido;
        this.iluminacion = iluminacion;
        this.escenografia = escenografia;
    }

    public double getTotalRecursos() {
        return sonido + iluminacion + escenografia;
    }

    @Override
    public String toString() {
        return "Recursos Técnicos:" +
               "\n  Sonido: $" + String.format("%.2f", sonido) +
               "\n  Iluminación: $" + String.format("%.2f", iluminacion) +
               "\n  Escenografía: $" + String.format("%.2f", escenografia) +
               "\n  Total: $" + String.format("%.2f", getTotalRecursos());
    }
}
/*
Salida esperada del programa:

REPORTE FINAL DE PRESENTACIONES
==================================
Título: Festival Jazz Nocturno
Duración: 100 minutos
Aforo permitido: 500
Costo base: $0.05
Costo final: $33.05
Recursos Técnicos:
  Sonido: $3000.00
  Iluminación: $2000.00
  Escenografía: $5000.00
  Total: $10000.00
-----------------------------------
Título: Comedia Urbana Contemporánea
Duración: 75 minutos
Aforo permitido: 250
Costo base: $0.05
Costo final: $3.80
Recursos Técnicos:
  Sonido: $1500.00
  Iluminación: $1200.00
  Escenografía: $2500.00
  Total: $5200.00
-----------------------------------
*/

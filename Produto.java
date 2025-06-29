/*
 - Classe `Produto` em um sistema de e-commerce onde dois produtos são considerados iguais se têm o mesmo código:
*/

// Principais Considerações

// 1. Passos obrigatórios no equals():
// - Verificar se é o mesmo objeto (this == obj)
// - Verificar se é null (obj == null)
// - Verificar se é da mesma classe (getClass() != obj.getClass())
// - Fazer o cast e comparar os campos relevantes

// 2. Alinhamento com hashCode():
// REGRA FUNDAMENTAL: Se dois objetos são iguais pelo equals(), DEVEM ter o mesmo hashCode().

// Se produto1.equals(produto2) é true, então:
// produto1.hashCode() == produto2.hashCode() (OBRIGATÓRIO)

// 3. Exemplo prático do problema:
// SEM equals() sobrescrito:
// Produto p1 = new Produto("ABC123", "Notebook", 2000.0);
// Produto p2 = new Produto("ABC123", "Notebook", 2000.0);
// System.out.println(p1.equals(p2)); // FALSE (compara referência)

// COM equals() sobrescrito:
// System.out.println(p1.equals(p2)); // TRUE (compara código)

public class Produto {
    private String codigo;
    private String nome;
    private double preco;

    public Produto(String codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Produto outro = (Produto) obj;
        return Objects.equals(codigo, outro.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
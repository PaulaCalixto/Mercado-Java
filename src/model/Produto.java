package model;

import java.util.Locale;
import java.util.Objects;

public class Produto {
    private static int count = 1;

    private int id;
    private String nome;
    private Double preco;

    public Produto(String nome, Double preco) {
        this.id = count++;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "ID: %d\nNome: %s\nPreço: R$ %.2f\n------------------------",
                getId(), getNome(), getPreco());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

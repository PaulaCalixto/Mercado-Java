package main;

import model.Produto;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mercado {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho;

    public static void main(String[] args) {
        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }

    private static void menu() {
        System.out.println("-------------------------------");
        System.out.println("-----------Bem-Vindo-----------");
        System.out.println("-------------------------------");
        System.out.println("*** Selecione uma operação: ***");
        System.out.println("-------------------------------");
        System.out.println("|    Opção 1 - Cadastrar    |");
        System.out.println("|    Opção 2 - Listar       |");
        System.out.println("|    Opção 3 - Comprar      |");
        System.out.println("|    Opção 4 - Carrinho     |");
        System.out.println("|    Opção 5 - Sair         |");

        int option = input.nextInt();

        switch (option) {
            case 1:
                cadastrarProduto();
                break;
            case 2:
                listarProduto();
                break;
            case 3:
                comprarProduto();
                break;
            case 4:
                verCarrinho();
                break;
            case 5:
                System.out.println("Volte sempre!");
                System.exit(0);
            default:
                System.out.println("Opção Inválida!");
        }
    }

    private static void cadastrarProduto() {
        System.out.println("Nome do produto: ");
        String nome = input.next();

        System.out.println("Preço do produto: ");
        Double preco = input.nextDouble();

        Produto produto = new Produto(nome, preco);
        produtos.add(produto);

        System.out.println(produto.getNome() + " cadastrado com sucesso!");
        menu();
    }

    private static void listarProduto() {
        if (produtos.size() > 0) {
            System.out.println("Lista de produtos: \n");

            for (Produto p : produtos) {
                System.out.println(p);
            }
        } else {
            System.out.println("Nenhum produto cadastrado");
        }
        menu();
    }

    private static void comprarProduto() {
        if (produtos.size() > 0) {
            System.out.println("Código do produto: ");
            for (Produto p : produtos) {
                System.out.println(p);
            }

            System.out.println("Digite o ID do produto que deseja comprar: ");
            int id = Integer.parseInt(input.next());
            Produto produtoSelecionado = null;

            for (Produto p : produtos) {
                if (p.getId() == id) {
                    produtoSelecionado = p;
                    break;
                }
            }

            if (produtoSelecionado != null) {
                System.out.println("Quantidade desejada: ");
                int quantidade = Integer.parseInt(input.next());

                int qtdCarrinho = carrinho.getOrDefault(produtoSelecionado, 0);
                carrinho.put(produtoSelecionado, qtdCarrinho + quantidade);

                System.out.println(produtoSelecionado.getNome() + " adicionado no carrinho.");

                System.out.println("Deseja adicionar outro produto no carrinho?");
                System.out.println("Digite 1 para sim, ou 0 para finalizar a compra.");
                int option = Integer.parseInt(input.next());

                if (option == 1) {
                    comprarProduto();
                } else {
                    confirmarCompra();
                }
            } else {
                System.out.println("Produto não encontrado.");
                menu();
            }
        } else {
            System.out.println("Não existem produtos cadastrados!");
            menu();
        }
    }

    private static void confirmarCompra() {
        System.out.println("---Produtos no seu carrinho---");
        for (Produto p : carrinho.keySet()) {
            System.out.println("Produto: " + p.getNome() + "\nQuantidade: " + carrinho.get(p));
        }

        System.out.println("Deseja confirmar a compra?");
        System.out.println("Digite 1 para sim, ou 0 para cancelar.");
        int option = Integer.parseInt(input.next());

        if (option == 1) {
            finalizarCompra();
        } else {
            carrinho.clear();
            System.out.println("Compra cancelada. Obrigado!");
            menu();
        }
    }

    private static void verCarrinho() {
        System.out.println("---Produtos no seu carrinho---");
        if (carrinho.size() > 0) {
            for (Produto p : carrinho.keySet()) {
                System.out.println("Produto: " + p.getNome() + "\nQuantidade: " + carrinho.get(p));
            }
        } else {
            System.out.println("Carrinho vazio");
        }
        menu();
    }

    private static void finalizarCompra() {
        if (carrinho.size() > 0) {
            Double valorCompra = 0.0;
            System.out.println("Seus produtos:");

            for (Produto p : carrinho.keySet()) {
                int qtd = carrinho.get(p);
                valorCompra += p.getPreco() * qtd;
                System.out.println(p);
                System.out.println("Quantidade: " + qtd);
                System.out.println("------------------------");
            }
            System.out.println("O valor da sua compra é: " + Utils.doubleToString(valorCompra));
            carrinho.clear();
            System.out.println("Obrigado pela preferência!");
        } else {
            System.out.println("Seu carrinho está vazio. Nada a finalizar.");
        }
        menu();
    }
}

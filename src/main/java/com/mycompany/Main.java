package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class Main {

    /**
     * Classe que representa uma conta bancária com dados pessoais e operações.
     */
    static class ContaBancaria {
        private String nome;
        private String cpf;
        private double saldo;

        /**
         * Construtor da classe ContaBancaria.
         *
         * @param nome         O nome do titular da conta.
         * @param cpf          O CPF do titular da conta.
         * @param saldoInicial O saldo inicial da conta.
         */
        public ContaBancaria(String nome, String cpf, double saldoInicial) {
            this.nome = nome;
            this.cpf = cpf;
            this.saldo = saldoInicial;
        }

        /**
         * Deposita um valor na conta.
         *
         * @param valor O valor a ser depositado.
         */
        public void depositar(double valor) {
            if (valor > 0) {
                saldo += valor;
                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Valor de depósito inválido!");
            }
        }

        /**
         * Saca um valor da conta.
         *
         * @param valor O valor a ser sacado.
         */
        public void sacar(double valor) {
            if (valor > 0 && valor <= saldo) {
                saldo -= valor;
                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente ou valor inválido!");
            }
        }

        /**
         * Exibe o saldo atual da conta.
         */
        public String exibirSaldo() {
            return "Saldo atual: R$ " + saldo;
        }

        /**
         * Exibe os dados da conta, incluindo nome, CPF e saldo.
         */
        public String exibirDados() {
            return "Nome: " + nome + "\nCPF: " + cpf + "\n" + exibirSaldo();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aplicativo Bancário");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        ContaBancaria conta = new ContaBancaria("Carlos Alberto", "123.456.789-00", 1000.0);

        JTextArea outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        JButton exibirDadosButton = new JButton("Exibir dados da conta");
        JButton exibirSaldoButton = new JButton("Exibir saldo");
        JButton depositarButton = new JButton("Depositar");
        JButton sacarButton = new JButton("Sacar");
        JButton sairButton = new JButton("Sair");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));
        panel.add(exibirDadosButton);
        panel.add(exibirSaldoButton);
        panel.add(depositarButton);
        panel.add(sacarButton);
        panel.add(sairButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        exibirDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText(conta.exibirDados());
            }
        });

        exibirSaldoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText(conta.exibirSaldo());
            }
        });

        depositarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valor = JOptionPane.showInputDialog("Digite o valor para depósito:");
                if (valor != null && !valor.isEmpty()) {
                    try {
                        double valorDeposito = Double.parseDouble(valor);
                        conta.depositar(valorDeposito);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Valor inválido!");
                    }
                }
            }
        });

        sacarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valor = JOptionPane.showInputDialog("Digite o valor para saque:");
                if (valor != null && !valor.isEmpty()) {
                    try {
                        double valorSaque = Double.parseDouble(valor);
                        conta.sacar(valorSaque);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Valor inválido!");
                    }
                }
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalTime agora = LocalTime.now();
                if (agora.isBefore(LocalTime.NOON)) {
                    JOptionPane.showMessageDialog(null, "Obrigado por usar nossos serviços e tenha um bom dia!");
                } else {
                    JOptionPane.showMessageDialog(null, "Obrigado por usar nossos serviços e tenha uma boa noite!");
                }
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }
}

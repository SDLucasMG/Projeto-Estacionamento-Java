package ProjetoEstacionamento;

import java.util.ArrayList;
import java.util.Scanner;

public class Aplicativo {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int loop = 1;
        int idVaga = -1;
        int vagasTotais = 4;
        int id = 100;
        ArrayList<Usuarios> usua = new ArrayList<Usuarios>();
        ArrayList<Veiculos> veic = new ArrayList<Veiculos>();

        while(loop !=0){
            System.out.println();
            System.out.println("Bem vindo ao nosso Sistema de Estacionamento! ");
            System.out.println();
            System.out.println("A quantidade total de vagas é: "+(vagasTotais+1));
            int vagasDisponiveis = vagasTotais-idVaga;
            System.out.println("Atualmente, a quantidade de vagas disponíveis é: "+vagasDisponiveis);
            System.out.println();
            System.out.println("Opções de Uso: ");
            System.out.println();
            System.out.println("1-Cadastrar Vaga.");
            System.out.println("2-Pesquisar Vaga.");
            System.out.println("3-Opções de Administrador.");
            System.out.println("0-Encerrar Sistema.");
            System.out.println();

            System.out.printf("Escolha a opção desejada para continuar: ");
            loop = sc.nextInt();
            System.out.println();

            if(loop == 1){

                if(idVaga<vagasTotais){
                    idVaga++;
                    System.out.println("Por favor, digite mais algumas informações para finalizarmos seu cadastro.");
                    System.out.printf("Seu Nome Completo: ");
                    sc.nextLine();
                    String nome = sc.nextLine().toUpperCase();
                    System.out.printf("Você é:Estudante,Funcionário ou Visitante? ");
                    String funcao = sc.nextLine().toUpperCase();
                    System.out.printf("Informe seu CPF: ");
                    Long CPF = sc.nextLong();

                    usua.add(new Usuarios(nome, funcao, CPF));
                    //us[idVaga] = new Usuario(nome, funcao, CPF);

                    System.out.println();
                    System.out.println("Agora, informe alguns dados de seu Veículo.");
                    System.out.printf("Placa do Veículo: ");
                    sc.nextLine();
                    String placa = sc.nextLine().toUpperCase();
                    System.out.printf("Modelo do Veículo: ");
                    String marca = sc.nextLine().toUpperCase();

                    veic.add(new Veiculos(usua.get(idVaga), placa, marca,id));
                    id++;

                    System.out.println();
                    System.out.println("Pronto, suas informações foram cadastradas, esse é seu ID de uso: "+veic.get(idVaga).getId());

                }else{

                    System.out.println("Sentimos Muito, nesse momento estamos sem vagas disponíveis.");

                }

            }else if(loop == 2){

                if(vagasDisponiveis!=vagasTotais+1){
                    System.out.println("Tudo Bem, por favor insira seu ID de uso: ");
                    System.out.println();
                    System.out.printf("Esse é o ID de sua vaga: ");
                    int pesquisar = sc.nextInt();

                    boolean achou = false;
                    for(int i = 0;i<veic.size();i++){
                        if(veic.get(i).getId()==pesquisar){
                            achou = true;
                            pesquisar = i;
                            break;
                        }
                    }

                    if(achou){

                        System.out.println(veic.get(pesquisar).toString());
                        System.out.println();

                        System.out.printf("Quer liberar sua vaga? (1-Sim)-(2-Não):");
                        int liberarVaga = sc.nextInt();
                        if(liberarVaga == 1){
                            idVaga--;
                            vagasDisponiveis--;
                            veic.remove(pesquisar);
                            System.out.println();
                            System.out.println("Feito, sua vaga foi liberada!");
                        }else if(liberarVaga != 2){
                            System.out.printf("Por favor, digite corretamente a opção!(1-Sim)-(2-Não): ");
                            liberarVaga = sc.nextInt();
                        }else{
                            System.out.println();
                            System.out.println("Feito, voltando para a tela principal.");
                        }

                    }else{

                        System.out.println("Desculpe, não foi possível localizar a vaga com o ID informado.");
                        System.out.println("Retornando a tela principal.");
                    }


                }else{
                    System.out.println("No momento estamos sem vagas para liberar!");
                }

            }else if(loop == 3){
                System.out.printf("Opção de uso exclusivo para administradores!, por favor, informe a palavra passe.(Palavra:'cremosinho'): ");
                sc.nextLine();
                String palavraReservada = sc.nextLine();
                if(palavraReservada.equalsIgnoreCase("cremosinho")){
                    System.out.println();
                    System.out.println("Opções de administrador: ");
                    System.out.println("1-Informar todas as vagas ocupadas.");
                    System.out.println("2-Mudar a número total de vagas disponíveis.");
                    System.out.println("3-Remover uma vaga.");

                    System.out.println();
                    System.out.printf("Informe a opção desejada: ");
                    int opcaoAdministrador = sc.nextInt();

                    if(opcaoAdministrador == 1){
                        if(veic.size() == 0){
                            System.out.println();
                            System.out.println("Não existem vagas para informar!");
                            System.out.println("Retornando a tela principal");
                        }
                        for(int i =0;i<veic.size();i++){
                            System.out.println("Vaga "+i+"= "+veic.get(i).resultadoConsulta());
                        }
                    }else if(opcaoAdministrador == 2){
                        System.out.printf("Informe a nova quantidade vagas totais disponíveis: ");
                        vagasTotais = sc.nextInt()-1;
                        System.out.println("Mudança realizada com sucesso.");
                    }else if(opcaoAdministrador == 3){
                        System.out.printf("Informe o ID da vaga que deseja remover: ");
                        int removerVagaAdm = sc.nextInt();

                        boolean achou = false;
                        for(int i = 0;i<veic.size();i++){
                            if(veic.get(i).getId()==removerVagaAdm){
                                achou = true;
                                removerVagaAdm = i;
                                break;
                            }
                        }

                        if(achou){
                            veic.remove(removerVagaAdm);
                            idVaga--;
                            vagasDisponiveis--;
                            System.out.println();
                            System.out.println("Feito, a vaga foi removida.");
                        }else{
                            System.out.println("Não foi possível localizar nenhuma vaga com o ID informado.");
                            System.out.println("Retornando a tela principal");
                        }

                    }
                }else{
                    System.out.println("Palavra Passe incorreta.");
                    System.out.println("Retornando a tela principal");
                    System.out.println();
                    loop = 1;
                }
            }else if(loop!=0){
                System.out.printf("Opção inserida inválida! ");
                System.out.println("Retornando a tela principal");
                System.out.println();
                loop = 1;
            }
        }

        System.out.println("Agradeçemos por utilizar nosso sistema.");

        sc.close();
    }
}
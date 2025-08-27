package aula.quatro;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinearFeedbackShiftRegister {
    private static void inicializarRegistrador(int[] registrador) throws IOException{
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

        String chave = "";
        System.out.println("Digite uma chave(senha) de 4 letras: ");
        chave = leitor.readLine();

        for(int i = 0;i < 4;i++){
            int letra = chave.charAt(i);
            String binario = Integer.toBinaryString(letra);
            int tamanhoBinario = binario.length();

            for(int j = 0;j < (8 - tamanhoBinario);j++){
                binario = "0" + binario;
            }

            for(int k = 0;k < 8;k++){
                registrador[(i*8) + k] = Integer.parseInt(binario.substring(k,k+1));
            }
        }
    }

    private static int rotacionar(int[] registrador,int tipo){
        int retorno = 0;
        int xor = 0;
        if(tipo == 0){
            xor = registrador[31]^registrador[6]^registrador[4]^registrador[2]^registrador[1]^registrador[0];
        }else if(tipo == 1){
            xor = registrador[31]^registrador[6]^registrador[5]^registrador[1];
        }

        retorno = registrador[0];

        for(int i = 0;i < 31;i++){
            registrador[i] = registrador[i+1];
        }
        registrador[31] = xor;

        return retorno;
    }

    public static void main(String[] args) {
        try {
            int[] cabeca = new int[32];
            int[] gerador0 = new int[32];
            int[] gerador1 = new int[32];
            FileWriter arquivo = new FileWriter("lfsr.txt");

            inicializarRegistrador(cabeca);
            inicializarRegistrador(gerador0);
            inicializarRegistrador(gerador1);
            
            String acumulador ="";


            for(int i = 0;i < 801;i++){
                int bit0 = 0;
                int bit1 = 0;

                if(rotacionar(cabeca,0)==0){
                    bit0 = rotacionar(gerador0,0);
                    bit1 = gerador1[0];
                }else{
                    bit0 = gerador0[0];
                    bit1 = rotacionar(gerador1, 1);
                }
                acumulador += bit0^bit1;
                if((i%8) == 0){
                    arquivo.write((char) (Integer.parseInt(acumulador,2)));
                    acumulador = "";
                }

            }
            arquivo.close();
            System.out.println("AGORA FOI POWHA!!!");
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

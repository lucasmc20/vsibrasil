package br.com.vsibrasil.lucascoelho.service;

import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Classe de serviço com a lógica de negócio para manipulação de anagramas.
 * Contém a validação da entrada e o algoritmo para gerar todas.
 *
 * @author Lucas Coelho
 */
@Service
public class AnagramaService {

    /**
     * Método principal que orquestra a geração de anagramas.
     * Primeiro, ele valida a entrada e depois chama a função para encontrar todas as combinações.
     *
     * @param letras Uma string contendo as letras distintas para gerar os anagramas.
     * @return Um conjunto (Set) com todos os anagramas possíveis.
     */
    public Set<String> gerarAnagramas(String letras) {
        // Passo 1: Validar os dados de entrada
        validarEntrada(letras);

        // Passo 2: Preparar o conjunto que vai guardar os resultados
        Set<String> anagramasEncontrados = new HashSet<>();

        // Passo 3: Chamar a função mágica (recursiva) que faz o trabalho duro
        encontrarCombinacoes("", letras, anagramasEncontrados);

        return anagramasEncontrados;
    }

    /**
     * Valida a string de entrada para garantir que ela atenda aos requisitos.
     *
     * @param letras A string a ser validada.
     */
    private void validarEntrada(String letras) {
        // 1. A entrada não pode ser nula ou vazia.
        if (letras == null || letras.trim().isEmpty()) {
            throw new IllegalArgumentException("A entrada não pode ser vazia ou nula.");
        }

        // 2. A entrada deve conter apenas letras.
        if (!letras.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("A entrada deve conter apenas letras (sem números, espaços ou símbolos).");
        }

        // 3. A entrada deve conter apenas letras distintas.
        // Um jeito fácil de checar é converter a string para um Set de caracteres
        // e comparar o tamanho. Se os tamanhos forem diferentes, há letras repetidas.
        Set<Character> caracteresUnicos = letras.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());

        if (caracteresUnicos.size() < letras.length()) {
            throw new IllegalArgumentException("A entrada deve conter apenas letras distintas.");
        }
    }

    /**
     * Algoritmo recursivo que gera todas as combinações de um conjunto de caracteres.
     * A ideia é fixar um caractere e gerar as combinações do resto da string.
     *
     * @param prefixo  A parte do anagrama que já foi construída.
     * @param sufixo   As letras que ainda precisam ser combinadas.
     * @param resultados O conjunto onde guardamos os anagramas completos.
     */
    private void encontrarCombinacoes(String prefixo, String sufixo, Set<String> resultados) {
        // Caso base da recursão: se não há mais letras no sufixo,
        // significa que formamos um anagrama completo. Adicionamos ao resultado.
        if (sufixo.isEmpty()) {
            resultados.add(prefixo);
            return;
        }

        // Passo recursivo: percorremos cada letra do que ainda resta (sufixo).
        for (int i = 0; i < sufixo.length(); i++) {
            // Pegamos a letra da vez e a movemos para o prefixo.
            String novoPrefixo = prefixo + sufixo.charAt(i);

            // O novo sufixo será o que sobrou das letras.
            String novoSufixo = sufixo.substring(0, i) + sufixo.substring(i + 1);

            // Chamamos a função novamente com os novos valores.
            encontrarCombinacoes(novoPrefixo, novoSufixo, resultados);
        }
    }
}
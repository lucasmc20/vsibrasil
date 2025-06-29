package br.com.vsibrasil.lucascoelho.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de testes para garantir a qualidade e o funcionamento.
 * Cada teste simula um cenário diferente de uso.
 *
 * @author Lucas Coelho
 */
@SpringBootTest // Diz ao Spring para carregar o contexto da aplicação para este teste
public class AnagramaServiceTest {
    @Autowired
    private AnagramaService servicoAnagrama;

    @Test
    @DisplayName("Deve gerar todos os 6 anagramas para a entrada 'abc'")
    void deveGerarAnagramasCorretamenteParaTresLetras() {
        // Cenário
        String entrada = "abc";
        Set<String> anagramasEsperados = Set.of("abc", "acb", "bac", "bca", "cab", "cba");

        // Execução
        Set<String> resultado = servicoAnagrama.gerarAnagramas(entrada);

        // Verificação
        assertNotNull(resultado);
        assertEquals(6, resultado.size());
        assertEquals(anagramasEsperados, resultado);
    }

    @Test
    @DisplayName("Deve retornar um conjunto com a própria letra para uma entrada única")
    void deveRetornarPropriaLetraParaEntradaUnica() {
        // Cenário
        String entrada = "a";

        // Execução
        Set<String> resultado = servicoAnagrama.gerarAnagramas(entrada);

        // Verificação
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains("a"));
    }

    @Test
    @DisplayName("Deve lançar exceção para entrada nula")
    void deveLancarExcecaoParaEntradaNula() {
        // Verificação
        // Aqui, verificamos se uma exceção do tipo IllegalArgumentException é lançada
        // quando tentamos chamar o método com um valor nulo.
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> servicoAnagrama.gerarAnagramas(null)
        );

        assertEquals("A entrada não pode ser vazia ou nula.", excecao.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção para entrada vazia")
    void deveLancarExcecaoParaEntradaVazia() {
        // Verificação (caso de borda)
        assertThrows(IllegalArgumentException.class, () -> {
            servicoAnagrama.gerarAnagramas("");
        });
    }

    @Test
    @DisplayName("Deve lançar exceção para entrada com caracteres não alfabéticos")
    void deveLancarExcecaoParaEntradaComNumerosOuSimbolos() {
        // Verificação (validação)
        assertThrows(IllegalArgumentException.class, () -> servicoAnagrama.gerarAnagramas("a1"));
        assertThrows(IllegalArgumentException.class, () -> servicoAnagrama.gerarAnagramas("ab@"));
    }

    @Test
    @DisplayName("Deve lançar exceção para entrada com letras repetidas")
    void deveLancarExcecaoParaEntradaComLetrasRepetidas() {
        // Verificação (validação)
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> servicoAnagrama.gerarAnagramas("banana")
        );

        assertEquals("A entrada deve conter apenas letras distintas.", excecao.getMessage());
    }
}

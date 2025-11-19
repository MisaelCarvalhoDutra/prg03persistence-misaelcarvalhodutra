package br.com.ifba.infrastructure.util;

/**
 * Classe utilitária para validações relacionadas a Strings.
 */
public class StringUtil {
    
    
     // aqui verificamos se a String é nula ou vazia ("").
     //aqui não usa trim(), então "   " NÃO é considerado vazio.
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
    
    /**
     * Verifica se a String é nula, vazia ou contém apenas espaços em branco.
     * Usa trim(), então "   " É considerado vazio.
     */
    public static boolean isNullOrBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

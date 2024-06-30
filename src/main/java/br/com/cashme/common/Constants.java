package br.com.cashme.common;

import java.time.format.DateTimeFormatter;

public class Constants {
    public static final String CLIENTE_NAO_ENCONTRADO_PARA_REALIZAR_A_OPERACAO = "Cliente não encontrado para realizar a operação.";
    public static final String CLIENTE_EXCLUIDO_COM_SUCESSO = "Cliente %s excluído com sucesso no dia %s.";
    public static final String CLIENTE_CRIADO_COM_SUCESSO = "Cliente %s criado com sucesso  no dia %s.";
    public static final String TODOS_CLIENTES_RETORNADOS_COM_SUCESSO_E_TOTAL = "Todos os clientes retornados com sucesso. Total: %d";
    public static final String CLIENTE_ENCONTRADO_NA_BASE_DE_DADOS = "Cliente %s encontrado na base de dados.";
    public static final String INFORMACOES_DO_CLIENTE_ATUALIZADAS_COM_SUCESSO = "Informações do cliente %s atualizadas com sucesso no dia %s.";

    public static final DateTimeFormatter FORMATTER_DD_MM_YYYY_HH_MM_SS = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static final String ERRO_TODOS_OS_CAMPOS_DEVEM_SER_PREENCHIDOS = "Ocorreu um erro durante a operação. Todos os campos devem ser preenchidos.";
    public static final String OCORREU_ALGUM_ERRO_DURANTE_A_EXECECAO = "Ocorreu algum erro durante a execeção.";
}

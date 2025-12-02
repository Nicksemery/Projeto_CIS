package Cis.api.domain.enums;

public enum SessaoStatus {
    PENDENTE_APROVACAO, // Criada, aguardando coordenação
    APROVADA,           // Aprovada, pronta para ocorrer
    REALIZADA,          // Sessão ocorreu
    CANCELADA
}

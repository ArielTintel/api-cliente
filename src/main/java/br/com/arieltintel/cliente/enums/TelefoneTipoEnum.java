package br.com.arieltintel.cliente.enums;

public enum TelefoneTipoEnum{

    COMERCIAL(1L, "Telefone Comercial"),
    RESIDENCIAL(2L, "Telefone Residencial"),
    PESSOAL_WHATSAPP(3L, "Telefone e Whatsapp"),
    PESSOAL(4L, "Telefone Pessoal"),
    RECADO(5L, "Telefone para Recados");

    private Long id;
    private String descricao;

    TelefoneTipoEnum(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }
}

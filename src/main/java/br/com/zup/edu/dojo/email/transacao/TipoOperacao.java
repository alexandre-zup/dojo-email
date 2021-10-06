package br.com.zup.edu.dojo.email.transacao;

public enum TipoOperacao {
    DEPOSITO {
        @Override
        public String getMensagem(TransacaoRequest request) {
            return "Prezado clinte, sua conta " + request.getNumeroDaConta()
                    + " recebeu um depósito no valor de "
                    + request.getValor() + " às " + request.getEfetuadaEm();
        }
    },
    SAQUE {
        @Override
        public String getMensagem(TransacaoRequest request) {
            return "Prezado clinte, houve um saque em sua conta " + request.getNumeroDaConta()
                    + " no valor de "
                    + request.getValor() + " às " + request.getEfetuadaEm();
        }
    };

    public abstract String getMensagem(TransacaoRequest request);
}

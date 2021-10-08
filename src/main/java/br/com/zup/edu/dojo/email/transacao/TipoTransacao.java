package br.com.zup.edu.dojo.email.transacao;

public enum TipoTransacao {
    DEPOSITO {
        @Override
        public String getMensagem(TransacaoRequest request) {
            return "Prezado cliente, sua conta " + request.getNumeroConta()
                    + " recebeu um depósito no valor de "
                    + request.getValor() + " às " + request.getEfetuadaEm();
        }
    },
    SAQUE {
        @Override
        public String getMensagem(TransacaoRequest request) {
            return "Prezado cliente, houve um saque em sua conta " + request.getNumeroConta()
                    + " no valor de "
                    + request.getValor() + " às " + request.getEfetuadaEm();
        }
    },
    RECARGA_CELULAR {
        @Override
        public String getMensagem(TransacaoRequest request) {
            return "Prezado cliente, houve uma recarga de telefone através de sua conta " + request.getNumeroConta()
                    + " no valor de "
                    + request.getValor() + " às " + request.getEfetuadaEm();
        }
    },
    BOLETO {
        @Override
        public String getMensagem(TransacaoRequest request) {
            return "Prezado cliente, houve um pagemnto de boleto através de sua conta " + request.getNumeroConta()
                    + " no valor de "
                    + request.getValor() + " às " + request.getEfetuadaEm();
        }
    };

    public abstract String getMensagem(TransacaoRequest request);
}

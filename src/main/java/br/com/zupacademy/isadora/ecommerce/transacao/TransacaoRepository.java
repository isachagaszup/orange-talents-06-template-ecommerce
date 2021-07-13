package br.com.zupacademy.isadora.ecommerce.transacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    Optional<Transacao> findFirstByTransacaoGatewayIdAndStatusTransacao(Long transacaoGatewayId, StatusTransacao statusTransacao);
}

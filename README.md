# Adapter Pattern para Desacoplamento de Bibliotecas de Terceiros

## O Problema

Você está usando uma biblioteca de pagamento (ex: PayPal) e quer garantir que, se precisar trocar para outra (ex: Stripe), não vai quebrar todo o seu código.

## A Solução: Adapter Pattern

Criar uma interface própria e um adapter que "traduz" para a biblioteca específica.

---

## Estrutura dos Arquivos

### 1. `PaymentService.java` - Interface Principal
```java
public interface PaymentService {
    boolean processPayment(double amount, String cardNumber);
    String getTransactionId();
}
```

### 2. `PayPalAdapter.java` - Adapter para PayPal
```java
public class PayPalAdapter implements PaymentService {
    private PayPalAPI paypal; // Biblioteca externa do PayPal
    private String lastTransactionId;
    
    public PayPalAdapter(String clientId, String clientSecret) {
        this.paypal = new PayPalAPI(clientId, clientSecret);
    }
    
    @Override
    public boolean processPayment(double amount, String cardNumber) {
        try {
            // Traduz nossa chamada para a API específica do PayPal
            PayPalTransaction transaction = paypal.createPayment()
                .setAmount(amount)
                .setCreditCard(cardNumber)
                .execute();
                
            this.lastTransactionId = transaction.getId();
            return transaction.getStatus().equals("COMPLETED");
            
        } catch (PayPalException e) {
            System.err.println("Erro no PayPal: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public String getTransactionId() {
        return lastTransactionId;
    }
}
```

---

## Como Usar

```java
public class LojaOnline {
    private PaymentService paymentService;
    
    public LojaOnline() {
        // Hoje uso PayPal
        this.paymentService = new PayPalAdapter("client-id", "client-secret");
        
        // Amanhã posso trocar para Stripe sem quebrar nada:
        // this.paymentService = new StripeAdapter("api-key");
    }
    
    public void finalizarCompra(double valor, String cartao) {
        if (paymentService.processPayment(valor, cartao)) {
            System.out.println("Pagamento aprovado! ID: " + 
                paymentService.getTransactionId());
        } else {
            System.out.println("Pagamento rejeitado!");
        }
    }
}
```

---

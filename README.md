# 🏢 Motor de Análise de Crédito - Fintech Solutions

## 🎯 O que o projeto faz?

O sistema simula um motor automatizado de análise e concessão de crédito bancário corporativo. O usuário insere o **nome do proponente**, a **renda mensal comprovada**, o **valor do empréstimo solicitado**, a **quantidade de parcelas** e a **pontuação do Score de Crédito** (0 a 1000). O programa processa esses dados aplicando réguas de risco automatizadas: reprova perfis com alto risco de inadimplência, define taxas de juros dinâmicas com base no score e valida o limite prudencial de **comprometimento de renda** antes de emitir o parecer técnico final.

### 🔄 Fluxo da Aplicação
```text
[ Dados do Proponente ] ➔ [ Validação de Risco (Score) ] ➔ [ Cálculo de Parcelas e Juros ] ➔ [ Análise de Margem Consignável ] ➔ [ Parecer Técnico Gerado ]
```

---

## 🎨 Design do Código (Como as regras conversam)

Este projeto foi estruturado utilizando uma arquitetura lógica baseada em **Estruturas Condicionais Aninhadas (If-Else)** para implementar uma árvore de decisão clara para a esteira de crédito. Veja o fluxo lógico do design:

```text
       ┌────────────────────────┐
       │    Entrada de Dados    │ (Console Input via Scanner)
       └───────────┬────────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │  Filtro de Risco Mínimo│ (Score < 300 ➔ Reprovação Imediata)
       └───────────┬────────────┘
                   │
         ┌─────────┴─────────┐ (Se Score >= 300)
         ▼                   ▼
   [Score >= 700]      [Score < 700]
   Juros: 2.0% a.m.    Juros: 5.0% a.m.
         │                   │
         └─────────┬─────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │   Cálculo Financeiro   │ (Parcelas, Juros e Total Financiado)
       └───────────┬────────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │ Margem Consignável 30% │ (Ultrapassou limite ➔ Reprovado por Renda)
       └────────────────────────┘
```

### 📋 Mapeamento de Variáveis e Estruturas

| Estrutura / Variável | O que ela faz no código? |
| :--- | :--- |
| 🚀 `CreditAnalyzer.java` | Classe principal que centraliza toda a lógica de tomada de decisão e a interface via console do motor de crédito. |
| 👤 `clienteNome` | String que armazena o nome completo do cliente que está solicitando o crédito. |
| 💰 `rendaMensal` | Ponto flutuante (double) usado para validar se a parcela cabe no bolso do cliente (máximo de 30%). |
| 📉 `scoreCredito` | Inteiro (0 a 1000) que atua como o principal critério de risco e precificação da taxa de juros. |
| 📈 `jurosAplicados` | Percentual de juros definido de forma dinâmica (2% para score alto ou 5% para score médio). |
| 📊 `percentualComprometimento` | Armazena o cálculo de impacto na renda do usuário para aplicar a trava de segurança financeira. |

---

## 🧠 Conceitos de Programação Praticados

* **Árvore de Decisão com Condicionais Aninhadas:** Implementação de regras de negócio complexas onde uma validação depende do sucesso da etapa anterior (Score mínimo -> Definição de taxa -> Validação de renda).
* **Fórmula de Juros Simples sobre Parcela:** Aplicação de regras matemáticas financeiras diretamente no código para calcular o custo total do financiamento (`totalPagarComJuros`).
* **Tratamento de Consumo de Buffer:** Uso correto do `scanner.nextLine()` após leituras numéricas para limpar o buffer do teclado e evitar bugs na captura dos dados.

---

## 💻 Exemplo Prático de Uso

Imagine a seguinte simulação de uma análise de crédito aprovada rodando direto no terminal:

```text
=== FINTECH SOLUTIONS - MOTOR DE ANÁLISE DE CRÉDITO ===
Nome completo do proponente: Alex Green
Renda mensal comprovada (R\$): 5000.00
Valor do empréstimo solicitado (R\$): 10000.00
Quantidade de parcelas (meses): 10
Pontuação de Score de Crédito (0 a 1000): 750

========================================
       PARECER TÉCNICO DE ANÁLISE       
========================================
 Solicitante      : Alex Green
 Score Cadastrado : 750 pontos
 Renda Informada  : R\$ 5000.00
----------------------------------------
 STATUS DA ANÁLISE: [APROVADO]
----------------------------------------
 [+] Valor da Parcela   : R\$ 1200.00 /mês
 [+] Taxa de Juros      : 2.0% ao mês
 [+] Total Financiado   : R\$ 12000.00 (em 10x)
 [+] Impacto na Renda   : 24.0% de comprometimento
========================================
    Sistema Fintech Analytica © 2026   
========================================
```
